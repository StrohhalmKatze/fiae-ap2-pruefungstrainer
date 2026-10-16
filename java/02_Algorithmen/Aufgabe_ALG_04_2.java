import java.util.Locale;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-04.2
 * Datentypen wählen und Speicherbedarf eines Datensatzes
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_04_2.java
 * oder:  javac Aufgabe_ALG_04_2.java  und danach  java Aufgabe_ALG_04_2
 */
public class Aufgabe_ALG_04_2 {

    public static void main(String[] args) {
        String[] feld = {"ausleihNr", "kundenNr", "radNr", "startStation",
                         "startZeit", "dauer", "strecke", "preisCent"};
        String[] typ  = {"int", "int", "short", "byte", "long", "short", "float", "int"};
        int[] bytes   = {Integer.BYTES, Integer.BYTES, Short.BYTES, Byte.BYTES,
                         Long.BYTES, Short.BYTES, Float.BYTES, Integer.BYTES};
        int summe = 0;
        for (int i = 0; i < feld.length; i++) {
            System.out.printf("   %-13s %-6s %d Byte%n", feld[i], typ[i], bytes[i]);
            summe += bytes[i];
        }
        System.out.println("a/b) Datensatz = " + summe + " Byte");
        System.out.println("   Wertebereiche: byte " + Byte.MIN_VALUE + ".." + Byte.MAX_VALUE
                + ", short " + Short.MIN_VALUE + ".." + Short.MAX_VALUE);
        long proJahr = (long) summe * 2400 * 365;
        System.out.println("   pro Jahr = " + summe + " * 2400 * 365 = " + proJahr + " Byte");
        System.out.printf(Locale.GERMANY, "   = %.4f MiB  (zum Vergleich %.4f MB)%n",
                proJahr / 1024.0 / 1024.0, proJahr / 1_000_000.0);
    }
}
