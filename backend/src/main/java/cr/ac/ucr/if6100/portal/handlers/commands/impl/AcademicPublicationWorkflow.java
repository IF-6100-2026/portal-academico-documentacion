package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Persiste publicaciones, calificaciones, recordatorios y datos de supervisión. */
@Component
public class AcademicPublicationWorkflow {
    private static final int TITLE_LENGTH = 180;
    private static final int TEXT_LENGTH = 4000;
    private static final int REASON_LENGTH = 500;
    private static final BigDecimal MAX_GRADE = BigDecimal.valueOf(100);
    private final JdbcTemplate jdbcTemplate;

    public AcademicPublicationWorkflow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public PublicationView publishAssessment(String actor, UUID groupId, AssessmentCommand command) {
        String title = normalized(command.title());
        String instructions = normalized(command.instructions());
        if (invalid(title, TITLE_LENGTH) || invalid(instructions, TEXT_LENGTH)
            || command.assignedDate() == null || command.dueDate() == null) {
            throw badRequest("Complete título, fechas e instrucciones.");
        }
        GroupInfo group = group(groupId);
        requireTeachingAssignment(actor, groupId);
        GroupPublication existing = findGroupPublication(groupId, title, command.assignedDate());
        if (existing != null && "PUBLISHED".equals(existing.status())) {
            return publicationView(existing.id(), true, null);
        }
        boolean datesValid = !command.dueDate().isBefore(command.assignedDate());
        Instant now = Instant.now();
        UUID publicationId;
        if (existing == null) {
            publicationId = UUID.randomUUID();
            jdbcTemplate.update("""
                INSERT INTO group_assessment_publication
                  (id, group_id, teacher_username, title, subject, period, assigned_date,
                   due_date, instructions, confirmed_with_notebook, status, published_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'DRAFT', NULL)
                """, publicationId, groupId, actor, title, group.subject(), group.period(),
                command.assignedDate(), command.dueDate(), instructions,
                command.confirmedWithNotebook());
        } else {
            publicationId = existing.id();
            jdbcTemplate.update("""
                UPDATE group_assessment_publication
                SET teacher_username = ?, due_date = ?, instructions = ?,
                    confirmed_with_notebook = ?
                WHERE id = ? AND status = 'DRAFT'
                """, actor, command.dueDate(), instructions, command.confirmedWithNotebook(),
                publicationId);
        }
        if (!datesValid) {
            return publicationView(publicationId, false,
                "La fecha de entrega debe ser igual o posterior a la fecha de asignación.");
        }
        if (!command.confirmedWithNotebook()) {
            return publicationView(publicationId, false,
                "Confirme que los datos coinciden con el cuaderno para publicar.");
        }
        List<EnrolledStudent> students = jdbcTemplate.query("""
            SELECT s.id, s.full_name FROM student_enrollment e
            JOIN student s ON s.id = e.student_id
            WHERE e.group_id = ? AND e.active = TRUE AND s.active = TRUE
            ORDER BY s.full_name
            """, (result, row) -> new EnrolledStudent(
                result.getObject("id", UUID.class), result.getString("full_name")), groupId);
        if (students.isEmpty()) {
            return publicationView(publicationId, false,
                "El grupo no tiene estudiantes activos inscritos.");
        }
        jdbcTemplate.update("""
            UPDATE group_assessment_publication
            SET status = 'PUBLISHED', confirmed_with_notebook = TRUE, published_at = ?
            WHERE id = ? AND status = 'DRAFT'
            """, Timestamp.from(now), publicationId);
        for (EnrolledStudent student : students) {
            UUID assessmentId = UUID.randomUUID();
            jdbcTemplate.update("""
                INSERT INTO assessment
                  (id, student_id, subject, title, period, assigned_date, instructions,
                   published, published_at, group_id, group_publication_id, due_date,
                   created_by, cancelled, cuaderno_confirmed)
                VALUES (?, ?, ?, ?, ?, ?, ?, TRUE, ?, ?, ?, ?, ?, FALSE, TRUE)
                ON CONFLICT (group_publication_id, student_id)
                  WHERE group_publication_id IS NOT NULL DO NOTHING
                """, assessmentId, student.id(), group.subject(), title, group.period(),
                command.assignedDate(), instructions, Timestamp.from(now), groupId,
                publicationId, command.dueDate(), actor);
            UUID persistedAssessmentId = assessmentIdFor(publicationId, student.id());
            createNotifications(student.id(), "ASSESSMENT_ASSIGNMENT", persistedAssessmentId,
                "Evaluación asignada: " + title);
            List<ComplaintRecipient> complaints = jdbcTemplate.query("""
                SELECT id, guardian_username FROM information_complaint
                WHERE student_id = ? AND subject = ? AND period = ?
                  AND requested_teacher = ? AND status = 'IN_PROGRESS'
                """, (result, row) -> new ComplaintRecipient(
                    result.getObject("id", UUID.class), result.getString("guardian_username")),
                student.id(), group.subject(), group.period(), actor);
            for (ComplaintRecipient complaint : complaints) {
                jdbcTemplate.update("""
                    UPDATE information_complaint
                    SET related_publication_id = ?, status = 'ATTENDED',
                        finding = 'PUBLISHED_NOT_READ',
                        response = CONCAT(COALESCE(response, ''), ' Publicación relacionada: ', ?),
                        updated_by = ?, updated_at = ?
                    WHERE id = ?
                    """, publicationId, title, actor, Timestamp.from(now), complaint.id());
                jdbcTemplate.update("""
                    INSERT INTO portal_notification
                      (id, recipient_username, student_id, notification_type,
                       reference_id, subject, created_at)
                    VALUES (?, ?, ?, 'COMPLAINT_RESPONSE', ?, ?, ?)
                    ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
                    """, UUID.randomUUID(), complaint.guardianUsername(), student.id(),
                    complaint.id(), "La información solicitada ya fue publicada.",
                    Timestamp.from(now));
            }
        }
        return publicationView(publicationId, false, null);
    }

    @Transactional
    public GradeView publishGrade(String actor, UUID assessmentId, GradeCommand command) {
        AssessmentInfo assessment = assessment(assessmentId);
        requireTeachingAssignment(actor, assessment.groupId());
        validateGradeCommand(command);
        if (!assessment.published() || assessment.cancelled()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La evaluación no está disponible para calificar.");
        }
        if (gradeExists(assessmentId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La calificación ya está publicada; use CU-09 para corregirla.");
        }
        if (command.numericValue().compareTo(BigDecimal.ZERO) < 0
            || command.numericValue().compareTo(MAX_GRADE) > 0) {
            saveGradeDraft(actor, assessmentId, command);
            return new GradeView(assessmentId, command.numericValue(), "DRAFT", null, 0,
                "La nota está fuera de la escala de 0 a 100; no se publicó.");
        }
        if (!command.confirmedWithNotebook()) {
            saveGradeDraft(actor, assessmentId, command);
            return new GradeView(assessmentId, command.numericValue(), "DRAFT", null, 0,
                "Confirme que la nota coincide con el cuaderno para publicar.");
        }
        Instant now = Instant.now();
        UUID gradeId = UUID.randomUUID();
        jdbcTemplate.update("""
            INSERT INTO grade_record
              (id, assessment_id, numeric_value, observation, published_date,
               created_by, created_at, version)
            VALUES (?, ?, ?, ?, ?, ?, ?, 0)
            """, gradeId, assessmentId, command.numericValue(), normalized(command.observation()),
            command.publishedDate(), actor, Timestamp.from(now));
        jdbcTemplate.update("DELETE FROM grade_draft WHERE assessment_id = ?", assessmentId);
        createNotifications(assessment.studentId(), "GRADE_PUBLICATION", gradeId,
            "Calificación publicada: " + assessment.title());
        return new GradeView(assessmentId, command.numericValue(), "PUBLISHED", gradeId, 0, null);
    }

    @Transactional
    public GradeView correctGrade(String actor, UUID assessmentId, GradeCorrection command) {
        AssessmentInfo assessment = assessment(assessmentId);
        requireTeachingAssignment(actor, assessment.groupId());
        if (command.numericValue() == null || command.publishedDate() == null
            || command.expectedVersion() < 0) {
            throw badRequest("Revise la calificación, la fecha y la versión vigente.");
        }
        String reason = normalized(command.reason());
        if (invalid(reason, REASON_LENGTH)) {
            throw badRequest("Indique el motivo de la corrección.");
        }
        if (!command.confirmedWithNotebook()) {
            throw badRequest("Confirme que la corrección coincide con el cuaderno.");
        }
        if (command.numericValue().compareTo(BigDecimal.ZERO) < 0
            || command.numericValue().compareTo(MAX_GRADE) > 0) {
            throw badRequest("La calificación debe estar entre 0 y 100.");
        }
        GradeCurrent current = currentGrade(assessmentId);
        if (current == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No hay una calificación publicada para corregir.");
        }
        if (current.version() != command.expectedVersion()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La versión vigente cambió a " + current.numericValue() + ". Recargue antes de corregir.");
        }
        Instant now = Instant.now();
        UUID revisionId = UUID.randomUUID();
        jdbcTemplate.update("""
            INSERT INTO grade_revision
              (id, assessment_id, numeric_value, observation, published_date, reason,
               corrected_by, corrected_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """, revisionId, assessmentId, current.numericValue(), current.observation(),
            current.publishedDate(), reason, actor, Timestamp.from(now));
        int changed = jdbcTemplate.update("""
            UPDATE grade_record
            SET numeric_value = ?, observation = ?, published_date = ?,
                updated_by = ?, updated_at = ?, version = version + 1
            WHERE assessment_id = ? AND version = ?
            """, command.numericValue(), normalized(command.observation()),
            command.publishedDate(), actor, Timestamp.from(now), assessmentId,
            command.expectedVersion());
        if (changed != 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "La versión vigente cambió. Recargue antes de corregir.");
        }
        createNotifications(assessment.studentId(), "GRADE_CORRECTION", revisionId,
            "Calificación corregida: " + assessment.title());
        return new GradeView(assessmentId, command.numericValue(), "PUBLISHED",
            current.id(), command.expectedVersion() + 1, null);
    }

    @Transactional
    public SettingsView updateSettings(String actor, SettingsCommand command) {
        if (command.appealWindowDays() < 1 || command.resolutionWindowDays() < 1
            || command.reminderIntervalHours() < 1
            || command.maxReminders() < 1) {
            throw badRequest("Los plazos e intervalos deben ser mayores que cero.");
        }
        Instant now = Instant.now();
        jdbcTemplate.update("""
            INSERT INTO appeal_deadline_config
              (id, appeal_window_days, resolution_window_days, reminder_interval_hours, max_reminders,
               changed_by, changed_at)
            VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)
            """, command.appealWindowDays(), command.resolutionWindowDays(),
            command.reminderIntervalHours(), command.maxReminders(), actor, Timestamp.from(now));
        return settings();
    }

    @Transactional(readOnly = true)
    public SettingsView settings() {
        List<SettingsView> rows = jdbcTemplate.query("""
            SELECT appeal_window_days, resolution_window_days, reminder_interval_hours, max_reminders,
                   changed_by, changed_at
            FROM appeal_deadline_config ORDER BY changed_at DESC, id DESC LIMIT 1
            """, (result, row) -> new SettingsView(result.getInt("appeal_window_days"),
                result.getInt("resolution_window_days"), result.getInt("reminder_interval_hours"),
                result.getInt("max_reminders"),
                result.getString("changed_by"), instant(result.getTimestamp("changed_at"))));
        return rows.stream().findFirst().orElse(null);
    }

    @Transactional
    public ReminderRun runReminders(String actor) {
        SettingsView config = settings();
        if (config == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Configure primero el intervalo y el máximo de recordatorios.");
        }
        List<PendingNotice> notices = jdbcTemplate.query("""
            SELECT n.id, n.recipient_username, n.student_id, n.subject, n.reminder_count,
                   COALESCE(n.last_reminded_at, n.created_at) AS last_event
            FROM portal_notification n
            JOIN portal_user recipient ON recipient.username = n.recipient_username
              AND recipient.role_name = 'GUARDIAN' AND recipient.active = TRUE
            JOIN guardian_student_link l ON l.guardian_username = n.recipient_username
              AND l.student_id = n.student_id AND l.status = 'ACTIVE'
            WHERE n.active = TRUE AND n.read_at IS NULL
              AND n.notification_type IN ('ASSESSMENT_ASSIGNMENT', 'GRADE_PUBLICATION',
                'GRADE_CORRECTION', 'MESSAGE', 'APPEAL_RESOLUTION', 'COMPLAINT_RESPONSE')
              AND n.reminder_count < ?
              AND COALESCE(n.last_reminded_at, n.created_at)
                    <= CURRENT_TIMESTAMP - (? * INTERVAL '1 hour')
            ORDER BY n.created_at
            """, (result, row) -> new PendingNotice(result.getObject("id", UUID.class),
                result.getString("recipient_username"), result.getObject("student_id", UUID.class),
                result.getString("subject"), result.getInt("reminder_count")),
            config.maxReminders(), config.reminderIntervalHours());
        Instant now = Instant.now();
        for (PendingNotice notice : notices) {
            jdbcTemplate.update("""
                UPDATE portal_notification SET reminder_count = reminder_count + 1,
                  last_reminded_at = ? WHERE id = ? AND read_at IS NULL
                """, Timestamp.from(now), notice.id());
            jdbcTemplate.update("""
                INSERT INTO portal_notification
                  (id, recipient_username, student_id, notification_type, reference_id,
                   related_notification_id, subject, created_at)
                VALUES (?, ?, ?, 'REMINDER', ?, ?, ?, ?)
                """, UUID.randomUUID(), notice.recipient(), notice.studentId(), UUID.randomUUID(),
                notice.id(), "Recordatorio: " + notice.subject(), Timestamp.from(now));
        }
        List<PendingNotice> exhausted = jdbcTemplate.query("""
            SELECT n.id, n.recipient_username, n.student_id, n.subject, n.reminder_count
            FROM portal_notification n JOIN portal_user recipient
              ON recipient.username = n.recipient_username AND recipient.role_name = 'GUARDIAN'
            WHERE n.active = TRUE AND n.read_at IS NULL AND n.reminder_count >= ?
              AND n.alerted_at IS NULL AND n.notification_type IN ('ASSESSMENT_ASSIGNMENT',
                'GRADE_PUBLICATION', 'GRADE_CORRECTION', 'MESSAGE', 'APPEAL_RESOLUTION',
                'COMPLAINT_RESPONSE')
            """, (result, row) -> new PendingNotice(result.getObject("id", UUID.class),
                result.getString("recipient_username"), result.getObject("student_id", UUID.class),
                result.getString("subject"), result.getInt("reminder_count")),
            config.maxReminders());
        for (PendingNotice notice : exhausted) {
            jdbcTemplate.update("UPDATE portal_notification SET alerted_at = ? WHERE id = ?",
                Timestamp.from(now), notice.id());
            jdbcTemplate.update("""
                INSERT INTO portal_notification
                  (id, recipient_username, student_id, notification_type, reference_id,
                   subject, created_at)
                VALUES (?, 'direccion.demo', ?, 'REMINDER_LIMIT_ALERT', ?, ?, ?)
                ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
                """, UUID.randomUUID(), notice.studentId(), notice.id(),
                "Aviso sin consulta tras " + notice.reminderCount() + " recordatorios: "
                    + notice.subject(), Timestamp.from(now));
        }
        List<PendingNotice> revoked = jdbcTemplate.query("""
            SELECT n.id, n.recipient_username, n.student_id, n.subject, n.reminder_count
            FROM portal_notification n
            JOIN portal_user recipient ON recipient.username = n.recipient_username
              AND recipient.role_name = 'GUARDIAN' AND recipient.active = TRUE
            WHERE n.active = TRUE AND n.read_at IS NULL
              AND NOT EXISTS (SELECT 1 FROM guardian_student_link l
                WHERE l.guardian_username = n.recipient_username
                  AND l.student_id = n.student_id AND l.status = 'ACTIVE')
            """, (result, row) -> new PendingNotice(result.getObject("id", UUID.class),
                result.getString("recipient_username"), result.getObject("student_id", UUID.class),
                result.getString("subject"), result.getInt("reminder_count")));
        for (PendingNotice notice : revoked) {
            jdbcTemplate.update("UPDATE portal_notification SET active = FALSE WHERE id = ?",
                notice.id());
            jdbcTemplate.update("""
                INSERT INTO portal_notification
                  (id, recipient_username, student_id, notification_type, reference_id,
                   subject, created_at)
                VALUES (?, 'direccion.demo', ?, 'NO_ACTIVE_GUARDIAN_ALERT', ?, ?, ?)
                ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
                """, UUID.randomUUID(), notice.studentId(), notice.id(),
                "Sin vínculo activo para el aviso: " + notice.subject(), Timestamp.from(now));
        }
        List<String> recipients = notices.stream().map(PendingNotice::recipient).distinct().toList();
        return new ReminderRun(notices.size(), recipients, now, exhausted.size(), revoked.size());
    }

    @Transactional(readOnly = true)
    public SupervisionView supervision() {
        List<StudentFollowup> students = jdbcTemplate.query("""
            SELECT s.id, s.full_name,
              COUNT(DISTINCT CASE WHEN l.status = 'ACTIVE' THEN l.id END) AS guardians,
              COUNT(DISTINCT CASE WHEN n.read_at IS NULL AND n.active THEN n.id END) AS pending
            FROM student s
            LEFT JOIN guardian_student_link l ON l.student_id = s.id
            LEFT JOIN portal_notification n ON n.student_id = s.id
            WHERE s.active = TRUE
            GROUP BY s.id, s.full_name
            HAVING COUNT(DISTINCT CASE WHEN l.status = 'ACTIVE' THEN l.id END) = 0
              OR COUNT(DISTINCT CASE WHEN n.read_at IS NULL AND n.active THEN n.id END) > 0
            ORDER BY s.full_name
            """, (result, row) -> new StudentFollowup(result.getObject("id", UUID.class),
                result.getString("full_name"), result.getLong("guardians"),
                result.getLong("pending")));
        Integer appeals = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM grade_appeal WHERE status IN ('OPEN', 'IN_REVIEW', 'PENDING_INFORMATION')
            """, Integer.class);
        Integer complaints = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM information_complaint WHERE status = 'OPEN'
            """, Integer.class);
        return new SupervisionView(students, appeals == null ? 0 : appeals,
            complaints == null ? 0 : complaints);
    }

    @Transactional(readOnly = true)
    public TeacherCatalog teacherCatalog(String actor) {
        List<TeacherGroupView> groups = jdbcTemplate.query("""
            SELECT g.id, g.subject, g.grade_level, g.group_name, g.period,
                   COUNT(DISTINCT CASE WHEN s.id IS NOT NULL THEN e.student_id END) AS student_count
            FROM teaching_assignment a JOIN academic_group g ON g.id = a.group_id
            LEFT JOIN student_enrollment e ON e.group_id = g.id AND e.active = TRUE
            LEFT JOIN student s ON s.id = e.student_id AND s.active = TRUE
            WHERE a.teacher_username = ? AND a.active = TRUE AND g.active = TRUE
            GROUP BY g.id, g.subject, g.grade_level, g.group_name, g.period
            ORDER BY g.period DESC, g.grade_level, g.group_name, g.subject
            """, (result, row) -> new TeacherGroupView(result.getObject("id", UUID.class),
                result.getString("subject"), result.getString("grade_level"),
                result.getString("group_name"), result.getString("period"),
                result.getInt("student_count")), actor);
        List<TeacherAssessmentView> assessments = jdbcTemplate.query("""
            SELECT a.id, a.group_id, a.subject, a.title, a.student_id, s.full_name,
                   a.due_date, g.numeric_value, g.version
            FROM assessment a JOIN student s ON s.id = a.student_id
            JOIN teaching_assignment t ON t.group_id = a.group_id
              AND t.teacher_username = ? AND t.active = TRUE
            LEFT JOIN grade_record g ON g.assessment_id = a.id
            WHERE a.published = TRUE AND a.cancelled = FALSE
            ORDER BY a.assigned_date DESC, s.full_name
            """, (result, row) -> new TeacherAssessmentView(
                result.getObject("id", UUID.class), result.getObject("group_id", UUID.class),
                result.getString("subject"), result.getString("title"),
                result.getObject("student_id", UUID.class), result.getString("full_name"),
                result.getObject("due_date", LocalDate.class), result.getBigDecimal("numeric_value"),
                result.getObject("version", Integer.class)), actor);
        List<TeacherStudentView> students = jdbcTemplate.query("""
            SELECT DISTINCT s.id, s.full_name, g.id AS group_id, g.subject, g.grade_level,
                   g.group_name, g.period, l.guardian_username
            FROM teaching_assignment a JOIN academic_group g ON g.id = a.group_id
            JOIN student_enrollment e ON e.group_id = g.id AND e.active = TRUE
            JOIN student s ON s.id = e.student_id AND s.active = TRUE
            LEFT JOIN guardian_student_link l ON l.student_id = s.id AND l.status = 'ACTIVE'
            WHERE a.teacher_username = ? AND a.active = TRUE AND g.active = TRUE
            ORDER BY s.full_name, g.period
            """, (result, row) -> new TeacherStudentView(
                result.getObject("id", UUID.class), result.getString("full_name"),
                result.getObject("group_id", UUID.class), result.getString("subject"),
                result.getString("grade_level"), result.getString("group_name"),
                result.getString("period"), result.getString("guardian_username")), actor);
        return new TeacherCatalog(groups, assessments, students);
    }

    private void saveGradeDraft(String actor, UUID assessmentId, GradeCommand command) {
        jdbcTemplate.update("""
            INSERT INTO grade_draft
              (assessment_id, numeric_value, observation, published_date, updated_by, updated_at)
            VALUES (?, ?, ?, ?, ?, ?)
            ON CONFLICT (assessment_id) DO UPDATE SET
              numeric_value = EXCLUDED.numeric_value, observation = EXCLUDED.observation,
              published_date = EXCLUDED.published_date, updated_by = EXCLUDED.updated_by,
              updated_at = EXCLUDED.updated_at
            """, assessmentId, command.numericValue(), normalized(command.observation()),
            command.publishedDate(), actor, Timestamp.from(Instant.now()));
    }

    private void createNotifications(UUID studentId, String type, UUID referenceId, String subject) {
        jdbcTemplate.update("""
            INSERT INTO portal_notification
              (id, recipient_username, student_id, notification_type, reference_id, subject, created_at)
            SELECT gen_random_uuid(), guardian_username, student_id, ?, ?, ?, CURRENT_TIMESTAMP
            FROM guardian_student_link WHERE student_id = ? AND status = 'ACTIVE'
            ON CONFLICT (recipient_username, notification_type, reference_id) DO NOTHING
            """, type, referenceId, subject, studentId);
    }

    private UUID assessmentIdFor(UUID publicationId, UUID studentId) {
        return jdbcTemplate.queryForObject("""
            SELECT id FROM assessment WHERE group_publication_id = ? AND student_id = ?
            """, UUID.class, publicationId, studentId);
    }

    private PublicationView publicationView(UUID id, boolean existing, String message) {
        GroupPublication publication = jdbcTemplate.queryForObject("""
            SELECT id, status, title, assigned_date FROM group_assessment_publication WHERE id = ?
            """, (result, row) -> new GroupPublication(result.getObject("id", UUID.class),
                result.getString("status"), result.getString("title"),
                result.getObject("assigned_date", LocalDate.class)), id);
        Integer students = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM assessment WHERE group_publication_id = ?", Integer.class, id);
        Integer notifications = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM portal_notification WHERE notification_type = 'ASSESSMENT_ASSIGNMENT'
              AND reference_id IN (SELECT id FROM assessment WHERE group_publication_id = ?)
            """, Integer.class, id);
        return new PublicationView(id, publication.status(), publication.title(),
            publication.assignedDate(), students == null ? 0 : students,
            notifications == null ? 0 : notifications, existing, message);
    }

    private GroupPublication findGroupPublication(UUID groupId, String title, LocalDate assignedDate) {
        List<GroupPublication> rows = jdbcTemplate.query("""
            SELECT id, status, title, assigned_date FROM group_assessment_publication
            WHERE group_id = ? AND title = ? AND assigned_date = ?
            """, (result, row) -> new GroupPublication(result.getObject("id", UUID.class),
                result.getString("status"), result.getString("title"),
                result.getObject("assigned_date", LocalDate.class)), groupId, title, assignedDate);
        return rows.stream().findFirst().orElse(null);
    }

    private GroupInfo group(UUID groupId) {
        List<GroupInfo> rows = jdbcTemplate.query("""
            SELECT id, subject, period, active FROM academic_group WHERE id = ?
            """, (result, row) -> new GroupInfo(result.getObject("id", UUID.class),
                result.getString("subject"), result.getString("period"),
                result.getBoolean("active")), groupId);
        GroupInfo info = rows.stream().findFirst().orElse(null);
        if (info == null || !info.active()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Grupo académico no disponible.");
        }
        return info;
    }

    private void requireTeachingAssignment(String actor, UUID groupId) {
        Integer assigned = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM teaching_assignment a
            JOIN teacher_profile t ON t.username = a.teacher_username AND t.active = TRUE
            JOIN portal_user u ON u.username = a.teacher_username
              AND u.role_name = 'TEACHER' AND u.active = TRUE
            WHERE a.group_id = ? AND a.teacher_username = ? AND a.active = TRUE
            """, Integer.class, groupId, actor);
        if (assigned == null || assigned == 0) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                "El docente no tiene una asignación activa para este grupo.");
        }
    }

    private AssessmentInfo assessment(UUID assessmentId) {
        List<AssessmentInfo> rows = jdbcTemplate.query("""
            SELECT id, student_id, group_id, title, published, cancelled
            FROM assessment WHERE id = ?
            """, (result, row) -> new AssessmentInfo(result.getObject("id", UUID.class),
                result.getObject("student_id", UUID.class), result.getObject("group_id", UUID.class),
                result.getString("title"), result.getBoolean("published"),
                result.getBoolean("cancelled")), assessmentId);
        AssessmentInfo info = rows.stream().findFirst().orElse(null);
        if (info == null || info.groupId() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Evaluación académica no disponible.");
        }
        return info;
    }

    private boolean gradeExists(UUID assessmentId) {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM grade_record WHERE assessment_id = ?", Integer.class, assessmentId);
        return count != null && count > 0;
    }

    private GradeCurrent currentGrade(UUID assessmentId) {
        List<GradeCurrent> rows = jdbcTemplate.query("""
            SELECT id, numeric_value, observation, published_date, version
            FROM grade_record WHERE assessment_id = ?
            """, (result, row) -> new GradeCurrent(result.getObject("id", UUID.class),
                result.getBigDecimal("numeric_value"), result.getString("observation"),
                result.getObject("published_date", LocalDate.class), result.getInt("version")),
            assessmentId);
        return rows.stream().findFirst().orElse(null);
    }

    private void validateGradeCommand(GradeCommand command) {
        if (command.numericValue() == null || command.publishedDate() == null
            || invalid(command.observation(), TEXT_LENGTH)) {
            throw badRequest("Complete calificación, observación y fecha de devolución.");
        }
    }

    private boolean invalid(String value, int maxLength) {
        return value == null || value.isBlank() || value.length() > maxLength;
    }

    private String normalized(String value) {
        return value == null ? null : value.trim();
    }

    private ResponseStatusException badRequest(String message) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
    }

    private Instant instant(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toInstant();
    }

    public record AssessmentCommand(String title, LocalDate assignedDate, LocalDate dueDate,
        String instructions, boolean confirmedWithNotebook) { }
    public record GradeCommand(BigDecimal numericValue, String observation, LocalDate publishedDate,
        boolean confirmedWithNotebook) { }
    public record GradeCorrection(BigDecimal numericValue, String observation,
        LocalDate publishedDate, String reason, boolean confirmedWithNotebook, int expectedVersion) { }
    public record SettingsCommand(int appealWindowDays, int resolutionWindowDays,
        int reminderIntervalHours, int maxReminders) { }
    public record PublicationView(UUID id, String status, String title, LocalDate assignedDate,
        int studentCount, int notificationCount, boolean alreadyPublished, String message) { }
    public record GradeView(UUID assessmentId, BigDecimal numericValue, String status,
        UUID gradeRecordId, int version, String message) { }
    public record SettingsView(int appealWindowDays, int resolutionWindowDays,
        int reminderIntervalHours, int maxReminders, String changedBy, Instant changedAt) { }
    public record ReminderRun(int remindersCreated, List<String> recipients, Instant processedAt,
        int alertsCreated, int revokedNoticesClosed) { }
    public record StudentFollowup(UUID studentId, String fullName, long activeGuardians,
        long pendingNotifications) { }
    public record SupervisionView(List<StudentFollowup> studentsNeedingAttention,
        int openAppeals, int openComplaints) { }
    public record TeacherCatalog(List<TeacherGroupView> groups,
        List<TeacherAssessmentView> assessments, List<TeacherStudentView> students) { }
    public record TeacherGroupView(UUID id, String subject, String gradeLevel, String groupName,
        String period, int studentCount) { }
    public record TeacherAssessmentView(UUID id, UUID groupId, String subject, String title,
        UUID studentId, String studentName, LocalDate dueDate, BigDecimal grade,
        Integer gradeVersion) { }
    public record TeacherStudentView(UUID studentId, String studentName, UUID groupId,
        String subject, String gradeLevel, String groupName, String period,
        String guardianUsername) { }
    private record GroupInfo(UUID id, String subject, String period, boolean active) { }
    private record GroupPublication(UUID id, String status, String title, LocalDate assignedDate) { }
    private record EnrolledStudent(UUID id, String fullName) { }
    private record AssessmentInfo(UUID id, UUID studentId, UUID groupId, String title,
        boolean published, boolean cancelled) { }
    private record GradeCurrent(UUID id, BigDecimal numericValue, String observation,
        LocalDate publishedDate, int version) { }
    private record PendingNotice(UUID id, String recipient, UUID studentId, String subject,
        int reminderCount) { }
    private record ComplaintRecipient(UUID id, String guardianUsername) { }
}
