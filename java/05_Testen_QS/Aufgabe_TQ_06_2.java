/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-06.2
 * Spezifikationsgerechte Methode beitrag (Beitragsordnung, Anlage 3) und Ausführung der
 * Testfälle aus der Musterlösung (Äquivalenzklassen-Repräsentanten und Grenzwerte).
 * Ersatz-Testhilfe statt JUnit.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_06_2.java
 */
public class Aufgabe_TQ_06_2 {

    // Referenzimplementierung nach Anlage 3
    static double beitrag(int alter, String tarif) {
        if (alter < 14 || alter > 120) {
            throw new IllegalArgumentException("Alter ungültig");
        }
        double grundbeitrag;
        if ("BASIC".equals(tarif)) {
            grundbeitrag = 29.90;
        } else if ("PREMIUM".equals(tarif)) {
            grundbeitrag = 49.90;
        } else {
            throw new IllegalArgumentException("Tarif unbekannt");
        }
        if (alter <= 17) {
            return grundbeitrag * 0.5;
        }
        if (alter >= 65) {
            return grundbeitrag * 0.8;
        }
        return grundbeitrag;
    }

    static int ok = 0, fehler = 0;

    // erwartet: Zahl als Text ("14.95") oder "Exception"
    static void pruefe(String tf, int alter, String tarif, String erwartet) {
        String ist;
        try {
            ist = String.format(java.util.Locale.ROOT, "%.2f", beitrag(alter, tarif));
        } catch (IllegalArgumentException e) {
            ist = "Exception";
        }
        boolean gleich = ist.equals(erwartet);
        if (gleich) ok++; else fehler++;
        System.out.printf("   %-5s beitrag(%3d, %-9s) erwartet %-9s tatsächlich %-9s %s%n",
                tf, alter, tarif == null ? "null" : "\"" + tarif + "\"", erwartet, ist, gleich ? "OK" : "FEHLER");
    }

    public static void main(String[] args) {
        System.out.println("b) Testfälle aus Äquivalenzklassen (gültige kombiniert, ungültige einzeln):");
        pruefe("T1", 15, "BASIC", "14.95");
        pruefe("T2", 40, "PREMIUM", "49.90");
        pruefe("T3", 70, "BASIC", "23.92");
        pruefe("T4", 10, "BASIC", "Exception");
        pruefe("T5", 130, "PREMIUM", "Exception");
        pruefe("T6", 40, "GOLD", "Exception");
        pruefe("T7", 40, null, "Exception");
        System.out.println("c) Grenzwerte für das Alter (Tarif BASIC):");
        pruefe("G1", 13, "BASIC", "Exception");
        pruefe("G2", 14, "BASIC", "14.95");
        pruefe("G3", 17, "BASIC", "14.95");
        pruefe("G4", 18, "BASIC", "29.90");
        pruefe("G5", 64, "BASIC", "29.90");
        pruefe("G6", 65, "BASIC", "23.92");
        pruefe("G7", 120, "BASIC", "23.92");
        pruefe("G8", 121, "BASIC", "Exception");
        System.out.println("Zusatz: PREMIUM für Jugendliche/Senioren: 16 -> " + beitrag(16, "PREMIUM")
                + ", 65 -> " + String.format(java.util.Locale.ROOT, "%.2f", beitrag(65, "PREMIUM")));
        System.out.println("-> " + ok + " bestanden, " + fehler + " fehlgeschlagen");
    }
}
