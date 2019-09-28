package rmit.spring.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import rmit.spring.hospital.models.*;
import rmit.spring.hospital.repositories.*;

import java.awt.image.BandCombineOp;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    // Test Data
    CommandLineRunner initDatabase(PatientRepository patientRepository, DrugRepository drugRepository,
                                   DiseaseRepository diseaseRepository, UserRepository userRepository,
                                   BCryptPasswordEncoder encoder, LabTestRepository labTestRepository) {
        Date date = new Date();
        return args -> {
            userRepository.save(new User("admin", encoder.encode("123456"), "admin@gmail.com", "Alex", "Nguyen", ""
                    , Set.of(new Role("ADMIN"))));
            userRepository.save(new User("nurse", encoder.encode("123456"), "nurse@gmail.com", "Alex", "Nguyen", ""
                    , Set.of(new Role("NURSE"))));
            userRepository.save(new User("doctor", encoder.encode("123456"), "doctor@gmail.com", "Alex", "Nguyen", ""
                    , Set.of(new Role("DOCTOR"))));
            userRepository.save(new User("staff", encoder.encode("123456"), "staff@gmail.com", "Alex", "Nguyen", ""
                    , Set.of(new Role("STAFF"))));
            patientRepository.save(new Patient("Hannah Lang", 23, "Female", date));
            patientRepository.save(new Patient("Chris Lang", 30, "Male", date));
            patientRepository.save(new Patient("Bob Hulk", 17, "Male", date));
            drugRepository.save(new Drug("Paradol"));
            drugRepository.save(new Drug("Ketamine"));
            drugRepository.save(new Drug("Ibuprofen"));
            diseaseRepository.save(new Disease("Fever"));
            diseaseRepository.save(new Disease("Headache"));
            labTestRepository.save(new LabTest("X-Ray"));
            labTestRepository.save(new LabTest("CT Scan"));
        };
    }

}
