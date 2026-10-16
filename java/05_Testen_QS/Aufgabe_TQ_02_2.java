/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-02.2
 * Fehlerhafte Methode belastungsDurchschnitt (wie in der Aufgabe) und korrigierte Fassung.
 * Enthält eine kleine Ersatz-Testhilfe (assertEquals), weil JUnit nicht installiert ist.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_02_2.java
 * oder:  javac Aufgabe_TQ_02_2.java  und danach  java Aufgabe_TQ_02_2
 */
import java.util.Arrays;

public class Aufgabe_TQ_02_2 {

    // ---------- Original aus der Aufgabe (enthält 3 Fehler) ----------
    static double belastungsDurchschnittFehlerhaft(int[] puls, int grenze) {   // 01
        int summe = 0;                                                         // 02
        int anzahl = 0;                                                        // 03
        for (int i = 1; i < puls.length; i++) {                                // 04  Fehler: Start bei 1
            if (puls[i] > grenze) {                                            // 05  Fehler: > statt >=
                summe = summe + puls[i];                                       // 06
                anzahl++;                                                      // 07
            }                                                                  // 08
        }                                                                      // 09
        if (anzahl == 0) {                                                     // 10
            return 0.0;                                                        // 11
        }                                                                      // 12
        return (double) summe / puls.length;                                   // 13  Fehler: falscher Divisor
    }                                                                          // 14

    // ---------- korrigierte Fassung ----------
    static double belastungsDurchschnitt(int[] puls, int grenze) {
        int summe = 0;
        int anzahl = 0;
        for (int i = 0; i < puls.length; i++) {          // 04: ab Index 0
            if (puls[i] >= grenze) {                      // 05: "mindestens" -> >=
                summe = summe + puls[i];
                anzahl++;
            }
        }
        if (anzahl == 0) {
            return 0.0;
        }
        return (double) summe / anzahl;                   // 13: durch Anzahl der gezählten Werte
    }

    // ---------- Schreibtischtest der fehlerhaften Fassung ----------
    static void traceFehlerhaft(int[] puls, int grenze) {
        int summe = 0, anzahl = 0;
        System.out.println("   i | puls[i] | puls[i] > " + grenze + " | summe | anzahl");
        for (int i = 1; i < puls.length; i++) {
            boolean b = puls[i] > grenze;
            if (b) { summe += puls[i]; anzahl++; }
            System.out.printf("   %d | %7d | %-12s | %5d | %6d%n", i, puls[i], b ? "wahr" : "falsch", summe, anzahl);
        }
        System.out.println("   Rückgabe: " + summe + " / " + puls.length + " = " + ((double) summe / puls.length));
    }

    // ---------- Mini-Testhilfe (Ersatz für JUnit) ----------
    static int ok = 0, fehler = 0;

    static void assertEquals(double erwartet, double tatsaechlich, double delta, String name) {
        boolean gleich = Math.abs(erwartet - tatsaechlich) <= delta;
        if (gleich) ok++; else fehler++;
        System.out.printf("   %-6s %-45s erwartet %6.1f, tatsächlich %6.1f%n",
                gleich ? "OK" : "FEHLER", name, erwartet, tatsaechlich);
    }

    interface Methode { double rechne(int[] puls, int grenze); }

    static void testlauf(String titel, Methode m) {
        ok = 0; fehler = 0;
        System.out.println(titel);
        assertEquals(150.0, m.rechne(new int[]{150, 120, 140, 160, 135}, 140), 0.001, "Testdaten der Aufgabe");
        assertEquals(0.0,   m.rechne(new int[]{100, 110}, 140),               0.001, "kein Wert im Belastungsbereich");
        assertEquals(140.0, m.rechne(new int[]{140}, 140),                     0.001, "ein Element, genau auf der Grenze");
        assertEquals(155.0, m.rechne(new int[]{150, 160}, 140),                0.001, "alle Werte im Bereich");
        assertEquals(0.0,   m.rechne(new int[]{}, 140),                        0.001, "leeres Array");
        System.out.println("   -> " + ok + " bestanden, " + fehler + " fehlgeschlagen");
    }

    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.GERMANY);   // gleiche Zahlendarstellung auf jedem Rechner
        int[] puls = {150, 120, 140, 160, 135};
        int grenze = 140;
        System.out.println("Testdaten: puls = " + Arrays.toString(puls) + ", grenze = " + grenze);
        System.out.println("a) Schreibtischtest der fehlerhaften Methode:");
        traceFehlerhaft(puls, grenze);
        System.out.println("   fehlerhaft:  " + belastungsDurchschnittFehlerhaft(puls, grenze));
        System.out.println("   korrigiert:  " + belastungsDurchschnitt(puls, grenze)
                + "   (150 + 140 + 160) / 3");
        System.out.println();
        testlauf("Tests gegen die FEHLERHAFTE Methode:", Aufgabe_TQ_02_2::belastungsDurchschnittFehlerhaft);
        System.out.println();
        testlauf("Tests gegen die KORRIGIERTE Methode:", Aufgabe_TQ_02_2::belastungsDurchschnitt);
    }
}
