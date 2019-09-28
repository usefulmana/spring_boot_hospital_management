package rmit.spring.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.LabTest;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
