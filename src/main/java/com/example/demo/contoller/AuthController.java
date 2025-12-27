package com.example.demo.controller;

import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenProvider jwt;

    public AuthController(JwtTokenProvider jwt) {
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestParam Long userId,
            @RequestParam String email,
            @RequestParam String role) {

        String token = jwt.generateToken(userId, email, role);
        return Map.of("token", token);
    }
}
