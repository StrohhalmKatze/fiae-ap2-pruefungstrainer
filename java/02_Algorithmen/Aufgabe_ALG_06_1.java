
/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-06.1
 * String-Methoden auswerten, == vs. equals
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_06_1.java
 * oder:  javac Aufgabe_ALG_06_1.java  und danach  java Aufgabe_ALG_06_1
 */
public class Aufgabe_ALG_06_1 {

    public static void main(String[] args) {
        String code = "AR-0107-8";
        System.out.println("code.length()        = " + code.length());
        System.out.println("code.charAt(3)       = '" + code.charAt(3) + "'");
        System.out.println("code.substring(3, 7) = \"" + code.substring(3, 7) + "\"");
        System.out.println("code.indexOf('-')    = " + code.indexOf('-'));

        String a = "AR-0107-8";
        String b = new String("AR-0107-8");      // z. B. aus Benutzereingabe oder Datei
        System.out.println("a == b      -> " + (a == b) + "   (vergleicht Referenzen)");
        System.out.println("a.equals(b) -> " + a.equals(b) + "    (vergleicht Inhalte)");
    }
}
