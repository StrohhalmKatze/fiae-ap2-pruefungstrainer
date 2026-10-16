
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-07.2
 * Ablauf von try/catch/finally nachvollziehen
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_07_2.java
 * oder:  javac Aufgabe_ALG_07_2.java  und danach  java Aufgabe_ALG_07_2
 */
public class Aufgabe_ALG_07_2 {

    // gegebene Methode aus der Aufgabe (unverändert)
    static int centProMinute(String betragText, int minuten) {
        int ergebnis = -1;
        try {
            System.out.print("A ");
            if (minuten < 0) {
                throw new IllegalArgumentException("Minuten negativ");
            }
            int betrag = Integer.parseInt(betragText);
            ergebnis = betrag / minuten;
            System.out.print("B ");
        } catch (NumberFormatException e) {
            System.out.print("C ");
        } catch (ArithmeticException e) {
            System.out.print("D ");
            ergebnis = 0;
        } finally {
            System.out.print("E ");
        }
        System.out.print("F ");
        return ergebnis;
    }

    public static void main(String[] args) {
        String[] texte = {"750", "7,50", "750", "750"};
        int[] minuten = {30, 30, 0, -5};
        for (int i = 0; i < texte.length; i++) {
            System.out.print((i + 1) + ") centProMinute(\"" + texte[i] + "\", " + minuten[i] + "): Ausgabe ");
            try {
                int r = centProMinute(texte[i], minuten[i]);
                System.out.println("| Rückgabe " + r);
            } catch (IllegalArgumentException e) {
                System.out.println("| keine Rückgabe");
                System.out.println("   -> Ausnahme beim Aufrufer: " + e.getClass().getSimpleName()
                        + ": " + e.getMessage());
            }
        }
    }
}
