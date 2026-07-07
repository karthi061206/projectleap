package com.example.productreviewsystem.config;

import com.example.productreviewsystem.model.Product;
import com.example.productreviewsystem.model.Review;
import com.example.productreviewsystem.repository.ProductRepository;
import com.example.productreviewsystem.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
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
            Product p1 = new Product(
                "Sony WH-1000XM5 Wireless Headphones", 
                "Industry-leading noise cancellation with two processors and 8 microphones for unprecedented call quality and 30-hour battery life.", 
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=800&auto=format&fit=crop&q=80",
                "Audio"
            );
            Product p2 = new Product(
                "MacBook Air 13-inch M3", 
                "Supercharged by Apple M3 chip. Strikingly thin design, 13.6-inch Liquid Retina display, 8GB Unified Memory, and 256GB SSD Storage.", 
                "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?w=800&auto=format&fit=crop&q=80",
                "Computers"
            );
            Product p3 = new Product(
                "Logitech MX Master 3S Mouse", 
                "Wireless performance mouse with 8000 DPI track-on-glass sensor, Quiet Clicks, and ergonomic MagSpeed scrolling.", 
                "https://images.unsplash.com/photo-1615663245857-ac93bb7c39e7?w=800&auto=format&fit=crop&q=80",
                "Peripherals"
            );
            Product p4 = new Product(
                "Sony Alpha a7 IV Mirrorless Camera",
                "Breathtaking 33MP full-frame Exmor R CMOS sensor, 4K 60p 10-bit video recording, and real-time autofocus tracking.",
                "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=800&auto=format&fit=crop&q=80",
                "Photography"
            );
            Product p5 = new Product(
                "Apple iPad Pro 13-inch (M4)",
                "Ultra-thin design with Ultra Retina XDR OLED display, blistering M4 performance, and Apple Pencil Pro support.",
                "https://images.unsplash.com/photo-1544244015-0df4b3ffc6b0?w=800&auto=format&fit=crop&q=80",
                "Tablets"
            );
            Product p6 = new Product(
                "Keychron Q1 Pro Mechanical Keyboard",
                "QMK/VIA wireless custom mechanical keyboard with full aluminum CNC machined body and hot-swappable switches.",
                "https://images.unsplash.com/photo-1587829741301-dc798b83add3?w=800&auto=format&fit=crop&q=80",
                "Peripherals"
            );

            productRepo.saveAll(List.of(p1, p2, p3, p4, p5, p6));

            Review r1 = new Review("Alex Rivera", "Best headphones I've ever owned! Noise cancellation is magical on flights.", 5, p1, LocalDate.now().minusDays(5), 14);
            Review r2 = new Review("Sarah Chen", "Very comfortable for long work sessions, battery lasts forever.", 5, p1, LocalDate.now().minusDays(3), 8);
            Review r3 = new Review("David K.", "The M3 chip is blisteringly fast. Silent operation and incredible battery life.", 5, p2, LocalDate.now().minusDays(10), 23);
            Review r4 = new Review("Priya Patel", "Ergonomics are top-notch. The electromagnetic scroll wheel is a game changer.", 5, p3, LocalDate.now().minusDays(2), 5);
            Review r5 = new Review("Marcus Brody", "Autofocus is unreal! Perfect hybrid camera for both photos and 4K video.", 5, p4, LocalDate.now().minusDays(7), 19);
            Review r6 = new Review("Elena Rostova", "The OLED screen is jaw-dropping. Best tablet for digital artists.", 5, p5, LocalDate.now().minusDays(1), 11);

            reviewRepo.saveAll(List.of(r1, r2, r3, r4, r5, r6));
        }
    }
}
