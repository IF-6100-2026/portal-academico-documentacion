package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.PortalUserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortalUserRepository extends JpaRepository<PortalUserEntity, String> {
    Optional<PortalUserEntity> findByUsernameAndRoleNameAndActiveTrue(String username, String roleName);
}
