import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Block 07 IT-Sicherheit & Datenschutz - Lösung zu Aufgabe SEC-04.2
 *
 *  a) Warum ungesalzenes MD5 unsicher ist: Wörterbuchangriff auf den Tabellenauszug aus Anlage 8
 *  c) pruefePasswort: Passwort mit Pepper, Salt und 10 000 Hash-Durchläufen prüfen
 *  Zusatz: dasselbe Prinzip mit der fertigen Bibliotheksfunktion PBKDF2 (so macht man es in der Praxis)
 *
 * Start (JDK 11+):  java Aufgabe_SEC_04_2.java
 */
public class Aufgabe_SEC_04_2 {

    // In der Praxis NICHT im Quellcode und NICHT in der Datenbank, sondern z. B. in einer
    // geschützten Konfiguration oder einem Hardware-Sicherheitsmodul (siehe Anlage 10).
    static final String PEPPER = "Pulsar-Pepper-aus-der-Konfiguration";
    static final int DURCHLAEUFE = 10000;

    // ---------------------------------------------------------------- Hilfsfunktionen
    static String sha256(String text) {
        return hashHex("SHA-256", text);
    }

    static String md5(String text) {
        return hashHex("MD5", text);
    }

    private static String hashHex(String verfahren, String text) {
        try {
            MessageDigest md = MessageDigest.getInstance(verfahren);
            return HexFormat.of().formatHex(md.digest(text.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /** 16 zufällige Byte als Hex-String (32 Zeichen) - für jeden Benutzer neu. */
    static String zufallsSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return HexFormat.of().formatHex(salt);
    }

    // ---------------------------------------------------------------- Registrierung (gegeben)
    /** h := sha256(PEPPER + salt + passwort), danach 9 999-mal h := sha256(h + salt). */
    static String berechneHash(String passwort, String salt) {
        String h = sha256(PEPPER + salt + passwort);
        for (int i = 1; i <= DURCHLAEUFE - 1; i++) {
            h = sha256(h + salt);
        }
        return h;
    }

    // ---------------------------------------------------------------- Lösung c)
    static boolean pruefePasswort(String eingabe, String salt, String gespeicherterHash) {
        String h = sha256(PEPPER + salt + eingabe);          // 1. Durchlauf
        for (int i = 1; i <= 9999; i++) {                      // Durchläufe 2 bis 10 000
            h = sha256(h + salt);
        }
        // Vergleich: h.equals(gespeicherterHash) ist fachlich richtig; MessageDigest.isEqual
        // vergleicht zusätzlich in konstanter Zeit (verrät über die Laufzeit nichts).
        return MessageDigest.isEqual(h.getBytes(StandardCharsets.UTF_8),
                                     gespeicherterHash.getBytes(StandardCharsets.UTF_8));
    }

    // ---------------------------------------------------------------- Zusatz: PBKDF2 aus dem JDK
    static String pbkdf2(String passwort, String saltHex) {
        try {
            PBEKeySpec spec = new PBEKeySpec((PEPPER + passwort).toCharArray(),
                    HexFormat.of().parseHex(saltHex), 310000, 256);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return HexFormat.of().formatHex(f.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new IllegalStateException(e);
        }
    }

    // ---------------------------------------------------------------- Testdaten und Ausgabe
    public static void main(String[] args) {
        System.out.println("=== a) Tabelle mitglied des Altsystems (Anlage 8) ===");
        String[][] tabelle = {
            {"admin@pulsar-fitness.example", "5491f5505c21cf4d6af2befd5a69fb34"},
            {"m.berger@mail.example",        "10b43971a8295f3720f38fbcdd9d6ac6"},
            {"s.yilmaz@mail.example",        "e10adc3949ba59abbe56e057f20f883e"},
            {"k.hansen@mail.example",        "10b43971a8295f3720f38fbcdd9d6ac6"},
            {"t.wolff@mail.example",         "37880b01d2a4f6376fa3d292a7352c54"}
        };
        // Mini-Wörterbuch mit häufigen Passwörtern (echte Listen haben Millionen Einträge)
        String[] woerterbuch = {"passwort", "123456", "hallo123", "qwertz", "fitness",
                                "Sommer2025!", "Pulsar2026!", "letmein"};
        for (String[] zeile : tabelle) {
            String gefunden = null;
            for (String wort : woerterbuch) {
                if (md5(wort).equals(zeile[1])) {
                    gefunden = wort;
                }
            }
            System.out.printf("%-30s %s  ->  %s%n", zeile[0], zeile[1],
                    gefunden == null ? "nicht im Wörterbuch" : "Passwort: " + gefunden);
        }
        System.out.println("Gleiche Hashwerte bei m.berger und k.hansen -> gleiches Passwort erkennbar.");
        // Geschwindigkeit eines Rateversuchs gegen MD5 (nach kurzem Aufwärmen der JVM)
        for (int i = 0; i < 50000; i++) { md5("warmup" + i); }
        int anzahl = 300000;
        long start = System.nanoTime();
        for (int i = 0; i < anzahl; i++) { md5("versuch" + i); }
        double proSekunde = anzahl / ((System.nanoTime() - start) / 1e9);
        System.out.printf("MD5: ca. %.1f Mio. Rateversuche pro Sekunde auf EINEM Rechenkern%n"
                + "(Grafikkarten schaffen Milliarden; Wert schwankt je nach Rechner).%n", proSekunde / 1e6);

        System.out.println();
        System.out.println("=== c) Neue Speicherung: Pepper + Salt + 10 000 Durchläufe ===");
        // feste Beispiel-Salts, damit die Ausgabe nachvollziehbar bleibt; im Betrieb: zufallsSalt()
        String saltBerger = "3f9a1c0be7d24a6f8b51c2e9d07a4b13";
        String saltHansen = "c84e2b7190fa4d3e9a6b5f01d2c8e7a4";
        String hashBerger = berechneHash("hallo123", saltBerger);
        String hashHansen = berechneHash("hallo123", saltHansen);
        System.out.println("m.berger  salt=" + saltBerger + "  hash=" + hashBerger);
        System.out.println("k.hansen  salt=" + saltHansen + "  hash=" + hashHansen);
        System.out.println("Gleiches Passwort, verschiedene Hashwerte: " + !hashBerger.equals(hashHansen));
        System.out.println("zufallsSalt() liefert " + zufallsSalt().length() + " Hex-Zeichen (16 Byte).");

        System.out.println("pruefePasswort(\"hallo123\", saltBerger, hashBerger) = "
                + pruefePasswort("hallo123", saltBerger, hashBerger));
        System.out.println("pruefePasswort(\"Hallo123\", saltBerger, hashBerger) = "
                + pruefePasswort("Hallo123", saltBerger, hashBerger));
        System.out.println("pruefePasswort(\"hallo123\", saltHansen, hashBerger) = "
                + pruefePasswort("hallo123", saltHansen, hashBerger) + "  (falsches Salt)");

        start = System.nanoTime();
        int n = 20;
        for (int i = 0; i < n; i++) {
            pruefePasswort("rateversuch" + i, saltBerger, hashBerger);
        }
        double msJePruefung = (System.nanoTime() - start) / 1e6 / n;
        System.out.printf("Eine Prüfung mit 10 000 Durchläufen dauert ca. %.1f ms -> für den Nutzer egal,%n"
                + "ein Angreifer schafft pro Rechenkern aber nur noch ca. %.0f Versuche pro Sekunde.%n",
                msJePruefung, 1000 / msJePruefung);

        System.out.println();
        System.out.println("=== Zusatz: PBKDF2WithHmacSHA256 (310 000 Iterationen) aus dem JDK ===");
        String p1 = pbkdf2("hallo123", saltBerger);
        System.out.println("PBKDF2(hallo123, saltBerger) = " + p1);
        System.out.println("erneute Berechnung stimmt überein: " + p1.equals(pbkdf2("hallo123", saltBerger)));
    }
}
