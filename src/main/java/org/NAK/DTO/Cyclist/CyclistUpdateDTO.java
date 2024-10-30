package org.NAK.DTO.Cyclist;

import jakarta.validation.constraints.NotNull;

public class CyclistUpdateDTO {

    @NotNull
    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private String nationality;
    private Long teamId;
}
