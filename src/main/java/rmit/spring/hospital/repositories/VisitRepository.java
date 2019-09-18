package rmit.spring.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.Visit;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> getAllByPatient_Id(Long id);
}
