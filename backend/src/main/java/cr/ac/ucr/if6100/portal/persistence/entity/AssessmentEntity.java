package cr.ac.ucr.if6100.portal.persistence.entity;

import java.time.LocalDate;
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
@Table(name = "assessment")
public class AssessmentEntity {
    private static final int SUBJECT_LENGTH = 100;
    private static final int TITLE_LENGTH = 180;
    private static final int PERIOD_LENGTH = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @Column(nullable = false, length = SUBJECT_LENGTH)
    private String subject;

    @Column(nullable = false, length = TITLE_LENGTH)
    private String title;

    @Column(nullable = false, length = PERIOD_LENGTH)
    private String period;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate;

    @Column(nullable = false, columnDefinition = "text")
    private String instructions;

    @Column(nullable = false)
    private boolean published;

    protected AssessmentEntity() { }

    public UUID getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public String getPeriod() {
        return period;
    }

    public LocalDate getAssignedDate() {
        return assignedDate;
    }

    public String getInstructions() {
        return instructions;
    }
}
