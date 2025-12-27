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
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }

        List<String> normalizedSkills = skills.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .collect(Collectors.toList());

        List<Employee> results = employeeSkillRepository.findEmployeesByAllSkillNames(normalizedSkills, userId);

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId);
        record.setSkillsRequested(String.join(",", normalizedSkills));
        record.setResultsCount(results.size());
        
        searchQueryRecordRepository.save(record);

        return results;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return searchQueryRecordRepository.save(query);
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return searchQueryRecordRepository.findById(id).orElse(null);
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return searchQueryRecordRepository.findBySearcherId(userId);
    }
}