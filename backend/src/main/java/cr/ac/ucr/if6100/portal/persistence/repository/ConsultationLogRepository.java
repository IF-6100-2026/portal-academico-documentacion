package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.ConsultationLogEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationLogRepository extends JpaRepository<ConsultationLogEntity, UUID> {
    Optional<ConsultationLogEntity> findTopByGuardianUsernameAndStudentIdAndOutcomeOrderByConsultedAtDesc(
        String guardianUsername, UUID studentId, String outcome);
}
