package com.evereats.fooder.domain.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@JsonRootName("kitchen")
@Table(name = "tb_kitchens")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Kitchen {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}
