package com.academy.calculator;

/**
 * Calculator
 * 
 * Provides static arithmetic operations and evaluation logic for standard operations:
 * addition, subtraction, multiplication, division, modulo, and power.
 */
public class Calculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Division by zero is undefined.");
        }
        return a / b;
    }

    public static double modulo(double a, double b) {
        if (b == 0.0) {
            throw new ArithmeticException("Modulo by zero is undefined.");
        }
        return a % b;
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double squareRoot(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
        }
        return Math.sqrt(value);
    }

    /**
     * Evaluates a binary operation specified by operator character:
     * '+', '-', '*', '/', '%', '^'
     */
    public static double evaluate(double a, double b, char operator) {
        return switch (operator) {
            case '+' -> add(a, b);
            case '-' -> subtract(a, b);
            case '*' -> multiply(a, b);
            case '/' -> divide(a, b);
            case '%' -> modulo(a, b);
            case '^' -> power(a, b);
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        };
    }

    public static void main(String[] args) {
        System.out.println("=== Calculator Automated Demonstration ===");
        double x = 20.0;
        double y = 4.0;

        System.out.printf("%.2f + %.2f = %.2f%n", x, y, evaluate(x, y, '+'));
        System.out.printf("%.2f - %.2f = %.2f%n", x, y, evaluate(x, y, '-'));
        System.out.printf("%.2f * %.2f = %.2f%n", x, y, evaluate(x, y, '*'));
        System.out.printf("%.2f / %.2f = %.2f%n", x, y, evaluate(x, y, '/'));
        System.out.printf("%.2f %% %.2f = %.2f%n", x, y, evaluate(x, y, '%'));
        System.out.printf("%.2f ^ %.2f = %.2f%n", 2.0, 8.0, evaluate(2.0, 8.0, '^'));
        System.out.printf("sqrt(%.2f) = %.2f%n", 144.0, squareRoot(144.0));

        // Exception test
        try {
            divide(10.0, 0.0);
        } catch (ArithmeticException ex) {
            System.out.println("Expected error handled: " + ex.getMessage());
        }
        System.out.println("===========================================");
    }
}
