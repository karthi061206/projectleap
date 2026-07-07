package com.example.productreviewsystem.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username = "Anonymous Reviewer";

    @Column(nullable = false, length = 1000)
    private String comment = "No comment provided.";

    @Column(nullable = false)
    private int rating = 5;

    private LocalDate createdAt = LocalDate.now();

    private int helpfulCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {}

    public Review(String username, String comment, int rating, Product product) {
        this.username = (username != null && !username.isBlank()) ? username.trim() : "Anonymous Reviewer";
        this.comment = (comment != null && !comment.isBlank()) ? comment.trim() : "No comment provided.";
        this.rating = Math.max(1, Math.min(5, rating));
        this.product = product;
        this.createdAt = LocalDate.now();
        this.helpfulCount = 0;
    }

    public Review(String username, String comment, int rating, Product product, LocalDate createdAt, int helpfulCount) {
        this.username = (username != null && !username.isBlank()) ? username.trim() : "Anonymous Reviewer";
        this.comment = (comment != null && !comment.isBlank()) ? comment.trim() : "No comment provided.";
        this.rating = Math.max(1, Math.min(5, rating));
        this.product = product;
        this.createdAt = createdAt != null ? createdAt : LocalDate.now();
        this.helpfulCount = Math.max(0, helpfulCount);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username != null ? username : "Anonymous Reviewer"; }
    public void setUsername(String username) { this.username = (username != null && !username.isBlank()) ? username.trim() : "Anonymous Reviewer"; }
    public String getComment() { return comment != null ? comment : "No comment provided."; }
    public void setComment(String comment) { this.comment = (comment != null && !comment.isBlank()) ? comment.trim() : "No comment provided."; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = Math.max(1, Math.min(5, rating)); }
    public LocalDate getCreatedAt() { return createdAt != null ? createdAt : LocalDate.now(); }
    public void setCreatedAt(LocalDate createdAt) { this.createdAt = createdAt != null ? createdAt : LocalDate.now(); }
    public int getHelpfulCount() { return helpfulCount; }
    public void setHelpfulCount(int helpfulCount) { this.helpfulCount = Math.max(0, helpfulCount); }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
