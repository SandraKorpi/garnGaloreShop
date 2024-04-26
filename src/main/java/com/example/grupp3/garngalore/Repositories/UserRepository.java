package com.example.grupp3.garngalore.Repositories;

import com.example.grupp3.garngalore.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
