package org.NAK.DTO.Team;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TeamCreateDTO {

    @NotBlank
    private String name;
    @NotBlank
    private String country;
}
