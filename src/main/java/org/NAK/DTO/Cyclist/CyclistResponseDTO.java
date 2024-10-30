package org.NAK.DTO.Cyclist;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.NAK.DTO.Team.TeamCreateDTO;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CyclistResponseDTO {

    private Long id;

    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String nationality;
    @NotBlank
    private int age;
    private TeamCreateDTO team;
}


