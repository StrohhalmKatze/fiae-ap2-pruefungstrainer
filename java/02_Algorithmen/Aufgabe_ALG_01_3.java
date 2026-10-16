import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-01.3
 * Prüfungsaufgabe Kundenstatistik (Ergebnis-Array, eindeutige Liste, verschachtelte Schleifen)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_01_3.java
 * oder:  javac Aufgabe_ALG_01_3.java  und danach  java Aufgabe_ALG_01_3
 */
public class Aufgabe_ALG_01_3 {

    /** Datumsklasse wie in Anlage 2 (vereinfacht, intern java.time.LocalDate). */
    static class Datum {
        private final LocalDate wert;

        Datum(String iso) {
            this.wert = LocalDate.parse(iso);
        }

        /** true, wenn das Datum zwischen von und bis liegt (Grenzen eingeschlossen). */
        boolean liegtZwischen(Datum von, Datum bis) {
            return !wert.isBefore(von.wert) && !wert.isAfter(bis.wert);
        }

        @Override
        public String toString() {
            return wert.toString();
        }
    }

    /** Klasse Ausleihe (Anlage 2). */
    static class Ausleihe {
        private final int ausleihNr;
        private final int kundenNr;
        private final int radNr;
        private final int startStation;
        private final int zielStation;
        private final Datum datum;
        private final int startMinute;
        private final int dauer;
        private final double strecke;
        private final double preis;

        Ausleihe(int ausleihNr, int kundenNr, int radNr, int startStation, int zielStation,
                 String datum, int startMinute, int dauer, double strecke, double preis) {
            this.ausleihNr = ausleihNr;
            this.kundenNr = kundenNr;
            this.radNr = radNr;
            this.startStation = startStation;
            this.zielStation = zielStation;
            this.datum = new Datum(datum);
            this.startMinute = startMinute;
            this.dauer = dauer;
            this.strecke = strecke;
            this.preis = preis;
        }

        int getAusleihNr() { return ausleihNr; }
        int getKundenNr() { return kundenNr; }
        int getRadNr() { return radNr; }
        int getStartStation() { return startStation; }
        int getZielStation() { return zielStation; }
        Datum getDatum() { return datum; }
        int getStartMinute() { return startMinute; }
        int getDauer() { return dauer; }
        double getStrecke() { return strecke; }
        double getPreis() { return preis; }

        @Override
        public String toString() {
            return String.format(Locale.GERMANY, "Ausleihe %2d: Kunde %d, Rad %d, Station %d -> %d, %s, %3d min, %5.2f km, %6.2f EUR",
                    ausleihNr, kundenNr, radNr, startStation, zielStation, datum, dauer, strecke, preis);
        }
    }

    /** Klasse KundenStatistik (Anlage 2). */
    static class KundenStatistik {
        private final int kundenNr;
        private int anzahl;
        private int gesamtDauer;
        private double durchschnitt;

        /** Konstruktor: anzahl, gesamtDauer und durchschnitt werden mit 0 vorbelegt. */
        KundenStatistik(int kundenNr) {
            this.kundenNr = kundenNr;
            this.anzahl = 0;
            this.gesamtDauer = 0;
            this.durchschnitt = 0.0;
        }

        int getKundenNr() { return kundenNr; }
        int getAnzahl() { return anzahl; }
        int getGesamtDauer() { return gesamtDauer; }
        double getDurchschnitt() { return durchschnitt; }
        void setAnzahl(int anzahl) { this.anzahl = anzahl; }
        void setGesamtDauer(int gesamtDauer) { this.gesamtDauer = gesamtDauer; }
        void setDurchschnitt(double durchschnitt) { this.durchschnitt = durchschnitt; }

        @Override
        public String toString() {
            return String.format(Locale.GERMANY, "Kunde %d: anzahl=%d, gesamtDauer=%d, durchschnitt=%.2f",
                    kundenNr, anzahl, gesamtDauer, durchschnitt);
        }
    }

    /** Die 12 Ausleihen vom 14.09. bis 17.09.2026 (Tabelle "Ausleihen"). */
    static List<Ausleihe> testAusleihen() {
        List<Ausleihe> a = new ArrayList<>();
        a.add(new Ausleihe(1, 5001, 101, 1, 3, "2026-09-14", 462, 18, 4.6, 0.00));
        a.add(new Ausleihe(2, 5002, 105, 2, 1, "2026-09-14", 485, 12, 2.1, 3.40));
        a.add(new Ausleihe(3, 5003, 106, 3, 5, "2026-09-14", 750, 75, 9.8, 7.50));
        a.add(new Ausleihe(4, 5001, 104, 3, 1, "2026-09-14", 1030, 22, 4.9, 0.00));
        a.add(new Ausleihe(5, 5004, 110, 5, 4, "2026-09-15", 555, 95, 12.4, 6.50));
        a.add(new Ausleihe(6, 5005, 109, 4, 2, "2026-09-15", 1008, 31, 5.0, 7.20));
        a.add(new Ausleihe(7, 5002, 111, 6, 3, "2026-09-15", 1055, 64, 8.7, 13.80));
        a.add(new Ausleihe(8, 5003, 107, 3, 3, "2026-09-16", 785, 40, 6.2, 4.00));
        a.add(new Ausleihe(9, 5001, 101, 1, 6, "2026-09-16", 475, 26, 7.3, 0.00));
        a.add(new Ausleihe(10, 5004, 112, 6, 1, "2026-09-16", 1100, 33, 7.1, 0.30));
        a.add(new Ausleihe(11, 5005, 102, 1, 2, "2026-09-17", 492, 9, 1.9, 2.80));
        a.add(new Ausleihe(12, 5003, 106, 5, 3, "2026-09-17", 640, 58, 9.1, 5.80));
        return a;
    }

    // a) Anzahl der Fahrten über 60 Minuten je Startstation; Index = stationNr - 1
    static int[] langeFahrtenJeStation(List<Ausleihe> ausleihen, int anzahlStationen) {
        int[] ergebnis = new int[anzahlStationen];          // alle Elemente sind 0
        for (int i = 0; i < ausleihen.size(); i++) {
            Ausleihe a = ausleihen.get(i);
            if (a.getDauer() > 60) {
                int index = a.getStartStation() - 1;
                ergebnis[index] = ergebnis[index] + 1;
            }
        }
        return ergebnis;
    }

    // b) für jede Kundennummer genau ein KundenStatistik-Objekt (Werte noch 0)
    static List<KundenStatistik> erzeugeStatistik(List<Ausleihe> ausleihen) {
        List<KundenStatistik> statistik = new ArrayList<>();
        for (int i = 0; i < ausleihen.size(); i++) {
            int kundenNr = ausleihen.get(i).getKundenNr();
            boolean vorhanden = false;
            int j = 0;
            while (j < statistik.size() && !vorhanden) {
                if (statistik.get(j).getKundenNr() == kundenNr) {
                    vorhanden = true;
                }
                j = j + 1;
            }
            if (!vorhanden) {
                statistik.add(new KundenStatistik(kundenNr));
            }
        }
        return statistik;
    }

    // c) Werte je Kunde für den Zeitraum von..bis berechnen und setzen
    static void berechneStatistik(List<Ausleihe> ausleihen, List<KundenStatistik> statistik,
                                  Datum von, Datum bis) {
        for (int i = 0; i < statistik.size(); i++) {
            KundenStatistik ks = statistik.get(i);
            int anzahl = 0;                                   // für jeden Kunden neu!
            int summe = 0;
            for (int j = 0; j < ausleihen.size(); j++) {
                Ausleihe a = ausleihen.get(j);
                if (a.getKundenNr() == ks.getKundenNr()
                        && a.getDatum().liegtZwischen(von, bis)) {
                    anzahl = anzahl + 1;
                    summe = summe + a.getDauer();
                }
            }
            ks.setAnzahl(anzahl);
            ks.setGesamtDauer(summe);
            if (anzahl > 0) {
                ks.setDurchschnitt((double) summe / anzahl);   // keine Ganzzahldivision
            } else {
                ks.setDurchschnitt(0.0);
            }
        }
    }

    public static void main(String[] args) {
        List<Ausleihe> ausleihen = testAusleihen();

        System.out.println("a) langeFahrtenJeStation(ausleihen, 6) = "
                + Arrays.toString(langeFahrtenJeStation(ausleihen, 6)));

        List<KundenStatistik> statistik = erzeugeStatistik(ausleihen);
        System.out.println("b) erzeugeStatistik: " + statistik.size() + " Objekte");
        for (KundenStatistik ks : statistik) {
            System.out.println("   " + ks);
        }

        berechneStatistik(ausleihen, statistik, new Datum("2026-09-16"), new Datum("2026-09-17"));
        System.out.println("c) berechneStatistik(..., 2026-09-16, 2026-09-17):");
        for (KundenStatistik ks : statistik) {
            System.out.println("   " + ks);
        }

        berechneStatistik(ausleihen, statistik, new Datum("2026-09-14"), new Datum("2026-09-17"));
        System.out.println("   Kontrolle Gesamtzeitraum 2026-09-14 bis 2026-09-17:");
        for (KundenStatistik ks : statistik) {
            System.out.println("   " + ks);
        }
    }
}
