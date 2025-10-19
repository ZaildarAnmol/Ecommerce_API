package com.example.shop.dto.payment;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
public record CreatePaymentRequest(@NotNull Long orderId, @NotNull BigDecimal amount, String currency) {}
