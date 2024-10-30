package org.NAK.entities.Embeded;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Embeddable
@Getter
public class GeneralResultsEmbd {
    @Column(name = "cyclist_id")
    private Long CyclistId;
    @Column(name = "competition_id")
    private Long CompetitionId;
}
