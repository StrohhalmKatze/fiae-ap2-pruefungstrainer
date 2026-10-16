import java.util.ArrayList;
import java.util.List;

/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-05.3
 * Observer-Muster (Pull-Variante) für die Sendungsverfolgung:
 * Sendungsstatus (konkretes Subject) benachrichtigt KundenApp und Hallenanzeige.
 * Zusätzlich: Push-Variante als Vergleich (Teilaufgabe f).
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_05_3.java
 * oder:  javac Aufgabe_OOP_05_3.java  und danach  java Aufgabe_OOP_05_3
 */
public class Aufgabe_OOP_05_3 {

    public static void main(String[] args) {
        // #region ablauf
        Sendungsstatus status = new Sendungsstatus("KT-4711");
        KundenApp app = new KundenApp(status);            // meldet sich im Konstruktor an
        Hallenanzeige halle = new Hallenanzeige(status, 3);  // meldet sich im Konstruktor an

        status.setStatus("In Zustellung");
        // #endregion ablauf
        status.setStatus("In Paketstation eingelegt");
        System.out.println("-- Hallenanzeige meldet sich ab --");
        status.removeObserver(halle);
        status.setStatus("Abgeholt");
        System.out.println("Letzte Meldung in der App: " + app.getLetzteMeldung());

        System.out.println("-- f) Push-Variante zum Vergleich --");
        StatusQuellePush quelle = new StatusQuellePush("KT-4712");
        quelle.addObserver((nr, s) -> System.out.println("  Push-Observer erhält: " + nr + " -> " + s));
        quelle.setStatus("Im Sortierzentrum");
    }
}

// #region interfaces
interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

interface Observer {
    void update();
}
// #endregion interfaces

// #region sendungsstatus
class Sendungsstatus implements Subject {
    private String sendungsNr;
    private String status;
    private List<Observer> observers;

    public Sendungsstatus(String sendungsNr) {
        this.sendungsNr = sendungsNr;
        this.status = "Angekündigt";
        this.observers = new ArrayList<>();
    }

    public String getSendungsNr() {
        return sendungsNr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update();
        }
    }
}
// #endregion sendungsstatus

// #region beobachter
class KundenApp implements Observer {
    private Sendungsstatus quelle;
    private String letzteMeldung;

    public KundenApp(Sendungsstatus quelle) {
        this.quelle = quelle;
        quelle.addObserver(this);
    }

    @Override
    public void update() {
        letzteMeldung = quelle.getStatus();     // Pull: Daten selbst abholen
        anzeigen();
    }

    public void anzeigen() {
        System.out.println("  KundenApp:       " + letzteMeldung);
    }

    public String getLetzteMeldung() {
        return letzteMeldung;
    }
}

class Hallenanzeige implements Observer {
    private Sendungsstatus quelle;
    private int hallenNr;
    private String zeile;

    public Hallenanzeige(Sendungsstatus quelle, int hallenNr) {
        this.quelle = quelle;
        this.hallenNr = hallenNr;
        quelle.addObserver(this);
    }

    @Override
    public void update() {
        zeile = quelle.getStatus();             // Pull
        anzeigen();
    }

    public void anzeigen() {
        System.out.println("  Hallenanzeige " + hallenNr + ": " + zeile);
    }
}
// #endregion beobachter

// ---------- Push-Variante (Teilaufgabe f, nur zum Vergleich) ----------
// #region push
interface PushObserver {
    void update(String sendungsNr, String status);   // Daten werden mitgeschickt
}

class StatusQuellePush {
    private String sendungsNr;
    private String status;
    private List<PushObserver> observers = new ArrayList<>();

    public StatusQuellePush(String sendungsNr) {
        this.sendungsNr = sendungsNr;
    }

    public void addObserver(PushObserver o) {
        observers.add(o);
    }

    public void setStatus(String status) {
        this.status = status;
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(sendungsNr, status);
        }
    }
}
// #endregion push
