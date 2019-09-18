package rmit.spring.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import rmit.spring.hospital.Repositories.DiseaseRepository;
import rmit.spring.hospital.Repositories.DrugRepository;
import rmit.spring.hospital.Repositories.PatientRepository;
import rmit.spring.hospital.models.Disease;
import rmit.spring.hospital.models.Drug;
import rmit.spring.hospital.models.Patient;

import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    // Test Data
    CommandLineRunner initDatabase(PatientRepository patientRepository, DrugRepository drugRepository) {
        Date date = new Date();
        return args -> {
            patientRepository.deleteAll();
            drugRepository.deleteAll();
            patientRepository.save(new Patient("Hannah Lang", 23, "Female", date));
            patientRepository.save(new Patient("Chris Lang", 30, "Male", date));
            patientRepository.save(new Patient("Bob Hulk", 17, "Male", date));
            drugRepository.save(new Drug("Paradol"));
            drugRepository.save(new Drug("Ketamine"));
            drugRepository.save(new Drug("Ibuprofen"));
//            diseaseRepository.save(new Disease("Fever"));
//            diseaseRepository.save(new Disease("Headache"));*/
        };
    }

}
