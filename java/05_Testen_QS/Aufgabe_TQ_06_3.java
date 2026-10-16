/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-06.3
 * Methode beitrag aus Anlage 4 (fehlerhaft) und korrigierte Fassung.
 * Die sechs Testfälle werden wie JUnit-5-Testmethoden ausgeführt (assertEquals mit Delta,
 * assertThrows). Da JUnit nicht installiert ist, gibt es eine kleine Ersatz-Testhilfe.
 * Außerdem wird gezeigt, welche Anweisungen jeder Testfall in der korrigierten Fassung ausführt.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_06_3.java
 */
import java.util.*;

public class Aufgabe_TQ_06_3 {

    static Set<Integer> zeilen = new TreeSet<>();

    // ---------- Anlage 4 (fehlerhaft) ----------
    static double beitragFehlerhaft(int alter, String tarif) {
        if (alter < 14 || alter > 120) {                              // 02
            throw new IllegalArgumentException("Alter ungültig");     // 03
        }
        double grundbeitrag;                                          // 05
        if (tarif.equals("BASIC")) {                                  // 06  NullPointerException bei null
            grundbeitrag = 29.90;                                     // 07
        } else if (tarif.equals("PREMIUM")) {                         // 08
            grundbeitrag = 49.90;                                     // 09
        } else {
            throw new IllegalArgumentException("Tarif unbekannt");    // 11
        }
        if (alter <= 17) {                                            // 13
            return grundbeitrag * 0.5;                                // 14
        }
        if (alter > 65) {                                             // 16  Fehler: > statt >=
            return grundbeitrag * 0.8;                                // 17
        }
        return grundbeitrag;                                          // 19
    }

    // ---------- korrigierte Fassung (mit Zeilenprotokoll für d) ----------
    static double beitrag(int alter, String tarif) {
        zeilen.add(2);
        if (alter < 14 || alter > 120) {
            zeilen.add(3);
            throw new IllegalArgumentException("Alter ungültig");
        }
        double grundbeitrag;
        zeilen.add(6);
        if ("BASIC".equals(tarif)) {                                  // 06 korrigiert (null-sicher)
            zeilen.add(7);
            grundbeitrag = 29.90;
        } else {
            zeilen.add(8);
            if ("PREMIUM".equals(tarif)) {                            // 08 korrigiert (null-sicher)
                zeilen.add(9);
                grundbeitrag = 49.90;
            } else {
                zeilen.add(11);
                throw new IllegalArgumentException("Tarif unbekannt");
            }
        }
        zeilen.add(13);
        if (alter <= 17) {
            zeilen.add(14);
            return grundbeitrag * 0.5;
        }
        zeilen.add(16);
        if (alter >= 65) {                                            // 16 korrigiert
            zeilen.add(17);
            return grundbeitrag * 0.8;
        }
        zeilen.add(19);
        return grundbeitrag;
    }

    // ---------- Ersatz für org.junit.jupiter.api.Assertions ----------
    static void assertEquals(double erwartet, double tatsaechlich, double delta) {
        if (Math.abs(erwartet - tatsaechlich) > delta) {
            throw new AssertionError("expected: <" + erwartet + "> but was: <" + tatsaechlich + ">");
        }
    }

    interface Ausfuehrbar { void ausfuehren(); }

    static <T extends Throwable> T assertThrows(Class<T> typ, Ausfuehrbar code) {
        try {
            code.ausfuehren();
        } catch (Throwable t) {
            if (typ.isInstance(t)) {
                return typ.cast(t);
            }
            throw new AssertionError("Unexpected exception type thrown, expected: <" + typ.getSimpleName()
                    + "> but was: <" + t.getClass().getSimpleName() + ">");
        }
        throw new AssertionError("Expected " + typ.getSimpleName() + " to be thrown, but nothing was thrown.");
    }

    interface Beitrag { double berechne(int alter, String tarif); }

    static Beitrag sut;   // "system under test"

    // ---------- die sechs Testmethoden (Inhalt wie in der JUnit-Lösung) ----------
    static void tf1JugendlichPremium()   { assertEquals(24.95, sut.berechne(16, "PREMIUM"), 0.001); }
    static void tf2ErwachsenBasic()      { assertEquals(29.90, sut.berechne(40, "BASIC"), 0.001); }
    static void tf3SeniorAbGrenze65()    { assertEquals(23.92, sut.berechne(65, "BASIC"), 0.001); }
    static void tf4AlterZuNiedrig()      { assertThrows(IllegalArgumentException.class, () -> sut.berechne(13, "BASIC")); }
    static void tf5TarifUnbekannt()      { assertThrows(IllegalArgumentException.class, () -> sut.berechne(30, "GOLD")); }
    static void tf6TarifFehlt()          { assertThrows(IllegalArgumentException.class, () -> sut.berechne(30, null)); }

    static void lauf(String titel, Beitrag b) {
        sut = b;
        System.out.println(titel);
        Map<String, Runnable> tests = new LinkedHashMap<>();
        tests.put("TF1 tf1JugendlichPremium", Aufgabe_TQ_06_3::tf1JugendlichPremium);
        tests.put("TF2 tf2ErwachsenBasic", Aufgabe_TQ_06_3::tf2ErwachsenBasic);
        tests.put("TF3 tf3SeniorAbGrenze65", Aufgabe_TQ_06_3::tf3SeniorAbGrenze65);
        tests.put("TF4 tf4AlterZuNiedrig", Aufgabe_TQ_06_3::tf4AlterZuNiedrig);
        tests.put("TF5 tf5TarifUnbekannt", Aufgabe_TQ_06_3::tf5TarifUnbekannt);
        tests.put("TF6 tf6TarifFehlt", Aufgabe_TQ_06_3::tf6TarifFehlt);
        int ok = 0;
        for (Map.Entry<String, Runnable> e : tests.entrySet()) {
            try {
                e.getValue().run();
                System.out.println("   OK      " + e.getKey());
                ok++;
            } catch (AssertionError err) {
                System.out.println("   FEHLER  " + e.getKey() + " -> " + err.getMessage());
            }
        }
        System.out.println("   " + ok + " von " + tests.size() + " Tests bestanden");
    }

    public static void main(String[] args) {
        lauf("b) Tests gegen die FEHLERHAFTE Methode (Anlage 4):", Aufgabe_TQ_06_3::beitragFehlerhaft);
        System.out.println();
        lauf("c) Tests gegen die KORRIGIERTE Methode:", Aufgabe_TQ_06_3::beitrag);
        System.out.println();

        System.out.println("d) Ausgeführte Anweisungszeilen der korrigierten Methode je Testfall:");
        Object[][] tf = {{16, "PREMIUM"}, {40, "BASIC"}, {65, "BASIC"}, {13, "BASIC"}, {30, "GOLD"}, {30, null}};
        List<Set<Integer>> abdeckung = new ArrayList<>();
        for (int k = 0; k < tf.length; k++) {
            zeilen = new TreeSet<>();
            try { beitrag((Integer) tf[k][0], (String) tf[k][1]); } catch (IllegalArgumentException e) { }
            abdeckung.add(zeilen);
            System.out.println("   TF" + (k + 1) + ": " + zeilen);
        }
        Set<Integer> alle = new TreeSet<>(List.of(2, 3, 6, 7, 8, 9, 11, 13, 14, 16, 17, 19));
        int min = 99; List<String> beste = new ArrayList<>(); int besteMaske = 0;
        for (int maske = 1; maske < (1 << tf.length); maske++) {
            Set<Integer> u = new TreeSet<>();
            for (int k = 0; k < tf.length; k++) if ((maske & (1 << k)) != 0) u.addAll(abdeckung.get(k));
            if (u.containsAll(alle)) {
                int n = Integer.bitCount(maske);
                if (n < min) { min = n; beste.clear(); }
                if (n == min) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < tf.length; k++) if ((maske & (1 << k)) != 0) sb.append("TF").append(k + 1).append(' ');
                    beste.add(sb.toString().trim());
                }
            }
        }
        System.out.println("   minimale Anzahl für 100 % Anweisungsüberdeckung: " + min + " -> " + beste);
    }
}
