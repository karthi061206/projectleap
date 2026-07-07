package com.example.productreviewsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.productreviewsystem.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {}
