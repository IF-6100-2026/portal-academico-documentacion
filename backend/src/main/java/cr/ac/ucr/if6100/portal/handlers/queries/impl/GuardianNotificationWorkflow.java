package cr.ac.ucr.if6100.portal.handlers.queries.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Lista avisos propios y registra el primer acuse de lectura. */
@Component
public class GuardianNotificationWorkflow {
    private final JdbcTemplate jdbcTemplate;

    public GuardianNotificationWorkflow(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<NotificationView> notifications(String actor) {
        return jdbcTemplate.query("""
            SELECT id, student_id, notification_type, reference_id, subject,
                   created_at, read_at, reminder_count
            FROM portal_notification WHERE recipient_username = ? AND active = TRUE
            ORDER BY created_at DESC
            """, (result, row) -> new NotificationView(result.getObject("id", UUID.class),
                result.getObject("student_id", UUID.class), result.getString("notification_type"),
                result.getObject("reference_id", UUID.class), result.getString("subject"),
                result.getTimestamp("created_at").toInstant(),
                instant(result.getTimestamp("read_at")), result.getInt("reminder_count")), actor);
    }

    @Transactional
    public NotificationView markRead(String actor, UUID notificationId) {
        NotificationView notification = find(actor, notificationId);
        if (notification == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviso no disponible.");
        }
        Integer activeLink = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM guardian_student_link
            WHERE guardian_username = ? AND student_id = ? AND status = 'ACTIVE'
            """, Integer.class, actor, notification.studentId());
        if (activeLink == null || activeLink == 0) {
            jdbcTemplate.update("""
                INSERT INTO security_access_log(id, username, requested_path, occurred_at)
                VALUES (?, ?, ?, CURRENT_TIMESTAMP)
                """, UUID.randomUUID(), actor, "/api/v1/guardian/notifications/" + notificationId);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aviso no disponible.");
        }
        Instant now = Instant.now();
        jdbcTemplate.update("""
            UPDATE portal_notification SET read_at = COALESCE(read_at, ?)
            WHERE id = ? AND recipient_username = ?
            """, Timestamp.from(now), notificationId, actor);
        if ("MESSAGE".equals(notification.type())) {
            jdbcTemplate.update("""
                UPDATE student_message SET read_at = COALESCE(read_at, ?)
                WHERE id = ? AND recipient_username = ?
                """, Timestamp.from(now), notification.referenceId(), actor);
        }
        if (List.of("ASSESSMENT_ASSIGNMENT", "GRADE_PUBLICATION", "GRADE_CORRECTION")
            .contains(notification.type())) {
            String period = periodFor(notification);
            jdbcTemplate.update("""
                INSERT INTO consultation_log
                  (id, guardian_username, student_id, period, outcome, consulted_at)
                VALUES (?, ?, ?, ?, 'AUTHORIZED', ?)
                """, UUID.randomUUID(), actor, notification.studentId(), period, Timestamp.from(now));
        }
        return find(actor, notificationId);
    }

    private String periodFor(NotificationView notification) {
        String query = switch (notification.type()) {
            case "ASSESSMENT_ASSIGNMENT" -> "SELECT period FROM assessment WHERE id = ?";
            case "GRADE_PUBLICATION" -> """
                SELECT a.period FROM grade_record g JOIN assessment a ON a.id = g.assessment_id
                WHERE g.id = ?
                """;
            case "GRADE_CORRECTION" -> """
                SELECT a.period FROM grade_revision r JOIN assessment a ON a.id = r.assessment_id
                WHERE r.id = ?
                """;
            default -> throw new IllegalStateException("Tipo de aviso académico no esperado.");
        };
        return jdbcTemplate.queryForObject(query, String.class, notification.referenceId());
    }

    private NotificationView find(String actor, UUID id) {
        List<NotificationView> rows = jdbcTemplate.query("""
            SELECT id, student_id, notification_type, reference_id, subject,
                   created_at, read_at, reminder_count
            FROM portal_notification
            WHERE id = ? AND recipient_username = ? AND active = TRUE
            """, (result, row) -> new NotificationView(result.getObject("id", UUID.class),
                result.getObject("student_id", UUID.class), result.getString("notification_type"),
                result.getObject("reference_id", UUID.class), result.getString("subject"),
                result.getTimestamp("created_at").toInstant(),
                instant(result.getTimestamp("read_at")), result.getInt("reminder_count")), id, actor);
        return rows.stream().findFirst().orElse(null);
    }

    private Instant instant(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toInstant();
    }

    public record NotificationView(UUID id, UUID studentId, String type, UUID referenceId,
        String subject, Instant createdAt, Instant readAt, int reminderCount) { }
}
