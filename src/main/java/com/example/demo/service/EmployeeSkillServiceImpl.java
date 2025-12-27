package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Skill;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.SkillRepository;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill) {
        Employee employee = employeeRepository.findById(employeeSkill.getEmployee().getId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        if (!employee.getActive()) {
            throw new IllegalArgumentException("Cannot assign skill to inactive employee");
        }

        Skill skill = skillRepository.findById(employeeSkill.getSkill().getId())
                .orElseThrow(() -> new RuntimeException("Skill not found"));
        if (!skill.getActive()) {
            throw new IllegalArgumentException("Cannot assign inactive skill");
        }

        if (employeeSkill.getYearsOfExperience() < 0) {
            throw new IllegalArgumentException("Experience years cannot be negative");
        }

        String[] validLevels = {"Beginner", "Intermediate", "Advanced", "Expert"};
        boolean validLevel = false;
        for (String level : validLevels) {
            if (level.equals(employeeSkill.getProficiencyLevel())) {
                validLevel = true;
                break;
            }
        }
        if (!validLevel) {
            throw new IllegalArgumentException("Invalid proficiency level");
        }

        return employeeSkillRepository.save(employeeSkill);
    }

    @Override
    public List<EmployeeSkill> getSkillsForEmployee(Long employeeId) {
        return employeeSkillRepository.findByEmployeeIdAndActiveTrue(employeeId);
    }

    @Override
    public List<EmployeeSkill> getEmployeesBySkill(Long skillId) {
        return employeeSkillRepository.findBySkillIdAndActiveTrue(skillId);
    }

    @Override
    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill employeeSkill = employeeSkillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EmployeeSkill not found"));
        employeeSkill.setActive(false);
        employeeSkillRepository.save(employeeSkill);
    }
}
