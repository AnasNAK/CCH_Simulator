package org.NAK.DTO.Competition;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer year;

    @NotNull
    private String lieu;

    @NotNull
    private Double distance;

    @NotNull
    private String startDate;

    @NotNull
    private String endDate;

}
