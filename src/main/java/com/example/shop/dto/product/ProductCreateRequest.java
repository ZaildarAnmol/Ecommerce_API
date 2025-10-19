package com.example.shop.dto.product;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProductCreateRequest(
        @NotBlank String name,
        String description,
        @NotNull @DecimalMin("0.0") BigDecimal price,
        @NotNull @Min(0) Integer stock,
        Long categoryId,
        String sku,
        String imageUrl
) {}
