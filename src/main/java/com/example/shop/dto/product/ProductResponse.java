package com.example.shop.dto.product;

import java.math.BigDecimal;
public record ProductResponse(
        Long id, String name, String description, BigDecimal price, Integer stock,
        String sku, String imageUrl, String category
) {}