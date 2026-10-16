// Lösung UML-07.1: Schreibtischtest zum Struktogramm "gebuehrGesamt"
// Start: java Aufgabe_UML_07_1.java   (JDK 11+, keine Kompilierung nötig)
public class Aufgabe_UML_07_1 {

    // Umsetzung des Struktogramms aus Abb. C-10
    static double gebuehrGesamt(int[] tage, boolean istKind) {
        double summe = 0.00;
        if (istKind) {
            // leerer Zweig (Kinder zahlen keine Säumnisgebühr)
        } else {
            for (int i = 0; i <= tage.length - 1; i++) {
                double g = tage[i] * 0.50;
                if (g > 5.00) {
                    g = 5.00;          // Deckelung je Medium
                }
                summe = summe + g;
            }
        }
        return summe;
    }

    static void pruefe(String aufruf, double ist, double soll) {
        System.out.printf("%-38s -> %6.2f  (erwartet %6.2f) %s%n", aufruf, ist, soll,
                Math.abs(ist - soll) < 1e-9 ? "OK" : "FEHLER");
    }

    public static void main(String[] args) {
        pruefe("gebuehrGesamt([4, 15, 0, 9], falsch)", gebuehrGesamt(new int[]{4, 15, 0, 9}, false), 11.50);
        pruefe("gebuehrGesamt([12], wahr)", gebuehrGesamt(new int[]{12}, true), 0.00);
        pruefe("gebuehrGesamt([10, 11], falsch)", gebuehrGesamt(new int[]{10, 11}, false), 10.00);
        pruefe("gebuehrGesamt([], falsch)", gebuehrGesamt(new int[]{}, false), 0.00);
    }
}
