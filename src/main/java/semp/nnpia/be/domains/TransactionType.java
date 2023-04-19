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
@Table(name = "transaction_types")
public class TransactionType {
    @Column(name = "transaction_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "transaction_type", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String type;

    public TransactionType(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public TransactionType(String type) {
        this.type = type;
    }
}