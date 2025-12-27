package com.example.demo.service.impl;

import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;

import java.util.List;

public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository repository;

    public SkillCategoryServiceImpl(SkillCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SkillCategory createCategory(SkillCategory category) {
        return repository.save(category);
    }

    @Override
    public SkillCategory updateCategory(Long id, SkillCategory category) {
        SkillCategory existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setCategoryName(category.getCategoryName());
        return repository.save(existing);
    }

    @Override
    public List<SkillCategory> getAllCategories() {
        return repository.findAll();
    }
}
