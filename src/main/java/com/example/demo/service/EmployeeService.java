package com.example.demo.service;

import com.example.demo.model.Employee;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long id, Employee employee); // Use Long
    Employee getEmployeeById(Long id); // Use Long
    List<Employee> getAllEmployees();
    void deactivateEmployee(Long id); // Use Long
}