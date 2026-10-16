import java.util.ArrayList;
import java.util.List;

/**
 * Block 06 Schnittstellen & Datenformate - Lösung zu Aufgabe SD-02.3 d)
 * Offene Stopps einer Tour als JSON-Array ausgeben (Fahrer-App).
 *
 * Starten (JDK 11+):  java Aufgabe_SD_02_3.java
 * oder:  javac Aufgabe_SD_02_3.java  und danach  java Aufgabe_SD_02_3
 */
public class Aufgabe_SD_02_3 {

    /** Klasse Stopp (Anlage 4). */
    static class Stopp {
        private final int position;
        private final int kundenNr;
        private final String ort;
        private final int anzahlKisten;
        private final boolean zugestellt;

        Stopp(int position, int kundenNr, String ort, int anzahlKisten, boolean zugestellt) {
            this.position = position;
            this.kundenNr = kundenNr;
            this.ort = ort;
            this.anzahlKisten = anzahlKisten;
            this.zugestellt = zugestellt;
        }

        int getPosition() { return position; }
        int getKundenNr() { return kundenNr; }
        String getOrt() { return ort; }
        int getAnzahlKisten() { return anzahlKisten; }
        boolean isZugestellt() { return zugestellt; }
    }

    /** Lösung d): alle noch nicht zugestellten Stopps als JSON-Array (ohne Komma am Ende). */
    static String offeneStoppsAlsJson(List<Stopp> stopps) {
        String json = "[";
        boolean erster = true;
        for (int i = 0; i < stopps.size(); i++) {
            Stopp s = stopps.get(i);
            if (!s.isZugestellt()) {
                if (!erster) {
                    json = json + ", ";                  // Trennzeichen nur ZWISCHEN Objekten
                }
                json = json + "{\"position\": " + s.getPosition()
                        + ", \"kundenNr\": " + s.getKundenNr()
                        + ", \"ort\": \"" + s.getOrt() + "\""   // Zeichenkette in Anführungszeichen
                        + ", \"kisten\": " + s.getAnzahlKisten() + "}";
                erster = false;
            }
        }
        json = json + "]";
        return json;
    }

    /** Testdaten: Tour T3 vom 10.11.2026 (Anlage 9). */
    static List<Stopp> tourT3() {
        List<Stopp> l = new ArrayList<>();
        l.add(new Stopp(1, 10427, "Heidmoor", 2, true));
        l.add(new Stopp(2, 20015, "Heidmoor", 6, false));
        l.add(new Stopp(3, 10533, "Wiesede", 1, false));
        l.add(new Stopp(4, 10871, "Kleinmoor", 3, true));
        l.add(new Stopp(5, 20022, "Wiesede", 4, false));
        return l;
    }

    public static void main(String[] args) {
        List<Stopp> tour = tourT3();
        System.out.println("Tour T3 (Anlage 9):");
        System.out.println(offeneStoppsAlsJson(tour));

        List<Stopp> leer = new ArrayList<>();
        System.out.println("Leere Liste:            " + offeneStoppsAlsJson(leer));

        List<Stopp> alleZugestellt = new ArrayList<>();
        alleZugestellt.add(new Stopp(1, 10427, "Heidmoor", 2, true));
        System.out.println("Alles zugestellt:       " + offeneStoppsAlsJson(alleZugestellt));

        List<Stopp> nurLetzterOffen = new ArrayList<>();
        nurLetzterOffen.add(new Stopp(1, 10427, "Heidmoor", 2, true));
        nurLetzterOffen.add(new Stopp(2, 20022, "Wiesede", 4, false));
        System.out.println("Nur letzter Stopp offen: " + offeneStoppsAlsJson(nurLetzterOffen));
    }
}
