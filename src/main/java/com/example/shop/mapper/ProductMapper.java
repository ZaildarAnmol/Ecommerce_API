package com.example.shop.mapper;

import com.example.shop.domain.entity.Product;
import com.example.shop.dto.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(
            target = "category",
            expression = "java(p.getCategory() != null ? p.getCategory().getName() : null)"
    )
    ProductResponse toDto(Product p);
}