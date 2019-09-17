package rmit.spring.hospital.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patients")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "dob", nullable = false)

    private Date dob;

    @Column(name = "insurance_number")
    private String insuranceNumber;

    @Column(name = "national_ID_number")
    private String nationalIDNumber;

    @Column(name = "date_joined")
    @CreatedDate
    private Date dateJoined;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    public Patient(String firstName, String lastName, Integer age, String sex, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.dob = dob;
    }


}
