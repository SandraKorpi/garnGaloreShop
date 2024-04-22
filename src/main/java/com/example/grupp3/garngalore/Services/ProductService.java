package com.example.grupp3.garngalore.Services;

import com.example.grupp3.garngalore.Models.Product;
import com.example.grupp3.garngalore.Repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void createUser(Product product) {
        productRepository.save(product);
    }

    public Product getUserById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new EntityNotFoundException("Produkt med ID " + id + " hittades inte.");
        }
    }

    public void deleteProduct(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Produkt med ID " + id + " finns inte i databasen.");
        }
    }

    public void updateProduct(String id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException("Produkt med ID " + id + " finns inte i databasen.");
        }
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
