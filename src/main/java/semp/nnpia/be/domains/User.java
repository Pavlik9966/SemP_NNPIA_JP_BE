package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    @NotBlank
    @NotNull
    @Size(max = 255, min = 1)
    private String username;

    @Column
    private String password;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToMany(mappedBy = "users")
    private List<Role> roles = Collections.emptyList();

    public User(Long id, String username, String password, LocalDateTime creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }

    public User(String username, String password, LocalDateTime creationDate) {
        this.username = username;
        this.password = password;
        this.creationDate = creationDate;
    }
}