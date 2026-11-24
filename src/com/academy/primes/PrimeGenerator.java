package com.academy.primes;

import java.util.List;

/**
 * PrimeGenerator
 * 
 * Alternate utility class that delegates or provides prime generation.
 */
public class PrimeGenerator {

    public static boolean isPrime(int n) {
        return PrimeFinder.isPrime(n);
    }

    public static List<Integer> findPrimes(int limit) {
        return PrimeFinder.findPrimes(limit);
    }

    public static void main(String[] args) {
        PrimeFinder.main(args);
    }
}
