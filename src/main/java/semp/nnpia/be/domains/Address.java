package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import semp.nnpia.be.dtos.AddressOutputDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "street_address")
    @NotBlank
    @NotNull
    @Size(max = 200)
    private String streetAddress;

    @Column(name = "city")
    @NotBlank
    @NotNull
    @Size(max = 100)
    private String city;

    @Column(name = "zip_code", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 20)
    private String zipCode;

    @JoinColumn(name = "state_id", referencedColumnName = "state_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private State state;

    public AddressOutputDto toDto() {
        return new AddressOutputDto(
                getStreetAddress(),
                getCity(),
                getZipCode(),
                getState().toDto()
        );
    }
}