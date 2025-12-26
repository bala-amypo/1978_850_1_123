package com.example.demo.controller;

import com.example.demo.dto.EmployeeSearchRequest;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Search")
@RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/search")
@SecurityRequirement(name = "bearerAuth")
public class SearchQueryController {

    private final SearchQueryService service;

    public SearchQueryController(SearchQueryService service) {
        this.service = service;
    }

    @PostMapping("/employees")
    public ResponseEntity<List<?>> searchEmployees(
            @RequestBody EmployeeSearchRequest req
    ) {
        return ResponseEntity.ok(service.searchEmployeesBySkills(
                req.getSkills(), req.getUserId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchQueryRecord> getQuery(@PathVariable Long id) {
        return ResponseEntity.ok(service.getQueryById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SearchQueryRecord>> getUserQueries(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(service.getQueriesForUser(userId));
    }
}
