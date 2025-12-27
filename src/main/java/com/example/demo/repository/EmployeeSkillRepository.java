package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long id);

    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long id);

    List<Employee> findEmployeesByAllSkillNames(List<String> skills, Long count);
}
