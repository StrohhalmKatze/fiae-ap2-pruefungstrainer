
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-08.3
 * Rekursive binäre Suche (und iterative Variante)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_08_3.java
 * oder:  javac Aufgabe_ALG_08_3.java  und danach  java Aufgabe_ALG_08_3
 */
public class Aufgabe_ALG_08_3 {

    // a) rekursive binäre Suche in einem aufsteigend sortierten Array; -1 = nicht gefunden
    static int suche(int[] nummern, int gesucht, int links, int rechts) {
        String info = links <= rechts ? "  mitte = " + ((links + rechts) / 2) : "  -> links > rechts";
        System.out.println("   suche(nummern, " + gesucht + ", " + links + ", " + rechts + ")" + info);
        if (links > rechts) {
            return -1;                                      // Basisfall 1: Bereich leer
        }
        int mitte = (links + rechts) / 2;
        if (nummern[mitte] == gesucht) {
            return mitte;                                   // Basisfall 2: gefunden
        } else if (nummern[mitte] < gesucht) {
            return suche(nummern, gesucht, mitte + 1, rechts);  // rechte Hälfte
        } else {
            return suche(nummern, gesucht, links, mitte - 1);   // linke Hälfte
        }
    }

    // zum Vergleich: iterative Variante
    static int sucheIterativ(int[] nummern, int gesucht) {
        int links = 0;
        int rechts = nummern.length - 1;
        while (links <= rechts) {
            int mitte = (links + rechts) / 2;
            if (nummern[mitte] == gesucht) {
                return mitte;
            } else if (nummern[mitte] < gesucht) {
                links = mitte + 1;
            } else {
                rechts = mitte - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nummern = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112};
        System.out.println("b) Aufrufe für gesucht = 112:");
        int r = suche(nummern, 112, 0, nummern.length - 1);
        System.out.println("   Ergebnis: Index " + r);
        System.out.println("   Aufrufe für gesucht = 100:");
        System.out.println("   Ergebnis: Index " + suche(nummern, 100, 0, nummern.length - 1));
        System.out.println("   iterativ: 112 -> " + sucheIterativ(nummern, 112) + ", 104 -> " + sucheIterativ(nummern, 104)
                + ", 100 -> " + sucheIterativ(nummern, 100));
        int n = 1000, schritte = 0;
        while (n > 0) {
            n = n / 2;
            schritte++;
        }
        System.out.println("   max. Anzahl Halbierungen bei 1000 Elementen: " + schritte);
    }
}
