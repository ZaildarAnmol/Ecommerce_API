package com.example.shop.controller;

import com.example.shop.domain.entity.Order;
import com.example.shop.dto.order.PlaceOrderRequest;
import com.example.shop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Order> place(Authentication auth, @Valid @RequestBody PlaceOrderRequest req) {
        String email = auth.getName();
        return ResponseEntity.ok(orderService.placeOrder(email, req));
    }
    @GetMapping
    public ResponseEntity<Page<Order>> list(Authentication auth,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size) {
        String email = auth.getName();
        return ResponseEntity.ok(orderService.listForUser(email, PageRequest.of(page, size)));
    }
}