// package com.example.demo.service;

// import com.example.demo.model.Employee;
// import com.example.demo.model.SearchQueryRecord;
// import java.util.List;

// public interface SearchQueryService {
//     SearchQueryRecord saveQuery(SearchQueryRecord query);
//     List<SearchQueryRecord> getQueriesForUser(Long userId);
//     SearchQueryRecord getQueryById(Long id);
//     List<Employee> searchEmployeesBySkills(List<String> skills, Long userId);
// }
@Override
public List<Employee> searchEmployeesBySkills(List<String> skills, Long searcherId) {
    if (skills == null || skills.isEmpty()) {
        throw new IllegalArgumentException("Skills list must not be empty");
    }

    List<String> normalized = skills.stream()
            .filter(s -> s != null && !s.isBlank())
            .map(s -> s.trim().toLowerCase())
            .distinct()
            .toList();

    List<Employee> employees = employeeSkillRepository.findEmployeesByAllSkillNames(normalized, (long) normalized.size());

    SearchQueryRecord record = new SearchQueryRecord();
    record.setSearcherId(searcherId);
    record.setSkillsRequested(String.join(",", normalized));
    record.setResultsCount((long) employees.size());
    searchQueryRecordRepository.save(record);

    return employees;
}
