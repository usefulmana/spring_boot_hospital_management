package rmit.spring.hospital.models;

import lombok.*;
import org.hibernate.annotations.CollectionId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "drugs")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "drug_name", nullable = false)
    private String drugName;

    @Column(name = "date_added")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    public Drug(String drugName){
        this.drugName = drugName;
    }

}
