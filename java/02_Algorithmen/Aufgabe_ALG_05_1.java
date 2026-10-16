import java.util.Arrays;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-05.1
 * Bubblesort durchspielen (Durchläufe protokollieren)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_05_1.java
 * oder:  javac Aufgabe_ALG_05_1.java  und danach  java Aufgabe_ALG_05_1
 */
public class Aufgabe_ALG_05_1 {

    public static void main(String[] args) {
        int[] zahlen = {42, 17, 8, 33, 25};
        System.out.println("Start:          " + Arrays.toString(zahlen));
        boolean getauscht = true;
        int durchlauf = 0;
        while (getauscht) {
            getauscht = false;
            int tausche = 0;
            for (int j = 0; j < zahlen.length - 1 - durchlauf; j++) {
                if (zahlen[j] > zahlen[j + 1]) {
                    int h = zahlen[j];
                    zahlen[j] = zahlen[j + 1];
                    zahlen[j + 1] = h;
                    getauscht = true;
                    tausche++;
                }
            }
            durchlauf++;
            System.out.println("nach Durchlauf " + durchlauf + ": " + Arrays.toString(zahlen)
                    + "  Vertauschungen: " + tausche);
        }
        System.out.println("Abbruch, weil im letzten Durchlauf nicht getauscht wurde.");
    }
}
