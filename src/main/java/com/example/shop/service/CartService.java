package com.example.shop.service;

import com.example.shop.domain.entity.*;
import com.example.shop.domain.repository.*;
import com.example.shop.dto.cart.AddToCartRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private Cart getOrCreateCart(User user) {
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return cartRepository.save(c);
        });
    }
    @Transactional
    public Cart addItem(String userEmail, AddToCartRequest req) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Cart cart = getOrCreateCart(user);
        Product p = productRepository.findById(req.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        CartItem existing = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(p.getId()))
                .findFirst().orElse(null);
        if (existing == null) {
            existing = new CartItem();
            existing.setCart(cart);
            existing.setProduct(p);
            existing.setQuantity(req.quantity());
            cart.getItems().add(existing);
        } else {
            existing.setQuantity(existing.getQuantity() + req.quantity());
        }
        return cartRepository.save(cart);
    }
    public Cart getCart(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return cartRepository.findByUser(user).orElseGet(() -> {
            Cart c = new Cart();
            c.setUser(user);
            return c;
        });
    }
}