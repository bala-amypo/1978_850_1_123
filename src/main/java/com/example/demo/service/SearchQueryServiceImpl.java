package com.example.demo.service.impl;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.repository.EmployeeSkillRepository;
import com.example.demo.repository.SearchQueryRecordRepository;
import com.example.demo.service.SearchQueryService;

import java.util.List;
import java.util.stream.Collectors;

public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repository;
    private final EmployeeSkillRepository employeeSkillRepository;

    public SearchQueryServiceImpl(SearchQueryRecordRepository repository,
                                  EmployeeSkillRepository employeeSkillRepository) {
        this.repository = repository;
        this.employeeSkillRepository = employeeSkillRepository;
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long searcherId) {

        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("Skills must not be empty");
        }

        List<String> normalized = skills.stream()
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .collect(Collectors.toList());

        List<Employee> result =
                employeeSkillRepository.findEmployeesByAllSkillNames(
                        normalized, (long) normalized.size());

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(searcherId);
        record.setSkillsRequested(String.join(",", normalized));
        record.setResultsCount(result.size());
        repository.save(record);

        return result;
    }

    @Override
    public void saveQuery(SearchQueryRecord record) {
        repository.save(record);
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Query not found"));
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long searcherId) {
        return repository.findBySearcherId(searcherId);
    }
}
