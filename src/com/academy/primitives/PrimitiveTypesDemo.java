package com.academy.primitives;

/**
 * PrimitiveTypesDemo
 * 
 * Demonstrates all 8 Java primitive data types:
 * - byte, short, int, long (Integer types)
 * - float, double (Floating-point types)
 * - char (Character type)
 * - boolean (Logical type)
 * 
 * Covers bit sizes, ranges, literal syntax, widening/narrowing casting,
 * overflow behavior, and autoboxing/unboxing.
 */
public class PrimitiveTypesDemo {

    public static void main(String[] args) {
        printHeader("JAVA PRIMITIVE DATA TYPES DEMO");
        displayPrimitiveSpecifications();
        demonstrateLiterals();
        demonstrateCasting();
        demonstrateAutoboxing();
        printHeader("DEMO COMPLETED SUCCESSFULLY");
    }

    private static void displayPrimitiveSpecifications() {
        printHeader("1. Primitive Specifications & Ranges");

        System.out.printf("%-10s %-8s %-12s %-25s %-25s%n", 
            "Type", "Bytes", "Bits", "Min Value", "Max Value");
        System.out.println("-".repeat(80));

        System.out.printf("%-10s %-8d %-12d %-25d %-25d%n",
            "byte", Byte.BYTES, Byte.SIZE, Byte.MIN_VALUE, Byte.MAX_VALUE);

        System.out.printf("%-10s %-8d %-12d %-25d %-25d%n",
            "short", Short.BYTES, Short.SIZE, Short.MIN_VALUE, Short.MAX_VALUE);

        System.out.printf("%-10s %-8d %-12d %-25d %-25d%n",
            "int", Integer.BYTES, Integer.SIZE, Integer.MIN_VALUE, Integer.MAX_VALUE);

        System.out.printf("%-10s %-8d %-12d %-25d %-25d%n",
            "long", Long.BYTES, Long.SIZE, Long.MIN_VALUE, Long.MAX_VALUE);

        System.out.printf("%-10s %-8d %-12d %-25e %-25e%n",
            "float", Float.BYTES, Float.SIZE, Float.MIN_VALUE, Float.MAX_VALUE);

        System.out.printf("%-10s %-8d %-12d %-25e %-25e%n",
            "double", Double.BYTES, Double.SIZE, Double.MIN_VALUE, Double.MAX_VALUE);

        System.out.printf("%-10s %-8d %-12d %-25d %-25d%n",
            "char", Character.BYTES, Character.SIZE, (int) Character.MIN_VALUE, (int) Character.MAX_VALUE);

        System.out.printf("%-10s %-8s %-12s %-25s %-25s%n",
            "boolean", "1 bit*", "JVM-dependent", "false", "true");
        System.out.println();
    }

    private static void demonstrateLiterals() {
        printHeader("2. Literal Formats and Representations");

        int decVal = 42;
        int hexVal = 0x2A;
        int binVal = 0b0010_1010;
        long largeLong = 9_876_543_210L;
        float floatVal = 3.14159f;
        double sciDouble = 1.496e8; // 1.496 * 10^8 km (Earth to Sun)
        char letter = 'J';
        char unicodeChar = '\u2615'; // Coffee symbol
        boolean isJavaFun = true;

        System.out.println("Decimal int:            " + decVal);
        System.out.println("Hexadecimal int (0x2A):  " + hexVal);
        System.out.println("Binary int (0b00101010): " + binVal);
        System.out.println("Long with 'L' suffix:   " + largeLong);
        System.out.println("Float with 'f' suffix:  " + floatVal);
        System.out.println("Scientific notation:    " + sciDouble + " km");
        System.out.println("Char literal:           " + letter);
        System.out.println("Unicode char (\\u2615):   " + unicodeChar);
        System.out.println("Boolean flag:           " + isJavaFun);
        System.out.println();
    }

    private static void demonstrateCasting() {
        printHeader("3. Type Casting (Widening & Narrowing)");

        // Widening (Implicit) Casting: byte -> short -> int -> long -> float -> double
        int smallInt = 100;
        long widerLong = smallInt;
        double widerDouble = widerLong;
        System.out.println("Widening (Implicit):");
        System.out.println("  int (" + smallInt + ") -> long (" + widerLong + ") -> double (" + widerDouble + ")");

        // Narrowing (Explicit) Casting: double -> float -> long -> int -> short -> byte
        double pi = 3.141592653589793;
        float piFloat = (float) pi;
        int piInt = (int) pi;
        System.out.println("\nNarrowing (Explicit):");
        System.out.println("  double (" + pi + ") -> float (" + piFloat + ") -> truncated int (" + piInt + ")");

        // Overflow Demonstration
        byte maxByte = 127;
        byte overflowedByte = (byte) (maxByte + 1);
        System.out.println("\nOverflow Behavior (Wraparound in two's complement):");
        System.out.println("  Byte.MAX_VALUE = " + maxByte);
        System.out.println("  (byte)(127 + 1) = " + overflowedByte + " (wrapped to Byte.MIN_VALUE)");
        System.out.println();
    }

    private static void demonstrateAutoboxing() {
        printHeader("4. Autoboxing and Unboxing (Primitives <-> Wrappers)");

        // Autoboxing: primitive -> Wrapper Object
        int primitiveInt = 500;
        Integer boxedInt = primitiveInt;

        // Unboxing: Wrapper Object -> primitive
        Double boxedDouble = 99.99;
        double primitiveDouble = boxedDouble;

        System.out.println("Autoboxing:   int " + primitiveInt + " -> Integer " + boxedInt);
        System.out.println("Unboxing:     Double " + boxedDouble + " -> double " + primitiveDouble);

        // Utility method usage on wrapper
        System.out.println("Wrapper utility Integer.toBinaryString(500): " + Integer.toBinaryString(primitiveInt));
        System.out.println("Wrapper utility Double.isNaN(0.0/0.0):        " + Double.isNaN(0.0 / 0.0));
        System.out.println();
    }

    private static void printHeader(String title) {
        System.out.println("=".repeat(80));
        System.out.println(" " + title);
        System.out.println("=".repeat(80));
    }
}
