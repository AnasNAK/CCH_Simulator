package org.NAK.entities;


import org.NAK.entities.Embeded.ResultsStage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stage_results")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StagesResults {

    @EmbeddedId
    private ResultsStage resultsStage;

    @NotNull
    @Positive
    private Integer position;


    private Long duration;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @MapsId("cyclistId")
    private Cyclist cyclist;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @MapsId("stageId")
    private Stage stage;

}