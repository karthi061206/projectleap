package com.example.productreviewsystem.config;

import com.example.productreviewsystem.model.Product;
import com.example.productreviewsystem.model.Review;
import com.example.productreviewsystem.repository.ProductRepository;
import com.example.productreviewsystem.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;

    public DataInitializer(ProductRepository productRepo, ReviewRepository reviewRepo) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productRepo.count() == 0) {
            Product p1 = new Product("Sony WH-1000XM5 Headphones", "Industry-leading noise cancellation with 30-hour battery life and ultra-comfortable lightweight design.");
            Product p2 = new Product("MacBook Air M3", "Supercharged by Apple M3 chip. 13.6-inch Liquid Retina display, 8GB Unified Memory, 256GB SSD Storage.");
            Product p3 = new Product("Logitech MX Master 3S", "Wireless performance mouse with 8000 DPI track-on-glass sensor and quiet clicks for ergonomic productivity.");

            productRepo.saveAll(List.of(p1, p2, p3));

            Review r1 = new Review("Alex Rivera", "Best headphones I've ever owned! Noise cancellation is magical on flights.", 5, p1);
            Review r2 = new Review("Sarah Chen", "Very comfortable for long work sessions, battery lasts forever.", 5, p1);
            Review r3 = new Review("David K.", "The M3 chip is blisteringly fast. Silent operation and incredible battery life.", 5, p2);
            Review r4 = new Review("Priya Patel", "Ergonomics are top-notch. The electromagnetic scroll wheel is a game changer.", 5, p3);

            reviewRepo.saveAll(List.of(r1, r2, r3, r4));
        }
    }
}
