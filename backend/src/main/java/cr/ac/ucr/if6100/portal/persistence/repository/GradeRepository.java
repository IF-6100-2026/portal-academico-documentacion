package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.GradeEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<GradeEntity, UUID> {
    Optional<GradeEntity> findByAssessmentId(UUID assessmentId);
}
