package com.example.demo.repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {

    
    @Query("SELECT DISTINCT es.employee FROM EmployeeSkill es " +
           "WHERE es.skill.name IN :skills AND es.active = true " +
           "AND (:userId IS NULL OR :userId = :userId)")
    List<Employee> findEmployeesByAllSkillNames(@Param("skills") List<String> skills, @Param("userId") Long userId);

    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);
    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
}