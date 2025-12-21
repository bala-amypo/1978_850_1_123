package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.SkillCategory;
import com.example.demo.repository.SkillCategoryRepository;
import com.example.demo.service.SkillCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillCategoryServiceImpl implements SkillCategoryService {

    private final SkillCategoryRepository skillCategoryRepository;

    public SkillCategoryServiceImpl(SkillCategoryRepository skillCategoryRepository) {
        this.skillCategoryRepository = skillCategoryRepository;
    }

    @Override
    public SkillCategory createSkillCategory(SkillCategory category) {
        return skillCategoryRepository.save(category);
    }

    @Override
    public SkillCategory updateSkillCategory(Long id, SkillCategory category) {
        SkillCategory existing = getSkillCategoryById(id);
        existing.setCategoryName(category.getCategoryName());
        existing.setDescription(category.getDescription());
        return skillCategoryRepository.save(existing);
    }

    @Override
    public SkillCategory getSkillCategoryById(Long id) {
        return skillCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SkillCategory not found with id: " + id));
    }

    @Override
    public List<SkillCategory> getAllSkillCategories() {
        return skillCategoryRepository.findAll();
    }

    @Override
    public void deleteSkillCategory(Long id) {
        SkillCategory existing = getSkillCategoryById(id);
        skillCategoryRepository.delete(existing);
    }
}
