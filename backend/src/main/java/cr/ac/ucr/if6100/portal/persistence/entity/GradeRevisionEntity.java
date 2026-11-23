package cr.ac.ucr.if6100.portal.persistence.entity;

import java.math.BigDecimal;
import java.time.Instant;
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
@Table(name = "grade_revision")
public class GradeRevisionEntity {
    private static final int VALUE_PRECISION = 6;
    private static final int VALUE_SCALE = 2;
    private static final int REASON_LENGTH = 500;
    private static final int USERNAME_LENGTH = 80;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assessment_id", nullable = false)
    private AssessmentEntity assessment;

    @Column(name = "numeric_value", nullable = false, precision = VALUE_PRECISION, scale = VALUE_SCALE)
    private BigDecimal numericValue;

    @Column(nullable = false, columnDefinition = "text")
    private String observation;

    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate;

    @Column(nullable = false, length = REASON_LENGTH)
    private String reason;

    @Column(name = "corrected_by", nullable = false, length = USERNAME_LENGTH)
    private String correctedBy;

    @Column(name = "corrected_at", nullable = false)
    private Instant correctedAt;

    protected GradeRevisionEntity() { }

    public BigDecimal getNumericValue() {
        return numericValue;
    }

    public String getObservation() {
        return observation;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public String getReason() {
        return reason;
    }

    public String getCorrectedBy() {
        return correctedBy;
    }

    public Instant getCorrectedAt() {
        return correctedAt;
    }
}
