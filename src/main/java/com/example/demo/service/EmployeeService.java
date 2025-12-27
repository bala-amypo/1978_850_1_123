package com.example.demo.service;

import com.example.demo.model.*;
import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee e);
    Employee updateEmployee(Long id, Employee e);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    void deactivateEmployee(Long id);
}

public interface SkillService {
    Skill createSkill(Skill s);
    Skill updateSkill(Long id, Skill s);
    void deactivateSkill(Long id);
}

public interface SkillCategoryService {
    SkillCategory createCategory(SkillCategory c);
}

public interface EmployeeSkillService {
    EmployeeSkill createEmployeeSkill(EmployeeSkill es);
    void deactivateEmployeeSkill(Long id);
    List<EmployeeSkill> getSkillsForEmployee(Long id);
    List<EmployeeSkill> getEmployeesBySkill(Long id);
}

public interface SearchQueryService {
    List<Employee> searchEmployeesBySkills(List<String> skills, Long userId);
    SearchQueryRecord saveQuery(SearchQueryRecord r);
    SearchQueryRecord getQueryById(Long id);
    List<SearchQueryRecord> getQueriesForUser(Long id);
}
