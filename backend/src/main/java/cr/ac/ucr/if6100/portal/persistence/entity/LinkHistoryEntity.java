package cr.ac.ucr.if6100.portal.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "guardian_link_history")
public class LinkHistoryEntity {
    private static final int STATUS_LENGTH = 20;
    private static final int REASON_LENGTH = 500;
    private static final int USERNAME_LENGTH = 80;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "link_id", nullable = false)
    private GuardianStudentLinkEntity link;

    @Column(name = "previous_status", length = STATUS_LENGTH)
    private String previousStatus;

    @Column(name = "new_status", nullable = false, length = STATUS_LENGTH)
    private String newStatus;

    @Column(nullable = false, length = REASON_LENGTH)
    private String reason;

    @Column(name = "actor_username", nullable = false, length = USERNAME_LENGTH)
    private String actorUsername;

    @Column(name = "occurred_at", nullable = false)
    private Instant occurredAt;

    protected LinkHistoryEntity() { }

    public LinkHistoryEntity(GuardianStudentLinkEntity link, String previousStatus,
        String newStatus, String reason, String actorUsername, Instant occurredAt) {
        this.link = link;
        this.previousStatus = previousStatus;
        this.newStatus = newStatus;
        this.reason = reason;
        this.actorUsername = actorUsername;
        this.occurredAt = occurredAt;
    }
}
