package rmit.spring.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
}
