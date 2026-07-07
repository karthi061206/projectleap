package com.example.productreviewsystem.model;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 1000)
    private String comment;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public Review() {}

    public Review(String username, String comment, Integer rating, Product product) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.product = product;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Integer getRating() { return rating != null ? rating : 0; }
    public void setRating(Integer rating) { this.rating = rating; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
