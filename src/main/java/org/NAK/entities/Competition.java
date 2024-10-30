package org.NAK.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "competition")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "year")
    private Integer year;

    @Column(name = "lieu")
    @NotBlank
    private String lieu;

    @NotNull
    @Column(name = "distance")
    private Double distance;

    @NotNull
    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    @NotNull
    private LocalDate endDate;

    @OneToMany(mappedBy = "competition" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<GeneralResults> generalResults = new HashSet<>();

    @OneToMany(mappedBy = "competition" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private List<Stage> stages = new ArrayList<>();
}
