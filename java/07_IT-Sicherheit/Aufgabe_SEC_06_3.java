import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.HexFormat;

/**
 * Block 07 IT-Sicherheit & Datenschutz - Lösung zu Aufgabe SEC-06.3 (Anlage 8, Klasse MitgliederDAO)
 *
 *  b) zeigt, welche SQL-Anweisung die unsichere Methode aus einer E-Mail-Adresse mit Apostroph baut
 *  c) loginNeu / loginSicher: dieselbe Abfrage mit PreparedStatement
 *  d) htmlEscape: Abhilfe für die ungefilterte HTML-Ausgabe in bewertungenAlsHtml
 *
 * Start ohne Datenbanktreiber:  java Aufgabe_SEC_06_3.java
 *     -> Teil b) und d) laufen, der Datenbanktest wird übersprungen.
 * Start mit H2-Treiber (optional, Test an einer Datenbank im Arbeitsspeicher):
 *     java -cp h2-2.2.220.jar Aufgabe_SEC_06_3.java
 */
public class Aufgabe_SEC_06_3 {

    // ------------------------------------------------------------------------------------------
    // Klasse aus Anlage 8 (unverändert übernommen, nur als statische innere Klasse).
    // Sie wird hier nur kompiliert, nicht ausgeführt - es gibt keinen MySQL-Server "db.pulsar.intern".
    // ------------------------------------------------------------------------------------------
    static class MitgliederDAO {

        private static final String DB_URL  = "jdbc:mysql://db.pulsar.intern:3306/pulsar";
        private static final String DB_USER = "root";
        private static final String DB_PASS = "Pulsar2026!";

        // Anmeldung im Web-Portal: true, wenn E-Mail und Passwort stimmen
        public boolean login(String email, String passwort) throws Exception {
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "SELECT mitglied_nr FROM mitglied WHERE email = '" + email
                       + "' AND passwort_md5 = '" + md5(passwort) + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            return rs.next();              // mindestens ein Treffer = angemeldet
        }

        // Kursbewertungen als HTML-Liste für das Web-Portal
        public String bewertungenAlsHtml(int kursId) {
            StringBuilder html = new StringBuilder("<ul>");
            try {
                Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                PreparedStatement ps = con.prepareStatement(
                    "SELECT kommentar FROM bewertung WHERE kurs_id = ?");
                ps.setInt(1, kursId);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    html.append("<li>").append(rs.getString("kommentar")).append("</li>");
                }
            } catch (SQLException e) {
                return "<p>Datenbankfehler: " + e.getMessage() + "</p>";
            }
            return html.append("</ul>").toString();
        }

        // MD5-Hashwert als Hex-String (32 Zeichen)
        private static String md5(String text) throws Exception {
            MessageDigest md = MessageDigest.getInstance("MD5");
            StringBuilder hex = new StringBuilder();
            for (byte b : md.digest(text.getBytes("UTF-8"))) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        }

        // ---------------------------------------------------------------- Lösung c)
        // Überarbeitete Methode login: PreparedStatement statt String-Verkettung
        public boolean loginNeu(String email, String passwort) throws Exception {
            String sql = "SELECT mitglied_nr FROM mitglied WHERE email = ? AND passwort_md5 = ?";
            try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, email);               // Eingaben werden nur als Daten übergeben
                ps.setString(2, md5(passwort));
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    // ------------------------------------------------------------------------------------------
    // Testfassungen mit übergebener Connection (gleiche SQL-Logik wie oben), damit sie gegen eine
    // Testdatenbank laufen können.
    // ------------------------------------------------------------------------------------------
    static String md5(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return HexFormat.of().formatHex(md.digest(text.getBytes(StandardCharsets.UTF_8)));
    }

    /** Baut die SQL-Anweisung genau wie Zeile 13/14 in Anlage 8. */
    static String baueSqlUnsicher(String email, String passwort) throws Exception {
        return "SELECT mitglied_nr FROM mitglied WHERE email = '" + email
             + "' AND passwort_md5 = '" + md5(passwort) + "'";
    }

    static String loginUnsicher(Connection con, String email, String passwort) throws Exception {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(baueSqlUnsicher(email, passwort))) {
            return String.valueOf(rs.next());
        } catch (SQLException e) {
            return "SQL-Fehler";
        }
    }

    static boolean loginSicher(Connection con, String email, String passwort) throws Exception {
        String sql = "SELECT mitglied_nr FROM mitglied WHERE email = ? AND passwort_md5 = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, md5(passwort));
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    /** d) Abhilfe: Sonderzeichen vor der Ausgabe in HTML kodieren. */
    static String htmlEscape(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '<':  sb.append("&lt;");   break;
                case '>':  sb.append("&gt;");   break;
                case '&':  sb.append("&amp;");  break;
                case '"':  sb.append("&quot;"); break;
                case '\'': sb.append("&#39;");  break;
                default:   sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        String apostroph = "o'neil@mail.example";      // gültige Adresse mit Apostroph (Teil b)

        System.out.println("=== b) Von der unsicheren Methode erzeugte SQL-Anweisungen ===");
        System.out.println("normal   : " + baueSqlUnsicher("s.yilmaz@mail.example", "123456"));
        System.out.println("Apostroph: " + baueSqlUnsicher(apostroph, "Lauf2026"));
        System.out.println("-> Der Apostroph beendet die Zeichenkette vorzeitig; der Rest wird als SQL gelesen.");

        System.out.println();
        System.out.println("=== d) HTML-Ausgabe eines Kommentars ===");
        String kommentar = "Super Kurs <3 & tolle Musik";
        System.out.println("unverändert : <li>" + kommentar + "</li>");
        System.out.println("kodiert     : <li>" + htmlEscape(kommentar) + "</li>");

        System.out.println();
        System.out.println("=== Test gegen eine Datenbank (H2 im Arbeitsspeicher) ===");
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Kein H2-Treiber im Klassenpfad - Datenbanktest übersprungen.");
            System.out.println("Start mit Test: java -cp h2-2.2.220.jar Aufgabe_SEC_06_3.java");
            return;
        }
        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:pulsar;MODE=MySQL")) {
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE mitglied (mitglied_nr INT PRIMARY KEY, "
                         + "email VARCHAR(100), passwort_md5 CHAR(32))");
                st.execute("INSERT INTO mitglied VALUES "
                         + "(1, 'admin@pulsar-fitness.example', '5491f5505c21cf4d6af2befd5a69fb34'),"
                         + "(10231, 'm.berger@mail.example', '10b43971a8295f3720f38fbcdd9d6ac6'),"
                         + "(10232, 's.yilmaz@mail.example', 'e10adc3949ba59abbe56e057f20f883e'),"
                         + "(10233, 'k.hansen@mail.example', '10b43971a8295f3720f38fbcdd9d6ac6'),"
                         + "(10234, 't.wolff@mail.example', '37880b01d2a4f6376fa3d292a7352c54')");
            }
            // neues Mitglied mit Apostroph in der Adresse - sicher eingefügt per PreparedStatement
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO mitglied VALUES (?, ?, ?)")) {
                ps.setInt(1, 10235);
                ps.setString(2, apostroph);
                ps.setString(3, md5("Lauf2026"));
                ps.executeUpdate();
            }
            String[][] faelle = {
                {"s.yilmaz@mail.example", "123456", "richtiges Passwort"},
                {"s.yilmaz@mail.example", "falsch", "falsches Passwort"},
                {apostroph, "Lauf2026", "Adresse mit Apostroph"}
            };
            System.out.printf("%-24s %-15s %-15s%n", "Fall", "loginUnsicher", "loginSicher");
            for (String[] f : faelle) {
                System.out.printf("%-24s %-15s %-15s%n", f[2],
                        loginUnsicher(con, f[0], f[1]), loginSicher(con, f[0], f[1]));
            }
        }
    }
}
