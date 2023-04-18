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
@Table(name = "card_types")
public class CardType {
    @Column(name = "card_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "card_type", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String cardType;

    public CardType(Long id, String cardType) {
        this.id = id;
        this.cardType = cardType;
    }

    public CardType(String cardType) {
        this.cardType = cardType;
    }
}