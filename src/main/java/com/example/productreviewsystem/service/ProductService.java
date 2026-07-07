package com.example.productreviewsystem.service;

import com.example.productreviewsystem.model.Product;
import com.example.productreviewsystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    public List<Product> getProducts(String category, String keyword, String sort) {
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
        return products;
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
    }

    @Transactional
    public Product saveProduct(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            product.setName("Untitled Product");
        }
        if (product.getCategory() == null || product.getCategory().isBlank()) {
            product.setCategory("Other");
        }
        if (product.getImageUrl() == null || product.getImageUrl().isBlank()) {
            product.setImageUrl("https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80");
        }
        return productRepo.save(product);
    }
}
