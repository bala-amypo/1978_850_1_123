package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employee-skills")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeSkill> create(@RequestBody EmployeeSkill employeeSkill) {
        return ResponseEntity.ok(service.createEmployeeSkill(employeeSkill));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeSkill>> getAll() {
        return ResponseEntity.ok(service.getAllEmployeeSkills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSkill> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeSkillById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkill> update(@PathVariable Long id, @RequestBody EmployeeSkill employeeSkill) {
        return ResponseEntity.ok(service.updateEmployeeSkill(id, employeeSkill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteEmployeeSkill(id);
        return ResponseEntity.ok().build();
    }
}