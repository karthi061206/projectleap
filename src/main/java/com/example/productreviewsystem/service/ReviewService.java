package com.example.productreviewsystem.service;

import com.example.productreviewsystem.model.Product;
import com.example.productreviewsystem.model.Review;
import com.example.productreviewsystem.repository.ProductRepository;
import com.example.productreviewsystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductRepository productRepo;

    public Review addReview(Long productId, Review reviewRequest) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + productId));

        Review review = new Review();
        review.setUsername(reviewRequest.getUsername());
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setProduct(product);
        review.setCreatedAt(LocalDate.now());
        review.setHelpfulCount(0);

        return reviewRepo.save(review);
    }

    public Review upvoteHelpful(Long reviewId) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id: " + reviewId));
        review.setHelpfulCount(review.getHelpfulCount() + 1);
        return reviewRepo.save(review);
    }
}
