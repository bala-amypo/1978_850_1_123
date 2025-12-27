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
    if (emp.getEmail() == null || emp.getEmail().isEmpty()) {
        throw new IllegalArgumentException("Email must not be empty");
    }
    return employeeRepository.save(emp);
}


    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee ex = getEmployeeById(id);
        ex.setFullName(employee.getFullName());
        ex.setEmail(employee.getEmail());
        return repo.save(ex);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
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
