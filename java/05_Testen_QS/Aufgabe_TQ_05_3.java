/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-05.3
 * a) Methode laengsteSerie: Testtabelle mit erwartetem und tatsächlichem Ergebnis
 * b) Schreibtischtest der rekursiven Funktion serieAb
 * c) korrigierte Fassung
 * d) welche Zeilen der fehlerhaften Methode jeder Testfall ausführt (Anweisungsüberdeckung)
 *
 * Start (JDK 11+):  java Aufgabe_TQ_05_3.java
 */
import java.util.*;

public class Aufgabe_TQ_05_3 {

    static Set<Integer> zeilen = new TreeSet<>();   // ausgeführte Zeilen (nur für d)

    // ---------- Original aus der Aufgabe (Zeilennummern wie im Listing) ----------
    static int laengsteSerieFehlerhaft(boolean[] trainiert) {
        zeilen.add(2);
        if (trainiert == null) {                                         // 02
            zeilen.add(3);
            throw new IllegalArgumentException("keine Daten");           // 03
        }
        zeilen.add(5); int serie = 0;                                     // 05
        zeilen.add(6); int max = 0;                                       // 06
        zeilen.add(7);
        for (int i = 0; i < trainiert.length; i++) {                     // 07
            zeilen.add(8);
            if (trainiert[i]) {                                          // 08
                zeilen.add(9); serie++;                                   // 09
            } else {
                zeilen.add(11);
                if (serie > max) {                                       // 11
                    zeilen.add(12); max = serie;                          // 12
                }
                zeilen.add(14); serie = 0;                                // 14
            }
        }
        zeilen.add(17);
        return max;                                                      // 17
    }

    // ---------- korrigierte Fassung ----------
    static int laengsteSerie(boolean[] trainiert) {
        if (trainiert == null || trainiert.length == 0) {                // Zeile 02 ergänzt
            throw new IllegalArgumentException("keine Daten");
        }
        int serie = 0;
        int max = 0;
        for (int i = 0; i < trainiert.length; i++) {
            if (trainiert[i]) {
                serie++;
                if (serie > max) {        // Maximum sofort nach jedem Trainingstag aktualisieren,
                    max = serie;          // damit auch eine Serie am Ende berücksichtigt wird
                }
            } else {
                serie = 0;
            }
        }
        return max;
    }

    // ---------- rekursive Hilfsfunktion aus b) ----------
    static int serieAb(boolean[] t, int i, int tiefe) {
        String einzug = "   " + "  ".repeat(tiefe);
        boolean ende = i >= t.length;
        String ti = ende ? "nicht geprüft" : String.valueOf(t[i]);
        if (ende || !t[i]) {
            System.out.println(einzug + "Aufruf " + (tiefe + 1) + ": serieAb(t, " + i + ")  i >= t.length: " + ende
                    + ", t[i]: " + ti + "  -> Basisfall, Rückgabe 0");
            return 0;
        }
        System.out.println(einzug + "Aufruf " + (tiefe + 1) + ": serieAb(t, " + i + ")  i >= t.length: false, t[i]: true"
                + "  -> 1 + serieAb(t, " + (i + 1) + ")");
        int r = 1 + serieAb(t, i + 1, tiefe + 1);
        System.out.println(einzug + "Aufruf " + (tiefe + 1) + " liefert " + r);
        return r;
    }

    interface Methode { int rechne(boolean[] t); }

    static String auswerten(Methode m, boolean[] t) {
        try {
            return String.valueOf(m.rechne(t));
        } catch (IllegalArgumentException e) {
            return "Exception";
        }
    }

    static final boolean W = true, F = false;

    public static void main(String[] args) {
        Object[][] tabelle = {
                {"{W, W, F, W, W, W, F}", new boolean[]{W, W, F, W, W, W, F}, "3"},
                {"{W, F, W, W, W, W}",    new boolean[]{W, F, W, W, W, W},    "4"},
                {"{F, F, F}",             new boolean[]{F, F, F},             "0"},
                {"{W, W, W}",             new boolean[]{W, W, W},             "3"},
                {"{} (leeres Array)",     new boolean[]{},                    "Exception"},
                {"null",                  null,                               "Exception"}
        };
        System.out.println("a) Testtabelle (W = true, F = false, Exception = IllegalArgumentException):");
        System.out.println("   TF | Testdaten             | erwartet  | fehlerhaft | korrigiert");
        int k = 1;
        for (Object[] z : tabelle) {
            boolean[] t = (boolean[]) z[1];
            String ist = auswerten(Aufgabe_TQ_05_3::laengsteSerieFehlerhaft, t);
            String neu = auswerten(Aufgabe_TQ_05_3::laengsteSerie, t);
            System.out.printf("   %2d | %-21s | %-9s | %-10s | %-10s%s%n", k++, z[0], z[2], ist, neu,
                    ist.equals(z[2]) ? "" : "   <- Abweichung");
        }

        System.out.println();
        System.out.println("b) Schreibtischtest serieAb(t, 3) mit t = {F, W, F, W, W, W}:");
        boolean[] t = {F, W, F, W, W, W};
        System.out.println("   Ergebnis: " + serieAb(t, 3, 0));
        System.out.println("   Zusatz serieAb(t, 1): " + serieAbStill(t, 1) + ", serieAb(t, 0): " + serieAbStill(t, 0));

        System.out.println();
        System.out.println("d) Ausgeführte Zeilen der fehlerhaften Methode je Testfall:");
        k = 1;
        Set<Integer> alle = new TreeSet<>(List.of(2, 3, 5, 6, 7, 8, 9, 11, 12, 14, 17));
        for (Object[] z : tabelle) {
            zeilen = new TreeSet<>();
            auswerten(Aufgabe_TQ_05_3::laengsteSerieFehlerhaft, (boolean[]) z[1]);
            System.out.println("   TF" + k++ + ": " + zeilen);
        }
        Set<Integer> c = new TreeSet<>();
        zeilen = new TreeSet<>(); auswerten(Aufgabe_TQ_05_3::laengsteSerieFehlerhaft, (boolean[]) tabelle[0][1]); c.addAll(zeilen);
        zeilen = new TreeSet<>(); auswerten(Aufgabe_TQ_05_3::laengsteSerieFehlerhaft, (boolean[]) tabelle[5][1]); c.addAll(zeilen);
        System.out.println("   TF1 + TF6 zusammen: " + c + " -> alle Anweisungszeilen? " + c.containsAll(alle));
    }

    static int serieAbStill(boolean[] t, int i) {
        if (i >= t.length || !t[i]) return 0;
        return 1 + serieAbStill(t, i + 1);
    }
}
