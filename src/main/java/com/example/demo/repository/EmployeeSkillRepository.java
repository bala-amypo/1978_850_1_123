package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    @Query("SELECT e.employee FROM EmployeeSkill e WHERE e.skill.name IN :skills GROUP BY e.employee HAVING COUNT(e.employee) = :skillCount")
    List<Employee> findEmployeesByAllSkillNames(List<String> skills, Long skillCount);

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}
