import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * Block 06 Schnittstellen & Datenformate - Lösung zu Aufgabe SD-05.3 c)
 * Zeigt, dass eine Schema-Validierung (XSD) nur Struktur und Datentypen prüft:
 * Die Rechnung aus Anlage 12 ist XSD-gültig, verletzt aber Geschäftsregeln
 * (in echten XRechnungen prüft das der Schematron-Teil des Validators).
 *
 * Starten (JDK 11+):  java Aufgabe_SD_05_3.java
 * (Umlaute in der Konsole ggf. mit  java -Dstdout.encoding=UTF-8 ...)
 */
public class Aufgabe_SD_05_3 {

    static final String NS = "urn:kranich:frischekiste:rechnung:1.0";

    /** Vereinfachtes Schema (nicht das echte XRechnung-Schema). */
    static final String XSD = """
<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           targetNamespace="urn:kranich:frischekiste:rechnung:1.0"
           xmlns="urn:kranich:frischekiste:rechnung:1.0"
           elementFormDefault="qualified">
  <xs:element name="rechnung">
    <xs:complexType>
      <xs:sequence>
        <xs:element name="rechnungsnummer" type="xs:string"/>
        <xs:element name="rechnungsdatum" type="xs:date"/>
        <xs:element name="faelligAm" type="xs:date"/>
        <xs:element name="leitwegId" type="xs:string" minOccurs="0"/>
        <xs:element name="verkaeufer" type="partei"/>
        <xs:element name="kaeufer" type="partei"/>
        <xs:element name="position" type="position" maxOccurs="unbounded"/>
        <xs:element name="summen" type="summen"/>
      </xs:sequence>
    </xs:complexType>
  </xs:element>
  <xs:complexType name="partei">
    <xs:sequence>
      <xs:element name="name" type="xs:string"/>
      <xs:element name="ustId" type="xs:string" minOccurs="0"/>
    </xs:sequence>
  </xs:complexType>
  <xs:complexType name="position">
    <xs:sequence>
      <xs:element name="bezeichnung" type="xs:string"/>
      <xs:element name="menge" type="xs:decimal"/>
      <xs:element name="einzelpreis" type="xs:decimal"/>
      <xs:element name="nettobetrag" type="xs:decimal"/>
      <xs:element name="steuersatz" type="xs:decimal"/>
    </xs:sequence>
    <xs:attribute name="nr" type="xs:positiveInteger" use="required"/>
  </xs:complexType>
  <xs:complexType name="summen">
    <xs:sequence>
      <xs:element name="nettogesamt" type="xs:decimal"/>
      <xs:element name="umsatzsteuer" type="xs:decimal"/>
      <xs:element name="bruttogesamt" type="xs:decimal"/>
    </xs:sequence>
  </xs:complexType>
</xs:schema>
""";

    /** Rechnungsauszug aus Anlage 12. */
    static final String RECHNUNG = """
<?xml version="1.0" encoding="UTF-8"?>
<rechnung xmlns="urn:kranich:frischekiste:rechnung:1.0">
  <rechnungsnummer>RE-2026-04711</rechnungsnummer>
  <rechnungsdatum>2026-11-30</rechnungsdatum>
  <faelligAm>2026-11-16</faelligAm>
  <leitwegId>03359012-4711-08</leitwegId>
  <verkaeufer>
    <name>FrischeKiste Nordheide GmbH</name>
    <ustId>DE123456789</ustId>
  </verkaeufer>
  <kaeufer>
    <name>Stadt Heidmoor, Kita Sonnenblume</name>
  </kaeufer>
  <position nr="1">
    <bezeichnung>Obstkiste mittel</bezeichnung>
    <menge>8</menge>
    <einzelpreis>18.50</einzelpreis>
    <nettobetrag>148.00</nettobetrag>
    <steuersatz>7</steuersatz>
  </position>
  <position nr="2">
    <bezeichnung>Gemüsekiste groß</bezeichnung>
    <menge>4</menge>
    <einzelpreis>27.90</einzelpreis>
    <nettobetrag>101.60</nettobetrag>
    <steuersatz>7</steuersatz>
  </position>
  <summen>
    <nettogesamt>259.60</nettogesamt>
    <umsatzsteuer>18.17</umsatzsteuer>
    <bruttogesamt>278.77</bruttogesamt>
  </summen>
</rechnung>
""";

    /** Schritt 1: Schema-Validierung mit javax.xml.validation (im JDK enthalten). */
    static String validiereXsd(String xml) {
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new StreamSource(new StringReader(XSD)));
            Validator v = schema.newValidator();
            v.validate(new StreamSource(new StringReader(xml)));
            return "XSD-gültig";
        } catch (org.xml.sax.SAXParseException e) {
            return "XSD-Fehler in Zeile " + e.getLineNumber() + ": " + e.getMessage();
        } catch (Exception e) {
            return "XSD-Fehler: " + e.getMessage();
        }
    }

    static String text(Element parent, String name) {
        return parent.getElementsByTagNameNS(NS, name).item(0).getTextContent().trim();
    }

    static BigDecimal zahl(Element parent, String name) {
        return new BigDecimal(text(parent, name));
    }

    /** Schritt 2: Geschäftsregeln (vereinfacht, wie sie Schematron-Regeln prüfen würden). */
    static List<String> pruefeGeschaeftsregeln(String xml) throws Exception {
        List<String> fehler = new ArrayList<>();
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setNamespaceAware(true);
        Document doc = f.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
        Element r = doc.getDocumentElement();

        LocalDate rechnungsdatum = LocalDate.parse(text(r, "rechnungsdatum"));
        LocalDate faellig = LocalDate.parse(text(r, "faelligAm"));
        if (faellig.isBefore(rechnungsdatum)) {
            fehler.add("Regel 1 verletzt: Fälligkeitsdatum " + faellig + " liegt vor dem Rechnungsdatum "
                    + rechnungsdatum);
        }

        BigDecimal summePositionen = BigDecimal.ZERO;
        BigDecimal steuer = BigDecimal.ZERO;
        NodeList positionen = r.getElementsByTagNameNS(NS, "position");
        for (int i = 0; i < positionen.getLength(); i++) {
            Element p = (Element) positionen.item(i);
            BigDecimal soll = zahl(p, "menge").multiply(zahl(p, "einzelpreis")).setScale(2, RoundingMode.HALF_UP);
            BigDecimal ist = zahl(p, "nettobetrag");
            if (soll.compareTo(ist) != 0) {
                fehler.add("Regel 2 verletzt: Position " + p.getAttribute("nr") + ": Menge x Einzelpreis = "
                        + soll + ", angegeben " + ist);
            }
            summePositionen = summePositionen.add(ist);
            steuer = steuer.add(ist.multiply(zahl(p, "steuersatz")).divide(new BigDecimal("100")));
        }

        Element summen = (Element) r.getElementsByTagNameNS(NS, "summen").item(0);
        BigDecimal netto = zahl(summen, "nettogesamt");
        BigDecimal ust = zahl(summen, "umsatzsteuer");
        BigDecimal brutto = zahl(summen, "bruttogesamt");
        if (summePositionen.compareTo(netto) != 0) {
            fehler.add("Regel 3 verletzt: Summe der Positionsbeträge = " + summePositionen
                    + ", Nettogesamt angegeben " + netto);
        }
        BigDecimal ustSoll = netto.multiply(new BigDecimal("0.07")).setScale(2, RoundingMode.HALF_UP);
        if (ustSoll.compareTo(ust) != 0) {
            fehler.add("Regel 4 verletzt: 7 % von " + netto + " = " + ustSoll + ", angegeben " + ust);
        }
        if (netto.add(ust).compareTo(brutto) != 0) {
            fehler.add("Regel 5 verletzt: Netto + USt = " + netto.add(ust) + ", Bruttogesamt angegeben " + brutto);
        }
        return fehler;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Schritt 1 - Schema-Validierung: " + validiereXsd(RECHNUNG));
        System.out.println("Schritt 2 - Geschäftsregeln:");
        List<String> fehler = pruefeGeschaeftsregeln(RECHNUNG);
        if (fehler.isEmpty()) {
            System.out.println("  keine Fehler");
        }
        for (String f : fehler) {
            System.out.println("  " + f);
        }

        // Gegenprobe: Was das Schema sehr wohl erkennt (Struktur/Datentyp)
        System.out.println();
        System.out.println("Gegenprobe A (Dezimalkomma 18,50): "
                + validiereXsd(RECHNUNG.replace("<einzelpreis>18.50</einzelpreis>", "<einzelpreis>18,50</einzelpreis>")));
        System.out.println("Gegenprobe B (ungültiges Datum 2026-11-31): "
                + validiereXsd(RECHNUNG.replace("2026-11-30", "2026-11-31")));
        System.out.println("Gegenprobe C (Element summen fehlt): "
                + validiereXsd(RECHNUNG.replaceAll("(?s)<summen>.*</summen>", "")));
    }
}
