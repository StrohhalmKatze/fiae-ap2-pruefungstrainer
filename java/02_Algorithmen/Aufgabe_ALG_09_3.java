import java.util.ArrayList;
import java.util.List;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-09.3
 * Optimistisches Sperren mit Versionsnummer
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_09_3.java
 * oder:  javac Aufgabe_ALG_09_3.java  und danach  java Aufgabe_ALG_09_3
 */
public class Aufgabe_ALG_09_3 {

    /** Rückgabeobjekt von leseRad (Anlage 6) */
    static class RadInfo {
        private final String status;
        private final int version;
        RadInfo(String status, int version) { this.status = status; this.version = version; }
        String getStatus() { return status; }
        int getVersion() { return version; }
    }

    /** Simulation der Datenbankzugriffsklasse RadDB (Anlage 6) für ein Rad */
    static class RadDB {
        private String status = "frei";
        private int version = 7;
        private final List<String> reservierungen = new ArrayList<>();

        synchronized RadInfo leseRad(int radNr) {
            return new RadInfo(status, version);
        }

        /** atomar: ändert nur, wenn die gespeicherte Version noch der erwarteten entspricht */
        synchronized boolean setzeStatusWennVersion(int radNr, String neuerStatus, int erwarteteVersion) {
            if (version != erwarteteVersion) {
                return false;
            }
            status = neuerStatus;
            version = version + 1;
            return true;
        }

        synchronized void legeReservierungAn(int radNr, int kundenNr) {
            reservierungen.add("Rad " + radNr + " für Kunde " + kundenNr);
        }
    }

    // c) Reservierung mit optimistischem Sperren
    static boolean reserviere(RadDB db, int radNr, int kundenNr) {
        RadInfo info = db.leseRad(radNr);
        if (!info.getStatus().equals("frei")) {
            return false;                                   // schon vergeben
        }
        // ... Kunde bestätigt in der App, Zeit vergeht ...
        try { Thread.sleep(30); } catch (InterruptedException e) { }
        if (db.setzeStatusWennVersion(radNr, "reserviert", info.getVersion())) {
            db.legeReservierungAn(radNr, kundenNr);
            return true;
        }
        return false;                                       // Konflikt: jemand war schneller
    }

    public static void main(String[] args) throws InterruptedException {
        RadDB db = new RadDB();
        boolean[] ergebnis = new boolean[2];
        Thread a = new Thread(() -> ergebnis[0] = reserviere(db, 106, 5002));
        Thread b = new Thread(() -> ergebnis[1] = reserviere(db, 106, 5005));
        a.start();
        b.start();
        a.join();
        b.join();
        int erfolgreich = (ergebnis[0] ? 1 : 0) + (ergebnis[1] ? 1 : 0);
        System.out.println("Gleichzeitige Reservierungsversuche für Rad 106: 2");
        System.out.println("davon erfolgreich: " + erfolgreich + ", abgewiesen (Konflikt): " + (2 - erfolgreich));
        RadInfo info = db.leseRad(106);
        System.out.println("Status danach: " + info.getStatus() + ", Version: " + info.getVersion()
                + ", gespeicherte Reservierungen: " + db.reservierungen.size());
        System.out.println("Dritter Versuch später: reserviere(...) = " + reserviere(db, 106, 5001));
    }
}
