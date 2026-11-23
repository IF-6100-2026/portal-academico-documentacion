package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.AssessmentEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<AssessmentEntity, UUID> {
    List<AssessmentEntity> findByStudentIdAndPeriodAndPublishedTrueOrderByAssignedDateDesc(
        UUID studentId, String period);
}
