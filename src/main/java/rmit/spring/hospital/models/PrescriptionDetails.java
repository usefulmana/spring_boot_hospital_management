package rmit.spring.hospital.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Table(name = "prescription_details")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drug_name", nullable = false)
    private String drugName;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "dosage_per_day", nullable = false)
    private int dosage;

    @Column(name = "instruction", nullable = false)
    private String instruction;

}
