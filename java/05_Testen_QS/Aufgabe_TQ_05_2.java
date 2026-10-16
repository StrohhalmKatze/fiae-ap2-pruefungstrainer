/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-05.2
 * Schreibtischtest der Funktion spitzenphasen (verschachtelte Schleifen über ein 2D-Array),
 * fehlerhafte Fassung aus der Aufgabe und korrigierte Fassung.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_05_2.java
 */
public class Aufgabe_TQ_05_2 {

    // ---------- Original aus der Aufgabe: folge wird nur einmal vor der äußeren Schleife auf 0 gesetzt ----------
    static int spitzenphasenFehlerhaft(int[][] besucher, int grenze, boolean trace) {
        int anzahl = 0;                                              // 02
        int folge = 0;                                               // 03  Fehler: gehört in die äußere Schleife
        if (trace) System.out.println("   tag | std | besucher | > " + grenze + "?  | folge | anzahl");
        for (int tag = 0; tag <= besucher.length - 1; tag++) {      // 04
            for (int std = 0; std <= besucher[tag].length - 1; std++) {   // 05
                boolean b = besucher[tag][std] > grenze;            // 06
                if (b) {
                    folge = folge + 1;                               // 07
                    if (folge == 2) {                                // 08
                        anzahl = anzahl + 1;                         // 09
                    }
                } else {
                    folge = 0;                                       // 12
                }
                if (trace) System.out.printf("   %3d | %3d | %8d | %-7s | %5d | %6d%n",
                        tag, std, besucher[tag][std], b ? "wahr" : "falsch", folge, anzahl);
            }
        }
        return anzahl;                                               // 16
    }

    // ---------- korrigierte Fassung ----------
    static int spitzenphasen(int[][] besucher, int grenze) {
        int anzahl = 0;
        for (int tag = 0; tag <= besucher.length - 1; tag++) {
            int folge = 0;                                           // für jeden Tag neu beginnen
            for (int std = 0; std <= besucher[tag].length - 1; std++) {
                if (besucher[tag][std] > grenze) {
                    folge = folge + 1;
                    if (folge == 2) {
                        anzahl = anzahl + 1;
                    }
                } else {
                    folge = 0;
                }
            }
        }
        return anzahl;
    }

    public static void main(String[] args) {
        int[][] besucher = {
                {40, 85, 90, 95},   // Montag   17-20 Uhr
                {70, 82, 60, 88},   // Dienstag
                {91, 50, 96, 97}    // Mittwoch
        };
        int grenze = 80;
        System.out.println("a) Schreibtischtest der fehlerhaften Funktion (grenze = 80):");
        int ist = spitzenphasenFehlerhaft(besucher, grenze, true);
        System.out.println("   tatsächliches Ergebnis: " + ist);
        System.out.println("b) erwartetes Ergebnis laut Spezifikation (korrigierte Funktion): " + spitzenphasen(besucher, grenze));
        System.out.println();
        int[][] ohneUebertrag = {{40, 85, 90, 95}, {70, 82, 60, 50}, {91, 50, 96, 97}};
        System.out.println("Zusatz: Dienstag endet unter der Grenze -> fehlerhaft "
                + spitzenphasenFehlerhaft(ohneUebertrag, grenze, false) + ", korrigiert " + spitzenphasen(ohneUebertrag, grenze)
                + " (Fehler bleibt mit solchen Testdaten unentdeckt)");
    }
}
