package rmit.spring.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.Drug;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByDrugNameIgnoreCaseContaining(String drugName);
}
