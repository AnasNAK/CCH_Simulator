package org.NAK.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stage")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "stage_name")
    private String name;

    @ManyToOne
    private Competition competition;

    @OneToMany(mappedBy = "stage" , fetch = FetchType.EAGER)
    private Set<StagesResults> stagesResults = new HashSet<>();
}
