package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee createEmployee(Employee e) {
        return repo.save(e);
    }

    public Employee updateEmployee(Long id, Employee update) {
        Employee e = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        e.setFullName(update.getFullName());
        e.setEmail(update.getEmail());
        return repo.save(e);
    }

    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public void deactivateEmployee(Long id) {
        Employee e = getEmployeeById(id);
        e.setActive(false);
        repo.save(e);
    }
}
