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
public class StateOutputDto {
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 8)
    private String shortcut;
}