package com.example.grupp3.garngalore.config;

import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public ProductSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Check if products already exist
        List<Product> existingProducts = productRepository.findAll();

        // If no products exist, seed product data
        if (existingProducts.isEmpty()) {
            seedProducts();
        }
    }
    
    private void seedProducts() {
        // Seed product data
        Product product1 = new Product();
        product1.setName("Lila ekologiskt garn");
        product1.setPrice(100);
        product1.setDescription("Ekologiskt garn i lila färg.");
        product1.setCategory("Ekologiskt garn");
        product1.setQuantity(10);
        product1.setBild("/static/images/lila garn.png");
        product1.setColor("Lila");

        Product product2 = new Product();
        product1.setName("Grön ekologiskt garn");
        product1.setPrice(100);
        product1.setDescription("Ekologiskt garn i grön färg.");
        product1.setCategory("Ekologiskt garn");
        product1.setQuantity(10);
        product1.setBild("/static/images/grönt ullgarn.png");
        product1.setColor("Grön");

        Product product3 = new Product();
        product1.setName("Gult självlysande garn");
        product1.setPrice(150);
        product1.setDescription("Självlysande garn i gul färg.");
        product1.setCategory("Självlysande garn");
        product1.setQuantity(10);
        product1.setBild("/static/images/Gult självlysande garn.png");
        product1.setColor("Gul");

        Product product4 = new Product();
        product1.setName("Rosa självlysande garn");
        product1.setPrice(150);
        product1.setDescription("Självlysande garn i rosa färg.");
        product1.setCategory("Självlysande garn");
        product1.setQuantity(10);
        product1.setBild("/static/images/rosa självlysande garn.png");
        product1.setColor("Rosa");

        Product product5 = new Product();
        product1.setName("Blått handgjort ullgarn");
        product1.setPrice(200);
        product1.setDescription("Handgjort ullgarn i blå färg.");
        product1.setCategory("Handgjort ullgarn");
        product1.setQuantity(10);
        product1.setBild("/static/images/Blått ullgarn.jpg");
        product1.setColor("Blå");

        Product product6 = new Product();
        product1.setName("Gult handgjort ullgarn");
        product1.setPrice(200);
        product1.setDescription("Handgjort ullgarn i gul färg.");
        product1.setCategory("Handgjort ullgarn");
        product1.setQuantity(10);
        product1.setBild("/static/images/Gul ullgarn.jpg");
        product1.setColor("Gul");

        Product product7 = new Product();
        product1.setName("Grått Alpackagarn");
        product1.setPrice(250);
        product1.setDescription("Alpackagarn i grå färg.");
        product1.setCategory("Alpackagarn");
        product1.setQuantity(10);
        product1.setBild("/static/images/grå alpackagarn.jpg");
        product1.setColor("Grått");

        Product product8 = new Product();
        product1.setName("Vitt Alpackagarn");
        product1.setPrice(250);
        product1.setDescription("Alpackagarn i vit färg.");
        product1.setCategory("Alpackagarn");
        product1.setQuantity(10);
        product1.setBild("/static/images/Vitt Alpacka.jpg");
        product1.setColor("Vit");

        // Save products to the database
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);
    }
}