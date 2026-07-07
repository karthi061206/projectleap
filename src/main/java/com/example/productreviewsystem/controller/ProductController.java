package com.example.productreviewsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.productreviewsystem.model.*;
import com.example.productreviewsystem.repository.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @GetMapping("/")
    public String viewProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productRepo.findById(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("review", new Review());
        return "product";
    }

    @PostMapping("/product/{productId}/reviews")
    public String addReview(@PathVariable Long productId, @ModelAttribute Review reviewRequest) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = new Review();
        review.setUsername(reviewRequest.getUsername());
        review.setComment(reviewRequest.getComment());
        review.setRating(reviewRequest.getRating());
        review.setProduct(product);

        // Save the review to the database
        reviewRepo.save(review);

        // Redirect back to the product page
        return "redirect:/product/" + productId;
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
