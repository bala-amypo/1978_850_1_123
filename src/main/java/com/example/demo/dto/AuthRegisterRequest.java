package com.example.demo.dto;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    private String fullName;
    private String email;
    private String department;
    private String jobTitle;
    private String password;
}
