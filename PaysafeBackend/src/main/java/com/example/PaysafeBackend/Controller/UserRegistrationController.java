package com.example.PaysafeBackend.Controller;

import com.example.PaysafeBackend.Entity.User;
import com.example.PaysafeBackend.Service.RegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserRegistrationController {

    @Autowired
    private RegisterUserService registerUserService;

    @PostMapping(value = "/user/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String registerUser(@RequestBody User user ) {

        return registerUserService.registerUser(user);
    }
}
