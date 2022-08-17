package com.evereats.fooder.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_payment_methods")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentMethod {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;
}
