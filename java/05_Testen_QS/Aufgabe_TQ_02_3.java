/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-02.3
 * Prozedur kursStatistik (Gruppenwechsel): fehlerhafte Fassung aus Anlage 5 und korrigierte Fassung.
 * Die Ausgaben werden zusätzlich in einer Liste gesammelt, damit sie mit der erwarteten Ausgabe
 * verglichen werden können. Pseudocode "/" = normale Division -> in Java (double) summe / anzahl.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_02_3.java
 */
import java.util.ArrayList;
import java.util.List;

public class Aufgabe_TQ_02_3 {

    static class Termin {
        private final String kurs;
        private final String datum;
        private final int teilnehmer;
        Termin(String kurs, String datum, int teilnehmer) {
            this.kurs = kurs; this.datum = datum; this.teilnehmer = teilnehmer;
        }
        String getKurs() { return kurs; }
        String getDatum() { return datum; }
        int getTeilnehmer() { return teilnehmer; }
    }

    static List<String> ausgabe;

    static void ausgabe(String zeile) {
        ausgabe.add(zeile);
        System.out.println("   " + zeile);
    }

    // ---------- Original aus Anlage 5 (fehlerhaft) ----------
    static void kursStatistikFehlerhaft(List<Termin> termine, boolean trace) {
        ausgabe("Kursstatistik");                                         // 02
        String kurs = termine.get(0).getKurs();                           // 03 (leere Liste -> Laufzeitfehler)
        int anzahl = 0;                                                   // 04
        int summe = 0;                                                    // 05
        int max = 0;                                                      // 06
        int anzahlGesamt = 0;                                             // 07
        int summeGesamt = 0;                                              // 08
        for (int i = 0; i <= termine.size() - 1; i++) {                   // 09
            Termin t = termine.get(i);                                    // 10
            if (!t.getKurs().equals(kurs)) {                              // 11
                ausgabe(kurs + ": " + anzahl + " Termine, max. " + max    // 12
                        + ", Ø " + (double) summe / anzahl);              // 13
                anzahlGesamt = anzahlGesamt + anzahl;                     // 14
                summeGesamt = summeGesamt + summe;                        // 15
                kurs = t.getKurs();                                       // 16
                anzahl = 0;                                               // 17
                summe = 0;                                                // 18  (Fehler 1: max := 0 fehlt)
            }                                                             // 19
            anzahl = anzahl + 1;                                          // 20
            summe = summe + t.getTeilnehmer();                            // 21
            if (t.getTeilnehmer() > max) {                                // 22
                max = t.getTeilnehmer();                                  // 23
            }                                                             // 24
            if (trace) {
                System.out.printf("      [i=%d] kurs=%s anzahl=%d summe=%d max=%d anzahlGesamt=%d summeGesamt=%d%n",
                        i, kurs, anzahl, summe, max, anzahlGesamt, summeGesamt);
            }
        }                                                                 // 25  (Fehler 2: letzte Gruppe fehlt)
        ausgabe("Gesamt: " + anzahlGesamt + " Termine, Ø "                // 26
                + (double) summeGesamt / anzahl);                         // 27  (Fehler 3: anzahl statt anzahlGesamt)
    }

    // ---------- korrigierte Fassung ----------
    static void kursStatistik(List<Termin> termine) {
        ausgabe("Kursstatistik");
        if (termine.size() == 0) {                                        // d) neu: leere Liste abfangen
            ausgabe("Keine Termine vorhanden");
            return;
        }
        String kurs = termine.get(0).getKurs();
        int anzahl = 0;
        int summe = 0;
        int max = 0;
        int anzahlGesamt = 0;
        int summeGesamt = 0;
        for (int i = 0; i <= termine.size() - 1; i++) {
            Termin t = termine.get(i);
            if (!t.getKurs().equals(kurs)) {
                ausgabe(kurs + ": " + anzahl + " Termine, max. " + max
                        + ", Ø " + (double) summe / anzahl);
                anzahlGesamt = anzahlGesamt + anzahl;
                summeGesamt = summeGesamt + summe;
                kurs = t.getKurs();
                anzahl = 0;
                summe = 0;
                max = 0;                                                  // Korrektur 1 (nach Zeile 18)
            }
            anzahl = anzahl + 1;
            summe = summe + t.getTeilnehmer();
            if (t.getTeilnehmer() > max) {
                max = t.getTeilnehmer();
            }
        }
        // Korrektur 2 (nach Zeile 25): letzte Gruppe ausgeben und aufsummieren
        ausgabe(kurs + ": " + anzahl + " Termine, max. " + max + ", Ø " + (double) summe / anzahl);
        anzahlGesamt = anzahlGesamt + anzahl;
        summeGesamt = summeGesamt + summe;
        // Korrektur 3 (Zeile 27): durch anzahlGesamt teilen
        ausgabe("Gesamt: " + anzahlGesamt + " Termine, Ø " + (double) summeGesamt / anzahlGesamt);
    }

    static List<Termin> testdaten() {
        List<Termin> l = new ArrayList<>();
        l.add(new Termin("Aqua-Fitness", "03.11.2026", 14));
        l.add(new Termin("Aqua-Fitness", "10.11.2026", 18));
        l.add(new Termin("Rückenfit", "04.11.2026", 10));
        l.add(new Termin("Rückenfit", "11.11.2026", 12));
        l.add(new Termin("Rückenfit", "18.11.2026", 8));
        l.add(new Termin("Yoga", "05.11.2026", 16));
        l.add(new Termin("Yoga", "12.11.2026", 13));
        return l;
    }

    public static void main(String[] args) {
        List<String> erwartet = List.of(
                "Kursstatistik",
                "Aqua-Fitness: 2 Termine, max. 18, Ø 16.0",
                "Rückenfit: 3 Termine, max. 12, Ø 10.0",
                "Yoga: 2 Termine, max. 16, Ø 14.5",
                "Gesamt: 7 Termine, Ø 13.0");

        System.out.println("a) Ausgabe der FEHLERHAFTEN Prozedur (mit Variablenstand nach jedem Durchlauf):");
        ausgabe = new ArrayList<>();
        kursStatistikFehlerhaft(testdaten(), true);
        System.out.println("   stimmt mit erwarteter Ausgabe überein? " + ausgabe.equals(erwartet));
        System.out.println();

        System.out.println("c) Ausgabe der KORRIGIERTEN Prozedur:");
        ausgabe = new ArrayList<>();
        kursStatistik(testdaten());
        System.out.println("   stimmt mit erwarteter Ausgabe überein? " + ausgabe.equals(erwartet));
        System.out.println();

        System.out.println("d) Aufruf mit leerer Liste:");
        ausgabe = new ArrayList<>();
        try {
            kursStatistikFehlerhaft(new ArrayList<>(), false);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("   fehlerhaft: Laufzeitfehler " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        ausgabe = new ArrayList<>();
        System.out.println("   korrigiert:");
        kursStatistik(new ArrayList<>());
        System.out.println();

        System.out.println("Zusatz: nur ein Kurs (Grenzfall Gruppenwechsel nie ausgelöst):");
        List<Termin> einKurs = new ArrayList<>();
        einKurs.add(new Termin("Yoga", "05.11.2026", 16));
        einKurs.add(new Termin("Yoga", "12.11.2026", 13));
        ausgabe = new ArrayList<>();
        kursStatistik(einKurs);
    }
}
