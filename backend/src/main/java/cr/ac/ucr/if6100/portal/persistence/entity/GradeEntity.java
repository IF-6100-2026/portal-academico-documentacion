package cr.ac.ucr.if6100.portal.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "grade_record")
public class GradeEntity {
    private static final int VALUE_PRECISION = 6;
    private static final int VALUE_SCALE = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "assessment_id", nullable = false, unique = true)
    private AssessmentEntity assessment;

    @Column(name = "numeric_value", nullable = false, precision = VALUE_PRECISION, scale = VALUE_SCALE)
    private BigDecimal numericValue;

    @Column(nullable = false, columnDefinition = "text")
    private String observation;

    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate;

    protected GradeEntity() { }

    public BigDecimal getNumericValue() {
        return numericValue;
    }

    public String getObservation() {
        return observation;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }
}
