package org.NAK.entities.Embeded;


import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ResultsStage {
    private Long cyclistId;
    private Long stageId;
}