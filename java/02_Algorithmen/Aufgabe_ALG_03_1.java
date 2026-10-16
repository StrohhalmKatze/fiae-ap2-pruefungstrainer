
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-03.1
 * Fehlerhafte Minimumsuche korrigieren
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_03_1.java
 * oder:  javac Aufgabe_ALG_03_1.java  und danach  java Aufgabe_ALG_03_1
 */
public class Aufgabe_ALG_03_1 {

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

    // gegebene, FEHLERHAFTE Funktion
    static int minAkkuFalsch(Fahrrad[] raeder) {
        int min = 0;
        for (int i = 1; i <= raeder.length - 1; i++) {
            if (raeder[i].getAkkuStand() < min) {
                min = raeder[i].getAkkuStand();
            }
        }
        return min;
    }

    // korrigierte Funktion: Initialisierung mit dem ersten Element
    static int minAkku(Fahrrad[] raeder) {
        int min = raeder[0].getAkkuStand();
        for (int i = 1; i <= raeder.length - 1; i++) {
            if (raeder[i].getAkkuStand() < min) {
                min = raeder[i].getAkkuStand();
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Fahrrad[] raeder = {
            new Fahrrad(101, "E-Bike", 85, 1234.5, false),
            new Fahrrad(102, "E-Bike", 15, 2210.0, false),
            new Fahrrad(103, "Lastenrad", 60, 540.2, true),
            new Fahrrad(104, "E-Bike", 22, 980.7, false),
            new Fahrrad(105, "E-Bike", 95, 310.0, false)
        };
        System.out.println("fehlerhaft: minAkkuFalsch(101..105) = " + minAkkuFalsch(raeder));
        System.out.println("korrigiert: minAkku(101..105)      = " + minAkku(raeder));
        try {
            minAkku(new Fahrrad[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("leeres Array -> " + e.getClass().getSimpleName() + " (Vorbedingung: mindestens 1 Element)");
        }
    }
}
