package org.NAK.entities;


import org.NAK.entities.Embeded.GeneralResultsEmbd;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;


@Getter
@Setter
@Entity
@Table(name = "general_results")
public class GeneralResults{
    @EmbeddedId
    private GeneralResultsEmbd generalResults;

    @Column(name = "generalrank")
    private Integer generalrank;

    @Column(name = "generalduration")
    private Long generalduration;

    @MapsId("CompetitionId")
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Competition competition;

    @MapsId("CyclistId")
    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Cyclist cyclist;

}