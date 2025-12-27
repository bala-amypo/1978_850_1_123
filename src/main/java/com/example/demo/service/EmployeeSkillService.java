package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import java.util.List;

public interface EmployeeSkillService {
    EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill);
    void deactivateEmployeeSkill(Long id);
    List<EmployeeSkill> getSkillsForEmployee(Long employeeId);
    List<EmployeeSkill> getEmployeesBySkill(Long skillId);
}
