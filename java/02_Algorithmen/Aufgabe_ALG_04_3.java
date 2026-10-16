import java.util.Locale;

/**
 * Block 02 Algorithmen & Programmierung - Lösung zu Aufgabe ALG-04.3
 * Speicherbedarf Schadensfotos (MiB, TiB, Festplatten in TB)
 *
 * Kompilieren und starten (JDK 11+):  java Aufgabe_ALG_04_3.java
 * oder:  javac Aufgabe_ALG_04_3.java  und danach  java Aufgabe_ALG_04_3
 */
public class Aufgabe_ALG_04_3 {

    public static void main(String[] args) {
        long pixel = 4000L * 3000L;
        long bitProBild = pixel * 24;
        long byteProBild = bitProBild / 8;
        System.out.println("a) Pixel pro Bild   = " + pixel);
        System.out.println("   Bit pro Bild     = " + bitProBild);
        System.out.println("   Byte pro Bild    = " + byteProBild);
        System.out.printf(Locale.GERMANY, "   MiB pro Bild     = %.4f%n", byteProBild / 1024.0 / 1024.0);
        long bilderProJahr = 2L * 2400 * 365;
        long bytesProJahr = byteProBild * bilderProJahr;
        double tib = bytesProJahr / Math.pow(1024, 4);
        System.out.println("   Bilder pro Jahr  = " + bilderProJahr);
        System.out.println("   Byte pro Jahr    = " + bytesProJahr);
        System.out.printf(Locale.GERMANY, "   TiB pro Jahr     = %.4f%n", tib);

        double komprimiert2Jahre = bytesProJahr * 2 / 15.0;
        System.out.printf(Locale.GERMANY, "b) JPEG 1:15, 2 Jahre = %.0f Byte = %.4f TB = %.4f TiB%n",
                komprimiert2Jahre, komprimiert2Jahre / 1e12, komprimiert2Jahre / Math.pow(1024, 4));
        System.out.printf(Locale.GERMANY, "   8 TB = %.4f TiB%n", 8e12 / Math.pow(1024, 4));
        System.out.println("   benötigte Platten = " + (int) Math.ceil(komprimiert2Jahre / 8e12));
        System.out.printf(Locale.GERMANY, "   (Falsch gerechnet mit TiB-Wert gegen 8: %.2f / 8 -> %d Platte)%n",
                komprimiert2Jahre / Math.pow(1024, 4), (int) Math.ceil(komprimiert2Jahre / Math.pow(1024, 4) / 8));
    }
}
