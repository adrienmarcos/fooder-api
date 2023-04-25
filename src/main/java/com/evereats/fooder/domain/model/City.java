package com.evereats.fooder.domain.model;

import com.evereats.fooder.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_city")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class City {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Valid
    @NotNull
    @ConvertGroup(to = Groups.StateID.class)
    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    private State state;
}
