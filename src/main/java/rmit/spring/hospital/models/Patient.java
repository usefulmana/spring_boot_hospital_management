package rmit.spring.hospital.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_name", nullable = false)
    private String patientName;

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

    @Column(name = "date_created")
    @CreatedDate
    private Date dateCreated;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    @OneToMany( cascade = CascadeType.ALL ,mappedBy = "patient")
    private List<Visit> visits = new ArrayList<>();

    public Patient(String name, Integer age, String sex, Date dob) {
        this.patientName = name;
        this.age = age;
        this.sex = sex;
        this.dob = dob;
    }


}
