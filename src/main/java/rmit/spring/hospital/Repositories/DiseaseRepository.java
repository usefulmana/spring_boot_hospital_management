package rmit.spring.hospital.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.Disease;

import java.util.List;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    List<Disease> findByDiseaseNameIgnoreCaseContaining(String name);
}
