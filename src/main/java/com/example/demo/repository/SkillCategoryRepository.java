package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.SkillCategory;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
    // Add custom query methods here if needed
}
