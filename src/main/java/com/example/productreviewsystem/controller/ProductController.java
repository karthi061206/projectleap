package com.example.productreviewsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.productreviewsystem.model.*;
import com.example.productreviewsystem.repository.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @GetMapping("/")
    public String viewProducts(@RequestParam(required = false) String category,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "rating") String sort,
                               Model model) {
        List<Product> products;
        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productRepo.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword.trim(), keyword.trim());
        } else if (category != null && !category.trim().isEmpty() && !"All".equalsIgnoreCase(category)) {
            products = productRepo.findByCategoryIgnoreCase(category.trim());
        } else {
            products = productRepo.findAll();
        }

        if ("reviews".equalsIgnoreCase(sort)) {
            products.sort((p1, p2) -> Integer.compare(p2.getReviewCount(), p1.getReviewCount()));
        } else if ("name".equalsIgnoreCase(sort)) {
            products.sort((p1, p2) -> p1.getName().compareToIgnoreCase(p2.getName()));
        } else {
            // default: sort by rating descending
            products.sort((p1, p2) -> Double.compare(p2.getAverageRating(), p1.getAverageRating()));
        }

        model.addAttribute("products", products);
        model.addAttribute("selectedCategory", category == null ? "All" : category);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("currentSort", sort);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("review", new Review());
        return "product";
    }

    @PostMapping("/product/{productId}/reviews")
    public String addReview(@PathVariable Long productId, @ModelAttribute Review reviewRequest) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + productId));

        Review review = new Review();
        review.setUsername(reviewRequest.getUsername());
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setProduct(product);
        review.setCreatedAt(LocalDate.now());
        review.setHelpfulCount(0);

        reviewRepo.save(review);
        return "redirect:/product/" + productId;
    }

    @PostMapping("/review/{reviewId}/helpful")
    public String helpfulUpvote(@PathVariable Long reviewId) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid review Id:" + reviewId));
        review.setHelpfulCount(review.getHelpfulCount() + 1);
        reviewRepo.save(review);
        return "redirect:/product/" + review.getProduct().getId();
    }

    @GetMapping("/add-product")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productRepo.save(product);
        return "redirect:/";
    }
}
