package com.example.shop.service;

import com.example.shop.domain.entity.*;
import com.example.shop.domain.repository.*;
import com.example.shop.dto.order.PlaceOrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order placeOrder(String userEmail, PlaceOrderRequest req) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Cart is empty"));

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);

        BigDecimal subtotal = BigDecimal.ZERO;
        for (CartItem ci : cart.getItems()) {
            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setUnitPrice(ci.getProduct().getPrice());
            oi.setLineTotal(ci.getProduct().getPrice()
                    .multiply(BigDecimal.valueOf(ci.getQuantity())));
            order.getItems().add(oi);
            subtotal = subtotal.add(oi.getLineTotal());
        }

        order.setSubtotal(subtotal);
        order.setShipping(new BigDecimal("0.00"));
        order.setTax(subtotal.multiply(new BigDecimal("0.07")));
        order.setTotal(order.getSubtotal().add(order.getTax()).add(order.getShipping()));
        order.setStatus(OrderStatus.PENDING);

        orderRepository.save(order);

        cart.getItems().clear();
        cartRepository.save(cart);

        return order;
    }

    public Page<Order> listForUser(String userEmail, Pageable pageable) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return orderRepository.findByUser(user, pageable);
    }
}
