package com.example.productreviewsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.productreviewsystem.model.*;
import com.example.productreviewsystem.service.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/")
    public String viewProducts(@RequestParam(required = false) String category,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(required = false, defaultValue = "rating") String sort,
                               Model model) {
        List<Product> products = productService.getProducts(category, keyword, sort);

        model.addAttribute("products", products);
        model.addAttribute("selectedCategory", (category == null || category.isBlank()) ? "All" : category.trim());
        model.addAttribute("keyword", (keyword == null) ? "" : keyword.trim());
        model.addAttribute("currentSort", sort);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("review", new Review());
        return "product";
    }

    @PostMapping("/product/{productId}/reviews")
    public String addReview(@PathVariable Long productId, @ModelAttribute Review reviewRequest) {
        reviewService.addReview(productId, reviewRequest);
        return "redirect:/product/" + productId;
    }

    @PostMapping("/review/{reviewId}/helpful")
    public String helpfulUpvote(@PathVariable Long reviewId) {
        Review review = reviewService.upvoteHelpful(reviewId);
        return "redirect:/product/" + review.getProduct().getId();
    }

    @GetMapping("/add-product")
    public String showAddProductPage(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/";
    }
}
