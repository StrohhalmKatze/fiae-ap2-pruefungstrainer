import java.util.HashMap;
import java.util.Map;

/**
 * Block 10 Netzwerk, IoT & Infrastruktur - Lösung zu Aufgabe NET-03.3
 * Inventarisierung des Klärwerk-Netzes 10.10.20.0/24 als JSON-Array,
 * Erweiterung um den Hersteller (OUI), Subnetting /26 und Scan nur eines Subnetzes.
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_NET_03_3.java
 * oder:  javac Aufgabe_NET_03_3.java  und danach  java Aufgabe_NET_03_3
 *
 * getMacAddress(...) und getVendor(...) sind hier nur simuliert (Anlage 7):
 * Statt echte ARP-Anfragen zu senden, schlagen sie in einer Tabelle nach.
 * Die Herstellernamen sind fiktiv (Übungsdaten).
 */
public class Aufgabe_NET_03_3 {

    // ------------------------------------------------------------------
    // Simulierte Hilfsfunktionen aus Anlage 7
    // ------------------------------------------------------------------

    /** Simuliertes Netz des Klärwerks: IP-Adresse -> MAC-Adresse. */
    private static final Map<String, String> NETZ = new HashMap<>();
    /** Simulierte Herstellerliste: OUI (erste drei Byte) -> Hersteller (fiktiv). */
    private static final Map<String, String> HERSTELLER = new HashMap<>();

    static {
        NETZ.put("10.10.20.1",   "5C:F2:86:00:14:01");  // Router/Firewall R2
        NETZ.put("10.10.20.2",   "5C:F2:86:00:14:02");  // Switch SW2 (Management)
        NETZ.put("10.10.20.31",  "3C:52:82:5E:90:31");  // Leitwarten-PC 1
        NETZ.put("10.10.20.32",  "3C:52:82:5E:90:32");  // Leitwarten-PC 2
        NETZ.put("10.10.20.41",  "3C:52:82:5E:91:41");  // Labor-PC
        NETZ.put("10.10.20.50",  "7C:4F:B5:3A:07:D1");  // LoRaWAN-Gateway GW2 (Hersteller nicht in der Liste)
        NETZ.put("10.10.20.70",  "00:0E:8C:A1:22:70");  // SPS Belebung
        NETZ.put("10.10.20.71",  "00:0E:8C:A1:22:71");  // SPS Zulauf
        NETZ.put("10.10.20.140", "A4:C1:38:0B:5D:8C");  // Kamera Zulauf
        NETZ.put("10.10.20.201", "F2:6B:19:C8:44:0E");  // Tablet mit zufälliger (lokal vergebener) MAC
        NETZ.put("10.10.20.254", "5C:F2:86:00:14:FE");  // WLAN-Access-Point AP2 (letzte Hostadresse!)

        HERSTELLER.put("5C:F2:86", "Nordwerk Netzwerktechnik");
        HERSTELLER.put("3C:52:82", "Lumetra Computer");
        HERSTELLER.put("00:0E:8C", "Prozessa Automation");
        HERSTELLER.put("A4:C1:38", "Visiotec Kamerasysteme");
    }

    /** Anlage 7: liefert die MAC-Adresse oder null, wenn kein Gerät antwortet. */
    static String getMacAddress(String ip) {
        return NETZ.get(ip);   // null, wenn die IP nicht vergeben ist
    }

    /** Anlage 7: liefert den Hersteller zu einer OUI "XX:XX:XX" oder null. */
    static String getVendor(String oui) {
        return HERSTELLER.get(oui);
    }

    // ------------------------------------------------------------------
    // a) Inventarisierung als JSON-Array
    // ------------------------------------------------------------------

    static String inventarisiere(String netz) {
        String json = "[";
        boolean ersterEintrag = true;
        for (int i = 1; i <= 254; i++) {                 // .0 = Netzadresse, .255 = Broadcast
            String ip = netz + "." + i;
            String mac = getMacAddress(ip);
            if (mac != null) {                           // nur Geräte, die geantwortet haben
                if (!ersterEintrag) {
                    json = json + ", ";                  // Komma nur vor jedem weiteren Objekt
                }
                json = json + "{\"ip\": \"" + ip + "\", \"mac\": \"" + mac + "\"}";
                ersterEintrag = false;
            }
        }
        json = json + "]";
        return json;
    }

    // ------------------------------------------------------------------
    // b) Erweiterung um den Hersteller
    // ------------------------------------------------------------------

    static String inventarisiereMitHersteller(String netz) {
        String json = "[";
        boolean ersterEintrag = true;
        for (int i = 1; i <= 254; i++) {
            String ip = netz + "." + i;
            String mac = getMacAddress(ip);
            if (mac != null) {
                String oui = mac.substring(0, 8);        // "5C:F2:86:00:14:01" -> "5C:F2:86"
                String hersteller = getVendor(oui);
                if (hersteller == null) {
                    hersteller = "unbekannt";
                }
                if (!ersterEintrag) {
                    json = json + ", ";
                }
                json = json + "{\"ip\": \"" + ip + "\", \"mac\": \"" + mac
                            + "\", \"hersteller\": \"" + hersteller + "\"}";
                ersterEintrag = false;
            }
        }
        json = json + "]";
        return json;
    }

    // ------------------------------------------------------------------
    // d) nur das Subnetz Leittechnik 10.10.20.64/26 durchsuchen (Hosts .65 bis .126)
    // ------------------------------------------------------------------

    static String inventarisiereBereich(String netz, int von, int bis) {
        String json = "[";
        boolean ersterEintrag = true;
        for (int i = von; i <= bis; i++) {               // geänderte Schleifengrenzen
            String ip = netz + "." + i;
            String mac = getMacAddress(ip);
            if (mac != null) {
                if (!ersterEintrag) {
                    json = json + ", ";
                }
                json = json + "{\"ip\": \"" + ip + "\", \"mac\": \"" + mac + "\"}";
                ersterEintrag = false;
            }
        }
        json = json + "]";
        return json;
    }

    // ------------------------------------------------------------------
    // c) Subnetting zur Kontrolle: 10.10.20.0/24 in vier /26-Netze
    // ------------------------------------------------------------------

    static long ipZuZahl(String ip) {
        String[] o = ip.split("\\.");
        return (Long.parseLong(o[0]) << 24) | (Long.parseLong(o[1]) << 16)
             | (Long.parseLong(o[2]) << 8) | Long.parseLong(o[3]);
    }

    static String zahlZuIp(long z) {
        return ((z >> 24) & 255) + "." + ((z >> 16) & 255) + "." + ((z >> 8) & 255) + "." + (z & 255);
    }

    static void subnetzTabelle(String basis, int altesPraefix, int neuesPraefix, String[] namen) {
        long start = ipZuZahl(basis);
        long groesse = 1L << (32 - neuesPraefix);          // Adressen je Subnetz
        long maske = (0xFFFFFFFFL << (32 - neuesPraefix)) & 0xFFFFFFFFL;
        System.out.println("Maske /" + neuesPraefix + " = " + zahlZuIp(maske)
                + ", Hosts je Subnetz = " + (groesse - 2));
        int anzahl = 1 << (neuesPraefix - altesPraefix);
        for (int k = 0; k < anzahl; k++) {
            long netzAdr = start + k * groesse;
            long broadcast = netzAdr + groesse - 1;
            System.out.printf("%-12s %-17s %-14s %-14s %s%n", namen[k],
                    zahlZuIp(netzAdr) + "/" + neuesPraefix, zahlZuIp(netzAdr + 1),
                    zahlZuIp(broadcast - 1), zahlZuIp(broadcast));
        }
    }

    // ------------------------------------------------------------------
    // Testlauf
    // ------------------------------------------------------------------

    public static void main(String[] args) {
        System.out.println("a) inventarisiere(\"10.10.20\"):");
        System.out.println(inventarisiere("10.10.20"));
        System.out.println();
        System.out.println("b) inventarisiereMitHersteller(\"10.10.20\"):");
        System.out.println(inventarisiereMitHersteller("10.10.20"));
        System.out.println();
        System.out.println("c) Aufteilung 10.10.20.0/24 in vier gleich große Subnetze:");
        subnetzTabelle("10.10.20.0", 24, 26, new String[] {"Büro", "Leittechnik", "Kameras", "WLAN"});
        System.out.println();
        System.out.println("d) nur Subnetz Leittechnik (Hosts 65 bis 126), heutiger Gerätebestand:");
        System.out.println(inventarisiereBereich("10.10.20", 65, 126));
        System.out.println();
        System.out.println("Kontrolle: leeres Netz 10.10.99 -> " + inventarisiere("10.10.99"));
    }
}
