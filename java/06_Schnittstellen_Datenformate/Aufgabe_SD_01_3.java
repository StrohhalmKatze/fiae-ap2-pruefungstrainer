import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Block 06 Schnittstellen & Datenformate - Lösung zu Aufgabe SD-01.3
 * Wochenangebot (Anlage 5) mit dem XML-Parser des JDK prüfen:
 *   1. Wohlgeformtheit: Der Parser bricht beim ERSTEN Wohlgeformtheitsfehler ab.
 *   2. Gültigkeit: Ist das Dokument wohlgeformt, meldet er alle Verstöße gegen die DTD.
 *   3. Die korrigierte Fassung ist gültig.
 *   4. Teil c): Die Datei für KW 47 ist gültig, obwohl die Werte unsinnig sind -> eigene Plausibilitätsprüfung.
 *
 * Starten (JDK 11+):  java Aufgabe_SD_01_3.java
 * (Umlaute in der Konsole ggf. mit  java -Dstdout.encoding=UTF-8 ...)
 */
public class Aufgabe_SD_01_3 {

    /** DTD aus Anlage 5. */
    static final String DTD = """
<!ELEMENT wochenangebot (erzeuger, artikel+)>
<!ATTLIST wochenangebot kw   CDATA #REQUIRED
                        jahr CDATA #REQUIRED>
<!ELEMENT erzeuger (name, ort, biozertifikat?)>
<!ATTLIST erzeuger erzeugerId ID #REQUIRED>
<!ELEMENT name (#PCDATA)>
<!ELEMENT ort (#PCDATA)>
<!ELEMENT biozertifikat (#PCDATA)>
<!ELEMENT artikel (bezeichnung, menge, preis, hinweis*)>
<!ATTLIST artikel artNr   ID              #REQUIRED
                  einheit (kg|stueck|bund) "kg">
<!ELEMENT bezeichnung (#PCDATA)>
<!ELEMENT menge (#PCDATA)>
<!ELEMENT preis (#PCDATA)>
<!ELEMENT hinweis (#PCDATA)>
""";

    /** Wochenangebot KW 46 aus Anlage 5 (fehlerhaft). */
    static final String FEHLERHAFT = """
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE wochenangebot SYSTEM "wochenangebot.dtd">
<wochenangebot kw="46">
  <erzeuger erzeugerId="E07">
    <name>Hof Brammer</name>
    <ort>Wiesede</ort>
    <biozertifikat>DE-ÖKO-006</biozertifikat>
  </erzeuger>
  <artikel artNr="A-101" einheit="kg">
    <bezeichnung>Möhren, lose</bezeichnung>
    <menge>120</menge>
    <preis>1.40</preis>
  </artikel>
  <artikel artNr="A-102" einheit="stueck">
    <bezeichnung>Kürbis Hokkaido</Bezeichnung>
    <menge>40</menge>
    <preis>2.10</preis>
    <hinweis>nur bis KW 48 lieferbar</hinweis>
  </artikel>
  <artikel artNr="A-103" einheit="bund">
    <bezeichnung>Lauchzwiebeln</bezeichnung>
    <preis>0.90</preis>
    <menge>60</menge>
  </artikel>
  <artikel artNr="A-101" einheit="Kilo">
    <bezeichnung>Rote Bete</bezeichnung>
    <menge>35<menge>
    <preis>1.80</preis>
    <lieferant>Hof Brammer</lieferant>
  </artikel>
  <artikel artNr=A-105>
    <bezeichnung>Feldsalat</bezeichnung>
    <menge>15</menge>
    <preis>7.50</preis>
  </artikel>
</wochenangebot>
""";

    /** Korrigierte Fassung (Lösung). */
    static final String KORRIGIERT = """
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE wochenangebot SYSTEM "wochenangebot.dtd">
<wochenangebot kw="46" jahr="2026">
  <erzeuger erzeugerId="E07">
    <name>Hof Brammer</name>
    <ort>Wiesede</ort>
    <biozertifikat>DE-ÖKO-006</biozertifikat>
  </erzeuger>
  <artikel artNr="A-101" einheit="kg">
    <bezeichnung>Möhren, lose</bezeichnung>
    <menge>120</menge>
    <preis>1.40</preis>
  </artikel>
  <artikel artNr="A-102" einheit="stueck">
    <bezeichnung>Kürbis Hokkaido</bezeichnung>
    <menge>40</menge>
    <preis>2.10</preis>
    <hinweis>nur bis KW 48 lieferbar</hinweis>
  </artikel>
  <artikel artNr="A-103" einheit="bund">
    <bezeichnung>Lauchzwiebeln</bezeichnung>
    <menge>60</menge>
    <preis>0.90</preis>
  </artikel>
  <artikel artNr="A-104" einheit="kg">
    <bezeichnung>Rote Bete</bezeichnung>
    <menge>35</menge>
    <preis>1.80</preis>
  </artikel>
  <artikel artNr="A-105">
    <bezeichnung>Feldsalat</bezeichnung>
    <menge>15</menge>
    <preis>7.50</preis>
  </artikel>
</wochenangebot>
""";

    /** Datei für KW 47 aus Aufgabe c). */
    static final String KW47 = """
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE wochenangebot SYSTEM "wochenangebot.dtd">
<wochenangebot kw="47" jahr="2026">
  <erzeuger erzeugerId="E07">
    <name>Hof Brammer</name>
    <ort>Wiesede</ort>
  </erzeuger>
  <artikel artNr="A-110" einheit="kg">
    <bezeichnung>Grünkohl</bezeichnung>
    <menge>ca. 80</menge>
    <preis>-1.90</preis>
  </artikel>
</wochenangebot>
""";

    static Document letztesDokument;

    static List<String> validiere(String xml) {
        List<String> meldungen = new ArrayList<>();
        letztesDokument = null;
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(true);
            DocumentBuilder b = f.newDocumentBuilder();
            b.setEntityResolver((publicId, systemId) ->
                    systemId != null && systemId.endsWith("wochenangebot.dtd")
                            ? new InputSource(new StringReader(DTD)) : null);
            b.setErrorHandler(new ErrorHandler() {
                public void warning(SAXParseException e) {
                    meldungen.add("Warnung        Zeile " + e.getLineNumber() + ": " + e.getMessage());
                }
                public void error(SAXParseException e) {
                    meldungen.add("GÜLTIGKEIT     Zeile " + e.getLineNumber() + ": " + e.getMessage());
                }
                public void fatalError(SAXParseException e) throws SAXException {
                    throw e;
                }
            });
            letztesDokument = b.parse(new InputSource(new StringReader(xml)));
        } catch (SAXParseException e) {
            meldungen.add("WOHLGEFORMTHEIT Zeile " + e.getLineNumber() + ": " + e.getMessage()
                    + "  -> Parser bricht ab");
        } catch (Exception e) {
            meldungen.add("Fehler: " + e);
        }
        return meldungen;
    }

    static void ausgabe(String titel, List<String> meldungen) {
        System.out.println("--- " + titel);
        if (meldungen.isEmpty()) {
            System.out.println("    gültig (wohlgeformt und DTD-konform)");
        }
        for (String m : meldungen) {
            System.out.println("    " + m);
        }
    }

    /** Teil c): fachliche Plausibilitätsprüfung, die eine DTD nicht leisten kann. */
    static List<String> plausibilitaet(Document doc) {
        List<String> fehler = new ArrayList<>();
        NodeList artikel = doc.getElementsByTagName("artikel");
        for (int i = 0; i < artikel.getLength(); i++) {
            Element a = (Element) artikel.item(i);
            String nr = a.getAttribute("artNr");
            String menge = a.getElementsByTagName("menge").item(0).getTextContent().trim();
            String preis = a.getElementsByTagName("preis").item(0).getTextContent().trim();
            try {
                if (Integer.parseInt(menge) <= 0) {
                    fehler.add(nr + ": Menge muss größer als 0 sein (" + menge + ")");
                }
            } catch (NumberFormatException e) {
                fehler.add(nr + ": Menge ist keine ganze Zahl (\"" + menge + "\")");
            }
            try {
                if (Double.parseDouble(preis) <= 0) {
                    fehler.add(nr + ": Preis muss positiv sein (" + preis + ")");
                }
            } catch (NumberFormatException e) {
                fehler.add(nr + ": Preis ist keine Zahl (\"" + preis + "\")");
            }
        }
        return fehler;
    }

    public static void main(String[] args) {
        // 1. Wohlgeformtheit: Fehler einzeln beheben, um jeweils den nächsten zu sehen
        String xml = FEHLERHAFT;
        ausgabe("1a) Originaldatei Anlage 5", validiere(xml));
        xml = xml.replace("</Bezeichnung>", "</bezeichnung>");
        ausgabe("1b) nach Korrektur Zeile 15", validiere(xml));
        xml = xml.replace("<menge>35<menge>", "<menge>35</menge>");
        ausgabe("1c) nach Korrektur Zeile 27", validiere(xml));
        xml = xml.replace("artNr=A-105", "artNr=\"A-105\"");

        // 2. Jetzt wohlgeformt -> alle Gültigkeitsfehler werden gemeldet
        ausgabe("2) nach Korrektur Zeile 31: wohlgeformt, Prüfung gegen die DTD", validiere(xml));

        // 3. Musterlösung
        ausgabe("3) korrigierte Fassung", validiere(KORRIGIERT));

        // 4. Teil c)
        List<String> m = validiere(KW47);
        ausgabe("4) Datei KW 47 gegen die DTD", m);
        if (m.isEmpty() && letztesDokument != null) {
            System.out.println("    ... aber fachliche Prüfung:");
            for (String f : plausibilitaet(letztesDokument)) {
                System.out.println("    PLAUSIBILITÄT " + f);
            }
        }
    }
}
