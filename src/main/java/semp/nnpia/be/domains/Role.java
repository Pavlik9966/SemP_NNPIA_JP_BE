package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<User> users = new HashSet<>();
}