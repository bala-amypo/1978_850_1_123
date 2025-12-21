package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    private String description;
    private Boolean active = true;

    @OneToMany(mappedBy = "skillCategory", cascade = CascadeType.ALL)
    private List<Skill> skills;
}
