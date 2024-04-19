package com.example.grupp3.garngalore.config;

import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Autowired
    public ProductSeeder(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Seed product data
        Product product1 = new Product();
        product1.setName("Lila ekologiskt garn");
        product1.setPrice(100);
        product1.setDescription("Ekologiskt garn i lila färg.");
        product1.setCategory("Ekologiskt garn");
        product1.setQuantity(10);
        product1.setBild("to be set");
        product1.setColor("Lila");

        Product product2 = new Product();
        product1.setName("Blå ekologiskt garn");
        product1.setPrice(100);
        product1.setDescription("Ekologiskt garn i blå färg.");
        product1.setCategory("Ekologiskt garn");
        product1.setQuantity(10);
        product1.setBild("to be set");
        product1.setColor("Blå");

        Product product3 = new Product();
        product1.setName("Gult självlysande garn");
        product1.setPrice(150);
        product1.setDescription("Självlysande garn i gul färg.");
        product1.setCategory("Självlysande garn");
        product1.setQuantity(10);
        product1.setBild("to be set");
        product1.setColor("Gul");

        Product product4 = new Product();
        product1.setName("Rosa självlysande garn");
        product1.setPrice(150);
        product1.setDescription("Självlysande garn i rosa färg.");
        product1.setCategory("Självlysande garn");
        product1.setQuantity(10);
        product1.setBild("to be set");
        product1.setColor("Rosa");

        Product product5 = new Product();
        product1.setName("Blått handgjort garn");
        product1.setPrice(140);
        product1.setDescription("Handgjort garn i blå färg.");
        product1.setCategory("Handgjort garn");
        product1.setQuantity(10);
        product1.setBild("to be set");
        product1.setColor("Blå");

        // Save products to the database
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
    }
}