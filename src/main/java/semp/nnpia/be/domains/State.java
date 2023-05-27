package semp.nnpia.be.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import semp.nnpia.be.dtos.StateOutputDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@NoArgsConstructor
@Table(name = "states")
public class State {
    @Column(name = "state_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "state_name", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name = "state_shortcut", unique = true)
    @NotBlank
    @NotNull
    @Size(max = 8)
    private String shortcut;

    public State(String name, String shortcut) {
        this.name = name;
        this.shortcut = shortcut;
    }

    public StateOutputDto toDto() {
        return new StateOutputDto(
                getName(),
                getShortcut()
        );
    }
}