package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Controla el expediente de apelación y conserva aportes, resolución y consulta final. */
@Component
public class AppealWorkflow {
    private static final int TEXT_LENGTH = 4000;
    private static final int SHORT_TEXT_LENGTH = 500;
    private static final int TYPE_LENGTH = 30;
    private final JdbcTemplate jdbcTemplate;

    public AppealWorkflow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public AppealView fileAppeal(String actor, UUID studentId, AppealCommand command) {
        requireGuardianLink(actor, studentId);
        String points = normalized(command.points());
        String reason = normalized(command.reason());
        String evidence = normalized(command.evidenceReference());
        if (invalid(points, SHORT_TEXT_LENGTH) || invalid(reason, TEXT_LENGTH)
            || invalid(evidence, SHORT_TEXT_LENGTH)) {
            throw badRequest("Indique los puntos, el motivo y una referencia de respaldo.");
        }
        GradeAndAssessment grade = gradeAndAssessment(command.assessmentId(), studentId);
        Settings settings = currentSettings();
        if (settings == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Dirección aún no ha configurado el plazo de apelación.");
        }
        LocalDate deadline = grade.publishedDate().plusDays(settings.appealWindowDays());
        LocalDate resolutionDeadline = LocalDate.now().plusDays(settings.resolutionWindowDays());
        if (LocalDate.now().isAfter(deadline)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "El plazo configurado para esta calificación ya venció.");
        }
        AppealView existing = findOpenAppeal(command.assessmentId(), actor);
        if (existing != null) {
            return existing;
        }
        Long sequence = jdbcTemplate.queryForObject("SELECT nextval('grade_appeal_number_seq')", Long.class);
        UUID id = UUID.randomUUID();
        String number = String.format(Locale.ROOT, "APL-%06d", sequence == null ? 0 : sequence);
        Instant now = Instant.now();
        jdbcTemplate.update("""
            INSERT INTO grade_appeal
              (id, appeal_number, assessment_id, guardian_username, points, reason,
               evidence_reference, status, created_at, deadline_at, resolution_due_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, 'IN_REVIEW', ?, ?, ?)
            """, id, number, command.assessmentId(), actor, points, reason, evidence,
            Timestamp.from(now), deadline, resolutionDeadline);
        notifyAppealParticipants(id, studentId, grade.groupId(), grade.title(), now);
        return findAppeal(id);
    }

    @Transactional(readOnly = true)
    public List<AppealView> appeals(String actor) {
        return jdbcTemplate.query("""
            SELECT a.id, a.appeal_number, a.assessment_id, e.student_id, a.guardian_username,
                   a.points, a.reason, a.evidence_reference, a.status, a.created_at,
                   a.deadline_at, a.resolution_due_at, r.outcome, r.foundation, r.correction_required
            FROM grade_appeal a JOIN assessment e ON e.id = a.assessment_id
            LEFT JOIN appeal_resolution r ON r.appeal_id = a.id
            WHERE EXISTS (SELECT 1 FROM portal_user u WHERE u.username = ?
              AND u.active = TRUE AND u.role_name IN ('DIRECTOR', 'COMMITTEE'))
              OR EXISTS (SELECT 1 FROM teaching_assignment t
                WHERE t.group_id = e.group_id AND t.teacher_username = ? AND t.active = TRUE)
            ORDER BY a.created_at DESC
            """, (result, row) -> appealView(result), actor, actor);
    }

    @Transactional(readOnly = true)
    public List<AppealView> guardianAppeals(String actor) {
        return jdbcTemplate.query("""
            SELECT a.id, a.appeal_number, a.assessment_id, e.student_id, a.guardian_username,
                   a.points, a.reason, a.evidence_reference, a.status, a.created_at,
                   a.deadline_at, a.resolution_due_at, r.outcome, r.foundation,
                   r.correction_required
            FROM grade_appeal a JOIN assessment e ON e.id = a.assessment_id
            LEFT JOIN appeal_resolution r ON r.appeal_id = a.id
            WHERE a.guardian_username = ? ORDER BY a.created_at DESC
            """, (result, row) -> appealView(result), actor);
    }

    @Transactional(readOnly = true)
    public AppealView appeal(String actor, UUID appealId) {
        AppealView appeal = findAppeal(appealId);
        if (appeal == null || !authorizedParticipant(actor, appealId, appeal.guardianUsername())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expediente no disponible.");
        }
        return appeal;
    }

    @Transactional
    public ContributionView contribute(String actor, UUID appealId, ContributionCommand command) {
        AppealView appeal = requireAppeal(appealId);
        String content = normalized(command.content());
        String type = normalized(command.type());
        if (invalid(content, TEXT_LENGTH) || invalid(type, TYPE_LENGTH)) {
            throw badRequest("El aporte debe incluir contenido y tipo.");
        }
        String role = role(actor);
        if (!("DIRECTOR".equals(role) || "COMMITTEE".equals(role)
            || ("TEACHER".equals(role) && isAssignedToAssessment(actor, appeal.assessmentId())))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "El actor no tiene acceso a este expediente.");
        }
        if ("REQUEST_INFORMATION".equals(type)) {
            if (!"COMMITTEE".equals(role) || command.targetUsername() == null
                || !List.of("TEACHER", "DIRECTOR").contains(role(command.targetUsername()))) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Solo el Comité puede solicitar información a un participante.");
            }
        }
        if ("CLOSED".equals(appeal.status()) || "RESOLVED".equals(appeal.status())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "El expediente ya está resuelto y no admite nuevos aportes.");
        }
        if ("PENDING_INFORMATION".equals(appeal.status())
            && !"REQUEST_INFORMATION".equals(type)) {
            Integer pendingRequest = jdbcTemplate.queryForObject("""
                SELECT COUNT(*) FROM appeal_contribution
                WHERE appeal_id = ? AND contribution_type = 'REQUEST_INFORMATION'
                  AND target_username = ?
                """, Integer.class, appealId, actor);
            if (pendingRequest == null || pendingRequest == 0) {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "El expediente espera información de otro participante.");
            }
        }
        UUID contributionId = UUID.randomUUID();
        Instant now = Instant.now();
        jdbcTemplate.update("""
            INSERT INTO appeal_contribution
              (id, appeal_id, actor_username, target_username, contribution_type, content, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """, contributionId, appealId, actor, command.targetUsername(), type,
            content, Timestamp.from(now));
        String nextStatus = "REQUEST_INFORMATION".equals(type) ? "PENDING_INFORMATION" : "IN_REVIEW";
        jdbcTemplate.update("UPDATE grade_appeal SET status = ? WHERE id = ?", nextStatus, appealId);
        if (command.targetUsername() != null) {
            notifyUser(command.targetUsername(), appeal.studentId(), "APPEAL_INFORMATION_REQUEST",
                contributionId, "Solicitud de información para " + appeal.appealNumber(), now);
        }
        return new ContributionView(contributionId, appealId, actor, command.targetUsername(),
            type, content, now);
    }

    @Transactional
    public ResolutionView resolve(String actor, UUID appealId, ResolutionCommand command) {
        AppealView appeal = requireAppeal(appealId);
        if (!"COMMITTEE".equals(role(actor))) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "Solo el Comité de Evaluación puede registrar la resolución.");
        }
        if (!"IN_REVIEW".equals(appeal.status())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "El expediente debe estar en revisión y sin solicitudes pendientes.");
        }
        String outcome = normalized(command.outcome());
        String foundation = normalized(command.foundation());
        String members = normalized(command.committeeMembers());
        if (invalid(outcome, SHORT_TEXT_LENGTH) || invalid(foundation, TEXT_LENGTH)
            || invalid(members, SHORT_TEXT_LENGTH)) {
            throw badRequest("Indique resultado, fundamento e integrantes que resuelven.");
        }
        Instant now = Instant.now();
        UUID id = UUID.randomUUID();
        jdbcTemplate.update("""
            INSERT INTO appeal_resolution
              (id, appeal_id, outcome, foundation, committee_members, correction_required,
               resolved_by, resolved_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """, id, appealId, outcome, foundation, members, command.correctionRequired(),
            actor, Timestamp.from(now));
        jdbcTemplate.update("UPDATE grade_appeal SET status = 'RESOLVED' WHERE id = ?", appealId);
        notifyUser(appeal.guardianUsername(), appeal.studentId(), "APPEAL_RESOLUTION",
            appealId, "Resolución disponible: " + appeal.appealNumber(), now);
        if (command.correctionRequired()) {
            String teacher = assignedTeacher(appeal.assessmentId());
            if (teacher != null) {
                notifyUser(teacher, appeal.studentId(), "APPEAL_CORRECTION_REQUIRED", appealId,
                    "El Comité solicita corregir la calificación " + appeal.appealNumber()
                        + " mediante CU-09.", now);
            }
        }
        return new ResolutionView(id, appealId, outcome, foundation, members,
            command.correctionRequired(), actor, now, null, null);
    }

    @Transactional
    public ResolutionView readResolution(String actor, UUID appealId) {
        AppealView appeal = requireAppeal(appealId);
        if (!appeal.guardianUsername().equals(actor)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "La resolución no está disponible para esta persona encargada.");
        }
        requireGuardianLink(actor, appeal.studentId());
        List<ResolutionView> rows = jdbcTemplate.query("""
            SELECT id, appeal_id, outcome, foundation, committee_members, correction_required,
                   resolved_by, resolved_at, guardian_read_at, closed_at
            FROM appeal_resolution WHERE appeal_id = ?
            """, (result, row) -> resolutionView(result), appealId);
        ResolutionView resolution = rows.stream().findFirst().orElse(null);
        if (resolution == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La resolución aún no está disponible.");
        }
        Instant now = Instant.now();
        jdbcTemplate.update("""
            UPDATE appeal_resolution SET guardian_read_at = COALESCE(guardian_read_at, ?),
              closed_at = COALESCE(closed_at, ?) WHERE appeal_id = ?
            """, Timestamp.from(now), Timestamp.from(now), appealId);
        jdbcTemplate.update("UPDATE grade_appeal SET status = 'CLOSED' WHERE id = ?", appealId);
        jdbcTemplate.update("""
            UPDATE portal_notification SET read_at = COALESCE(read_at, ?)
            WHERE recipient_username = ? AND notification_type = 'APPEAL_RESOLUTION'
              AND reference_id = ?
            """, Timestamp.from(now), actor, appealId);
        return resolution(appealId);
    }

    private void notifyAppealParticipants(UUID appealId, UUID studentId, UUID groupId,
        String title, Instant now) {
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            SELECT gen_random_uuid(), username, ?, 'APPEAL_ASSIGNED', ?, ?, ?
            FROM portal_user WHERE username = 'comite.demo' AND active = TRUE
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, studentId, appealId, "Nueva apelación asignada: " + title, Timestamp.from(now));
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            SELECT gen_random_uuid(), a.teacher_username, ?, 'APPEAL_FILED', ?, ?, ?
            FROM teaching_assignment a WHERE a.group_id = ? AND a.active = TRUE
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, studentId, appealId, "Apelación recibida: " + title, Timestamp.from(now), groupId);
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            VALUES (gen_random_uuid(), 'direccion.demo', ?, 'APPEAL_FILED', ?, ?, ?)
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, studentId, appealId, "Apelación recibida: " + title, Timestamp.from(now));
    }

    private void notifyUser(String username, UUID studentId, String type, UUID referenceId,
        String subject, Instant now) {
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            VALUES (gen_random_uuid(), ?, ?, ?, ?, ?, ?)
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, username, studentId, type, referenceId, subject, Timestamp.from(now));
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
                "No se puede presentar la apelación para este estudiante.");
        }
    }

    private GradeAndAssessment gradeAndAssessment(UUID assessmentId, UUID studentId) {
        List<GradeAndAssessment> rows = jdbcTemplate.query("""
            SELECT a.id, a.group_id, a.title, a.published, a.cancelled,
                   g.published_date, g.numeric_value
            FROM assessment a LEFT JOIN grade_record g ON g.assessment_id = a.id
            WHERE a.id = ? AND a.student_id = ?
            """, (result, row) -> new GradeAndAssessment(result.getObject("id", UUID.class),
                result.getObject("group_id", UUID.class), result.getString("title"),
                result.getBoolean("published"), result.getBoolean("cancelled"),
                result.getObject("published_date", LocalDate.class),
                result.getBigDecimal("numeric_value")), assessmentId, studentId);
        GradeAndAssessment grade = rows.stream().findFirst().orElse(null);
        if (grade == null || !grade.published() || grade.cancelled()
            || grade.publishedDate() == null || grade.groupId() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La evaluación no tiene una calificación publicada que pueda apelarse.");
        }
        return grade;
    }

    private Settings currentSettings() {
        List<Settings> rows = jdbcTemplate.query("""
            SELECT appeal_window_days, resolution_window_days FROM appeal_deadline_config
            ORDER BY changed_at DESC, id DESC LIMIT 1
            """, (result, row) -> new Settings(result.getInt("appeal_window_days"),
                result.getInt("resolution_window_days")));
        return rows.stream().findFirst().orElse(null);
    }

    private AppealView findOpenAppeal(UUID assessmentId, String guardian) {
        List<AppealView> rows = jdbcTemplate.query("""
            SELECT a.id, a.appeal_number, a.assessment_id, e.student_id, a.guardian_username,
                   a.points, a.reason, a.evidence_reference, a.status, a.created_at,
                   a.deadline_at, a.resolution_due_at, r.outcome, r.foundation, r.correction_required
            FROM grade_appeal a JOIN assessment e ON e.id = a.assessment_id
            LEFT JOIN appeal_resolution r ON r.appeal_id = a.id
            WHERE a.assessment_id = ? AND a.guardian_username = ?
              AND a.status IN ('OPEN', 'IN_REVIEW', 'PENDING_INFORMATION')
            """, (result, row) -> appealView(result), assessmentId, guardian);
        return rows.stream().findFirst().orElse(null);
    }

    private AppealView requireAppeal(UUID id) {
        AppealView appeal = findAppeal(id);
        if (appeal == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Expediente no disponible.");
        }
        return appeal;
    }

    private AppealView findAppeal(UUID id) {
        List<AppealView> rows = jdbcTemplate.query("""
            SELECT a.id, a.appeal_number, a.assessment_id, e.student_id, a.guardian_username,
                   a.points, a.reason, a.evidence_reference, a.status, a.created_at,
                   a.deadline_at, a.resolution_due_at, r.outcome, r.foundation, r.correction_required
            FROM grade_appeal a JOIN assessment e ON e.id = a.assessment_id
            LEFT JOIN appeal_resolution r ON r.appeal_id = a.id
            WHERE a.id = ?
            """, (result, row) -> appealView(result), id);
        return rows.stream().findFirst().orElse(null);
    }

    private AppealView appealView(java.sql.ResultSet result) throws java.sql.SQLException {
        return new AppealView(result.getObject("id", UUID.class), result.getString("appeal_number"),
            result.getObject("assessment_id", UUID.class), result.getString("guardian_username"),
            result.getObject("student_id", UUID.class),
            result.getString("points"), result.getString("reason"),
            result.getString("evidence_reference"), result.getString("status"),
            result.getTimestamp("created_at").toInstant(),
            result.getObject("deadline_at", LocalDate.class),
            result.getObject("resolution_due_at", LocalDate.class), result.getString("outcome"),
            result.getString("foundation"), (Boolean) result.getObject("correction_required"));
    }

    private ResolutionView resolution(UUID appealId) {
        return jdbcTemplate.queryForObject("""
            SELECT id, appeal_id, outcome, foundation, committee_members, correction_required,
                   resolved_by, resolved_at, guardian_read_at, closed_at
            FROM appeal_resolution WHERE appeal_id = ?
            """, (result, row) -> resolutionView(result), appealId);
    }

    private ResolutionView resolutionView(java.sql.ResultSet result) throws java.sql.SQLException {
        return new ResolutionView(result.getObject("id", UUID.class),
            result.getObject("appeal_id", UUID.class), result.getString("outcome"),
            result.getString("foundation"), result.getString("committee_members"),
            result.getBoolean("correction_required"), result.getString("resolved_by"),
            result.getTimestamp("resolved_at").toInstant(),
            instant(result.getTimestamp("guardian_read_at")), instant(result.getTimestamp("closed_at")));
    }

    private boolean authorizedParticipant(String actor, UUID appealId, String guardian) {
        String role = role(actor);
        if (actor.equals(guardian) || "DIRECTOR".equals(role) || "COMMITTEE".equals(role)) {
            return true;
        }
        if (!"TEACHER".equals(role)) {
            return false;
        }
        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM grade_appeal p JOIN assessment e ON e.id = p.assessment_id
            JOIN teaching_assignment a ON a.group_id = e.group_id AND a.teacher_username = ?
              AND a.active = TRUE WHERE p.id = ?
            """, Integer.class, actor, appealId);
        return count != null && count > 0;
    }

    private String role(String username) {
        List<String> roles = jdbcTemplate.query("""
            SELECT role_name FROM portal_user WHERE username = ? AND active = TRUE
            """, (result, row) -> result.getString("role_name"), username);
        return roles.stream().findFirst().orElse("");
    }

    private boolean isAssignedToAssessment(String actor, UUID appealOrAssessmentId) {
        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM assessment e JOIN teaching_assignment a
              ON a.group_id = e.group_id AND a.teacher_username = ? AND a.active = TRUE
            WHERE e.id = ?
            """, Integer.class, actor, appealOrAssessmentId);
        return count != null && count > 0;
    }

    private String assignedTeacher(UUID assessmentId) {
        List<String> teachers = jdbcTemplate.query("""
            SELECT a.teacher_username FROM assessment e
            JOIN teaching_assignment a ON a.group_id = e.group_id AND a.active = TRUE
            WHERE e.id = ? ORDER BY a.assigned_at LIMIT 1
            """, (result, row) -> result.getString("teacher_username"), assessmentId);
        return teachers.stream().findFirst().orElse(null);
    }

    private boolean invalid(String value, int maximum) {
        return value == null || value.isBlank() || value.length() > maximum;
    }

    private String normalized(String value) {
        return value == null ? null : value.trim();
    }

    private org.springframework.web.server.ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private Instant instant(Timestamp value) {
        return value == null ? null : value.toInstant();
    }

    public record AppealCommand(UUID assessmentId, String points, String reason,
        String evidenceReference) { }
    public record ContributionCommand(String type, String content, String targetUsername) { }
    public record ResolutionCommand(String outcome, String foundation, String committeeMembers,
        boolean correctionRequired) { }
    public record AppealView(UUID id, String appealNumber, UUID assessmentId, String guardianUsername,
        UUID studentId, String points, String reason, String evidenceReference, String status,
        Instant createdAt, LocalDate deadlineAt, LocalDate resolutionDueAt, String outcome,
        String foundation, Boolean correctionRequired) { }
    public record ContributionView(UUID id, UUID appealId, String actorUsername,
        String targetUsername, String type, String content, Instant createdAt) { }
    public record ResolutionView(UUID id, UUID appealId, String outcome, String foundation,
        String committeeMembers, boolean correctionRequired, String resolvedBy, Instant resolvedAt,
        Instant guardianReadAt, Instant closedAt) { }
    private record Settings(int appealWindowDays, int resolutionWindowDays) { }
    private record GradeAndAssessment(UUID id, UUID groupId, String title, boolean published,
        boolean cancelled, LocalDate publishedDate, java.math.BigDecimal numericValue) { }
}
