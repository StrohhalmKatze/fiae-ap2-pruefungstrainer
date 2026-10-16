/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-01.3
 * Klasse Paketfach aus den Anforderungen (Anlage 4) umsetzen:
 * Kapselung, unveränderliche Attribute, statischer Zähler, überladene Konstruktoren,
 * Methoden einlegen und entnehmen (Rückgabe null).
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_01_3.java
 * oder:  javac Aufgabe_OOP_01_3.java  und danach  java Aufgabe_OOP_01_3
 */
public class Aufgabe_OOP_01_3 {

    public static void main(String[] args) {
        Paketfach f1 = new Paketfach(1, "S");
        Paketfach f2 = new Paketfach(2);          // Größe M über den zweiten Konstruktor
        Paketfach f3 = new Paketfach(3, "L");
        System.out.println("Erzeugte Fächer: " + Paketfach.getAnzahlFaecher());
        System.out.println("Fach 2 hat die Größe " + f2.getGroesse());

        Sendung a = new Sendung("KT-1001", "M");
        Sendung b = new Sendung("KT-1002", "L");
        Sendung c = new Sendung("KT-1003", "S");

        System.out.println("f1.einlegen(KT-1001 [M]) -> " + f1.einlegen(a, "482913") + "  (Sendung zu groß für S)");
        System.out.println("f2.einlegen(KT-1001 [M]) -> " + f2.einlegen(a, "482913"));
        System.out.println("f2.einlegen(KT-1003 [S]) -> " + f2.einlegen(c, "111111") + "  (Fach belegt)");
        System.out.println("f3.einlegen(KT-1002 [L]) -> " + f3.einlegen(b, "730055"));
        System.out.println("f1.einlegen(KT-1003 [S]) -> " + f1.einlegen(c, "250117"));

        System.out.println("f2.entnehmen(\"000000\") -> " + f2.entnehmen("000000") + "  (falscher Code)");
        Sendung abgeholt = f2.entnehmen("482913");
        System.out.println("f2.entnehmen(\"482913\") -> " + abgeholt.getSendungsNr());
        System.out.println("f2.istFrei() -> " + f2.istFrei());
        System.out.println("f2.entnehmen(\"482913\") -> " + f2.entnehmen("482913") + "  (Fach leer)");
    }
}

/** Klasse Sendung (Auszug wie in Anlage 2). */
class Sendung {
    private String sendungsNr;
    private String groesse;   // "S", "M" oder "L"

    public Sendung(String sendungsNr, String groesse) {
        this.sendungsNr = sendungsNr;
        this.groesse = groesse;
    }

    public String getSendungsNr() {
        return sendungsNr;
    }

    public String getGroesse() {
        return groesse;
    }
}

// #region paketfach
class Paketfach {
    private static int anzahlFaecher = 0;
    private final int fachNr;
    private final String groesse;
    private Sendung inhalt;
    private String abholcode;

    public Paketfach(int fachNr, String groesse) {
        this.fachNr = fachNr;
        this.groesse = groesse;
        this.inhalt = null;
        this.abholcode = null;
        anzahlFaecher = anzahlFaecher + 1;
    }

    public Paketfach(int fachNr) {
        this(fachNr, "M");
    }

    public static int getAnzahlFaecher() {
        return anzahlFaecher;
    }

    public int getFachNr() {
        return fachNr;
    }

    public String getGroesse() {
        return groesse;
    }

    public boolean istFrei() {
        return inhalt == null;
    }

    public boolean einlegen(Sendung s, String code) {
        if (s != null && istFrei() && rang(s.getGroesse()) <= rang(groesse)) {
            inhalt = s;
            abholcode = code;
            return true;
        }
        return false;
    }

    public Sendung entnehmen(String code) {
        if (!istFrei() && abholcode.equals(code)) {
            Sendung s = inhalt;
            inhalt = null;
            abholcode = null;
            return s;
        }
        return null;
    }

    private int rang(String groesse) {
        if (groesse.equals("S")) {
            return 1;
        } else if (groesse.equals("M")) {
            return 2;
        } else if (groesse.equals("L")) {
            return 3;
        }
        return 0;
    }
}
// #endregion paketfach
