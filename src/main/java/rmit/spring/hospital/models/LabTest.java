package rmit.spring.hospital.models;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "lab_tests")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "description")
    private String description;

    @Column(name = "date_added")
    @CreatedDate
    private Date createdDate;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    public LabTest(String testName) {
        this.testName = testName;
    }
}
