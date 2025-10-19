package com.example.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@Table(name = "order_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderItem extends BaseEntity {
    @ManyToOne @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(precision = 10, scale = 2)
    private BigDecimal unitPrice;
    @Column(precision = 10, scale = 2)
    private BigDecimal lineTotal;
}