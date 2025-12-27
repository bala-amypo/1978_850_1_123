package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.service.EmployeeSkillService;
import com.example.demo.util.ProficiencyValidator;

import java.util.List;

public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository,
                                    EmployeeRepository employeeRepository,
                                    SkillRepository skillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill es) {

        if (es.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative");
        }

        if (!ProficiencyValidator.isValid(es.getProficiencyLevel())) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        Employee emp = employeeRepository.findById(es.getEmployee().getId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        if (!emp.getActive()) throw new IllegalArgumentException("inactive employee");

        Skill skill = skillRepository.findById(es.getSkill().getId())
                .orElseThrow(() -> new IllegalArgumentException("Skill not found"));
        if (!skill.getActive()) throw new IllegalArgumentException("inactive skill");

        return employeeSkillRepository.save(es);
    }

    @Overrid
