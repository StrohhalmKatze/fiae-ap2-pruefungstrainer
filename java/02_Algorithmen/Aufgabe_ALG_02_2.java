import java.util.Arrays;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-02.2
 * 2D-Array: Zeilen- und Spaltensummen
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_02_2.java
 * oder:  javac Aufgabe_ALG_02_2.java  und danach  java Aufgabe_ALG_02_2
 */
public class Aufgabe_ALG_02_2 {

    /** Anlage 4: Ausleihen an Station 1 in KW 38, Zeilen = Wochentag (0 = Mo), Spalten = Zeitfenster. */
    static final int[][] AUSLEIHEN_KW38 = {
        {1, 14,  9, 11, 18,  4},   // Mo
        {0, 16,  8, 10, 21,  3},   // Di
        {2, 15, 11,  9, 19,  5},   // Mi
        {0, 13, 10, 12, 22,  6},   // Do
        {3, 12, 12, 15, 17,  9},   // Fr
        {5,  2, 14, 20, 16, 11},   // Sa
        {6,  1, 10, 18, 13,  7}    // So
    };

    // a) Summe je Zeile (= je Wochentag)
    static int[] summeJeTag(int[][] daten) {
        int[] summen = new int[daten.length];
        for (int tag = 0; tag < daten.length; tag++) {
            int summe = 0;
            for (int f = 0; f < daten[tag].length; f++) {
                summe = summe + daten[tag][f];
            }
            summen[tag] = summe;
        }
        return summen;
    }

    // b) Summe je Spalte (= je Zeitfenster)
    static int[] summeJeZeitfenster(int[][] daten) {
        int anzahlFenster = daten[0].length;
        int[] summen = new int[anzahlFenster];
        for (int f = 0; f < anzahlFenster; f++) {
            int summe = 0;
            for (int tag = 0; tag < daten.length; tag++) {
                summe = summe + daten[tag][f];
            }
            summen[f] = summe;
        }
        return summen;
    }

    public static void main(String[] args) {
        int[] proTag = summeJeTag(AUSLEIHEN_KW38);
        int[] proFenster = summeJeZeitfenster(AUSLEIHEN_KW38);
        System.out.println("a) summeJeTag         = " + Arrays.toString(proTag));
        System.out.println("b) summeJeZeitfenster = " + Arrays.toString(proFenster));
        int gesamt = 0;
        for (int s : proTag) {
            gesamt += s;
        }
        int gesamt2 = 0;
        for (int s : proFenster) {
            gesamt2 += s;
        }
        System.out.println("Kontrolle: Gesamtsumme über Zeilen = " + gesamt + ", über Spalten = " + gesamt2);
    }
}
