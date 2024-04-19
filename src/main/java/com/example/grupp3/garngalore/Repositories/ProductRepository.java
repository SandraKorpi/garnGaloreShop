package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, Long> {
}
