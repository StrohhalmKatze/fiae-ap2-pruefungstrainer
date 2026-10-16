// Lösung UML-07.3: Prüfziffer des Bibliotheksausweises – fehlerhafte und korrigierte Fassung
// Start: java Aufgabe_UML_07_3.java
public class Aufgabe_UML_07_3 {

    // Hilfsfunktion wie in der Aufgabe: Zahlenwert des Zeichens an Position i (ab 0)
    static int ziffer(String nr, int i) {
        return nr.charAt(i) - '0';
    }

    // Fehlerhafte Fassung aus Abb. C-11 (mit Schreibtischtest-Ausgabe)
    static boolean pruefeAusweisFehlerhaft(String nr, boolean trace) {
        if (nr.length() != 8) {
            return false;
        }
        int summe = 0;                       // in Java nötig, im Struktogramm fehlt diese Zeile vor der Schleife
        int gewicht;
        for (int i = 0; i <= 7; i++) {       // Fehler 1: bis 7 statt bis 6
            summe = 0;                       // Fehler 2: Summe wird in jedem Durchlauf zurückgesetzt
            if (i % 2 == 0) {
                gewicht = 3;                 // Fehler 3: Gewichte vertauscht
            } else {
                gewicht = 1;
            }
            summe = summe + ziffer(nr, i) * gewicht;
            if (trace) {
                System.out.printf("  i=%d  gewicht=%d  ziffer=%d  summe=%d%n", i, gewicht, ziffer(nr, i), summe);
            }
        }
        int pz = (10 - summe % 10) % 10;
        if (trace) {
            System.out.printf("  pz=%d  ziffer(nr,7)=%d%n", pz, ziffer(nr, 7));
        }
        return pz == ziffer(nr, 7);
    }

    // Korrigierte Fassung
    static boolean pruefeAusweis(String nr) {
        if (nr.length() != 8) {
            return false;
        }
        int summe = 0;                       // Korrektur 2: einmal vor der Schleife
        int gewicht;
        for (int i = 0; i <= 6; i++) {       // Korrektur 1: nur die ersten sieben Ziffern
            if (i % 2 == 0) {
                gewicht = 1;                 // Korrektur 3: 1., 3., 5., 7. Ziffer mit 1
            } else {
                gewicht = 3;
            }
            summe = summe + ziffer(nr, i) * gewicht;
        }
        int pz = (10 - summe % 10) % 10;
        return pz == ziffer(nr, 7);
    }

    public static void main(String[] args) {
        System.out.println("Schreibtischtest fehlerhafte Fassung mit 40517287:");
        boolean f = pruefeAusweisFehlerhaft("40517287", true);
        System.out.println("  Rückgabe: " + f + "  (erwartet laut Spezifikation: true)");
        System.out.println();
        String[] tests = {"40517287", "40517280", "12345678", "1234567", "00000000"};
        boolean[] soll = {true, false, true, false, true};
        for (int k = 0; k < tests.length; k++) {
            boolean ist = pruefeAusweis(tests[k]);
            System.out.printf("korrigiert: pruefeAusweis(\"%s\") = %b  %s%n", tests[k], ist, ist == soll[k] ? "OK" : "FEHLER");
        }
    }
}
