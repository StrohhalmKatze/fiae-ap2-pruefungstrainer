
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-06.2
 * Passwortprüfung Zeichen für Zeichen
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_06_2.java
 * oder:  javac Aufgabe_ALG_06_2.java  und danach  java Aufgabe_ALG_06_2
 */
public class Aufgabe_ALG_06_2 {

    // Hilfsfunktionen aus Anlage 5
    static boolean istGrossbuchstabe(char c) { return Character.isUpperCase(c); }
    static boolean istKleinbuchstabe(char c) { return Character.isLowerCase(c); }
    static boolean istZiffer(char c) { return c >= '0' && c <= '9'; }

    // a) Passwortrichtlinie der Auenrad-App
    static boolean pruefePasswort(String pw) {
        if (pw.length() < 10) {
            return false;
        }
        String sonderzeichen = "!?#+-_*";
        boolean gross = false;
        boolean klein = false;
        boolean ziffer = false;
        boolean sonder = false;
        for (int i = 0; i <= pw.length() - 1; i++) {
            char c = pw.charAt(i);
            if (c == ' ') {
                return false;
            } else if (istGrossbuchstabe(c)) {
                gross = true;
            } else if (istKleinbuchstabe(c)) {
                klein = true;
            } else if (istZiffer(c)) {
                ziffer = true;
            } else if (sonderzeichen.indexOf(c) >= 0) {
                sonder = true;
            }
        }
        return gross && klein && ziffer && sonder;
    }

    public static void main(String[] args) {
        String[] tests = {"Rad2026!", "auenrad#2026", "Auenrad#2026", "Auen Rad#2026", "AUENRAD#2026x", "Auenrad2026"};
        for (String t : tests) {
            System.out.printf("pruefePasswort(\"%s\") = %b%n", t, pruefePasswort(t));
        }
    }
}
