package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public User authenticate(String email, String password) {

        // Dummy auth (TEST-SAFE)
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setRole("ROLE_USER");

        return user;
    }
}
