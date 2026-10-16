// Lösung UML-07.2: Fälligkeitstag mit Fallauswahl und kopfgesteuerter Schleife
// Start: java Aufgabe_UML_07_2.java
public class Aufgabe_UML_07_2 {

    // Umsetzung des Struktogramms (Fallauswahl = switch, kopfgesteuerte Schleife = while)
    static int faelligkeit(String medientyp, int ausleihTag, boolean[] geschlossen) {
        int frist;
        switch (medientyp) {
            case "Buch", "Hörbuch" -> frist = 28;
            case "Film", "Spiel" -> frist = 14;
            case "Zeitschrift" -> frist = 7;
            default -> frist = 21;
        }
        int tag = ausleihTag + frist;
        while (geschlossen[tag]) {      // kopfgesteuert: Bedingung wird VOR dem ersten Durchlauf geprüft
            tag = tag + 1;
        }
        return tag;
    }

    // Variante mit fußgesteuerter Schleife (do-while) – zeigt den Fehler aus Teilaufgabe b)
    static int faelligkeitFussgesteuert(String medientyp, int ausleihTag, boolean[] geschlossen) {
        int frist = medientyp.equals("Film") ? 14 : 28;
        int tag = ausleihTag + frist;
        do {
            tag = tag + 1;
        } while (geschlossen[tag]);
        return tag;
    }

    public static void main(String[] args) {
        boolean[] geschlossen = new boolean[366];
        // Testdaten: Tag 114 ist ein Sonntag, Tag 115 ein Feiertag, außerdem Sonntag 121
        geschlossen[114] = true;
        geschlossen[115] = true;
        geschlossen[121] = true;

        System.out.println("Film, ausgeliehen an Tag 100        -> " + faelligkeit("Film", 100, geschlossen) + " (erwartet 116)");
        System.out.println("Buch, ausgeliehen an Tag 100        -> " + faelligkeit("Buch", 100, geschlossen) + " (erwartet 128)");
        System.out.println("Zeitschrift, ausgeliehen an Tag 114 -> " + faelligkeit("Zeitschrift", 114, geschlossen) + " (erwartet 122)");
        System.out.println("Noten, ausgeliehen an Tag 100       -> " + faelligkeit("Noten", 100, geschlossen) + " (erwartet 122)");
        System.out.println("fußgesteuert, Buch an Tag 100       -> " + faelligkeitFussgesteuert("Buch", 100, geschlossen)
                + " (falsch: 129 statt 128, ein Tag zu spät)");
    }
}
