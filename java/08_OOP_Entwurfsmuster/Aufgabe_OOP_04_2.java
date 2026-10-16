import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Block 08 Objektorientierung & Entwurfsmuster - Lösung zu Aufgabe OOP-04.2
 * Singleton Tarifkonfiguration: korrigierte Fassung (privater Konstruktor,
 * statisches Attribut instanz, statische und synchronisierte Methode getInstanz).
 *
 * Starten (JDK 11+):  java Aufgabe_OOP_04_2.java
 * oder:  javac Aufgabe_OOP_04_2.java  und danach  java Aufgabe_OOP_04_2
 */
public class Aufgabe_OOP_04_2 {

    public static void main(String[] args) throws InterruptedException {
        // new Tarifkonfiguration();   // Compilerfehler: Konstruktor ist private
        Tarifkonfiguration t1 = Tarifkonfiguration.getInstanz();
        Tarifkonfiguration t2 = Tarifkonfiguration.getInstanz();
        System.out.println("t1 == t2: " + (t1 == t2));
        System.out.println(String.format(Locale.GERMANY, "Grundpreis Paket: %.2f EUR, Zuschlag je kg: %.2f EUR",
                t1.getGrundpreisPaket(), t2.getKgZuschlag()));

        // 10 Threads fragen gleichzeitig nach der Instanz -> es darf nur EIN Objekt geben
        Set<Tarifkonfiguration> gefunden = Collections.newSetFromMap(new IdentityHashMap<>());
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Tarifkonfiguration t = Tarifkonfiguration.getInstanz();
                synchronized (gefunden) {
                    gefunden.add(t);
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Verschiedene Instanzen bei 10 Threads: " + gefunden.size());
        System.out.println("Anzahl Ladevorgänge der Datei: " + Tarifkonfiguration.ladevorgaenge);
    }
}

// #region singleton
class Tarifkonfiguration {
    private static Tarifkonfiguration instanz = null;      // Fehler 1: static
    private double grundpreisPaket;
    private double kgZuschlag;

    private Tarifkonfiguration() {                         // Fehler 2: private
        grundpreisPaket = ladeWert("paket.grundpreis");
        kgZuschlag = ladeWert("paket.kgzuschlag");
    }

    public static synchronized Tarifkonfiguration getInstanz() {   // Fehler 3: static
        if (instanz == null) {                                     // Fehler 4: nur einmal erzeugen
            instanz = new Tarifkonfiguration();
        }
        return instanz;
    }

    public double getGrundpreisPaket() {
        return grundpreisPaket;
    }

    public double getKgZuschlag() {
        return kgZuschlag;
    }
    // #endregion singleton

    static int ladevorgaenge = 0;   // nur für den Test: zählt, wie oft "geladen" wird

    /** Simuliert das Lesen aus der Datei tarife.properties. */
    private double ladeWert(String schluessel) {
        if (schluessel.equals("paket.grundpreis")) {
            ladevorgaenge = ladevorgaenge + 1;
            try {
                Thread.sleep(20);   // Dateizugriff dauert -> ohne synchronized entstünden Wettläufe
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return 4.20;
        }
        return 0.35;
    }
}
