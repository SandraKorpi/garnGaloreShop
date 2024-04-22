package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
