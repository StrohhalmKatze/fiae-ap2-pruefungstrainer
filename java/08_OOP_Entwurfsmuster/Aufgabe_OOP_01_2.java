/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-01.2
 * Klasse Etikett: statischer Zähler, überladener Konstruktor mit this(...),
 * Setter mit Prüfung, Referenzen (Aliasing), == gegenüber equals.
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_01_2.java
 * oder:  javac Aufgabe_OOP_01_2.java  und danach  java Aufgabe_OOP_01_2
 */
public class Aufgabe_OOP_01_2 {

    public static void main(String[] args) {
        // #region test
        Etikett e1 = new Etikett("Ahrens", 2.5);
        Etikett e2 = new Etikett("Bode");
        Etikett e3 = e1;
        e3.setGewicht(4.0);
        e2.setGewicht(40.0);
        Etikett e4 = new Etikett("Ahrens", 4.0);
        Etikett e5 = new Etikett("Cramer", 0.0);

        System.out.println(e1);                    // (1)
        System.out.println(e2);                    // (2)
        System.out.println(e1 == e3);              // (3)
        System.out.println(e1 == e4);              // (4)
        System.out.println(e1.equals(e4));         // (5)
        System.out.println(Etikett.getAnzahl());   // (6)
        // #endregion test

        System.out.println("--- Zusatz (nicht Teil der Aufgabe) ---");
        System.out.println("e5 = " + e5 + "  (Gewicht 0.0 wurde abgelehnt, Startwert 1.0 bleibt)");
        Etikett e6 = null;
        try {
            System.out.println(e6.getNummer());
        } catch (NullPointerException ex) {
            System.out.println("e6.getNummer() mit e6 = null -> NullPointerException");
        }
    }
}

// #region etikett
class Etikett {
    private static int naechsteNr = 1000;
    private int nummer;
    private String empfaenger;
    private double gewicht;

    public Etikett(String empfaenger, double gewicht) {
        this.nummer = naechsteNr;
        naechsteNr = naechsteNr + 1;
        this.empfaenger = empfaenger;
        this.gewicht = 1.0;
        setGewicht(gewicht);
    }

    public Etikett(String empfaenger) {
        this(empfaenger, 1.0);
    }

    public void setGewicht(double gewicht) {
        if (gewicht > 0 && gewicht <= 31.5) {
            this.gewicht = gewicht;
        }
    }

    public int getNummer() {
        return nummer;
    }

    public static int getAnzahl() {
        return naechsteNr - 1000;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Etikett)) {
            return false;
        }
        Etikett e = (Etikett) o;
        return empfaenger.equals(e.empfaenger) && gewicht == e.gewicht;
    }

    @Override
    public int hashCode() {
        return empfaenger.hashCode() + Double.hashCode(gewicht);
    }

    @Override
    public String toString() {
        return nummer + " | " + empfaenger + " | " + gewicht + " kg";
    }
}
// #endregion etikett
