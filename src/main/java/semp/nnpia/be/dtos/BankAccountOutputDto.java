package semp.nnpia.be.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class BankAccountOutputDto {
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String accountNumber;

    @NotNull
    private Double balance;

    @NotNull
    private LocalDateTime createdAt;
}