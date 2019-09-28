package rmit.spring.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rmit.spring.hospital.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
