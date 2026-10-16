/**
 * Block 05 Testen & Qualitätssicherung - Lösung zu Aufgabe TQ-03.3
 * Methode zugangPruefen (Anlage 6), instrumentiert: Jede markierte Bedingung (B) und
 * Anweisung (A) wird beim Durchlaufen protokolliert. Das Programm
 *  - ermittelt alle möglichen Pfade (Pfadüberdeckung),
 *  - bestimmt per Durchprobieren die minimale Anzahl Testfälle für vollständige
 *    Anweisungs- und Zweigüberdeckung,
 *  - prüft die Testfälle aus der Musterlösung.
 *
 * Start (JDK 11+):  java Aufgabe_TQ_03_3.java
 */
import java.util.*;

public class Aufgabe_TQ_03_3 {

    static class Mitglied {
        private final boolean vertragAktiv;
        private final boolean premium;
        private final double offenerBetrag;
        Mitglied(boolean vertragAktiv, boolean premium, double offenerBetrag) {
            this.vertragAktiv = vertragAktiv; this.premium = premium; this.offenerBetrag = offenerBetrag;
        }
        boolean istVertragAktiv() { return vertragAktiv; }
        boolean hatPremium() { return premium; }
        double getOffenerBetrag() { return offenerBetrag; }
    }

    static List<String> pfad;       // durchlaufene Markierungen
    static Set<String> zweige;      // durchlaufene Zweige, z. B. "B1 wahr"

    static boolean b(String marke, boolean wert) {
        pfad.add(marke);
        zweige.add(marke + (wert ? " wahr" : " falsch"));
        return wert;
    }
    static void a(String marke) { pfad.add(marke); }
    static void protokolliereVersuch(Mitglied m) { /* schreibt einen Eintrag ins Zutrittsprotokoll */ }

    // ---------- Methode aus Anlage 6 (instrumentiert) ----------
    static String zugangPruefen(Mitglied m, String bereich) {
        String meldung = "Kein Zutritt";
        if (b("B1", m.istVertragAktiv())) {
            a("A1"); meldung = "Zutritt Trainingsbereich";
            if (b("B2", bereich.equals("Sauna"))) {
                if (b("B3", m.hatPremium())) {
                    a("A2"); meldung = "Zutritt Sauna";
                } else {
                    a("A3"); meldung = "Sauna nur mit Premium";
                }
            }
            if (b("B4", m.getOffenerBetrag() > 0)) {
                a("A4"); meldung = meldung + " (Zahlung offen)";
            }
        } else {
            a("A5"); protokolliereVersuch(m);
        }
        return meldung;
    }

    static class Lauf {
        String beschreibung; String ergebnis; List<String> pfad; Set<String> zweige;
    }

    static Lauf ausfuehren(boolean aktiv, boolean premium, double offen, String bereich) {
        pfad = new ArrayList<>(); zweige = new TreeSet<>();
        Lauf l = new Lauf();
        l.ergebnis = zugangPruefen(new Mitglied(aktiv, premium, offen), bereich);
        l.pfad = pfad; l.zweige = zweige;
        l.beschreibung = String.format("aktiv=%-5s premium=%-5s offen=%5.2f bereich=%s", aktiv, premium, offen, bereich);
        return l;
    }

    public static void main(String[] args) {
        Locale.setDefault(Locale.GERMANY);   // gleiche Zahlendarstellung auf jedem Rechner
        // alle Eingabekombinationen durchprobieren und die verschiedenen Pfade sammeln
        Map<String, Lauf> pfade = new LinkedHashMap<>();
        for (boolean aktiv : new boolean[]{false, true})
            for (String bereich : new String[]{"Sauna", "Trainingsbereich"})
                for (boolean premium : new boolean[]{true, false})
                    for (double offen : new double[]{25.50, 0.0}) {
                        Lauf l = ausfuehren(aktiv, premium, offen, bereich);
                        pfade.putIfAbsent(String.join(", ", l.pfad), l);
                    }
        System.out.println("d) Alle Pfade (" + pfade.size() + "):");
        int nr = 1;
        for (Map.Entry<String, Lauf> e : pfade.entrySet()) {
            System.out.printf("   %d  %-28s -> \"%s\"%n", nr++, e.getKey(), e.getValue().ergebnis);
            System.out.printf("      z. B. %s%n", e.getValue().beschreibung);
        }

        // minimale Teilmengen der Pfade für C0 und C1 durch Ausprobieren aller 2^7 Teilmengen
        List<Lauf> liste = new ArrayList<>(pfade.values());
        Set<String> alleAnweisungen = Set.of("A1", "A2", "A3", "A4", "A5");
        Set<String> alleZweige = new TreeSet<>();
        for (Lauf l : liste) alleZweige.addAll(l.zweige);
        int minC0 = 99, minC1 = 99; int besteC0 = 0, besteC1 = 0;
        for (int maske = 1; maske < (1 << liste.size()); maske++) {
            Set<String> anw = new HashSet<>(), zw = new HashSet<>();
            for (int k = 0; k < liste.size(); k++) {
                if ((maske & (1 << k)) != 0) { anw.addAll(liste.get(k).pfad); zw.addAll(liste.get(k).zweige); }
            }
            int anzahl = Integer.bitCount(maske);
            if (anw.containsAll(alleAnweisungen) && anzahl < minC0) { minC0 = anzahl; besteC0 = maske; }
            if (zw.containsAll(alleZweige) && anzahl < minC1) { minC1 = anzahl; besteC1 = maske; }
        }
        System.out.println();
        System.out.println("c) Zweige insgesamt: " + alleZweige.size() + " " + alleZweige);
        System.out.println("   Minimale Anzahl Testfälle Anweisungsüberdeckung (C0): " + minC0);
        zeige(liste, besteC0);
        System.out.println("   Minimale Anzahl Testfälle Zweigüberdeckung (C1): " + minC1);
        zeige(liste, besteC1);
        System.out.println("   Pfadüberdeckung (C2): " + liste.size() + " Testfälle");

        // Testfälle der Musterlösung nachprüfen
        System.out.println();
        System.out.println("Testfälle der Musterlösung:");
        Lauf t1 = ausfuehren(false, false, 0.0, "Trainingsbereich");
        Lauf t2 = ausfuehren(true, true, 25.50, "Sauna");
        Lauf t3 = ausfuehren(true, false, 0.0, "Sauna");
        Lauf t4 = ausfuehren(true, false, 0.0, "Trainingsbereich");
        for (Lauf l : List.of(t1, t2, t3, t4)) {
            System.out.printf("   %s%n      Pfad %-28s -> \"%s\"%n", l.beschreibung, String.join(", ", l.pfad), l.ergebnis);
        }
        Set<String> anw = new HashSet<>(), zw = new TreeSet<>();
        for (Lauf l : List.of(t1, t2, t3)) { anw.addAll(l.pfad); }
        System.out.println("   T1-T3 decken alle Anweisungen ab: " + anw.containsAll(alleAnweisungen));
        for (Lauf l : List.of(t1, t2, t3)) { zw.addAll(l.zweige); }
        Set<String> fehlend = new TreeSet<>(alleZweige); fehlend.removeAll(zw);
        System.out.println("   T1-T3 lassen diese Zweige aus: " + fehlend);
        for (Lauf l : List.of(t1, t2, t3, t4)) { zw.addAll(l.zweige); }
        System.out.println("   T1-T4 decken alle Zweige ab: " + zw.containsAll(alleZweige));

        // e) 100 % Anweisungsüberdeckung findet nicht jeden Fehler
        System.out.println();
        System.out.println("e) Trotz 100 % Pfadüberdeckung: Aufruf mit bereich = null (z. B. fehlender Wert aus der App)");
        try {
            ausfuehren(true, true, 0.0, null);
        } catch (NullPointerException ex) {
            System.out.println("   -> NullPointerException in bereich.equals(...) - kein Überdeckungsmaß hat das angezeigt");
        }
    }

    static void zeige(List<Lauf> liste, int maske) {
        for (int k = 0; k < liste.size(); k++) {
            if ((maske & (1 << k)) != 0) {
                System.out.println("      Pfad " + (k + 1) + ": " + String.join(", ", liste.get(k).pfad));
            }
        }
    }
}
