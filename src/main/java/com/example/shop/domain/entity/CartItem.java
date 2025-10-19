package com.example.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "cart_items")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem extends BaseEntity {
    @ManyToOne @JoinColumn(name = "cart_id")
    private Cart cart;
    @ManyToOne @JoinColumn(name = "product_id")
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
}