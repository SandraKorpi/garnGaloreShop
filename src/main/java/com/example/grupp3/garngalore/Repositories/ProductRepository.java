package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
