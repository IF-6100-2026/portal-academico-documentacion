package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.GradeRevisionEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRevisionRepository extends JpaRepository<GradeRevisionEntity, UUID> {
    List<GradeRevisionEntity> findByAssessmentIdOrderByCorrectedAtDesc(UUID assessmentId);
}
