package com.example.demo.util;

import java.util.Arrays;
import java.util.List;

public class ProficiencyValidator {

    private static final List<String> VALID_PROFICIENCIES = Arrays.asList(
            "Beginner", "Intermediate", "Advanced", "Expert"
    );

    public static boolean isValid(String proficiency) {
        return VALID_PROFICIENCIES.contains(proficiency);
    }
}

