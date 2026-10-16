import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-04.3
 * Statische Fabrikmethode Benachrichtigung.erzeuge(kanal, ziel):
 * Der Zustellservice kennt nur die abstrakte Klasse, nicht die konkreten Unterklassen.
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_04_3.java
 * oder:  javac Aufgabe_OOP_04_3.java  und danach  java Aufgabe_OOP_04_3
 */
public class Aufgabe_OOP_04_3 {

    public static void main(String[] args) {
        List<Kunde> kunden = new ArrayList<>();
        kunden.add(new Kunde("Ahrens", "EMAIL", "ahrens@example.org"));
        kunden.add(new Kunde("Bode", "SMS", "+49 151 0000001"));
        kunden.add(new Kunde("Cramer", "PUSH", "geraet-7f3a"));
        kunden.add(new Kunde("Dahl", "FAX", "0451 000000"));

        Zustellservice service = new Zustellservice();
        String text = "Ihre Sendung KT-4711 liegt ab sofort in der Paketstation Hafenstrasse, Fach 12, "
                + "zur Abholung bereit. Bitte holen Sie die Sendung innerhalb von 7 Tagen ab. "
                + "Ihr Abholcode lautet 482913.";
        double kosten = 0.0;
        for (Kunde k : kunden) {
            kosten = kosten + service.informiereEmpfaenger(k, text);
        }
        System.out.println(String.format(Locale.GERMANY, "Kosten der Benachrichtigungen: %.2f EUR", kosten));
    }
}

// #region benachrichtigung
abstract class Benachrichtigung {
    protected String ziel;

    public Benachrichtigung(String ziel) {
        this.ziel = ziel;
    }

    public abstract boolean senden(String text);

    public abstract double kostenProNachricht();

    public static Benachrichtigung erzeuge(String kanal, String ziel) {
        if (kanal.equals("EMAIL")) {
            return new EmailBenachrichtigung(ziel);
        } else if (kanal.equals("SMS")) {
            return new SmsBenachrichtigung(ziel);
        } else if (kanal.equals("PUSH")) {
            return new PushBenachrichtigung(ziel);
        }
        return null;
    }
}
// #endregion benachrichtigung

// #region unterklassen
class EmailBenachrichtigung extends Benachrichtigung {
    public EmailBenachrichtigung(String ziel) {
        super(ziel);
    }

    @Override
    public boolean senden(String text) {
        System.out.println("  E-Mail an " + ziel + ": " + text.length() + " Zeichen");
        return true;
    }

    @Override
    public double kostenProNachricht() {
        return 0.00;
    }
}

class SmsBenachrichtigung extends Benachrichtigung {
    public SmsBenachrichtigung(String ziel) {
        super(ziel);
    }

    @Override
    public boolean senden(String text) {
        if (text.length() > 160) {
            text = text.substring(0, 160);
        }
        System.out.println("  SMS an " + ziel + ": " + text.length() + " Zeichen (gekürzt auf max. 160)");
        return true;
    }

    @Override
    public double kostenProNachricht() {
        return 0.07;
    }
}

class PushBenachrichtigung extends Benachrichtigung {
    public PushBenachrichtigung(String ziel) {
        super(ziel);
    }

    @Override
    public boolean senden(String text) {
        System.out.println("  Push an Gerät " + ziel + ": " + text.length() + " Zeichen");
        return true;
    }

    @Override
    public double kostenProNachricht() {
        return 0.00;
    }
}
// #endregion unterklassen

class Kunde {
    private String name;
    private String kanal;
    private String kontakt;

    public Kunde(String name, String kanal, String kontakt) {
        this.name = name;
        this.kanal = kanal;
        this.kontakt = kontakt;
    }

    public String getName() { return name; }
    public String getKanal() { return kanal; }
    public String getKontakt() { return kontakt; }
}

// #region service
class Zustellservice {
    /** Liefert die Kosten der Benachrichtigung (0, wenn keine gesendet wurde). */
    public double informiereEmpfaenger(Kunde k, String text) {
        Benachrichtigung b = Benachrichtigung.erzeuge(k.getKanal(), k.getKontakt());
        if (b == null) {
            System.out.println("  Kanal " + k.getKanal() + " von " + k.getName() + " unbekannt - keine Nachricht");
            return 0.0;
        }
        b.senden(text);
        return b.kostenProNachricht();
    }
}
// #endregion service
