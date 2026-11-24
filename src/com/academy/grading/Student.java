package com.academy.grading;

/**
 * Student
 * 
 * Immutable record representing a student with an ID, full name, and numeric score (0 - 100).
 * Provides helper methods for computing letter grades (A, B, C, D, F) and pass/fail status.
 */
public record Student(String id, String name, double score) {

    public Student {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be null or empty.");
        }
        if (score < 0.0 || score > 100.0) {
            throw new IllegalArgumentException("Score must be between 0.0 and 100.0. Provided: " + score);
        }
    }

    /**
     * Maps percentage score to standard letter grades:
     * 90 - 100: A
     * 80 - 89.9: B
     * 70 - 79.9: C
     * 60 - 69.9: D
     * < 60: F
     */
    public char getLetterGrade() {
        if (score >= 90.0) return 'A';
        if (score >= 80.0) return 'B';
        if (score >= 70.0) return 'C';
        if (score >= 60.0) return 'D';
        return 'F';
    }

    /**
     * Determines if the student passed (score >= 60.0).
     */
    public boolean isPassed() {
        return score >= 60.0;
    }
}
