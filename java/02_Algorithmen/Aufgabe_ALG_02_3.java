import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-02.3
 * Listen: Duplikate vermeiden, Elemente entfernen
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_02_3.java
 * oder:  javac Aufgabe_ALG_02_3.java  und danach  java Aufgabe_ALG_02_3
 */
public class Aufgabe_ALG_02_3 {

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

    /** Klasse Fahrrad (Anlage 2). */
    static class Fahrrad {
        private final int radNr;
        private final String typ;
        private final int akkuStand;
        private final double kmStand;
        private final boolean defekt;

        Fahrrad(int radNr, String typ, int akkuStand, double kmStand, boolean defekt) {
            this.radNr = radNr;
            this.typ = typ;
            this.akkuStand = akkuStand;
            this.kmStand = kmStand;
            this.defekt = defekt;
        }

        int getRadNr() { return radNr; }
        String getTyp() { return typ; }
        int getAkkuStand() { return akkuStand; }
        double getKmStand() { return kmStand; }
        boolean isDefekt() { return defekt; }

        @Override
        public String toString() {
            return "Rad " + radNr + " (" + typ + ", Akku " + akkuStand + " %" + (defekt ? ", defekt" : "") + ")";
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

    // ---------- Testdaten aus Anlage 3 ----------

    /** Alle 12 Räder (Tabelle "Fahrräder"). */
    static List<Fahrrad> testRaeder() {
        List<Fahrrad> r = new ArrayList<>();
        r.add(new Fahrrad(101, "E-Bike", 85, 1234.5, false));
        r.add(new Fahrrad(102, "E-Bike", 15, 2210.0, false));
        r.add(new Fahrrad(103, "Lastenrad", 60, 540.2, true));
        r.add(new Fahrrad(104, "E-Bike", 22, 980.7, false));
        r.add(new Fahrrad(105, "E-Bike", 95, 310.0, false));
        r.add(new Fahrrad(106, "Lastenrad", 40, 1502.3, false));
        r.add(new Fahrrad(107, "E-Bike", 8, 3120.9, false));
        r.add(new Fahrrad(108, "E-Bike", 71, 875.4, true));
        r.add(new Fahrrad(109, "E-Bike", 55, 455.0, false));
        r.add(new Fahrrad(110, "Lastenrad", 28, 760.8, false));
        r.add(new Fahrrad(111, "E-Bike", 90, 120.3, false));
        r.add(new Fahrrad(112, "E-Bike", 33, 1999.9, false));
        return r;
    }

    /** Sucht ein Rad in der Liste (nur für den Aufbau der Testdaten). */
    static Fahrrad rad(List<Fahrrad> raeder, int nr) {
        for (Fahrrad f : raeder) {
            if (f.getRadNr() == nr) {
                return f;
            }
        }
        throw new IllegalStateException("Rad " + nr + " fehlt");
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

    // a) jede Radnummer genau einmal, in der Reihenfolge des ersten Auftretens
    static List<Integer> genutzteRaeder(List<Ausleihe> ausleihen) {
        List<Integer> ergebnis = new ArrayList<>();
        for (int i = 0; i < ausleihen.size(); i++) {
            int radNr = ausleihen.get(i).getRadNr();
            if (!ergebnis.contains(radNr)) {
                ergebnis.add(radNr);
            }
        }
        return ergebnis;
    }

    // b) defekte Räder entfernen - rückwärts, damit kein Element übersprungen wird
    static int entferneDefekte(List<Fahrrad> raeder) {
        int entfernt = 0;
        for (int i = raeder.size() - 1; i >= 0; i--) {
            if (raeder.get(i).isDefekt()) {
                raeder.remove(i);            // remove(int index)
                entfernt = entfernt + 1;
            }
        }
        return entfernt;
    }

    // c) FEHLERHAFTE Variante zum Vergleich: vorwärts mit normalem i++
    static int entferneDefekteFalsch(List<Fahrrad> raeder) {
        int entfernt = 0;
        for (int i = 0; i < raeder.size(); i++) {
            if (raeder.get(i).isDefekt()) {
                raeder.remove(i);            // Nachfolger rutscht auf Index i, i++ überspringt ihn
                entfernt = entfernt + 1;
            }
        }
        return entfernt;
    }

    static List<Fahrrad> beispielListe() {
        List<Fahrrad> l = new ArrayList<>();
        l.add(new Fahrrad(107, "E-Bike", 8, 3120.9, false));
        l.add(new Fahrrad(108, "E-Bike", 71, 875.4, true));
        l.add(new Fahrrad(103, "Lastenrad", 60, 540.2, true));
        l.add(new Fahrrad(109, "E-Bike", 55, 455.0, false));
        return l;
    }

    static String nummern(List<Fahrrad> l) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < l.size(); i++) {
            sb.append(i == 0 ? "" : ", ").append(l.get(i).getRadNr());
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        System.out.println("a) genutzteRaeder = " + genutzteRaeder(testAusleihen()));

        List<Fahrrad> alle = testRaeder();
        int n = entferneDefekte(alle);
        System.out.println("b) entferneDefekte(Testdaten) = " + n + ", Rest: " + nummern(alle));

        List<Fahrrad> l1 = beispielListe();
        int r1 = entferneDefekte(l1);
        System.out.println("c) richtig (rückwärts) auf [107, 108d, 103d, 109]: entfernt " + r1 + " -> " + nummern(l1));
        List<Fahrrad> l2 = beispielListe();
        int r2 = entferneDefekteFalsch(l2);
        System.out.println("   falsch (vorwärts, i++)  auf [107, 108d, 103d, 109]: entfernt " + r2 + " -> " + nummern(l2));
    }
}
