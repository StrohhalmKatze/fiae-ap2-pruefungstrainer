
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-08.2
 * Rekursive Umwandlung Dezimal -> Binär: Schreibtischtest und Korrektur
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_08_2.java
 * oder:  javac Aufgabe_ALG_08_2.java  und danach  java Aufgabe_ALG_08_2
 */
public class Aufgabe_ALG_08_2 {

    static int tiefe = 0;

    // gegebene Funktion (Fehler bei n = 0) - mit Protokoll-Ausgabe für den Schreibtischtest
    static String binaer(int n) {
        String einr = "   ".repeat(tiefe);
        System.out.println(einr + "binaer(" + n + ") aufgerufen");
        if (n == 0) {
            System.out.println(einr + "binaer(" + n + ") liefert \"\"");
            return "";
        }
        tiefe++;
        String r = binaer(n / 2) + (n % 2);
        tiefe--;
        System.out.println(einr + "binaer(" + n + ") liefert \"" + r + "\"  (n div 2 = " + (n / 2) + ", n mod 2 = " + (n % 2) + ")");
        return r;
    }

    // korrigierte Funktion: Basisfall n < 2 liefert die Ziffer selbst
    static String binaerKorrigiert(int n) {
        if (n < 2) {
            return "" + n;
        }
        return binaerKorrigiert(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        System.out.println("a) Schreibtischtest binaer(13):");
        String r = binaer(13);
        System.out.println("   Ergebnis: \"" + r + "\"");
        System.out.println("b) binaer(0) = \"" + binaer(0) + "\"  <- falsch, erwartet \"0\"");
        int[] tests = {0, 1, 2, 13, 45, 255};
        for (int t : tests) {
            System.out.println("   binaerKorrigiert(" + t + ") = \"" + binaerKorrigiert(t) + "\"  Kontrolle: " + Integer.toBinaryString(t));
        }
    }
}
