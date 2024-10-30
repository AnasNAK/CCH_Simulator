package org.NAK.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "cyclist")
public class Cyclist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(name = "firstname")
    private String firstname;

    @NotBlank
    @Column(name = "lastname")
    private String lastname;

    @Positive
    @Column(name = "age")
    private int age;

    @Column(name = "nationality")
    @NotBlank
    private String nationality;

    @ManyToOne(fetch = FetchType.EAGER , optional = false)
    private Team team;

    @OneToMany(mappedBy = "cyclist" , fetch = FetchType.EAGER)
    private Set<GeneralResults> generalResults = new HashSet<>();

    @OneToMany(mappedBy = "cyclist" , fetch = FetchType.EAGER)
    private  Set<StagesResults> stagesResults = new HashSet<>();
}