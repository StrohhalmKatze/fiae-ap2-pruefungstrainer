import java.util.ArrayList;
import java.util.List;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-03.2
 * Lineare Suche mit Abbruch und Maximum mit Bedingung
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_03_2.java
 * oder:  javac Aufgabe_ALG_03_2.java  und danach  java Aufgabe_ALG_03_2
 */
public class Aufgabe_ALG_03_2 {

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

    // a) lineare Suche mit Abbruch; null, wenn nicht gefunden
    static Fahrrad findeRad(List<Fahrrad> raeder, int radNr) {
        Fahrrad gefunden = null;
        int i = 0;
        while (i < raeder.size() && gefunden == null) {
            if (raeder.get(i).getRadNr() == radNr) {
                gefunden = raeder.get(i);
            }
            i = i + 1;
        }
        return gefunden;
    }

    // b) nicht defektes Rad eines Typs mit dem höchsten Akkustand; null, wenn keines passt
    static Fahrrad vollstesRad(List<Fahrrad> raeder, String typ) {
        Fahrrad bestes = null;
        for (int i = 0; i < raeder.size(); i++) {
            Fahrrad r = raeder.get(i);
            if (r.getTyp().equals(typ) && !r.isDefekt()) {
                if (bestes == null || r.getAkkuStand() > bestes.getAkkuStand()) {
                    bestes = r;
                }
            }
        }
        return bestes;
    }

    public static void main(String[] args) {
        List<Fahrrad> raeder = testRaeder();
        System.out.println("a) findeRad(raeder, 109) = " + findeRad(raeder, 109));
        System.out.println("   findeRad(raeder, 200) = " + findeRad(raeder, 200));
        System.out.println("b) vollstesRad(raeder, \"Lastenrad\") = " + vollstesRad(raeder, "Lastenrad"));
        System.out.println("   vollstesRad(raeder, \"E-Bike\")    = " + vollstesRad(raeder, "E-Bike"));
        System.out.println("   vollstesRad(raeder, \"Tandem\")    = " + vollstesRad(raeder, "Tandem"));
    }
}
