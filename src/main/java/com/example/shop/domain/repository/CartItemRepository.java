package com.example.shop.domain.repository;

import com.example.shop.domain.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CartItemRepository extends JpaRepository<CartItem, Long> {}