package com.example.shop.dto.payment;

public record CreatePaymentResponse(String clientSecret, String paymentIntentId) {}
