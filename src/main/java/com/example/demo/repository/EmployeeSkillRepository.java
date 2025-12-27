package com.example.demo.repository;

import com.example.demo.model.EmployeeSkill;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, Long> {
    List<EmployeeSkill> findByEmployeeIdAndActiveTrue(Long employeeId);
    List<EmployeeSkill> findBySkillIdAndActiveTrue(Long skillId);
    
    @Query("SELECT DISTINCT e FROM Employee e JOIN e.employeeSkills es JOIN es.skill s WHERE s.name IN :skillNames AND es.active = true AND e.active = true AND :searcherId = :searcherId")
    List<Employee> findEmployeesByAllSkillNames(@Param("skillNames") List<String> skillNames, @Param("searcherId") Long searcherId);
}
