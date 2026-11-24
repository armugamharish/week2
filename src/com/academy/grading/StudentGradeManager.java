package com.academy.grading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * StudentGradeManager
 * 
 * Manages a roster of students, computes class statistics (class average, highest scorer, lowest scorer),
 * and prints formatted grade distribution reports.
 */
public class StudentGradeManager {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Cannot add a null student.");
        }
        students.add(student);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public double calculateClassAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Student s : students) {
            sum += s.score();
        }
        return sum / students.size();
    }

    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }
        return Collections.max(students, Comparator.comparingDouble(Student::score));
    }

    public Student getLowestStudent() {
        if (students.isEmpty()) {
            return null;
        }
        return Collections.min(students, Comparator.comparingDouble(Student::score));
    }

    public void displayGradeReport() {
        System.out.println("======================================================================");
        System.out.println("                     STUDENT GRADE REPORT                             ");
        System.out.println("======================================================================");
        System.out.printf("%-10s %-25s %-10s %-8s %-8s%n", "ID", "Name", "Score", "Grade", "Status");
        System.out.println("-".repeat(70));

        if (students.isEmpty()) {
            System.out.println("No students enrolled.");
            System.out.println("======================================================================");
            return;
        }

        for (Student s : students) {
            System.out.printf("%-10s %-25s %-10.2f %-8c %-8s%n",
                s.id(), s.name(), s.score(), s.getLetterGrade(), s.isPassed() ? "PASS" : "FAIL");
        }

        System.out.println("-".repeat(70));
        System.out.printf("Total Students:   %d%n", students.size());
        System.out.printf("Class Average:    %.2f%n", calculateClassAverage());

        Student top = getTopStudent();
        if (top != null) {
            System.out.printf("Highest Scorer:   %s (%.2f - %c)%n", top.name(), top.score(), top.getLetterGrade());
        }

        Student lowest = getLowestStudent();
        if (lowest != null) {
            System.out.printf("Lowest Scorer:    %s (%.2f - %c)%n", lowest.name(), lowest.score(), lowest.getLetterGrade());
        }
        System.out.println("======================================================================\n");
    }

    public static void main(String[] args) {
        StudentGradeManager manager = new StudentGradeManager();
        manager.addStudent(new Student("S101", "Alice Johnson", 94.5));
        manager.addStudent(new Student("S102", "Bob Smith", 82.0));
        manager.addStudent(new Student("S103", "Charlie Davis", 68.5));
        manager.addStudent(new Student("S104", "Diana Prince", 54.0));
        manager.addStudent(new Student("S105", "Ethan Hunt", 88.0));

        manager.displayGradeReport();
    }
}
