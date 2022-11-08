package com.evereats.fooder.domain.model;

import com.evereats.fooder.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_state")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class State {

    @Id
    @NotNull(groups = Groups.StateID.class)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;
}
