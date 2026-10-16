
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-04.1
 * Zahlensysteme und Einheiten (Kontrollrechnung)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_04_1.java
 * oder:  javac Aufgabe_ALG_04_1.java  und danach  java Aufgabe_ALG_04_1
 */
public class Aufgabe_ALG_04_1 {

    public static void main(String[] args) {
        System.out.println("a) 1 KiB = " + 1024 + " Byte, 1 kB = " + 1000 + " Byte, 1 MiB = "
                + (1024 * 1024) + " Byte, 1 GB = " + 1_000_000_000L + " Byte");
        int z = 202;
        System.out.println("b) 202 dezimal = " + Integer.toBinaryString(z) + " binär = "
                + Integer.toHexString(z).toUpperCase() + " hex");
        System.out.println("   Divisionsrestverfahren 202:");
        int n = z;
        while (n > 0) {
            System.out.println("   " + n + " : 2 = " + (n / 2) + " Rest " + (n % 2));
            n = n / 2;
        }
        System.out.println("c) 7E hex = " + Integer.parseInt("7E", 16) + " dezimal, 10110110 binär = "
                + Integer.parseInt("10110110", 2) + " dezimal");
        System.out.println("d) 12 Bit -> 2^12 = " + (1 << 12) + " verschiedene Werte (0 bis " + ((1 << 12) - 1) + ")");
    }
}
