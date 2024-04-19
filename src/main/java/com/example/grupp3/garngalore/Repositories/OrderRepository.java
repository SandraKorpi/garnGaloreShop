package com.example.grupp3.garngalore.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.grupp3.garngalore.Models.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
