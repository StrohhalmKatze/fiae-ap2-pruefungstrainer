/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-03.2
 * Polymorphie: dynamische Bindung beim Überschreiben, statische Bindung beim
 * Überladen, super-Aufrufe, Downcast mit instanceof, ClassCastException.
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_03_2.java
 * oder:  javac Aufgabe_OOP_03_2.java  und danach  java Aufgabe_OOP_03_2
 */
public class Aufgabe_OOP_03_2 {

    public static void main(String[] args) {
        // #region test
        Zustellung a = new Zustellung();
        Zustellung b = new Expresszustellung();
        Zustellung c = new Nachtzustellung();
        Expresszustellung d = new Nachtzustellung();
        Kasse k = new Kasse();

        System.out.println(b.beschreibung());   // (1)
        System.out.println(b.quittung());       // (2)
        System.out.println(c.quittung());       // (3)
        System.out.println(a.quittung());       // (4)
        System.out.println(k.buche(b));         // (5)
        System.out.println(k.buche(d));         // (6)
        // #endregion test

        System.out.println("--- b) Methode der Unterklasse über Oberklassen-Referenz ---");
        // b.zeitfenster();   // Compilerfehler: Zustellung kennt zeitfenster() nicht
        // #region loesung_b
        if (b instanceof Expresszustellung) {
            Expresszustellung e = (Expresszustellung) b;
            System.out.println(e.zeitfenster());
        }
        // #endregion loesung_b

        System.out.println("--- c) Downcast auf ein Objekt der Oberklasse ---");
        try {
            Expresszustellung x = (Expresszustellung) a;   // übersetzt, scheitert zur Laufzeit
            System.out.println(x.zeitfenster());
        } catch (ClassCastException ex) {
            System.out.println("ClassCastException: " + ex.getMessage());
        }
        System.out.println("a instanceof Expresszustellung: " + (a instanceof Expresszustellung));
    }
}

// #region klassen
class Zustellung {
    public String beschreibung() {
        return "Zustellung";
    }

    public double gebuehr() {
        return 4.00;
    }

    public String quittung() {
        return beschreibung() + ": " + gebuehr() + " EUR";
    }
}

class Expresszustellung extends Zustellung {
    @Override
    public String beschreibung() {
        return "Express";
    }

    @Override
    public double gebuehr() {
        return super.gebuehr() + 6.00;
    }

    public String zeitfenster() {
        return "bis 12 Uhr";
    }
}

class Nachtzustellung extends Expresszustellung {
    @Override
    public String beschreibung() {
        return "Nacht-" + super.beschreibung();
    }
}

class Kasse {
    public String buche(Zustellung z) {
        return "Standardbuchung";
    }

    public String buche(Expresszustellung e) {
        return "Expressbuchung";
    }
}
// #endregion klassen
