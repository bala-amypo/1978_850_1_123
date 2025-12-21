package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    private Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "skill_category_id")
    private SkillCategory skillCategory;

    @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL)
    private List<EmployeeSkill> employeeSkills;
}
