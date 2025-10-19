package com.example.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category extends BaseEntity {
    @Column(unique = true, nullable = false)
    private String name;
}