import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-03.3
 * Nächste Station mit genügend einsatzbereiten Rädern (nächster Nachbar)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_03_3.java
 * oder:  javac Aufgabe_ALG_03_3.java  und danach  java Aufgabe_ALG_03_3
 */
public class Aufgabe_ALG_03_3 {

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

    /** Klasse Station (Anlage 2): eine Station verwaltet die dort abgestellten Räder. */
    static class Station {
        private final int stationNr;
        private final String name;
        private final double x;
        private final double y;
        private final int kapazitaet;
        private final List<Fahrrad> raeder = new ArrayList<>();

        Station(int stationNr, String name, double x, double y, int kapazitaet) {
            this.stationNr = stationNr;
            this.name = name;
            this.x = x;
            this.y = y;
            this.kapazitaet = kapazitaet;
        }

        int getStationNr() { return stationNr; }
        String getName() { return name; }
        double getX() { return x; }
        double getY() { return y; }
        int getKapazitaet() { return kapazitaet; }
        List<Fahrrad> getRaeder() { return raeder; }

        @Override
        public String toString() {
            return "Station " + stationNr + " " + name;
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

    /** Die 6 Stationen mit ihren Rädern (Tabelle "Stationen", Spalte "Räder"). */
    static List<Station> testStationen(List<Fahrrad> raeder) {
        List<Station> s = new ArrayList<>();
        s.add(new Station(1, "Hauptbahnhof", 2.0, 1.0, 20));
        s.add(new Station(2, "Marktplatz", 3.5, 2.5, 12));
        s.add(new Station(3, "Uni-Campus", 6.0, 4.0, 16));
        s.add(new Station(4, "Hafen", 1.0, 5.5, 10));
        s.add(new Station(5, "Klinikum", 5.0, 7.0, 8));
        s.add(new Station(6, "Seepark", 8.5, 1.5, 10));
        int[][] belegung = { {101, 102, 103}, {104, 105}, {106, 107, 108}, {109}, {110}, {111, 112} };
        for (int i = 0; i < belegung.length; i++) {
            for (int nr : belegung[i]) {
                s.get(i).getRaeder().add(rad(raeder, nr));
            }
        }
        return s;
    }

    /** Hilfsfunktion aus Anlage 5: Luftlinie in km. */
    static double entfernung(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    // b) nächstgelegene Station mit mindestens "benoetigt" einsatzbereiten Rädern
    static Station naechsteStation(List<Station> stationen, double kx, double ky,
                                   int benoetigt) {
        Station beste = null;
        double minEntfernung = 0.0;              // wird erst gültig, wenn beste != null
        for (int i = 0; i < stationen.size(); i++) {
            Station s = stationen.get(i);
            int verfuegbar = 0;
            for (int j = 0; j < s.getRaeder().size(); j++) {
                Fahrrad r = s.getRaeder().get(j);
                if (!r.isDefekt() && r.getAkkuStand() >= 30) {
                    verfuegbar = verfuegbar + 1;
                }
            }
            if (verfuegbar >= benoetigt) {
                double e = entfernung(kx, ky, s.getX(), s.getY());
                if (beste == null || e < minEntfernung) {
                    beste = s;
                    minEntfernung = e;
                }
            }
        }
        return beste;
    }

    public static void main(String[] args) {
        List<Station> stationen = testStationen(testRaeder());
        double kx = 4.0, ky = 3.0;
        System.out.printf(Locale.GERMANY, "a) Entfernung Kunde (4,0|3,0) - Station 3 (6,0|4,0) = %.2f km%n",
                entfernung(kx, ky, 6.0, 4.0));
        System.out.println("   Übersicht je Station:");
        for (Station s : stationen) {
            int v = 0;
            for (Fahrrad r : s.getRaeder()) {
                if (!r.isDefekt() && r.getAkkuStand() >= 30) {
                    v++;
                }
            }
            System.out.printf(Locale.GERMANY, "   %-24s Entfernung %.2f km, einsatzbereit: %d%n",
                    s.toString(), entfernung(kx, ky, s.getX(), s.getY()), v);
        }
        for (int b = 1; b <= 3; b++) {
            System.out.println("b/c) naechsteStation(stationen, 4.0, 3.0, " + b + ") = " + naechsteStation(stationen, kx, ky, b));
        }
    }
}
