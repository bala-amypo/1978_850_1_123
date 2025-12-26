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

    private final SearchQueryRecordRepository queryRepo;
    private final EmployeeSkillRepository employeeSkillRepo;

    public SearchQueryServiceImpl(SearchQueryRecordRepository queryRepo,
                                  EmployeeSkillRepository employeeSkillRepo) {
        this.queryRepo = queryRepo;
        this.employeeSkillRepo = employeeSkillRepo;
    }

    @Override
    public SearchQueryRecord saveQuery(SearchQueryRecord query) {
        return queryRepo.save(query);
    }

    @Override
    public List<SearchQueryRecord> getQueriesForUser(Long userId) {
        return queryRepo.findBySearcherId(userId);
    }

    @Override
    public SearchQueryRecord getQueryById(Long id) {
        return queryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Query not found"));
    }

    @Override
    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }

        // Normalize skills - trim and convert to lowercase
        List<String> normalizedSkills = skills.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .toList();

        List<Employee> results = employeeSkillRepo.findEmployeesByAllSkillNames(normalizedSkills, (long) normalizedSkills.size());

        SearchQueryRecord record = new SearchQueryRecord();
        record.setSearcherId(userId);
        record.setSkillsRequested(String.join(",", normalizedSkills));
        record.setResultsCount(results.size());
        saveQuery(record);

        return results;
    }
}
