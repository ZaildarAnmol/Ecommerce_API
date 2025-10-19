package com.example.shop.controller;

import com.example.shop.domain.entity.Cart;
import com.example.shop.dto.cart.AddToCartRequest;
import com.example.shop.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/items")
    public ResponseEntity<Cart> addItem(Authentication auth, @Valid @RequestBody AddToCartRequest req) {
        String email = auth.getName();
        return ResponseEntity.ok(cartService.addItem(email, req));
    }
    @GetMapping
    public ResponseEntity<Cart> get(Authentication auth) {
        String email = auth.getName();
        return ResponseEntity.ok(cartService.getCart(email));
    }
}