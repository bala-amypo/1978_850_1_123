package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchQueryServiceImpl implements SearchQueryService {
    private final SearchQueryRecordRepository searchQueryRecordRepository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository searchQueryRecordRepository,
                                EmployeeSkillRepository employeeSkillRepository) {
        this.searchQueryRecordRepository = searchQueryRecordRepository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skillNames, Long searcherId) {
        if (skillNames == null || skillNames.isEmpty()) {
            throw new IllegalArgumentException("Skill names must not be empty");
        }

        List<String> normalizedSkills = skillNames.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        String skillsRequested = String.join(",", normalizedSkills);
        
        List<Employee> employees = employeeSkillRepository.findEmployeesByAllSkillNames(normalizedSkills, searcherId);
        
        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(searcherId);
        record.setSkillsRequested(skillsRequested);
        record.setResultsCount(employees.size());
        record.onCreate();
        searchQueryRecordRepository.save(record);

        return employees;
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return searchQueryRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Search query not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long searcherId) {
        return searchQueryRecordRepository.findBySearcherId(searcherId);
    }

    @Override
    public void saveQuery(SearchQueryRecord record) {
        searchQueryRecordRepository.save(record);
    }
}
