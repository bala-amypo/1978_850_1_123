package com.example.demo.controller;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.service.EmployeeSkillService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "EmployeeSkill")
@RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/employee-skills")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeSkillController {

    private final EmployeeSkillService service;

    public EmployeeSkillController(EmployeeSkillService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeSkill> create(
            @RequestBody EmployeeSkill mapping
    ) {
        return ResponseEntity.ok(service.createEmployeeSkill(mapping));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSkill> update(@PathVariable Long id,
                                                @RequestBody EmployeeSkill mapping) {
        return ResponseEntity.ok(service.updateEmployeeSkill(id, mapping));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<EmployeeSkill>> getForEmployee(
            @PathVariable Long employeeId
    ) {
        return ResponseEntity.ok(service.getSkillsForEmployee(employeeId));
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<EmployeeSkill>> getForSkill(
            @PathVariable Long skillId
    ) {
        return ResponseEntity.ok(service.getEmployeesBySkill(skillId));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateEmployeeSkill(id);
        return ResponseEntity.ok().build();
    }
}
