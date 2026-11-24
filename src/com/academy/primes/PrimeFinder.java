package com.academy.primes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * PrimeFinder
 * 
 * Demonstrates prime number checking and finding all primes up to a positive integer N.
 * Implements optimized trial division checking up to sqrt(number) and step-of-6 optimization.
 */
public class PrimeFinder {

    /**
     * Determines whether a given number is prime.
     * Time complexity: O(sqrt(n))
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; (long) i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds and returns all prime numbers up to and including limit N.
     */
    public static List<Integer> findPrimes(int limit) {
        List<Integer> primes = new ArrayList<>();
        if (limit < 2) {
            return primes;
        }

        // Using Sieve of Eratosthenes for high performance up to N
        boolean[] isComposite = new boolean[limit + 1];
        for (int p = 2; (long) p * p <= limit; p++) {
            if (!isComposite[p]) {
                for (int multiple = p * p; multiple <= limit; multiple += p) {
                    isComposite[multiple] = true;
                }
            }
        }

        for (int i = 2; i <= limit; i++) {
            if (!isComposite[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        System.out.println("=== Prime Number Generator & Tester ===");

        int sampleLimit = 50;
        if (args.length > 0) {
            try {
                sampleLimit = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {}
        }

        System.out.println("Generating primes up to N = " + sampleLimit + ":");
        List<Integer> primes = findPrimes(sampleLimit);
        System.out.println(primes);
        System.out.printf("Total prime count up to %d: %d%n%n", sampleLimit, primes.size());

        // Quick verification of trial division
        int[] testNumbers = { -5, 0, 1, 2, 3, 4, 17, 25, 29, 97, 100 };
        System.out.println("Individual Primality Checks:");
        for (int num : testNumbers) {
            System.out.printf("  isPrime(%3d) -> %b%n", num, isPrime(num));
        }

        System.out.println("========================================");
    }
}
