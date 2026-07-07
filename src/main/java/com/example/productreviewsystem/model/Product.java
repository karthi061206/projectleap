package com.example.productreviewsystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String imageUrl;

    private String category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Product() {}

    public Product(String name, String description, String imageUrl, String category) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    // Helper methods for clean UI display
    public double getAverageRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }
        double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
        return Math.round(avg * 10.0) / 10.0;
    }

    public int getReviewCount() {
        return reviews == null ? 0 : reviews.size();
    }

    public int getRatingCount(int stars) {
        if (reviews == null || reviews.isEmpty()) return 0;
        return (int) reviews.stream().filter(r -> r.getRating() == stars).count();
    }

    public int getRatingPercentage(int stars) {
        int total = getReviewCount();
        if (total == 0) return 0;
        return (int) Math.round((double) getRatingCount(stars) / total * 100.0);
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl != null && !imageUrl.isBlank() ? imageUrl : "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80"; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getCategory() { return category != null && !category.isBlank() ? category : "Tech Gadgets"; }
    public void setCategory(String category) { this.category = category; }
    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }
}
