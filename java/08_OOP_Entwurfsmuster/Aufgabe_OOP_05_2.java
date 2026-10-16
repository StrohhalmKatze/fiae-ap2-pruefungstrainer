import java.util.Locale;

/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-05.2
 * Strategie-Muster: Paketauftrag (Kontext) delegiert die Preisberechnung an ein
 * austauschbares Objekt vom Typ Preisstrategie.
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_05_2.java
 * oder:  javac Aufgabe_OOP_05_2.java  und danach  java Aufgabe_OOP_05_2
 */
public class Aufgabe_OOP_05_2 {

    public static void main(String[] args) {
        Preisstrategie[] tarife = {new Privattarif(), new Geschaeftstarif(), new Aktionstarif()};
        double[] gewichte = {1.5, 2.0, 6.0, 14.0};

        System.out.print("Gewicht ");
        for (Preisstrategie t : tarife) {
            System.out.print(String.format("%16s", t.getClass().getSimpleName()));
        }
        System.out.println();
        for (double g : gewichte) {
            System.out.print(String.format(Locale.GERMANY, "%5.1f kg", g));
            for (Preisstrategie t : tarife) {
                Paketauftrag auftrag = new Paketauftrag(g, t);
                System.out.print(String.format(Locale.GERMANY, "%16.2f", auftrag.berechnePreis()));
            }
            System.out.println();
        }

        System.out.println("Strategie zur Laufzeit wechseln:");
        Paketauftrag a = new Paketauftrag(6.0, new Privattarif());
        System.out.println(String.format(Locale.GERMANY, "  6 kg, Privattarif:  %.2f EUR", a.berechnePreis()));
        a.setStrategie(new Aktionstarif());
        System.out.println(String.format(Locale.GERMANY, "  6 kg, Aktionstarif: %.2f EUR", a.berechnePreis()));
    }
}

// #region strategie
interface Preisstrategie {
    double preis(double gewicht);
}

class Privattarif implements Preisstrategie {
    @Override
    public double preis(double gewicht) {
        if (gewicht <= 2.0) {
            return 4.99;
        } else if (gewicht <= 10.0) {
            return 7.49;
        }
        return 11.99;
    }
}

class Geschaeftstarif implements Preisstrategie {
    @Override
    public double preis(double gewicht) {
        return 3.20 + 0.25 * gewicht;
    }
}

class Aktionstarif implements Preisstrategie {
    @Override
    public double preis(double gewicht) {
        return 3.99;
    }
}

class Paketauftrag {
    private double gewicht;
    private Preisstrategie strategie;

    public Paketauftrag(double gewicht, Preisstrategie strategie) {
        this.gewicht = gewicht;
        this.strategie = strategie;
    }

    public void setStrategie(Preisstrategie strategie) {
        this.strategie = strategie;
    }

    public double berechnePreis() {
        return strategie.preis(gewicht);
    }
}
// #endregion strategie
