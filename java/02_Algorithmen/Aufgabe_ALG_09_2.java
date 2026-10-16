
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-09.2
 * Race Condition bei der Reservierung des letzten Lastenrads (und Lösung mit Sperre)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_09_2.java
 * oder:  javac Aufgabe_ALG_09_2.java  und danach  java Aufgabe_ALG_09_2
 */
public class Aufgabe_ALG_09_2 {

    static int freieLastenraeder;
    static int reservierungen;
    static final Object SPERRE = new Object();

    /** ungeschützt: Lesen - Prüfen - Schreiben ist nicht atomar */
    static void reserviereUngeschuetzt(String kunde) throws InterruptedException {
        int frei = freieLastenraeder;                 // lesen
        Thread.sleep(50);                             // z. B. Netzwerk-/DB-Latenz
        if (frei > 0) {                               // prüfen
            freieLastenraeder = frei - 1;             // schreiben
            reservierungen++;
            System.out.println("   " + kunde + ": Reservierung bestätigt");
        } else {
            System.out.println("   " + kunde + ": leider kein Rad frei");
        }
    }

    /** geschützt: kritischer Abschnitt unter einer Sperre (pessimistisch) */
    static void reserviereGesperrt(String kunde) throws InterruptedException {
        synchronized (SPERRE) {
            int frei = freieLastenraeder;
            Thread.sleep(50);
            if (frei > 0) {
                freieLastenraeder = frei - 1;
                reservierungen++;
                System.out.println("   " + kunde + ": Reservierung bestätigt");
            } else {
                System.out.println("   " + kunde + ": leider kein Rad frei");
            }
        }
    }

    interface Aktion { void ausfuehren(String kunde) throws InterruptedException; }

    static void lauf(String titel, Aktion aktion) throws InterruptedException {
        freieLastenraeder = 1;
        reservierungen = 0;
        System.out.println(titel);
        Thread a = new Thread(() -> { try { aktion.ausfuehren("Thread A (Kundin Jana)"); } catch (InterruptedException e) { } });
        Thread b = new Thread(() -> { try { aktion.ausfuehren("Thread B (Kunde Tom)  "); } catch (InterruptedException e) { } });
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println("   => Reservierungen: " + reservierungen + ", freieLastenraeder: " + freieLastenraeder);
    }

    public static void main(String[] args) throws InterruptedException {
        lauf("Ohne Sperre (1 freies Lastenrad, 2 gleichzeitige Anfragen):", Aufgabe_ALG_09_2::reserviereUngeschuetzt);
        lauf("Mit Sperre (synchronized):", Aufgabe_ALG_09_2::reserviereGesperrt);
    }
}
