package cr.ac.ucr.if6100.portal.persistence.entity;

import java.time.Instant;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "guardian_student_link")
public class GuardianStudentLinkEntity {
    private static final int USERNAME_LENGTH = 80;
    private static final int RELATIONSHIP_LENGTH = 80;
    private static final int EVIDENCE_LENGTH = 300;
    private static final int STATUS_LENGTH = 20;
    private static final int REASON_LENGTH = 500;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "guardian_username", nullable = false, columnDefinition = "varchar(80)")
    private PortalUserEntity guardian;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @Column(name = "relationship_type", nullable = false, length = RELATIONSHIP_LENGTH)
    private String relationshipType;

    @Column(name = "evidence_reference", nullable = false, length = EVIDENCE_LENGTH)
    private String evidenceReference;

    @Column(nullable = false, length = STATUS_LENGTH)
    private String status;

    @Column(name = "created_by", nullable = false, length = USERNAME_LENGTH)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "changed_by", length = USERNAME_LENGTH)
    private String changedBy;

    @Column(name = "changed_at")
    private Instant changedAt;

    @Column(name = "change_reason", length = REASON_LENGTH)
    private String changeReason;

    protected GuardianStudentLinkEntity() { }

    public GuardianStudentLinkEntity(PortalUserEntity guardian, StudentEntity student,
        String relationshipType, String evidenceReference, String createdBy, Instant createdAt) {
        this.guardian = guardian;
        this.student = student;
        this.relationshipType = relationshipType;
        this.evidenceReference = evidenceReference;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.status = "ACTIVE";
    }

    public void changeStatus(String newStatus, String reason, String actor, Instant at) {
        this.status = newStatus;
        this.changeReason = reason;
        this.changedBy = actor;
        this.changedAt = at;
    }

    public UUID getId() {
        return id;
    }

    public PortalUserEntity getGuardian() {
        return guardian;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public String getRelationshipType() {
        return relationshipType;
    }

    public String getEvidenceReference() {
        return evidenceReference;
    }

    public String getStatus() {
        return status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public Instant getChangedAt() {
        return changedAt;
    }

    public String getChangeReason() {
        return changeReason;
    }
}
