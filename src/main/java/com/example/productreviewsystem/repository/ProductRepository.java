package com.example.productreviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productreviewsystem.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}
