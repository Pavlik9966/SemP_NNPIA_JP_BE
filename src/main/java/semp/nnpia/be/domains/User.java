package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "username", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 256)
    private String username;

    @Column(name = "password")
    @NotBlank
    @NotNull
    @Size(max = 256)
    private String password;

    @Column(name = "name")
    @NotBlank
    @NotNull
    @Size(max = 256)
    private String name;

    @Column(name = "surname")
    @NotBlank
    @NotNull
    @Size(max = 256)
    private String surname;

    @Column(name = "date_of_birth")
    @NotNull
    private LocalDate dateOfBirth;

    @Column(name = "phone")
    @NotBlank
    @NotNull
    @Size(max = 20)
    private String phone;

    @Column(name = "email")
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String email;

    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles = new HashSet<>();

    public User(Long id, String username, String password, String name, String surname, LocalDate dateOfBirth, String phone, String email, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public User(String username, String password, String name, String surname, LocalDate dateOfBirth, String phone, String email, LocalDateTime createdAt) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }
}