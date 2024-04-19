package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
}
