package org.NAK.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "teams")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @OneToMany(mappedBy = "team")
    private List<Cyclist> cyclists;
}