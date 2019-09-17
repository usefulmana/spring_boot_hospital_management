package rmit.spring.hospital.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
