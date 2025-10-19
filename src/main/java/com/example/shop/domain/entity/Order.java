package com.example.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.*;
@Entity
@Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order extends BaseEntity {
    @ManyToOne @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();
    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;
    @Column(precision = 10, scale = 2)
    private BigDecimal shipping;
    @Column(precision = 10, scale = 2)
    private BigDecimal tax;
    @Column(precision = 10, scale = 2)
    private BigDecimal total;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}