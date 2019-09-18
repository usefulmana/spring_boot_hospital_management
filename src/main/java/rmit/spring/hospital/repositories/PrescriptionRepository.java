package rmit.spring.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.Prescription;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    List<Prescription> findAllByVisit_Id(Long id);
}
