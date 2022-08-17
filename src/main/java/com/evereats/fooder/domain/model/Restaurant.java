package com.evereats.fooder.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_restaurants")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "freight_tax", nullable = false)
    private BigDecimal freightTax;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;
}
