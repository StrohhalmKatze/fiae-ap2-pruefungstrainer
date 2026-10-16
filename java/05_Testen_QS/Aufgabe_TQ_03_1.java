/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-03.1 c)
 * Unit-Test nach dem Arrange-Act-Assert-Muster. Statt JUnit wird eine kleine
 * Ersatz-Testhilfe (assertEquals) verwendet; die JUnit-5-Fassung steht im Kommentar.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_03_1.java
 */
public class Aufgabe_TQ_03_1 {

    // zu testende Klasse (Auszug aus Anlage 2)
    static class Kurs {
        private final String name;
        private final int kapazitaet;
        private int gebucht;

        Kurs(String name, int kapazitaet) {
            this.name = name;
            this.kapazitaet = kapazitaet;
            this.gebucht = 0;
        }

        boolean buchen(int anzahl) {
            if (anzahl <= 0 || gebucht + anzahl > kapazitaet) {
                return false;
            }
            gebucht = gebucht + anzahl;
            return true;
        }

        int getFreiePlaetze() {
            return kapazitaet - gebucht;
        }
    }

    // ---------- Mini-Testhilfe (Ersatz für JUnit) ----------
    static void assertEquals(int erwartet, int tatsaechlich) {
        if (erwartet != tatsaechlich) {
            throw new AssertionError("expected: <" + erwartet + "> but was: <" + tatsaechlich + ">");
        }
    }

    /* JUnit-5-Fassung:
     *   @Test
     *   void buchungVerringertFreiePlaetze() {
     *       Kurs kurs = new Kurs("Yoga", 12);          // Arrange
     *       kurs.buchen(3);                            // Act
     *       assertEquals(9, kurs.getFreiePlaetze());   // Assert
     *   }
     */
    static void buchungVerringertFreiePlaetze() {
        Kurs kurs = new Kurs("Yoga", 12);          // Arrange: Ausgangszustand herstellen
        kurs.buchen(3);                            // Act: zu testende Aktion ausführen
        assertEquals(9, kurs.getFreiePlaetze());   // Assert: Ergebnis mit Erwartung vergleichen
    }

    static void ueberbuchungWirdAbgelehnt() {
        Kurs kurs = new Kurs("Yoga", 12);          // Arrange
        kurs.buchen(10);
        boolean erfolg = kurs.buchen(3);           // Act
        assertEquals(0, erfolg ? 1 : 0);           // Assert: Buchung abgelehnt
        assertEquals(2, kurs.getFreiePlaetze());   //         Plätze unverändert
    }

    static void fuehreAus(String name, Runnable test) {
        try {
            test.run();
            System.out.println("OK      " + name);
        } catch (AssertionError e) {
            System.out.println("FEHLER  " + name + " -> " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        fuehreAus("buchungVerringertFreiePlaetze", Aufgabe_TQ_03_1::buchungVerringertFreiePlaetze);
        fuehreAus("ueberbuchungWirdAbgelehnt", Aufgabe_TQ_03_1::ueberbuchungWirdAbgelehnt);
    }
}
