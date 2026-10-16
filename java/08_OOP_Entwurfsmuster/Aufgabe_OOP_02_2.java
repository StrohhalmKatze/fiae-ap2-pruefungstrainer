/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-02.2
 * Fuhrpark: abstrakte Klasse, Interface, Konstruktorverkettung mit super(...),
 * final-Methode, protected/private. Enthält die KORRIGIERTE Klasse EScooter.
 *
 * Die fehlerhafte Fassung aus der Aufgabe steht am Dateiende als Kommentar,
 * zusammen mit den Meldungen des Compilers (javac 21).
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_02_2.java
 * oder:  javac Aufgabe_OOP_02_2.java  und danach  java Aufgabe_OOP_02_2
 */
public class Aufgabe_OOP_02_2 {

    public static void main(String[] args) {
        System.out.println("--- a) Konstruktorverkettung ---");
        // #region test_a
        Fahrzeug f1 = new Lastenrad("LR-07");
        Fahrzeug f2 = new Kuehltransporter("KT-02", 180);
        // #endregion test_a

        System.out.println("--- b) korrigierte Klasse EScooter ---");
        Fahrzeug f3 = new EScooter("ES-11");
        System.out.println(f3.info() + ", Kosten/km: " + f3.kostenProKm());
        System.out.println(((EScooter) f3).beschreibung());
        // statt new Fahrzeug("RES-1", 50) (abstrakt!) eine konkrete Unterklasse:
        Fahrzeug reserve = new Lastenrad("RES-1");
        System.out.println(reserve.info());

        System.out.println("--- c) ein Objekt, zwei Typen ---");
        Kuehltransporter kt = new Kuehltransporter("KT-03", 220);
        Fahrzeug alsFahrzeug = kt;      // Kuehltransporter IST EIN Fahrzeug
        Kuehlbar alsKuehlbar = kt;      // Kuehltransporter IST Kuehlbar
        System.out.println(alsFahrzeug.info() + " / kuehlen(-18): " + alsKuehlbar.kuehlen(-18));
        System.out.println("f1 instanceof Kuehlbar: " + (f1 instanceof Kuehlbar)
                + ", f2 instanceof Kuehlbar: " + (f2 instanceof Kuehlbar));
    }
}

// #region fuhrpark
abstract class Fahrzeug {
    private String kennung;
    protected int reichweiteKm;

    public Fahrzeug(String kennung, int reichweiteKm) {
        this.kennung = kennung;
        this.reichweiteKm = reichweiteKm;
        System.out.println("Fahrzeug " + kennung + " angelegt");
    }

    public String getKennung() {
        return kennung;
    }

    public abstract double kostenProKm();

    public final String info() {
        return kennung + " (" + reichweiteKm + " km)";
    }
}

interface Kuehlbar {
    boolean kuehlen(int zielTemperatur);
}

class Lastenrad extends Fahrzeug {
    public Lastenrad(String kennung) {
        super(kennung, 60);
        System.out.println("Lastenrad " + getKennung() + " bereit");
    }

    @Override
    public double kostenProKm() {
        return 0.05;
    }
}

class Kuehltransporter extends Fahrzeug implements Kuehlbar {
    private int temperatur;

    public Kuehltransporter(String kennung, int reichweiteKm) {
        super(kennung, reichweiteKm);
        temperatur = 20;
        System.out.println("Kuehlung auf " + temperatur + " Grad");
    }

    @Override
    public double kostenProKm() {
        return 0.32;
    }

    @Override
    public boolean kuehlen(int zielTemperatur) {
        if (zielTemperatur < -20) {
            return false;
        }
        temperatur = zielTemperatur;
        return true;
    }
}
// #endregion fuhrpark

// #region escooter_korrigiert
class EScooter extends Fahrzeug {
    public EScooter(String kennung) {
        super(kennung, 35);                       // Fehler 1 behoben
        System.out.println("E-Scooter bereit");
    }

    @Override
    public double kostenProKm() {                 // Fehler 2 behoben
        return 0.03;
    }

    public String beschreibung() {                // Fehler 3 behoben (info() ist final)
        return "E-Scooter " + getKennung();       // Fehler 4 behoben (kennung ist private)
    }
}
// Fehler 5: new Fahrzeug(...) ist nicht erlaubt -> konkrete Unterklasse instanziieren
// #endregion escooter_korrigiert

/*
 * Fehlerhafte Fassung aus der Aufgabe (zum Ausprobieren in eine eigene Datei kopieren):
 *
 * class EScooter extends Fahrzeug {
 *     public EScooter(String kennung) {
 *         reichweiteKm = 35;
 *         System.out.println("E-Scooter bereit");
 *     }
 *
 *     @Override
 *     public String info() {
 *         return "E-Scooter " + kennung;
 *     }
 * }
 * ... in einer Testmethode:
 *     Fahrzeug reserve = new Fahrzeug("RES-1", 50);
 *
 * Meldungen von javac 21 (gekürzt, Zeilennummern einer Testdatei):
 *   Fehler: Fahrzeug ist abstrakt, kann nicht instanziiert werden
 *   Fehler: EScooter ist nicht abstrakt und setzt die abstrakte Methode kostenProKm()
 *           in Fahrzeug nicht außer Kraft
 *   Fehler: Konstruktor Fahrzeug in Klasse Fahrzeug kann nicht auf die angegebenen
 *           Typen angewendet werden. Erforderlich: String,int  Ermittelt: keine Argumente
 *   Fehler: info() in EScooter kann nicht info() in Fahrzeug außer Kraft setzen.
 *           Außer Kraft gesetzte Methode ist final
 *   Fehler: kennung hat private-Zugriff in Fahrzeug
 *   5 Fehler
 */
