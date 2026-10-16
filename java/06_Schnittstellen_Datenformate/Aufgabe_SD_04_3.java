import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Block 06 Schnittstellen & Datenformate - Lösung zu Aufgabe SD-04.3
 * Endpunkt GET /v1/kisten/{kisteId}: HTTP-Antworten mit Statuscode, Content-Type und
 * Content-Length (in BYTES) zusammenbauen.
 *
 * Starten (JDK 11+):  java Aufgabe_SD_04_3.java
 * (Umlaute in der Konsole ggf. mit  java -Dstdout.encoding=UTF-8 ...)
 */
public class Aufgabe_SD_04_3 {

    // ---------- vorgegebene Klassen (Anlage 4), hier minimal implementiert ----------

    static class HttpRequest {
        private final String methode;
        private final Map<String, String> pfadParameter = new HashMap<>();
        private final Map<String, String> header = new HashMap<>();
        private final Map<String, String> queryParameter = new HashMap<>();

        HttpRequest(String methode) { this.methode = methode; }

        HttpRequest pfad(String name, String wert) { pfadParameter.put(name, wert); return this; }
        HttpRequest header(String name, String wert) { header.put(name, wert); return this; }

        String getMethode() { return methode; }
        String getPfadParameter(String name) { return pfadParameter.get(name); }   // null, wenn nicht vorhanden
        String getHeader(String name) { return header.get(name); }                 // null, wenn der Header fehlt
        String getQueryParameter(String name) { return queryParameter.get(name); }
    }

    static class HttpResponse {
        private int status;
        private final Map<String, String> header = new LinkedHashMap<>();
        private String body = "";

        void setStatus(int code) { this.status = code; }
        void setHeader(String name, String wert) { header.put(name, wert); }
        void setBody(String inhalt) { this.body = inhalt; }

        int getStatus() { return status; }
        String getHeader(String name) { return header.get(name); }
        String getBody() { return body; }

        /** Antwort so ausgeben, wie sie über die Leitung ginge (Statuszeile, Header, Leerzeile, Body). */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("HTTP/1.1 " + status + " " + grund(status) + "\n");
            for (Map.Entry<String, String> h : header.entrySet()) {
                sb.append(h.getKey()).append(": ").append(h.getValue()).append("\n");
            }
            return sb.append("\n").append(body).toString();
        }

        static String grund(int code) {
            switch (code) {
                case 200: return "OK";
                case 401: return "Unauthorized";
                case 404: return "Not Found";
                case 503: return "Service Unavailable";
                default:  return "";
            }
        }
    }

    /** byteLength() aus Anlage 4: Anzahl der Bytes in UTF-8. */
    static int byteLength(String text) {
        return text.getBytes(StandardCharsets.UTF_8).length;
    }

    static class Kiste {
        private final String kisteId, bezeichnung, groesse;
        private final double preis;
        private final boolean bio;

        Kiste(String kisteId, String bezeichnung, String groesse, double preis, boolean bio) {
            this.kisteId = kisteId; this.bezeichnung = bezeichnung; this.groesse = groesse;
            this.preis = preis; this.bio = bio;
        }

        String toJson() {
            return "{\"kisteId\": \"" + kisteId + "\", \"bezeichnung\": \"" + bezeichnung
                    + "\", \"groesse\": \"" + groesse + "\", \"preis\": "
                    + String.format(java.util.Locale.ROOT, "%.2f", preis) + ", \"bio\": " + bio + "}";
        }
    }

    static class KistenKatalog {
        private final Map<String, Kiste> kisten = new HashMap<>();
        private boolean verfuegbar = true;

        void hinzufuegen(Kiste k) { kisten.put(k.kisteId, k); }
        void setVerfuegbar(boolean v) { verfuegbar = v; }

        boolean istVerfuegbar() { return verfuegbar; }
        Kiste findeKiste(String kisteId) { return kisten.get(kisteId); }          // null, wenn unbekannt
    }

    static class ApiSchluessel {
        private final Set<String> gueltig = Set.of("app-7Fq2-Lx91", "partner-E07-k3Lm");
        boolean istGueltig(String schluessel) { return gueltig.contains(schluessel); }
    }

    // ---------- Lösung ----------

    static class KistenController {
        private final KistenKatalog katalog;
        private final ApiSchluessel schluessel;

        KistenController(KistenKatalog katalog, ApiSchluessel schluessel) {
            this.katalog = katalog;
            this.schluessel = schluessel;
        }

        /** Lösung a): Fehlerantwort mit JSON-Body. */
        private HttpResponse createErrorResponse(int statusCode, String meldung) {
            String body = "{\"status\": " + statusCode + ", \"fehler\": \"" + meldung + "\"}";
            HttpResponse response = new HttpResponse();
            response.setStatus(statusCode);
            response.setHeader("Content-Type", "application/json; charset=utf-8");
            response.setHeader("Content-Length", String.valueOf(byteLength(body)));   // Bytes, nicht Zeichen!
            response.setBody(body);
            return response;
        }

        /** Lösung c): GET /v1/kisten/{kisteId}. */
        HttpResponse handleGetKiste(HttpRequest request) {
            String key = request.getHeader("X-API-Key");
            if (key == null || !schluessel.istGueltig(key)) {
                return createErrorResponse(401, "API-Schlüssel fehlt oder ist ungültig");
            }
            if (!katalog.istVerfuegbar()) {
                return createErrorResponse(503, "Katalog vorübergehend nicht verfügbar");
            }
            String kisteId = request.getPfadParameter("kisteId");
            Kiste kiste = katalog.findeKiste(kisteId);
            if (kiste == null) {
                return createErrorResponse(404, "Kiste " + kisteId + " nicht gefunden");
            }
            String body = kiste.toJson();
            HttpResponse response = new HttpResponse();
            response.setStatus(200);
            response.setHeader("Content-Type", "application/json; charset=utf-8");
            response.setHeader("Content-Length", String.valueOf(byteLength(body)));
            response.setBody(body);
            return response;
        }
    }

    static void test(String titel, KistenController c, HttpRequest r) {
        HttpResponse resp = c.handleGetKiste(r);
        System.out.println("=== " + titel);
        System.out.println(resp);
        int laut = Integer.parseInt(resp.getHeader("Content-Length"));
        System.out.println("[Kontrolle] Zeichen im Body: " + resp.getBody().length()
                + ", Bytes (UTF-8): " + byteLength(resp.getBody())
                + ", Content-Length korrekt: " + (laut == byteLength(resp.getBody())));
        System.out.println();
    }

    public static void main(String[] args) {
        KistenKatalog katalog = new KistenKatalog();
        katalog.hinzufuegen(new Kiste("K-OBST-M", "Obstkiste mittel", "M", 18.50, true));
        katalog.hinzufuegen(new Kiste("K-GEM-L", "Gemüsekiste groß", "L", 27.90, true));
        katalog.hinzufuegen(new Kiste("K-MIX-S", "Mischkiste klein", "S", 14.20, false));
        KistenController c = new KistenController(katalog, new ApiSchluessel());

        test("1) ohne X-API-Key", c, new HttpRequest("GET").pfad("kisteId", "K-OBST-M"));
        test("2) ungültiger Schlüssel", c,
                new HttpRequest("GET").pfad("kisteId", "K-OBST-M").header("X-API-Key", "abc"));
        test("3) unbekannte Kiste", c,
                new HttpRequest("GET").pfad("kisteId", "K-XXL-Z").header("X-API-Key", "app-7Fq2-Lx91"));
        test("4) Kiste gefunden", c,
                new HttpRequest("GET").pfad("kisteId", "K-GEM-L").header("X-API-Key", "app-7Fq2-Lx91"));
        katalog.setVerfuegbar(false);
        test("5) Katalog nicht verfügbar (Teil b)", c,
                new HttpRequest("GET").pfad("kisteId", "K-OBST-M").header("X-API-Key", "app-7Fq2-Lx91"));
    }
}
