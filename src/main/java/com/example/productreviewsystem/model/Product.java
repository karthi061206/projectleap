package com.example.productreviewsystem.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name = "Untitled Product";

    @Column(length = 1000)
    private String description = "No description provided.";

    @Column(length = 1000)
    private String imageUrl = "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80";

    private String category = "Tech Gadgets";

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public Product() {}

    public Product(String name, String description, String imageUrl, String category) {
        this.name = (name != null && !name.isBlank()) ? name.trim() : "Untitled Product";
        this.description = (description != null && !description.isBlank()) ? description.trim() : "No description provided.";
        this.imageUrl = (imageUrl != null && !imageUrl.isBlank()) ? imageUrl.trim() : "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80";
        this.category = (category != null && !category.isBlank()) ? category.trim() : "Tech Gadgets";
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
    public String getName() { return name != null ? name : "Untitled Product"; }
    public void setName(String name) { this.name = (name != null && !name.isBlank()) ? name.trim() : "Untitled Product"; }
    public String getDescription() { return description != null ? description : "No description provided."; }
    public void setDescription(String description) { this.description = (description != null && !description.isBlank()) ? description.trim() : "No description provided."; }
    public String getImageUrl() { return (imageUrl != null && !imageUrl.isBlank()) ? imageUrl : "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80"; }
    public void setImageUrl(String imageUrl) { this.imageUrl = (imageUrl != null && !imageUrl.isBlank()) ? imageUrl.trim() : "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80"; }
    public String getCategory() { return (category != null && !category.isBlank()) ? category : "Tech Gadgets"; }
    public void setCategory(String category) { this.category = (category != null && !category.isBlank()) ? category.trim() : "Tech Gadgets"; }
    public List<Review> getReviews() { return reviews != null ? reviews : new ArrayList<>(); }
    public void setReviews(List<Review> reviews) { this.reviews = reviews != null ? reviews : new ArrayList<>(); }
}
