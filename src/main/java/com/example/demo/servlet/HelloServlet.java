package com.example.demo.util;

import java.util.Set;

public class ProficiencyValidator {

    private static final Set<String> LEVELS =
            Set.of("Beginner", "Intermediate", "Advanced");

    public static boolean isValid(String level) {
        return LEVELS.contains(level);
    }
}
