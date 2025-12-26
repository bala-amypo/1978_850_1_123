package com.example.demo.controller;

import com.example.demo.model.SkillCategory;
import com.example.demo.service.SkillCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "SkillCategory")
@RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/skill-categories")
@SecurityRequirement(name = "bearerAuth")
public class SkillCategoryController {

    private final SkillCategoryService service;

    public SkillCategoryController(SkillCategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SkillCategory> create(
            @RequestBody SkillCategory category
    ) {
        return ResponseEntity.ok(service.createCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SkillCategory> update(@PathVariable Long id,
                                                @RequestBody SkillCategory category) {
        return ResponseEntity.ok(service.updateCategory(id, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SkillCategory> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<SkillCategory>> list() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        service.deactivateCategory(id);
        return ResponseEntity.ok().build();
    }
}
