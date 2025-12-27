package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SearchQueryService;

import java.util.*;
import java.util.stream.Collectors;

public class SearchQueryServiceImpl implements SearchQueryService {

    private final SearchQueryRecordRepository repo;
    private final EmployeeSkillRepository esRepo;

    public SearchQueryServiceImpl(SearchQueryRecordRepository repo,
                                  EmployeeSkillRepository esRepo) {
        this.repo = repo;
        this.esRepo = esRepo;
    }

    public List<Employee> searchEmployeesBySkills(List<String> skills, Long userId) {
        if (skills == null || skills.isEmpty())
            throw new IllegalArgumentException("must not be empty");

        List<String> normalized = skills.stream()
                .map(s -> s.trim().toLowerCase())
                .distinct()
                .collect(Collectors.toList());

        List<Employee> result =
                esRepo.findEmployeesByAllSkillNames(normalized, (long) normalized.size());

        SearchQueryRecord r = new SearchQueryRecord();
        r.setSearcherId(userId);
        r.setSkillsRequested(String.join(",", normalized));
        r.setResultsCount(result.size());
        repo.save(r);

        return result;
    }

    public SearchQueryRecord saveQuery(SearchQueryRecord r) {
        return repo.save(r);
    }

    public SearchQueryRecord getQueryById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<SearchQueryRecord> getQueriesForUser(Long id) {
        return repo.findBySearcherId(id);
    }
}
