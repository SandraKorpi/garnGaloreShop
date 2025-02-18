package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);

    Optional<Cart> findByIpAddress(String ipAddress);
}
