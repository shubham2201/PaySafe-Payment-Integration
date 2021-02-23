package com.example.PaysafeBackend.Service;

import com.example.PaysafeBackend.Domain.CreateUserResponse;
import com.example.PaysafeBackend.Entity.User;

public interface RegisterUserService {

    public String registerUser(User user);
}
