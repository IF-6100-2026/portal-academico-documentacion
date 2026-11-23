package cr.ac.ucr.if6100.portal.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "consultation_log")
public class ConsultationLogEntity {
    private static final int USERNAME_LENGTH = 80;
    private static final int PERIOD_LENGTH = 40;
    private static final int OUTCOME_LENGTH = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "guardian_username", nullable = false, length = USERNAME_LENGTH)
    private String guardianUsername;

    @Column(name = "student_id")
    private UUID studentId;

    @Column(nullable = false, length = PERIOD_LENGTH)
    private String period;

    @Column(nullable = false, length = OUTCOME_LENGTH)
    private String outcome;

    @Column(name = "consulted_at", nullable = false)
    private Instant consultedAt;

    protected ConsultationLogEntity() { }

    public ConsultationLogEntity(String guardianUsername, UUID studentId, String period,
        String outcome, Instant consultedAt) {
        this.guardianUsername = guardianUsername;
        this.studentId = studentId;
        this.period = period;
        this.outcome = outcome;
        this.consultedAt = consultedAt;
    }
}
