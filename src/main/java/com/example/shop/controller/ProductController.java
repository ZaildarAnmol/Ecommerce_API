package com.example.shop.controller;

import com.example.shop.domain.entity.Product;
import com.example.shop.dto.product.*;
import com.example.shop.mapper.ProductMapper;
import com.example.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper mapper;

    @PostMapping("/admin")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductCreateRequest req) {
        Product p = productService.create(req);
        return ResponseEntity.ok(mapper.toDto(p));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> list(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<Product> result = productService.list(q, PageRequest.of(page, size));
        Page<ProductResponse> dto = result.map(mapper::toDto);
        return ResponseEntity.ok(dto);
    } // <-- close list() here

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.toDto(productService.get(id)));
    }
}
