package com.example.demo.service;

import com.example.demo.model.User;

public interface AuthService {

    User authenticate(String email, String password);
}
