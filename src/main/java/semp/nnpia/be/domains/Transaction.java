package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import semp.nnpia.be.dtos.TransactionOutputDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private User user;

    @JoinColumn(name = "account_id_recipient")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private BankAccount accountRecipient;

    @JoinColumn(name = "account_id_sender")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private BankAccount accountSender;

    @Column(name = "transaction_date")
    @NotNull
    private LocalDateTime transactionDate = LocalDateTime.now();

    @Column(name = "amount")
    @NotNull
    private Double amount = 0.0;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "description")
    @NotBlank
    @NotNull
    @Size(max = 256)
    private String description;

    public Transaction(User user, BankAccount accountRecipient, BankAccount accountSender, LocalDateTime transactionDate, Double amount, LocalDateTime createdAt, String description) {
        this.user = user;
        this.accountRecipient = accountRecipient;
        this.accountSender = accountSender;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.createdAt = createdAt;
        this.description = description;
    }

    public TransactionOutputDto toDto() {
        return new TransactionOutputDto(
                getUser().toTransactionUserOutputDto(),
                getAccountRecipient().getAccountNumber(),
                getAccountSender().getAccountNumber(),
                getTransactionDate(),
                getAmount(),
                getCreatedAt(),
                getDescription()
        );
    }
}