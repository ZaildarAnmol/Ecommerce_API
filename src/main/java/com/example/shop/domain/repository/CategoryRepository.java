package com.example.shop.domain.repository;

import com.example.shop.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoryRepository extends JpaRepository<Category, Long> {}
