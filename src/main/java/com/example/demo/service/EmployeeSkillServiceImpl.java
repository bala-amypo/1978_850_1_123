package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.EmployeeSkillService;
import com.example.demo.util.ProficiencyValidator;

import java.util.List;

public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository esRepo;
    private final EmployeeRepository eRepo;
    private final SkillRepository sRepo;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository esRepo,
                                    EmployeeRepository eRepo,
                                    SkillRepository sRepo) {
        this.esRepo = esRepo;
        this.eRepo = eRepo;
        this.sRepo = sRepo;
    }

    public EmployeeSkill createEmployeeSkill(EmployeeSkill es) {
        if (es.getYearsOfExperience() < 0)
            throw new IllegalArgumentException("Experience years");

        if (!ProficiencyValidator.isValid(es.getProficiencyLevel()))
            throw new IllegalArgumentException("Invalid proficiency");

        Employee e = eRepo.findById(es.getEmployee().getId()).orElseThrow();
        Skill s = sRepo.findById(es.getSkill().getId()).orElseThrow();

        if (!e.getActive())
            throw new IllegalArgumentException("inactive employee");

        if (!s.getActive())
            throw new IllegalArgumentException("inactive skill");

        return esRepo.save(es);
    }

    public void deactivateEmployeeSkill(Long id) {
        EmployeeSkill es = esRepo.findById(id).orElseThrow();
        es.setActive(false);
        esRepo.save(es);
    }

    public List<EmployeeSkill> getSkillsForEmployee(Long id) {
        return esRepo.findByEmployeeIdAndActiveTrue(id);
    }

    public List<EmployeeSkill> getEmployeesBySkill(Long id) {
        return esRepo.findBySkillIdAndActiveTrue(id);
    }
}
