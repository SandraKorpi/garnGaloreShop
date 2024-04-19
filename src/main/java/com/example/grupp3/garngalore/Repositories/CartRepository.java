package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, Long> {
}
