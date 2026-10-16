import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-02.3
 * Redundante Fallunterscheidungen nach "typ" durch Vererbung ersetzen:
 * abstrakte Klasse Postsendung, Unterklassen Brief, Paket, Expresssendung,
 * Interface Versicherbar (nur Paket und Expresssendung).
 *
 * Zum Vergleich enthält die Datei auch die bisherige Klasse (PostsendungAlt).
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_02_3.java
 * oder:  javac Aufgabe_OOP_02_3.java  und danach  java Aufgabe_OOP_02_3
 */
public class Aufgabe_OOP_02_3 {

    public static void main(String[] args) {
        List<Postsendung> sendungen = new ArrayList<>();
        sendungen.add(new Brief("B-1", 0.02));
        sendungen.add(new Brief("B-2", 0.40));
        sendungen.add(new Paket("P-1", 3.0));
        sendungen.add(new Paket("P-2", 12.0));
        sendungen.add(new Expresssendung("E-1", 2.5));

        System.out.println("Nr    Art             Gewicht  Porto   max.kg  Station");
        for (Postsendung s : sendungen) {
            System.out.println(String.format(Locale.GERMANY, "%-5s %-15s %5.2f  %6.2f  %6.1f  %s",
                    s.getSendungsNr(), s.getClass().getSimpleName(), s.getGewicht(),
                    s.berechnePorto(), s.maxGewicht(), s.istStationsfaehig() ? "ja" : "nein"));
        }

        System.out.println("Versicherung (nur Objekte, die Versicherbar realisieren):");
        double[] warenwerte = {0, 0, 150.0, 600.0, 400.0};
        for (int i = 0; i < sendungen.size(); i++) {
            Postsendung s = sendungen.get(i);
            if (s instanceof Versicherbar) {
                Versicherbar v = (Versicherbar) s;
                System.out.println(String.format(Locale.GERMANY, "  %s  Warenwert %7.2f -> Versicherung %5.2f",
                        s.getSendungsNr(), warenwerte[i], v.berechneVersicherung(warenwerte[i])));
            }
        }

        System.out.println("Gegenprobe mit der bisherigen Klasse (Fallunterscheidung nach typ):");
        PostsendungAlt alt = new PostsendungAlt("E-1", "EXPRESS", 2.5);
        System.out.println(String.format(Locale.GERMANY, "  E-1 alt: Porto %.2f, Versicherung(400) %.2f",
                alt.berechnePorto(), alt.berechneVersicherung(400.0)));
    }
}

// #region hierarchie
abstract class Postsendung {
    private String sendungsNr;
    private double gewicht;

    public Postsendung(String sendungsNr, double gewicht) {
        this.sendungsNr = sendungsNr;
        this.gewicht = gewicht;
    }

    public String getSendungsNr() {
        return sendungsNr;
    }

    public double getGewicht() {
        return gewicht;
    }

    public abstract double berechnePorto();

    public abstract double maxGewicht();

    public abstract boolean istStationsfaehig();
}

interface Versicherbar {
    double berechneVersicherung(double warenwert);
}

class Brief extends Postsendung {
    public Brief(String sendungsNr, double gewicht) {
        super(sendungsNr, gewicht);
    }

    @Override
    public double berechnePorto() {
        if (getGewicht() <= 0.05) {
            return 0.95;
        }
        return 1.80;
    }

    @Override
    public double maxGewicht() {
        return 1.0;
    }

    @Override
    public boolean istStationsfaehig() {
        return false;
    }
}
// #endregion hierarchie

// #region paket
class Paket extends Postsendung implements Versicherbar {
    public Paket(String sendungsNr, double gewicht) {
        super(sendungsNr, gewicht);
    }

    @Override
    public double berechnePorto() {
        if (getGewicht() <= 5.0) {
            return 5.49;
        }
        return 8.99;
    }

    @Override
    public double maxGewicht() {
        return 31.5;
    }

    @Override
    public boolean istStationsfaehig() {
        return true;
    }

    @Override
    public double berechneVersicherung(double warenwert) {
        double betrag = warenwert * 0.01;
        if (betrag < 2.00) {
            betrag = 2.00;
        }
        return betrag;
    }
}
// #endregion paket

// #region express
class Expresssendung extends Postsendung implements Versicherbar {
    public Expresssendung(String sendungsNr, double gewicht) {
        super(sendungsNr, gewicht);
    }

    @Override
    public double berechnePorto() {
        return 12.90 + 0.80 * getGewicht();
    }

    @Override
    public double maxGewicht() {
        return 20.0;
    }

    @Override
    public boolean istStationsfaehig() {
        return false;
    }

    @Override
    public double berechneVersicherung(double warenwert) {
        return warenwert * 0.005;
    }
}
// #endregion express

/** Bisherige Fassung mit redundanten Fallunterscheidungen (Anlage 6) - nur zum Vergleich. */
class PostsendungAlt {
    private String sendungsNr;
    private String typ;
    private double gewicht;

    PostsendungAlt(String sendungsNr, String typ, double gewicht) {
        this.sendungsNr = sendungsNr;
        this.typ = typ;
        this.gewicht = gewicht;
    }

    double berechnePorto() {
        switch (typ) {
            case "BRIEF":   return gewicht <= 0.05 ? 0.95 : 1.80;
            case "PAKET":   return gewicht <= 5.0 ? 5.49 : 8.99;
            case "EXPRESS": return 12.90 + 0.80 * gewicht;
            default:        return 0.0;
        }
    }

    double berechneVersicherung(double warenwert) {
        switch (typ) {
            case "PAKET":   return Math.max(warenwert * 0.01, 2.00);
            case "EXPRESS": return warenwert * 0.005;
            default:        return 0.0;   // Briefe sind nicht versicherbar
        }
    }
}
