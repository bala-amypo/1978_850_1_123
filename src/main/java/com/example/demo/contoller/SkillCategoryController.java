package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/skill-categories")
public class SkillCategoryController {

    private final SkillCategoryService service;

    public SkillCategoryController(SkillCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SkillCategory> create(@RequestBody SkillCategory skillCategory) {
        return ResponseEntity.ok(service.createSkillCategory(skillCategory));
    }

    @GetMapping
    public ResponseEntity<List<SkillCategory>> getAll() {
        return ResponseEntity.ok(service.getAllSkillCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillCategory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSkillCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillCategory> update(@PathVariable Long id, @RequestBody SkillCategory skillCategory) {
        return ResponseEntity.ok(service.updateSkillCategory(id, skillCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteSkillCategory(id);
        return ResponseEntity.ok().build();
    }
}