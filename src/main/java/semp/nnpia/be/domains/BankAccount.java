package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import semp.nnpia.be.dtos.BankAccountOutputDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "bank_accounts")
public class BankAccount {
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private User user;

    @Column(name = "account_number", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String accountNumber;

    @Column(name = "balance")
    @NotNull
    private Double balance = 0.0;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public BankAccountOutputDto toDto() {
        return new BankAccountOutputDto(
                getId(),
                getAccountNumber(),
                getBalance(),
                getCreatedAt()
        );
    }
}