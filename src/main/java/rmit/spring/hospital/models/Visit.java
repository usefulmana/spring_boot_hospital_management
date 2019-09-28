package rmit.spring.hospital.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "visits")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "problems")
    private String problems;

    @Column(name = "date_created")
    @CreatedDate
    private Date dateCreated;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private Patient patient;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "visit")
    private List<Prescription> prescriptions = new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "visit")
    private List<Diagnosis> diagnoses = new ArrayList<>();

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "visit")
    private List<LabReport> labReports = new ArrayList<>();

}
