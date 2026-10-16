import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-03.3
 * Generische Klasse Ablage<T>, generisches Interface Vergleicher<T> (Funktionsobjekt),
 * polymorpher Aufruf von istFaellig() über eine Liste der abstrakten Klasse Lagerposten.
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_03_3.java
 * oder:  javac Aufgabe_OOP_03_3.java  und danach  java Aufgabe_OOP_03_3
 */
public class Aufgabe_OOP_03_3 {


    public static void main(String[] args) {
        Datum heute = new Datum(10, 10, 2026);
        Ablage<Lagerposten> regalA = new Ablage<>("Regal A", 6);

        // Testdaten aus Anlage 8
        Lagerposten[] testdaten = {
            new Retoure("R-101", new Datum(20, 9, 2026), 2.4),
            new Retoure("R-102", new Datum(1, 10, 2026), 0.8),
            new Zollposten("Z-201", new Datum(5, 10, 2026), 12.5, true),
            new Zollposten("Z-202", new Datum(8, 10, 2026), 3.1, false),
            new Lagerauftrag("L-301", new Datum(28, 9, 2026), 18.0, new Datum(10, 10, 2026)),
            new Lagerauftrag("L-302", new Datum(2, 10, 2026), 7.5, new Datum(12, 10, 2026))
        };
        for (Lagerposten p : testdaten) {
            System.out.println("ablegen(" + p.getPostenNr() + ") -> " + regalA.ablegen(p));
        }
        System.out.println("ablegen(R-101) erneut -> " + regalA.ablegen(testdaten[0]) + "  (schon enthalten)");
        Lagerposten neu = new Retoure("R-103", new Datum(9, 10, 2026), 1.1);
        System.out.println("ablegen(R-103) -> " + regalA.ablegen(neu) + "  (Kapazität 6 erreicht)");
        System.out.println("anzahl() = " + regalA.anzahl());

        Lagerverwaltung verwaltung = new Lagerverwaltung();
        System.out.print("c) faelligePosten(regalA, 10.10.2026): ");
        for (Lagerposten p : verwaltung.faelligePosten(regalA, heute)) {
            System.out.print(p.getPostenNr() + " ");
        }
        System.out.println();

        Lagerposten schwerster = regalA.maximum(new GewichtVergleicher());
        System.out.println("e) maximum(GewichtVergleicher) = " + schwerster.getPostenNr()
                + " (" + schwerster.getGewicht() + " kg)");

        // dieselbe generische Klasse mit einem anderen Typ: Typsicherheit ohne Casts
        Ablage<String> notizen = new Ablage<>("Notizbrett", 2);
        notizen.ablegen("Tor 3 gesperrt");
        notizen.ablegen("Zoll ruft zurück");
        String laengste = notizen.maximum((x, y) -> x.length() - y.length());   // Lambda als Vergleicher
        System.out.println("Ablage<String>.maximum(nach Länge) = \"" + laengste + "\"");
        // notizen.ablegen(neu);   // Compilerfehler: Lagerposten ist kein String

        Ablage<String> leer = new Ablage<>("leer", 3);
        System.out.println("maximum einer leeren Ablage = " + leer.maximum((x, y) -> x.compareTo(y)));
    }
}

// #region ablage
class Ablage<T> {
    private String bezeichnung;
    private int kapazitaet;
    private List<T> elemente;

    public Ablage(String bezeichnung, int kapazitaet) {
        this.bezeichnung = bezeichnung;
        this.kapazitaet = kapazitaet;
        this.elemente = new ArrayList<>();
    }

    public boolean ablegen(T element) {
        if (elemente.size() < kapazitaet && !elemente.contains(element)) {
            elemente.add(element);
            return true;
        }
        return false;
    }

    public int anzahl() {
        return elemente.size();
    }

    public List<T> getElemente() {
        return elemente;
    }

    public T maximum(Vergleicher<T> v) {
        if (elemente.size() == 0) {
            return null;
        }
        T max = elemente.get(0);
        for (int i = 1; i < elemente.size(); i++) {
            if (v.vergleiche(elemente.get(i), max) > 0) {
                max = elemente.get(i);
            }
        }
        return max;
    }
}

interface Vergleicher<T> {
    int vergleiche(T a, T b);
}
// #endregion ablage

// #region faellige
class Lagerverwaltung {
    public List<Lagerposten> faelligePosten(Ablage<Lagerposten> ablage, Datum heute) {
        List<Lagerposten> ergebnis = new ArrayList<>();
        List<Lagerposten> posten = ablage.getElemente();
        for (int i = 0; i < posten.size(); i++) {
            Lagerposten p = posten.get(i);
            if (p.istFaellig(heute)) {          // dynamische Bindung
                ergebnis.add(p);
            }
        }
        return ergebnis;
    }
}
// #endregion faellige

/** Datum (vereinfacht, intern java.time.LocalDate). */
class Datum {
    private final LocalDate wert;

    public Datum(int tag, int monat, int jahr) {
        this.wert = LocalDate.of(jahr, monat, tag);
    }

    private Datum(LocalDate wert) {
        this.wert = wert;
    }

    public Datum plusTage(int tage) {
        return new Datum(wert.plusDays(tage));
    }

    /** true, wenn dieses Datum vor dem Datum d liegt oder gleich ist. */
    public boolean istVorOderGleich(Datum d) {
        return !wert.isAfter(d.wert);
    }

    @Override
    public String toString() {
        return String.format("%02d.%02d.%d", wert.getDayOfMonth(), wert.getMonthValue(), wert.getYear());
    }
}

// #region lagerposten
abstract class Lagerposten {
    private String postenNr;
    private Datum eingang;
    private double gewicht;

    public Lagerposten(String postenNr, Datum eingang, double gewicht) {
        this.postenNr = postenNr;
        this.eingang = eingang;
        this.gewicht = gewicht;
    }

    public String getPostenNr() { return postenNr; }
    public Datum getEingang() { return eingang; }
    public double getGewicht() { return gewicht; }

    public abstract boolean istFaellig(Datum heute);
}

class Retoure extends Lagerposten {
    public Retoure(String postenNr, Datum eingang, double gewicht) {
        super(postenNr, eingang, gewicht);
    }

    @Override
    public boolean istFaellig(Datum heute) {
        return getEingang().plusTage(14).istVorOderGleich(heute);
    }
}

class Zollposten extends Lagerposten {
    private boolean freigegeben;

    public Zollposten(String postenNr, Datum eingang, double gewicht, boolean freigegeben) {
        super(postenNr, eingang, gewicht);
        this.freigegeben = freigegeben;
    }

    @Override
    public boolean istFaellig(Datum heute) {
        return freigegeben;
    }
}

class Lagerauftrag extends Lagerposten {
    private Datum abholtermin;

    public Lagerauftrag(String postenNr, Datum eingang, double gewicht, Datum abholtermin) {
        super(postenNr, eingang, gewicht);
        this.abholtermin = abholtermin;
    }

    @Override
    public boolean istFaellig(Datum heute) {
        return abholtermin.istVorOderGleich(heute);
    }
}

class GewichtVergleicher implements Vergleicher<Lagerposten> {
    @Override
    public int vergleiche(Lagerposten a, Lagerposten b) {
        if (a.getGewicht() < b.getGewicht()) {
            return -1;
        } else if (a.getGewicht() > b.getGewicht()) {
            return 1;
        }
        return 0;
    }
}
// #endregion lagerposten
