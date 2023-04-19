package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "account_types")
public class AccountType {
    @Column(name = "account_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "account_type", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String type;

    public AccountType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public AccountType(String type) {
        this.type = type;
    }
}