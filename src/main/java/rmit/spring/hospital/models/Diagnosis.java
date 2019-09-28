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
@Table(name = "diagnosis")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    @CreatedDate
    private Date dateCreated;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    @OneToMany( cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnosis_id")
    private List<DiagnosisDetails> diagnosisDetails = new ArrayList<>();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id")
    @JsonIgnore
    private Visit visit;
}
