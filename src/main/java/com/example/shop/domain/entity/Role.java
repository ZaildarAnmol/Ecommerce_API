package com.example.shop.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name; // e.g. ROLE_USER, ROLE_ADMIN
}