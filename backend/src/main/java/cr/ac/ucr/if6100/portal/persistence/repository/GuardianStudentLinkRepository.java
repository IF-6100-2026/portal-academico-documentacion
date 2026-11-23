package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.GuardianStudentLinkEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuardianStudentLinkRepository extends JpaRepository<GuardianStudentLinkEntity, UUID> {
    List<GuardianStudentLinkEntity> findByGuardianUsernameAndStatusOrderByCreatedAtDesc(
        String username, String status);
    Optional<GuardianStudentLinkEntity> findByGuardianUsernameAndStudentIdAndStatus(
        String username, UUID studentId, String status);
    Optional<GuardianStudentLinkEntity> findByIdAndStatus(UUID id, String status);
    boolean existsByGuardianUsernameAndStudentIdAndStatus(
        String username, UUID studentId, String status);
}
