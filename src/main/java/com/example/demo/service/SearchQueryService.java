package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import java.util.List;

public interface SearchQueryService {
    List<Employee> searchEmployeesBySkills(List<String> skills, Long searcherId);
    void saveQuery(SearchQueryRecord record);
    SearchQueryRecord getQueryById(Long id);
    List<SearchQueryRecord> getQueriesForUser(Long searcherId);
}
