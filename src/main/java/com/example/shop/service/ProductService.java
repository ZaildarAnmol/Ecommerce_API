package com.example.shop.service;

import com.example.shop.domain.entity.Category;
import com.example.shop.domain.entity.Product;
import com.example.shop.domain.repository.CategoryRepository;
import com.example.shop.domain.repository.ProductRepository;
import com.example.shop.dto.product.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Transactional
    public Product create(ProductCreateRequest req) {
        Product p = Product.builder()
                .name(req.name()).description(req.description())
                .price(req.price()).stock(req.stock())
                .sku(req.sku()).imageUrl(req.imageUrl())
                .build();
        if (req.categoryId() != null) {
            Category c = categoryRepository.findById(req.categoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            p.setCategory(c);
        }
        return productRepository.save(p);
    }
    public Page<Product> list(String q, Pageable pageable) {
        if (q == null || q.isBlank()) return productRepository.findAll(pageable);
        return productRepository.findByNameContainingIgnoreCase(q, pageable);
    }
    public Product get(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}