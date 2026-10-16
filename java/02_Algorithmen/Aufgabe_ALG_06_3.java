
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-06.3
 * Namen zerlegen und Radcode mit Prüfziffer prüfen
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_06_3.java
 * oder:  javac Aufgabe_ALG_06_3.java  und danach  java Aufgabe_ALG_06_3
 */
public class Aufgabe_ALG_06_3 {

    static boolean istZiffer(char c) { return c >= '0' && c <= '9'; }
    static int zahlwert(char c) { return c - '0'; }

    // a) "Nachname, Vorname" -> [Vorname, Nachname]
    static String[] zerlegeName(String vollname) {
        String[] ergebnis = new String[2];
        int pos = vollname.indexOf(',');
        if (pos == -1) {
            ergebnis[0] = "";
            ergebnis[1] = vollname.trim();
        } else {
            ergebnis[1] = vollname.substring(0, pos).trim();
            ergebnis[0] = vollname.substring(pos + 1, vollname.length()).trim();
        }
        return ergebnis;
    }

    // b) Format "AR-" + 4 Ziffern + "-" + Prüfziffer (= Summe der 4 Ziffern mod 10)
    static boolean pruefeRadCode(String code) {
        if (code.length() != 9) {
            return false;
        }
        if (!code.substring(0, 3).equals("AR-") || code.charAt(7) != '-') {
            return false;
        }
        int summe = 0;
        for (int i = 3; i <= 6; i++) {
            char c = code.charAt(i);
            if (!istZiffer(c)) {
                return false;
            }
            summe = summe + zahlwert(c);
        }
        char p = code.charAt(8);
        if (!istZiffer(p)) {
            return false;
        }
        return zahlwert(p) == summe % 10;
    }

    public static void main(String[] args) {
        String[] namen = {"Schulze-Ebert, Anna Lena", "Haas,Tom", "  Nowak ,  Piotr ", "Müller"};
        for (String n : namen) {
            String[] t = zerlegeName(n);
            System.out.printf("zerlegeName(\"%s\") -> Vorname \"%s\", Nachname \"%s\"%n", n, t[0], t[1]);
        }
        String[] codes = {"AR-0107-8", "AR-0107-7", "AR-01O7-8", "AR-107-8", "AR-0959-3", "XR-0107-8"};
        for (String c : codes) {
            System.out.printf("pruefeRadCode(\"%s\") = %b%n", c, pruefeRadCode(c));
        }
    }
}
