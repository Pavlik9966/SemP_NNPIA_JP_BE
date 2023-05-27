package semp.nnpia.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserOutputDto {
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 256)
    private String username;

    @NotBlank
    @NotNull
    @Size(max = 256)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 256)
    private String surname;

    @NotNull
    private LocalDate dateOfBirth;

    @NotBlank
    @NotNull
    @Size(max = 20)
    private String phone;

    @NotBlank
    @NotNull
    @Size(max = 100)
    @Email
    private String email;

    private AddressOutputDto address;

    @NotNull
    private LocalDateTime createdAt;
}