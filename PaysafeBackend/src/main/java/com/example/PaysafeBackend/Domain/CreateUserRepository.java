package com.example.PaysafeBackend.Domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreateUserRepository extends MongoRepository<CreateUserResponse, String> {
    public CreateUserResponse findByEmail(String email);
}
