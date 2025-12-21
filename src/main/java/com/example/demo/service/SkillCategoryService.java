package com.example.demo.service;

import com.example.demo.model.SkillCategory;
import java.util.List;

public interface SkillCategoryService {
    SkillCategory createSkillCategory(SkillCategory category);
    SkillCategory updateSkillCategory(Long id, SkillCategory category);
    SkillCategory getSkillCategoryById(Long id);
    List<SkillCategory> getAllSkillCategories();
    void deleteSkillCategory(Long id);
}
