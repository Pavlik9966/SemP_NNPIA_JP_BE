package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @JoinColumn(name = "account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private BankAccount account;

    /*@JoinColumn(name = "card_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private CreditCard card;*/

    @Column(name = "transaction_date")
    @NotNull
    private LocalDateTime transactionDate = LocalDateTime.now();

    /*@JoinColumn(name = "transaction_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private TransactionType type;*/

    @Column(name = "amount")
    @NotNull
    private Double amount = 0.0;

    /*@Column(name = "balance")
    @NotNull
    private Double balance = 0.0;*/

    /*@JoinColumn(name = "currency_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Currency currency;*/

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public Transaction(Long id, LocalDateTime transactionDate, Double amount/*, Double balance*/, LocalDateTime createdAt) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.amount = amount;
        /*this.balance = balance;*/
        this.createdAt = createdAt;
    }

    public Transaction(LocalDateTime transactionDate, Double amount/*, Double balance*/, LocalDateTime createdAt) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        /*this.balance = balance;*/
        this.createdAt = createdAt;
    }
}