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
public class TransactionInputDto {
    private Long senderId;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String accountNumberRecipient;

    private Long accountIdSender;

    @NotNull
    private LocalDateTime transactionDate;

    @NotNull
    private Double amount;

    @NotNull
    private LocalDateTime createdAt;

    @NotBlank
    @NotNull
    @Size(max = 256)
    private String description;
}