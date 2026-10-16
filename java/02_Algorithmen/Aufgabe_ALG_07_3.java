import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-07.3
 * Fehlerbehandlung mit eigenen und Standard-Ausnahmen
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_07_3.java
 * oder:  javac Aufgabe_ALG_07_3.java  und danach  java Aufgabe_ALG_07_3
 */
public class Aufgabe_ALG_07_3 {

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

    /** eigene, geprüfte (checked) Ausnahme */
    static class KeineDatenException extends Exception {
        private static final long serialVersionUID = 1L;

        KeineDatenException(String meldung) {
            super(meldung);
        }
    }

    // b) durchschnittliche Dauer der Ausleihen, die an stationNr beginnen
    static double durchschnittsDauer(List<Ausleihe> ausleihen, int stationNr)
            throws KeineDatenException {
        if (ausleihen == null) {
            throw new IllegalArgumentException("Liste der Ausleihen fehlt (null)");
        }
        if (stationNr < 1) {
            throw new IllegalArgumentException("Ungültige Stationsnummer: " + stationNr);
        }
        int summe = 0;
        int anzahl = 0;
        for (int i = 0; i < ausleihen.size(); i++) {
            Ausleihe a = ausleihen.get(i);
            if (a.getStartStation() == stationNr) {
                summe = summe + a.getDauer();
                anzahl = anzahl + 1;
            }
        }
        if (anzahl == 0) {
            throw new KeineDatenException("Keine Ausleihen für Station " + stationNr);
        }
        return (double) summe / anzahl;
    }

    // c) Aufrufer: fängt die Ausnahmen und reagiert benutzerfreundlich
    static void zeigeStationsbericht(List<Ausleihe> ausleihen, int stationNr) {
        try {
            double d = durchschnittsDauer(ausleihen, stationNr);
            System.out.printf(Locale.GERMANY, "Station %d: durchschnittliche Dauer %.1f min%n",
                    stationNr, d);
        } catch (KeineDatenException e) {
            System.out.println("Station " + stationNr
                    + ": Für diese Station liegen keine Ausleihen vor.");
        } catch (IllegalArgumentException e) {
            System.out.println("FEHLER-LOG: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        List<Ausleihe> ausleihen = testAusleihen();
        zeigeStationsbericht(ausleihen, 3);
        zeigeStationsbericht(ausleihen, 4);
        zeigeStationsbericht(ausleihen, 7);
        zeigeStationsbericht(ausleihen, 0);
        zeigeStationsbericht(null, 3);
    }
}
