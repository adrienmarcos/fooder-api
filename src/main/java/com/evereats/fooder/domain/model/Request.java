package com.evereats.fooder.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_request")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Request {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subtotal", nullable = false)
    private BigDecimal subTotal;

    @PositiveOrZero
    @Column(name = "freight_tax", nullable = false)
    private BigDecimal freightTax;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime creationDate;

    @Column(name = "confirmation_date", columnDefinition = "datetime")
    private LocalDateTime confirmationDate;

    @Column(name = "cancellation_date", columnDefinition = "datetime")
    private LocalDateTime cancellationDate;

    @Column(name = "delivery_date", columnDefinition = "datetime")
    private LocalDateTime deliveryDate;

    @Embedded
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_client_id", nullable = false)
    private User client;

    @OneToMany(mappedBy = "request")
    private List<OrderedItem> orderedItems = new ArrayList<>();
}
