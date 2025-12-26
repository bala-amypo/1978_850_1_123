package com.example.demo.dto;

import lombok.Data;
import java.util.List;

@Data
public class EmployeeSearchRequest {
    private List<String> skills;
    private Long userId;
}
