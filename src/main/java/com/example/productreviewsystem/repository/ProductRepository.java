package com.example.productreviewsystem.repository;

import com.example.productreviewsystem.model.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    @EntityGraph(attributePaths = "reviews")
    List<Product> findAll();

    @Override
    @EntityGraph(attributePaths = "reviews")
    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = "reviews")
    List<Product> findByCategoryIgnoreCase(String category);

    @EntityGraph(attributePaths = "reviews")
    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
