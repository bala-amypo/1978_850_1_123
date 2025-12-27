package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeServiceImpl(EmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee createEmployee(Employee emp) {
        // Validation: email must not be null or empty
        if (emp.getEmail() == null || emp.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }
        return repo.save(emp);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setFullName(employee.getFullName());
        existing.setEmail(employee.getEmail());
        return repo.save(existing);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    @Override
    public void deactivateEmployee(Long id) {
        Employee e = getEmployeeById(id);
        e.setActive(false);
        repo.save(e);
    }
}
