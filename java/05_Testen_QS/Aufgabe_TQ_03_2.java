/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-03.2
 * Methode freiePlaetze: fehlerhafte Fassung aus der Aufgabe, Auswertung der sechs
 * assertEquals-Testfälle, korrigierte Fassung. Zusätzlich wird gezeigt, dass eine
 * JUnit-Testmethode beim ersten fehlgeschlagenen assert abbricht.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_03_2.java
 */
public class Aufgabe_TQ_03_2 {

    // ---------- Original aus der Aufgabe ----------
    static int freiePlaetzeFehlerhaft(int kapazitaet, int gebucht) {   // 01
        int frei = -1;                                                  // 02
        if (kapazitaet > 0 && gebucht > 0) {                            // 03  Fehler: gebucht > 0
            if (gebucht < kapazitaet) {                                 // 04  Fehler: <
                frei = kapazitaet - gebucht;                            // 05
            }                                                           // 06
        }                                                               // 07
        return frei;                                                    // 08
    }                                                                   // 09

    // ---------- korrigierte Fassung ----------
    static int freiePlaetze(int kapazitaet, int gebucht) {
        int frei = -1;
        if (kapazitaet > 0 && gebucht >= 0) {        // Zeile 03: 0 Buchungen sind zulässig
            if (gebucht <= kapazitaet) {             // Zeile 04: voll belegter Kurs ist zulässig
                frei = kapazitaet - gebucht;
            }
        }
        return frei;
    }

    interface Methode { int rechne(int kapazitaet, int gebucht); }

    // Testfälle aus der Aufgabe: {erwartet, kapazitaet, gebucht}
    static final int[][] TESTFAELLE = {
            {5, 20, 15},
            {20, 20, 0},
            {0, 20, 20},
            {-1, 20, 21},
            {-1, 0, 0},
            {19, 20, 1}
    };

    static void einzelnAuswerten(String titel, Methode m) {
        System.out.println(titel);
        System.out.println("   TF | Aufruf                 | erwartet | tatsächlich | Ergebnis");
        for (int k = 0; k < TESTFAELLE.length; k++) {
            int[] tf = TESTFAELLE[k];
            int ist = m.rechne(tf[1], tf[2]);
            System.out.printf("   %2d | freiePlaetze(%2d, %2d)   | %8d | %11d | %s%n",
                    k + 1, tf[1], tf[2], tf[0], ist, ist == tf[0] ? "OK" : "Fehler");
        }
    }

    // ---------- so verhält sich JUnit: Abbruch beim ersten fehlgeschlagenen assert ----------
    static void assertEquals(int erwartet, int tatsaechlich) {
        if (erwartet != tatsaechlich) {
            throw new AssertionError("expected: <" + erwartet + "> but was: <" + tatsaechlich + ">");
        }
    }

    static void testeFreiePlaetzeWieJUnit(Methode m) {
        int zeile = 0;
        try {
            for (int[] tf : TESTFAELLE) {
                zeile++;
                assertEquals(tf[0], m.rechne(tf[1], tf[2]));
            }
            System.out.println("   Testmethode bestanden");
        } catch (AssertionError e) {
            System.out.println("   Testmethode FEHLGESCHLAGEN in Testfall " + zeile + ": " + e.getMessage());
            System.out.println("   (Testfälle " + (zeile + 1) + " bis " + TESTFAELLE.length + " wurden nicht mehr ausgeführt)");
        }
    }

    public static void main(String[] args) {
        einzelnAuswerten("a) Jeder Testfall einzeln gegen die FEHLERHAFTE Methode:", Aufgabe_TQ_03_2::freiePlaetzeFehlerhaft);
        System.out.println();
        System.out.println("c) Eine einzige JUnit-Testmethode mit allen sechs asserts (fehlerhafte Methode):");
        testeFreiePlaetzeWieJUnit(Aufgabe_TQ_03_2::freiePlaetzeFehlerhaft);
        System.out.println();
        einzelnAuswerten("b) Jeder Testfall einzeln gegen die KORRIGIERTE Methode:", Aufgabe_TQ_03_2::freiePlaetze);
        System.out.println("   JUnit-Lauf korrigiert:");
        testeFreiePlaetzeWieJUnit(Aufgabe_TQ_03_2::freiePlaetze);
        System.out.println();
        System.out.println("Zusatz: negative Buchungszahl freiePlaetze(20, -3) = " + freiePlaetze(20, -3)
                + ", negative Kapazität freiePlaetze(-5, 0) = " + freiePlaetze(-5, 0));
    }
}
