package semp.nnpia.be.domains;

/*
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "currencies")
public class Currency {
    @Column(name = "currency_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "currency_code", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 10)
    private String code;

    @Column(name = "name", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String name;

    @Column(name = "symbol")
    @NotBlank
    @NotNull
    @Size(max = 10)
    private String symbol;

    public Currency(Long id, String code, String name, String symbol) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public Currency(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }
}*/
