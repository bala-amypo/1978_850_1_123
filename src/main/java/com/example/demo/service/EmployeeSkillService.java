package com.example.demo.service;

import com.example.demo.model.EmployeeSkill;
import java.util.List;

public interface EmployeeSkillService {
    EmployeeSkill createEmployeeSkill(EmployeeSkill employeeSkill);
    EmployeeSkill updateEmployeeSkill(Long id, EmployeeSkill employeeSkill);
    EmployeeSkill getEmployeeSkillById(Long id);
    List<EmployeeSkill> getAllEmployeeSkills();
    void deleteEmployeeSkill(Long id);
}
