package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.EmployeeSkill;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.service.EmployeeSkillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeSkillServiceImpl implements EmployeeSkillService {

    private final EmployeeSkillRepository employeeSkillRepository;

    public EmployeeSkillServiceImpl(EmployeeSkillRepository employeeSkillRepository) {
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill) {
        return employeeSkillRepository.save(employeeSkill);
    }

    @Override
    public EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill employeeSkill) {
        EmployeeSkill existing = getEmployeeSkillById(id);
        existing.setProficiencyLevel(employeeSkill.getProficiencyLevel());
        existing.setYearsOfExperience(employeeSkill.getYearsOfExperience());
        return employeeSkillRepository.save(existing);
    }

    @Override
    public EmployeeSkill getEmployeeSkillById(Long id) {
        return employeeSkillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeSkill not found with id: " + id));
    }

    @Override
    public List<EmployeeSkill> getAllEmployeeSkills() {
        return employeeSkillRepository.findAll();
    }

    @Override
    public void deleteEmployeeSkill(Long id) {
        EmployeeSkill existing = getEmployeeSkillById(id);
        employeeSkillRepository.delete(existing);
    }
}
