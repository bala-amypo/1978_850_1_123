package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skill_categories")
public class SkillCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String categoryName;
    private boolean active = true;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public boolean getActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
