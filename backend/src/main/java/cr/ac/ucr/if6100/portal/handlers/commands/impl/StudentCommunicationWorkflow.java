package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Mantiene conversaciones por estudiante y tramita quejas de información. */
@Component
public class StudentCommunicationWorkflow {
    private static final int SUBJECT_LENGTH = 180;
    private static final int CONTENT_LENGTH = 4000;
    private static final int INFORMATION_TYPE_LENGTH = 60;
    private static final int SUBJECT_FIELD_LENGTH = 100;
    private static final int PERIOD_LENGTH = 40;
    private final JdbcTemplate jdbcTemplate;

    public StudentCommunicationWorkflow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<MessageView> conversation(String actor, UUID studentId) {
        requireConversationAccess(actor, studentId);
        return jdbcTemplate.query("""
            SELECT id, student_id, sender_username, recipient_username, subject, content,
                   reply_to_id, created_at, read_at
            FROM student_message WHERE student_id = ? ORDER BY created_at, id
            """, (result, row) -> new MessageView(result.getObject("id", UUID.class),
                result.getObject("student_id", UUID.class), result.getString("sender_username"),
                result.getString("recipient_username"), result.getString("subject"),
                result.getString("content"), result.getObject("reply_to_id", UUID.class),
                instant(result.getTimestamp("created_at")), instant(result.getTimestamp("read_at"))),
            studentId);
    }

    @Transactional
    public MessageView sendMessage(String actor, UUID studentId, MessageCommand command) {
        String subject = normalized(command.subject());
        String content = normalized(command.content());
        if (invalid(subject, SUBJECT_LENGTH) || invalid(content, CONTENT_LENGTH)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "El asunto y el contenido del mensaje son obligatorios.");
        }
        if ("GRADE_DISAGREEMENT".equals(command.topic())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Una inconformidad con una nota se presenta por CU-07 (apelación).");
        }
        if ("INFORMATION_NOT_RECEIVED".equals(command.topic())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La falta de información se tramita por CU-12 (queja ante Dirección).");
        }
        requireConversationAccess(actor, studentId);
        String recipient = command.recipientUsername();
        if (recipient == null || !authorizedCounterparty(actor, recipient, studentId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "El destinatario ya no está autorizado para este estudiante.");
        }
        UUID replyTo = command.replyToMessageId();
        if (replyTo != null) {
            MessageView original = findMessage(replyTo);
            if (original == null || !original.studentId().equals(studentId)
                || !original.recipientUsername().equals(actor)
                || !original.senderUsername().equals(recipient)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "No se puede responder ese mensaje.");
            }
        }
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        jdbcTemplate.update("""
            INSERT INTO student_message
              (id, student_id, sender_username, recipient_username, subject, content,
               reply_to_id, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """, id, studentId, actor, recipient, subject, content, replyTo, Timestamp.from(now));
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            VALUES (?, ?, ?, 'MESSAGE', ?, ?, ?)
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, UUID.randomUUID(), recipient, studentId, id,
            "Mensaje sobre estudiante: " + subject, Timestamp.from(now));
        return findMessage(id);
    }

    @Transactional
    public MessageView markMessageRead(String actor, UUID messageId) {
        MessageView message = findMessage(messageId);
        if (message == null || !message.recipientUsername().equals(actor)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mensaje no disponible.");
        }
        requireConversationAccess(actor, message.studentId());
        Instant now = Instant.now();
        jdbcTemplate.update("""
            UPDATE student_message SET read_at = COALESCE(read_at, ?) WHERE id = ?
            """, Timestamp.from(now), messageId);
        jdbcTemplate.update("""
            UPDATE portal_notification SET read_at = COALESCE(read_at, ?)
            WHERE recipient_username = ? AND notification_type = 'MESSAGE' AND reference_id = ?
            """, Timestamp.from(now), actor, messageId);
        return findMessage(messageId);
    }

    @Transactional
    public ComplaintView fileComplaint(String actor, UUID studentId, ComplaintCommand command) {
        requireGuardianLink(actor, studentId);
        String type = normalized(command.informationType());
        String subject = normalized(command.subject());
        String period = normalized(command.period());
        String content = normalized(command.content());
        if (invalid(type, INFORMATION_TYPE_LENGTH) || invalid(content, CONTENT_LENGTH)
            || invalid(subject, SUBJECT_FIELD_LENGTH) || invalid(period, PERIOD_LENGTH)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Indique tipo, materia, periodo y descripción de la queja.");
        }
        if ("GRADE_DISAGREEMENT".equals(command.reasonType())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La inconformidad con una calificación debe tramitarse como apelación CU-07.");
        }
        UUID assessmentId = command.assessmentId();
        if (assessmentId != null) {
            Integer matching = jdbcTemplate.queryForObject("""
                SELECT COUNT(*) FROM assessment WHERE id = ? AND student_id = ? AND published = TRUE
                """, Integer.class, assessmentId, studentId);
            if (matching != null && matching > 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "La publicación ya existe; ábrala y el Portal registrará la consulta.");
            }
        } else if (publishedInformationExists(type, subject, period, studentId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La información ya fue publicada; ábrala y el Portal registrará la consulta.");
        }
        UUID id = UUID.randomUUID();
        Instant now = Instant.now();
        jdbcTemplate.update("""
            INSERT INTO information_complaint
              (id, student_id, guardian_username, assessment_id, information_type, subject,
               period, content, status, created_at, assigned_to)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, 'OPEN', ?, 'direccion.demo')
            """, id, studentId, actor, assessmentId, type, subject, period, content,
            Timestamp.from(now));
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            VALUES (?, 'direccion.demo', ?, 'COMPLAINT', ?, ?, ?)
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, UUID.randomUUID(), studentId, id, "Queja nueva: " + type, Timestamp.from(now));
        return findComplaint(id);
    }

    @Transactional(readOnly = true)
    public List<ComplaintView> complaints() {
        return jdbcTemplate.query("""
            SELECT c.id, c.complaint_number, c.student_id, s.full_name, c.guardian_username,
                   c.information_type, c.subject, c.period, c.content, c.status, c.finding, c.response,
                   c.related_publication_id, c.created_at, c.updated_by, c.updated_at
            FROM information_complaint c JOIN student s ON s.id = c.student_id
            ORDER BY c.created_at
            """, (result, row) -> complaintView(result));
    }

    @Transactional
    public ComplaintView attendComplaint(String actor, UUID complaintId,
        ComplaintResolution resolution) {
        ComplaintView complaint = findComplaint(complaintId);
        if (complaint == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Queja no disponible.");
        }
        String finding = normalized(resolution.finding());
        String response = normalized(resolution.response());
        if (!List.of("NOT_PUBLISHED", "PUBLISHED_NOT_READ", "READ").contains(finding)
            || invalid(response, CONTENT_LENGTH)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Seleccione un hallazgo válido y redacte la respuesta.");
        }
        String status = "NOT_PUBLISHED".equals(finding) && resolution.teacherUsername() != null
            ? "IN_PROGRESS" : "ATTENDED";
        if ("NOT_PUBLISHED".equals(finding) && resolution.teacherUsername() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Seleccione el docente responsable para solicitar la publicación.");
        }
        Instant now = Instant.now();
        jdbcTemplate.update("""
            UPDATE information_complaint
            SET finding = ?, response = ?, status = ?, updated_by = ?, updated_at = ?,
                requested_teacher = ?
            WHERE id = ?
            """, finding, response, status, actor, Timestamp.from(now),
            "IN_PROGRESS".equals(status) ? resolution.teacherUsername() : null, complaintId);
        if ("IN_PROGRESS".equals(status)) {
            Integer teacherCount = jdbcTemplate.queryForObject("""
                SELECT COUNT(*) FROM portal_user WHERE username = ? AND role_name = 'TEACHER'
                  AND active = TRUE
                """, Integer.class, resolution.teacherUsername());
            if (teacherCount == null || teacherCount == 0) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Docente responsable no disponible.");
            }
            jdbcTemplate.update("""
                INSERT INTO portal_notification
                  (id, recipient_username, student_id, notification_type, reference_id,
                   subject, created_at)
                VALUES (?, ?, ?, 'PUBLISH_INFORMATION_REQUEST', ?, ?, ?)
                ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
                """, UUID.randomUUID(), resolution.teacherUsername(), complaint.studentId(),
                complaintId, "Dirección solicita publicar información: " + complaint.informationType(),
                Timestamp.from(now));
        } else {
            notifyGuardian(complaint.guardianUsername(), complaint.studentId(), complaintId,
                "Respuesta de Dirección a la queja " + complaint.complaintNumber(), now);
        }
        return findComplaint(complaintId);
    }

    private boolean authorizedCounterparty(String actor, String recipient, UUID studentId) {
        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM portal_user sender, portal_user target
            WHERE sender.username = ? AND sender.active = TRUE
              AND target.username = ? AND target.active = TRUE
              AND ((sender.role_name = 'GUARDIAN' AND target.role_name = 'TEACHER'
                    AND EXISTS (SELECT 1 FROM guardian_student_link l
                      WHERE l.guardian_username = sender.username AND l.student_id = ?
                        AND l.status = 'ACTIVE')
                    AND EXISTS (SELECT 1 FROM student_enrollment e
                      JOIN teaching_assignment a ON a.group_id = e.group_id AND a.active = TRUE
                      WHERE e.student_id = ? AND e.active = TRUE AND a.teacher_username = target.username))
                OR (sender.role_name = 'TEACHER' AND target.role_name = 'GUARDIAN'
                    AND EXISTS (SELECT 1 FROM guardian_student_link l
                      WHERE l.guardian_username = target.username AND l.student_id = ?
                        AND l.status = 'ACTIVE')
                    AND EXISTS (SELECT 1 FROM student_enrollment e
                      JOIN teaching_assignment a ON a.group_id = e.group_id AND a.active = TRUE
                      WHERE e.student_id = ? AND e.active = TRUE AND a.teacher_username = sender.username)))
            """, Integer.class, actor, recipient, studentId, studentId, studentId, studentId);
        return count != null && count > 0;
    }

    private void requireConversationAccess(String actor, UUID studentId) {
        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM portal_user u
            WHERE u.username = ? AND u.active = TRUE AND (
              (u.role_name = 'GUARDIAN' AND EXISTS (SELECT 1 FROM guardian_student_link l
                WHERE l.guardian_username = u.username AND l.student_id = ? AND l.status = 'ACTIVE'))
              OR (u.role_name = 'TEACHER' AND EXISTS (SELECT 1 FROM student_enrollment e
                JOIN teaching_assignment a ON a.group_id = e.group_id AND a.active = TRUE
                WHERE e.student_id = ? AND e.active = TRUE AND a.teacher_username = u.username)))
            """, Integer.class, actor, studentId, studentId);
        if (count == null || count == 0) {
            jdbcTemplate.update("""
                INSERT INTO security_access_log(id, username, requested_path, occurred_at)
                VALUES (?, ?, ?, CURRENT_TIMESTAMP)
                """, UUID.randomUUID(), actor, "/api/v1/conversations/" + studentId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Conversación no disponible para este usuario.");
        }
    }

    private void requireGuardianLink(String actor, UUID studentId) {
        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM portal_user u JOIN guardian_student_link l
              ON l.guardian_username = u.username
            WHERE u.username = ? AND u.role_name = 'GUARDIAN' AND u.active = TRUE
              AND l.student_id = ? AND l.status = 'ACTIVE'
            """, Integer.class, actor, studentId);
        if (count == null || count == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No se puede presentar la queja para este estudiante.");
        }
    }

    private MessageView findMessage(UUID id) {
        List<MessageView> rows = jdbcTemplate.query("""
            SELECT id, student_id, sender_username, recipient_username, subject, content,
                   reply_to_id, created_at, read_at FROM student_message WHERE id = ?
            """, (result, row) -> new MessageView(result.getObject("id", UUID.class),
                result.getObject("student_id", UUID.class), result.getString("sender_username"),
                result.getString("recipient_username"), result.getString("subject"),
                result.getString("content"), result.getObject("reply_to_id", UUID.class),
                instant(result.getTimestamp("created_at")), instant(result.getTimestamp("read_at"))), id);
        return rows.stream().findFirst().orElse(null);
    }

    private ComplaintView findComplaint(UUID id) {
        List<ComplaintView> rows = jdbcTemplate.query("""
            SELECT c.id, c.complaint_number, c.student_id, s.full_name, c.guardian_username,
                   c.information_type, c.subject, c.period, c.content, c.status, c.finding, c.response,
                   c.related_publication_id, c.created_at, c.updated_by, c.updated_at
            FROM information_complaint c JOIN student s ON s.id = c.student_id WHERE c.id = ?
            """, (result, row) -> complaintView(result), id);
        return rows.stream().findFirst().orElse(null);
    }

    private ComplaintView complaintView(java.sql.ResultSet result) throws java.sql.SQLException {
        return new ComplaintView(result.getObject("id", UUID.class), result.getLong("complaint_number"),
            result.getObject("student_id", UUID.class), result.getString("full_name"),
            result.getString("guardian_username"), result.getString("information_type"),
            result.getString("subject"), result.getString("period"), result.getString("content"),
            result.getString("status"), result.getString("finding"),
            result.getString("response"), result.getObject("related_publication_id", UUID.class),
            instant(result.getTimestamp("created_at")), result.getString("updated_by"),
            instant(result.getTimestamp("updated_at")));
    }

    private void notifyGuardian(String guardian, UUID student, UUID referenceId,
        String subject, Instant now) {
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            VALUES (?, ?, ?, 'COMPLAINT_RESPONSE', ?, ?, ?)
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, UUID.randomUUID(), guardian, student, referenceId, subject, Timestamp.from(now));
    }

    private boolean publishedInformationExists(String type, String subject, String period,
        UUID studentId) {
        String query = switch (type) {
            case "EVALUATION" -> """
                SELECT COUNT(*) FROM assessment
                WHERE student_id = ? AND subject = ? AND period = ? AND published = TRUE
                """;
            case "GRADE" -> """
                SELECT COUNT(*) FROM assessment a JOIN grade_record g ON g.assessment_id = a.id
                WHERE a.student_id = ? AND a.subject = ? AND a.period = ? AND a.published = TRUE
                """;
            default -> "SELECT 0";
        };
        if ("SELECT 0".equals(query)) {
            return false;
        }
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, studentId, subject, period);
        return count != null && count > 0;
    }

    private boolean invalid(String value, int maxLength) {
        return value == null || value.isBlank() || value.length() > maxLength;
    }

    private String normalized(String value) {
        return value == null ? null : value.trim();
    }

    private Instant instant(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toInstant();
    }

    public record MessageCommand(String recipientUsername, String subject, String content,
        String topic, UUID replyToMessageId) { }
    public record ComplaintCommand(String informationType, String subject, String period,
        String content, UUID assessmentId, String reasonType) { }
    public record ComplaintResolution(String finding, String response, String teacherUsername) { }
    public record MessageView(UUID id, UUID studentId, String senderUsername,
        String recipientUsername, String subject, String content, UUID replyToMessageId,
        Instant createdAt, Instant readAt) { }
    public record ComplaintView(UUID id, long complaintNumber, UUID studentId, String studentName,
        String guardianUsername, String informationType, String subject, String period,
        String content, String status, String finding, String response,
        UUID relatedPublicationId, Instant createdAt,
        String updatedBy, Instant updatedAt) { }
}
