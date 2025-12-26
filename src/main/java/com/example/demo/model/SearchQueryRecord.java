package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private Integer resultsCount;
    private LocalDateTime searchedAt;

    @PrePersist
    public void onCreate() {
        this.searchedAt = LocalDateTime.now();
        if (this.resultsCount == null) {
            this.resultsCount = 0;
        }
    }
}
