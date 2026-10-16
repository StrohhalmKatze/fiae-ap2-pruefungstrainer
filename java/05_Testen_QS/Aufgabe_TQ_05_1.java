/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-05.1
 * Schreibtischtest der Funktion bonuspunkte als Programmlauf (Trace-Tabelle).
 *
 * Start (JDK 11+):  java Aufgabe_TQ_05_1.java
 */
public class Aufgabe_TQ_05_1 {

    // Funktion aus der Aufgabe (Pseudocode -> Java)
    static int bonuspunkte(int[] minuten, boolean trace) {
        int punkte = 0;                                        // 02
        if (trace) System.out.println("   i | minuten[i] | >= 60 (Z. 04) | >= 30 (Z. 07) | punkte");
        if (trace) System.out.println("   - |     -      |       -       |       -       | " + punkte);
        for (int i = 0; i <= minuten.length - 1; i++) {        // 03
            boolean b60 = minuten[i] >= 60;                     // 04
            String b30 = "-";
            if (b60) {
                punkte = punkte + 3;                            // 05
            } else {
                b30 = (minuten[i] >= 30) ? "wahr" : "falsch";   // 07
                if (minuten[i] >= 30) {
                    punkte = punkte + 1;                        // 08
                }
            }
            if (trace) System.out.printf("   %d | %10d | %-13s | %-13s | %d%n", i, minuten[i], b60 ? "wahr" : "falsch", b30, punkte);
        }
        return punkte;                                         // 12
    }

    public static void main(String[] args) {
        int[] minuten = {45, 70, 20, 60, 30};
        System.out.println("Schreibtischtest bonuspunkte([45, 70, 20, 60, 30]):");
        int ergebnis = bonuspunkte(minuten, true);
        System.out.println("   Rückgabewert: " + ergebnis);
        System.out.println("Kontrolle Grenzwerte: [59] -> " + bonuspunkte(new int[]{59}, false)
                + ", [29] -> " + bonuspunkte(new int[]{29}, false)
                + ", [] -> " + bonuspunkte(new int[]{}, false));
    }
}
