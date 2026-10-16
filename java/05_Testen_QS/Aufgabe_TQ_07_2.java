/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-07.2
 * Ursprüngliche Methode p (schlechter Stil) und überarbeitete Methode berechneGastkartenPreis
 * nach Clean-Code-Regeln. Ein Vergleichstest (Regressionstest) zeigt, dass sich das
 * Verhalten durch das Refactoring nicht geändert hat.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_07_2.java
 */
public class Aufgabe_TQ_07_2 {

    // ---------- Original aus der Aufgabe ----------
    // Preis
    public static double p(int n, boolean e, boolean s) {
        double x = 0;
        if (e == true) {
            x = 10.0 * n;
            if (n >= 10) {
                x = 10.0 * n - 10.0 * n * 0.1;
            }
        } else {
            x = 15.0 * n;
            if (n >= 10) {
                x = 15.0 * n - 15.0 * n * 0.1;
            }
        }
        if (s == true) x = x + 5.0 * n;   // plus 5 mal n
        return x;
    }

    // ---------- überarbeitete Fassung ----------
    private static final double PREIS_TAGESKARTE = 15.00;
    private static final double PREIS_TAGESKARTE_ERMAESSIGT = 10.00;
    private static final double SAUNA_ZUSCHLAG_JE_KARTE = 5.00;
    private static final int MENGENRABATT_AB_ANZAHL = 10;
    private static final double MENGENRABATT_SATZ = 0.10;

    /**
     * Berechnet den Gesamtpreis für Gast-Tageskarten nach der Preisliste (Anlage 3).
     * Ab 10 Karten wird der Kartenpreis um 10 % ermäßigt;
     * der Sauna-Zuschlag wird nicht rabattiert.
     *
     * @param anzahlKarten  Anzahl der gekauften Tageskarten (mindestens 1)
     * @param ermaessigt    true, wenn ein Ermäßigungsnachweis (Schüler, Studierende) vorliegt
     * @param mitSauna      true, wenn die Karten die Saunanutzung einschließen
     * @return Gesamtpreis in Euro
     */
    public static double berechneGastkartenPreis(int anzahlKarten, boolean ermaessigt, boolean mitSauna) {
        double einzelpreis = ermaessigt ? PREIS_TAGESKARTE_ERMAESSIGT : PREIS_TAGESKARTE;
        double preis = einzelpreis * anzahlKarten;
        if (anzahlKarten >= MENGENRABATT_AB_ANZAHL) {
            preis = preis * (1 - MENGENRABATT_SATZ);
        }
        if (mitSauna) {
            preis = preis + SAUNA_ZUSCHLAG_JE_KARTE * anzahlKarten;
        }
        return preis;
    }

    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.GERMANY);   // gleiche Zahlendarstellung auf jedem Rechner
        int vergleiche = 0, abweichungen = 0;
        for (int n = 1; n <= 25; n++) {
            for (boolean e : new boolean[]{false, true}) {
                for (boolean s : new boolean[]{false, true}) {
                    vergleiche++;
                    double alt = p(n, e, s);
                    double neu = berechneGastkartenPreis(n, e, s);
                    if (Math.abs(alt - neu) > 0.000001) {
                        abweichungen++;
                        System.out.printf("Abweichung n=%d e=%b s=%b: alt %.2f, neu %.2f%n", n, e, s, alt, neu);
                    }
                }
            }
        }
        System.out.println("Regressionsvergleich alt/neu: " + vergleiche + " Kombinationen, " + abweichungen + " Abweichungen");
        System.out.printf("Beispiele: 1 Karte normal = %.2f | 3 Karten ermäßigt + Sauna = %.2f | 10 Karten normal = %.2f | 10 Karten normal + Sauna = %.2f%n",
                berechneGastkartenPreis(1, false, false), berechneGastkartenPreis(3, true, true),
                berechneGastkartenPreis(10, false, false), berechneGastkartenPreis(10, false, true));
    }
}
