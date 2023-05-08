package semp.nnpia.be.domains;

/*
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
@Table(name = "credit_cards")
public class CreditCard {
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @Column(name = "card_number", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String cardNumber;

    @JoinColumn(name = "card_type_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private CardType cardType;

    @Column(name = "credit_limit")
    @NotNull
    private Double creditLimit = 0.0;

    @Column(name = "balance")
    @NotNull
    private Double balance = 0.0;

    @JoinColumn(name = "currency_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Currency currency;

    @Column(name = "created_at")
    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public CreditCard(Long id, String cardNumber, Double creditLimit, Double balance, LocalDateTime createdAt) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public CreditCard(String cardNumber, Double creditLimit, Double balance, LocalDateTime createdAt) {
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = balance;
        this.createdAt = createdAt;
    }
}*/
