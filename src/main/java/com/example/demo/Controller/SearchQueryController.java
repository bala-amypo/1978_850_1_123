package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchQueryController {

    private final SearchQueryService service;

    public SearchQueryController(SearchQueryService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public List<Employee> searchEmployees(
            @RequestParam List<String> skills,
            @RequestParam Long userId) {
        return service.searchEmployeesBySkills(skills, userId);
    }

    @GetMapping("/queries/{id}")
    public SearchQueryRecord getQuery(@PathVariable Long id) {
        return service.getQueryById(id);
    }

    @GetMapping("/queries/user/{userId}")
    public List<SearchQueryRecord> getQueriesForUser(@PathVariable Long userId) {
        return service.getQueriesForUser(userId);
    }
}
