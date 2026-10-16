import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Block 06 Schnittstellen & Datenformate - Lösung zu Aufgabe SD-01.2 b)
 * Prüft die selbst erstellte DTD "lieferschein.dtd" mit dem XML-Parser des JDK:
 * Der Lieferschein aus Anlage 6 muss gültig sein, absichtlich fehlerhafte Varianten nicht.
 *
 * Starten (JDK 11+):  java Aufgabe_SD_01_2.java
 * oder:  javac Aufgabe_SD_01_2.java  und danach  java Aufgabe_SD_01_2
 * (Umlaute in der Konsole ggf. mit  java -Dstdout.encoding=UTF-8 ...)
 */
public class Aufgabe_SD_01_2 {

    /** Lösung: DTD für den Lieferschein (Aufgabe SD-01.2 b). */
    static final String DTD = """
<!ELEMENT lieferschein (kunde, position+, fahrer?)>
<!ATTLIST lieferschein nr    CDATA #REQUIRED
                       datum CDATA #REQUIRED>
<!ELEMENT kunde (name, adresse)>
<!ATTLIST kunde kundenNr CDATA #REQUIRED>
<!ELEMENT name (#PCDATA)>
<!ELEMENT adresse (#PCDATA)>
<!ELEMENT position (kiste, anzahl, bemerkung*)>
<!ELEMENT kiste (#PCDATA)>
<!ELEMENT anzahl (#PCDATA)>
<!ELEMENT bemerkung (#PCDATA)>
<!ELEMENT fahrer (#PCDATA)>
""";

    /** Lieferschein aus Anlage 6. */
    static final String LIEFERSCHEIN = """
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE lieferschein SYSTEM "lieferschein.dtd">
<lieferschein nr="LS-2026-1187" datum="2026-11-10">
  <kunde kundenNr="20015">
    <name>Kita Sonnenblume</name>
    <adresse>Am Mühlenbach 3, 21391 Heidmoor</adresse>
  </kunde>
  <position>
    <kiste>K-OBST-M</kiste>
    <anzahl>4</anzahl>
  </position>
  <position>
    <kiste>K-GEM-L</kiste>
    <anzahl>2</anzahl>
    <bemerkung>ohne Sellerie</bemerkung>
    <bemerkung>Kühlware zuerst ausladen</bemerkung>
  </position>
  <fahrer>T. Ohlsen</fahrer>
</lieferschein>
""";

    /**
     * Validiert ein XML-Dokument gegen die DTD.
     * Der Verweis SYSTEM "lieferschein.dtd" wird über einen EntityResolver auf den String DTD umgeleitet,
     * damit keine externe Datei nötig ist.
     */
    static List<String> validiere(String xml) {
        List<String> meldungen = new ArrayList<>();
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(true);                       // gegen die DTD prüfen (Gültigkeit)
            DocumentBuilder b = f.newDocumentBuilder();
            b.setEntityResolver((publicId, systemId) ->
                    systemId != null && systemId.endsWith("lieferschein.dtd")
                            ? new InputSource(new StringReader(DTD)) : null);
            b.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e) {
                    meldungen.add("Warnung, Zeile " + e.getLineNumber() + ": " + e.getMessage());
                }
                public void error(SAXParseException e) {      // Verstoß gegen die DTD
                    meldungen.add("ungültig, Zeile " + e.getLineNumber() + ": " + e.getMessage());
                }
                public void fatalError(SAXParseException e) throws SAXException {
                    throw e;                                  // nicht wohlgeformt -> Abbruch
                }
            });
            b.parse(new InputSource(new StringReader(xml)));
        } catch (SAXParseException e) {
            meldungen.add("nicht wohlgeformt, Zeile " + e.getLineNumber() + ": " + e.getMessage());
        } catch (Exception e) {
            meldungen.add("Fehler: " + e);
        }
        return meldungen;
    }

    static void pruefe(String titel, String xml) {
        List<String> m = validiere(xml);
        System.out.println("--- " + titel);
        if (m.isEmpty()) {
            System.out.println("    gültig (wohlgeformt und DTD-konform)");
        } else {
            for (String s : m) {
                System.out.println("    " + s);
            }
        }
    }

    public static void main(String[] args) {
        pruefe("Lieferschein aus Anlage 6", LIEFERSCHEIN);

        // Negativtests: Diese Varianten MÜSSEN gegen die DTD verstoßen.
        String ohnePosition = LIEFERSCHEIN.replaceAll("(?s)<position>.*</position>\\s*", "");
        pruefe("Negativtest 1: keine Position (position+ verlangt mindestens eine)", ohnePosition);

        String ohneNr = LIEFERSCHEIN.replace(" nr=\"LS-2026-1187\"", "");
        pruefe("Negativtest 2: Pflichtattribut nr fehlt", ohneNr);

        String reihenfolge = LIEFERSCHEIN.replace(
                "<kiste>K-OBST-M</kiste>\n    <anzahl>4</anzahl>",
                "<anzahl>4</anzahl>\n    <kiste>K-OBST-M</kiste>");
        pruefe("Negativtest 3: anzahl vor kiste (Reihenfolge der Sequenz)", reihenfolge);

        String zweiFahrer = LIEFERSCHEIN.replace("<fahrer>T. Ohlsen</fahrer>",
                "<fahrer>T. Ohlsen</fahrer>\n  <fahrer>M. Grote</fahrer>");
        pruefe("Negativtest 4: zwei Fahrer (fahrer? erlaubt höchstens einen)", zweiFahrer);

        String ohneFahrer = LIEFERSCHEIN.replace("  <fahrer>T. Ohlsen</fahrer>\n", "");
        pruefe("Positivtest: ohne Fahrer (optional) und ohne Bemerkung", ohneFahrer
                .replace("    <bemerkung>ohne Sellerie</bemerkung>\n", "")
                .replace("    <bemerkung>Kühlware zuerst ausladen</bemerkung>\n", ""));
    }
}
