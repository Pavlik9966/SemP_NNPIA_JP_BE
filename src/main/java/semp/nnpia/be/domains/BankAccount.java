package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @Column(name = "account_number", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String accountNumber;

    @JoinColumn(name = "account_type_id", referencedColumnName = "account_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private AccountType accountType;

    @Column(name = "balance")
    @NotNull
    private Double balance = 0.0;

    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Currency currency;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public BankAccount(Long id, String accountNumber, Double balance, LocalDateTime createdAt) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public BankAccount(String accountNumber, Double balance, LocalDateTime createdAt) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.createdAt = createdAt;
    }
}