import java.util.ArrayList;
import java.util.List;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-02.1
 * Array-Grundlagen: Indexgrenzen, 2D-Array
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_02_1.java
 * oder:  javac Aufgabe_ALG_02_1.java  und danach  java Aufgabe_ALG_02_1
 */
public class Aufgabe_ALG_02_1 {

    public static void main(String[] args) {
        int[] belegung = new int[24];
        System.out.println("belegung.length = " + belegung.length
                + ", kleinster Index = 0, größter Index = " + (belegung.length - 1));
        try {
            belegung[24] = 5;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("belegung[24] = 5  ->  " + e.getClass().getSimpleName());
            System.out.println("   Meldung: " + e.getMessage());
        }

        int[][] zaehler = new int[7][24];
        System.out.println("zaehler: " + zaehler.length + " Zeilen x " + zaehler[0].length
                + " Spalten = " + (zaehler.length * zaehler[0].length) + " Elemente");
        zaehler[4][17] = 12;   // Freitag (Index 4), 17-Uhr-Stunde
        System.out.println("zaehler[4][17] = " + zaehler[4][17]);

        List<Integer> liste = new ArrayList<>();
        liste.add(7);
        liste.add(3);
        liste.add(9);
        System.out.println("Liste wächst dynamisch: size() = " + liste.size()
                + " " + liste);
    }
}
