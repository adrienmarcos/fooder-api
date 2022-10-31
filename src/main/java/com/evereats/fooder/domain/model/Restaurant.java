package com.evereats.fooder.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_restaurant")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @DecimalMin("0")
    @Column(name = "freight_tax", nullable = false)
    private BigDecimal freightTax;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @JsonIgnore
    @Embedded
    private Address address;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "register_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime registerDate;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "last_update_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime lastUpdateDate;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "tb_restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products = new ArrayList<>();
}
