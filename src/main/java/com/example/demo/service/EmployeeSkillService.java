package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import java.util.List;

public interface EmployeeSkillService {
    EmployeeSkill createEmployeeSkill(EmployeeSkill mapping);
    List<EmployeeSkill> getSkillsForEmployee(Long employeeId); // Use Long
    List<EmployeeSkill> getEmployeesBySkill(Long skillId); // Use Long
    void deactivateEmployeeSkill(Long id); // Use Long
}