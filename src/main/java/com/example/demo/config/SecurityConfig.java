package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(title = "Employee Skills Management API", version = "v1", description = "Basic CRUD API for managing employees and their skills"))
@Configuration
public class SwaggerConfig { }
