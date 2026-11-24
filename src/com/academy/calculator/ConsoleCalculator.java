package com.academy.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ConsoleCalculator
 * 
 * Interactive menu-driven console calculator supporting binary and unary operations,
 * error handling, and calculation history tracking.
 */
public class ConsoleCalculator {

    private final List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        ConsoleCalculator app = new ConsoleCalculator();
        app.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Console Calculator!");
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option (1-9): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> performBinaryOperation(scanner, "+");
                case "2" -> performBinaryOperation(scanner, "-");
                case "3" -> performBinaryOperation(scanner, "*");
                case "4" -> performBinaryOperation(scanner, "/");
                case "5" -> performBinaryOperation(scanner, "%");
                case "6" -> performBinaryOperation(scanner, "^");
                case "7" -> performUnaryOperation(scanner, "sqrt");
                case "8" -> displayHistory();
                case "9" -> {
                    System.out.println("Thank you for using the Console Calculator. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please select an option between 1 and 9.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n-------------------------------------------");
        System.out.println("  1. Add (+)");
        System.out.println("  2. Subtract (-)");
        System.out.println("  3. Multiply (*)");
        System.out.println("  4. Divide (/)");
        System.out.println("  5. Modulo (%)");
        System.out.println("  6. Power (^)");
        System.out.println("  7. Square Root (sqrt)");
        System.out.println("  8. View Calculation History");
        System.out.println("  9. Exit");
        System.out.println("-------------------------------------------");
    }

    private void performBinaryOperation(Scanner scanner, String opStr) {
        try {
            double a = readDouble(scanner, "Enter first operand: ");
            double b = readDouble(scanner, "Enter second operand: ");
            char op = opStr.charAt(0);
            double result = Calculator.evaluate(a, b, op);

            String record = String.format("%.4f %s %.4f = %.4f", a, opStr, b, result);
            history.add(record);
            System.out.println("Result: " + record);
        } catch (ArithmeticException | IllegalArgumentException e) {
            System.out.println("Calculation Error: " + e.getMessage());
        }
    }

    private void performUnaryOperation(Scanner scanner, String opStr) {
        try {
            double val = readDouble(scanner, "Enter value: ");
            double result = Calculator.squareRoot(val);
            String record = String.format("sqrt(%.4f) = %.4f", val, result);
            history.add(record);
            System.out.println("Result: " + record);
        } catch (IllegalArgumentException e) {
            System.out.println("Calculation Error: " + e.getMessage());
        }
    }

    private double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid decimal number.");
            }
        }
    }

    private void displayHistory() {
        System.out.println("\n--- Calculation History ---");
        if (history.isEmpty()) {
            System.out.println("No calculations performed yet.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.printf("[%d] %s%n", i + 1, history.get(i));
            }
        }
    }
}
