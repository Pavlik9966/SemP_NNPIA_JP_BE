package semp.nnpia.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressOutputDto {
    @NotBlank
    @NotNull
    @Size(max = 200)
    private String streetAddress;

    @NotBlank
    @NotNull
    @Size(max = 100)
    private String city;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String zipCode;

    private StateOutputDto state;
}