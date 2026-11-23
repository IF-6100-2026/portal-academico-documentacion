package cr.ac.ucr.if6100.portal.persistence.repository;

import cr.ac.ucr.if6100.portal.persistence.entity.StudentEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    Optional<StudentEntity> findByIdAndActiveTrue(UUID id);
}
