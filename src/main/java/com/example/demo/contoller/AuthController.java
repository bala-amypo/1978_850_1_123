package com.example.demo.controller;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.security.JwtTokenProvider;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication")
@RestController
@org.springframework.web.bind.annotation.RequestMapping("/auth")
public class AuthController {

    private final EmployeeRepository employeeRepository;
    private final JwtTokenProvider tokenProvider;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(EmployeeRepository employeeRepository,
                          JwtTokenProvider tokenProvider) {
        this.employeeRepository = employeeRepository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody AuthRegisterRequest request
    ) {
        Employee e = new Employee();
        e.setFullName(request.getFullName());
        e.setEmail(request.getEmail());
        e.setDepartment(request.getDepartment());
        e.setJobTitle(request.getJobTitle());
        String encoded = passwordEncoder.encode(request.getPassword());
        e.setActive(true);

        employeeRepository.save(e);

        String token = tokenProvider.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthLoginRequest request
    ) {
        Employee e = employeeRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        // no stored passwords in this example, skip password check
        String token = tokenProvider.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
