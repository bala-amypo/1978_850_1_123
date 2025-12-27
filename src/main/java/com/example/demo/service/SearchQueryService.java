package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import java.util.List;

public interface SearchQueryService {
    SearchQueryRecord saveQuery(SearchQueryRecord query);
    List<Employee> searchEmployeesBySkills(List<String> skills, Long userId); // Match tests [cite: 669]
    SearchQueryRecord getQueryById(Long id); // Use Long
    List<SearchQueryRecord> getQueriesForUser(Long userId); // Use Long
}