package com.academy.grading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * GradeManager
 * 
 * Interactive console interface for managing student grades, displaying
 * summary statistics, and printing visual grade distributions.
 */
public class GradeManager {

    private final List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        GradeManager app = new GradeManager();
        app.loadSampleData();
        app.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Student Grade Management System!");

        while (running) {
            printMenu();
            System.out.print("Select an option (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addStudentInteractive(scanner);
                case "2" -> displayAllStudents();
                case "3" -> displayStatistics();
                case "4" -> loadSampleData();
                case "5" -> {
                    System.out.println("Exiting Student Grade Management System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n-------------------------------------------");
        System.out.println(" 1. Add New Student");
        System.out.println(" 2. Display All Students & Grade Report");
        System.out.println(" 3. View Class Statistics & Distribution");
        System.out.println(" 4. Reload Sample Data");
        System.out.println(" 5. Exit");
        System.out.println("-------------------------------------------");
    }

    private void addStudentInteractive(Scanner scanner) {
        System.out.print("Enter Student ID (e.g. S106): ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("Error: Student ID cannot be empty.");
            return;
        }

        System.out.print("Enter Student Full Name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Student Name cannot be empty.");
            return;
        }

        System.out.print("Enter Score (0.0 - 100.0): ");
        String scoreStr = scanner.nextLine().trim();
        try {
            double score = Double.parseDouble(scoreStr);
            Student student = new Student(id, name, score);
            students.add(student);
            System.out.printf("Successfully added student: %s (Score: %.2f, Grade: %c)%n", 
                student.name(), student.score(), student.getLetterGrade());
        } catch (NumberFormatException e) {
            System.out.println("Error: Score must be a valid decimal number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAllStudents() {
        StudentGradeManager manager = new StudentGradeManager();
        for (Student s : students) {
            manager.addStudent(s);
        }
        manager.displayGradeReport();
    }

    public void displayStatistics() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
        int passCount = 0;
        double sum = 0.0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (Student s : students) {
            double sc = s.score();
            sum += sc;
            if (sc < min) min = sc;
            if (sc > max) max = sc;
            if (s.isPassed()) passCount++;

            switch (s.getLetterGrade()) {
                case 'A' -> countA++;
                case 'B' -> countB++;
                case 'C' -> countC++;
                case 'D' -> countD++;
                default -> countF++;
            }
        }

        double avg = sum / students.size();
        double passRate = ((double) passCount / students.size()) * 100.0;

        System.out.println("\n============== CLASS PERFORMANCE STATISTICS ==============");
        System.out.printf("Enrolled Students: %d%n", students.size());
        System.out.printf("Average Score:     %.2f%n", avg);
        System.out.printf("Highest Score:     %.2f%n", max);
        System.out.printf("Lowest Score:      %.2f%n", min);
        System.out.printf("Passing Rate:      %.1f%% (%d/%d)%n%n", passRate, passCount, students.size());

        System.out.println("Grade Distribution:");
        printBar("A (90-100)", countA);
        printBar("B (80-89) ", countB);
        printBar("C (70-79) ", countC);
        printBar("D (60-69) ", countD);
        printBar("F (<60)   ", countF);
        System.out.println("==========================================================");
    }

    private void printBar(String label, int count) {
        String bar = "█".repeat(Math.max(0, count * 3));
        System.out.printf("  %s : %-15s (%d)%n", label, bar, count);
    }

    public void loadSampleData() {
        students.clear();
        students.add(new Student("S101", "Alice Johnson", 94.5));
        students.add(new Student("S102", "Bob Smith", 82.0));
        students.add(new Student("S103", "Charlie Davis", 68.5));
        students.add(new Student("S104", "Diana Prince", 54.0));
        students.add(new Student("S105", "Ethan Hunt", 88.0));
        students.add(new Student("S106", "Fiona Gallagher", 76.5));
        System.out.println("Loaded 6 default sample student records.");
    }
}
