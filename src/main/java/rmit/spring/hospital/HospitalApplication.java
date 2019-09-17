package rmit.spring.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import rmit.spring.hospital.Repositories.PatientRepository;
import rmit.spring.hospital.models.Patient;

import java.util.Date;

@SpringBootApplication
@EnableJpaAuditing
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(PatientRepository patientRepository) {
        Date date = new Date();
        return args -> {
            patientRepository.save(new Patient("Hannah", "Lang", 23, "F", date));
            patientRepository.save(new Patient("Chris", "Lang", 30, "M", date));
            patientRepository.save(new Patient("Bob", "Hulk", 17, "M", date));
        };
    }

}
