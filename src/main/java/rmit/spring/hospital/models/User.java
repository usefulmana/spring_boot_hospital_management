package rmit.spring.hospital.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "given_name", nullable = false)
    private String givenName;

    @Column(name = "last_name", nullable = false)
    private String familyName;

    @Column(name = "middle_name")
    private String middleName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "date_created")
    @CreatedDate
    private Date dateCreated;

    @Column(name = "last_updated")
    @LastModifiedDate
    private Date lastUpdated;

    public User(String userName, String password, String email, String givenName, String familyName, String middleName, Set<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.givenName = givenName;
        this.familyName = familyName;
        this.middleName = middleName;
        this.roles = roles;
    }

}
