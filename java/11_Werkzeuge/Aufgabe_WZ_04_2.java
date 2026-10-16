import java.util.ArrayList;
import java.util.List;

/**
 * Block 11 Entwicklungsumgebung & Werkzeuge - Lösung zu Aufgabe WZ-04.2
 * Fehlerarten: Syntaxfehler, Typfehler, Laufzeitfehler, logischer Fehler.
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_WZ_04_2.java
 * oder:  javac Aufgabe_WZ_04_2.java  und danach  java Aufgabe_WZ_04_2
 *
 * Zum Üben von Aufgabe c): Datei in der IDE öffnen, einen Haltepunkt (Breakpoint)
 * in die Zeile "double schnitt = summeMinuten / anzahl;" der Methode
 * durchschnittFehlerhaft setzen, im Debug-Modus starten und die Variablen
 * summeMinuten, anzahl und schnitt beobachten (Step Over).
 *
 * Die Ausschnitte (1) und (2) lassen sich NICHT kompilieren. Sie stehen deshalb
 * nur als Kommentar hier, zusammen mit der Meldung des Compilers (JDK 21, deutsch):
 *
 *   (1) String plz = 34117;
 *       Fehler: Inkompatible Typen: int kann nicht in String konvertiert werden
 *       -> Typfehler (semantischer Fehler), vom Compiler beim Übersetzen erkannt
 *
 *   (2) for (int i = 0; i < auftraege.size(); i++ {
 *       Fehler: ')' erwartet
 *       -> Syntaxfehler, vom Compiler beim Übersetzen erkannt
 */
public class Aufgabe_WZ_04_2 {

    /** Ausschnitt (3): Laufzeitfehler - gültige Indizes sind 0 bis size() - 1. */
    static String letzterFehlerhaft(List<String> auftraege) {
        return auftraege.get(auftraege.size());          // Fehler: Index = size()
    }

    /** Korrektur zu (3): letzter gültiger Index ist size() - 1 (leere Liste abfangen). */
    static String letzterKorrigiert(List<String> auftraege) {
        if (auftraege.isEmpty()) {
            return null;
        }
        return auftraege.get(auftraege.size() - 1);
    }

    /** Ausschnitt (4): logischer Fehler - Ganzzahldivision vor der Umwandlung in double. */
    static double durchschnittFehlerhaft(int summeMinuten, int anzahl) {
        double schnitt = summeMinuten / anzahl;          // 250 / 4 = 62 (int) -> 62.0
        return schnitt;
    }

    /** Korrektur zu (4): einen Operanden vor der Division in double umwandeln. */
    static double durchschnittKorrigiert(int summeMinuten, int anzahl) {
        if (anzahl == 0) {
            return 0.0;
        }
        return (double) summeMinuten / anzahl;           // 250.0 / 4 = 62.5
    }

    public static void main(String[] args) {
        List<String> auftraege = new ArrayList<>(List.of("A-1001", "A-1002", "A-1003"));

        System.out.println("(3) Laufzeitfehler:");
        try {
            System.out.println("    " + letzterFehlerhaft(auftraege));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        System.out.println("    korrigiert: " + letzterKorrigiert(auftraege));

        System.out.println("(4) Logischer Fehler (keine Fehlermeldung!):");
        System.out.println("    fehlerhaft:  Durchschnitt = " + durchschnittFehlerhaft(250, 4) + " min");
        System.out.println("    korrigiert:  Durchschnitt = " + durchschnittKorrigiert(250, 4) + " min");

        // kleiner Selbsttest
        pruefe("Korrektur (4)", 62.5, durchschnittKorrigiert(250, 4));
        pruefe("Fehler (4) reproduziert", 62.0, durchschnittFehlerhaft(250, 4));
        pruefe("Korrektur (3)", "A-1003", letzterKorrigiert(auftraege));
    }

    static void pruefe(String name, Object erwartet, Object tatsaechlich) {
        boolean ok = erwartet.equals(tatsaechlich);
        System.out.println((ok ? "OK    " : "FEHLER") + " " + name + ": erwartet " + erwartet
                + ", erhalten " + tatsaechlich);
    }
}
