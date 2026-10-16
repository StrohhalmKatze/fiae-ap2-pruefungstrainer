import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Block 11 Entwicklungsumgebung &amp; Werkzeuge - Lösung zu Aufgabe WZ-05.3
 * Dokumentierte Erweiterung der (fiktiven) Bibliothek TerminKern.
 *
 * <p>Damit die Datei ohne die Bibliothek lauffähig ist, sind {@code Kalender},
 * {@code Bundesland}, {@code Arbeitszeitmodell}, {@code Monteur} und
 * {@code Einsatzplan} unten als stark vereinfachte Platzhalter enthalten.
 * Im echten Projekt kämen {@code Kalender} und {@code Bundesland} aus TerminKern.</p>
 *
 * <p>Kompilieren und starten (JDK 11+):  {@code java Aufgabe_WZ_05_3.java}<br>
 * HTML-Dokumentation erzeugen:  {@code javadoc -d doku -package Aufgabe_WZ_05_3.java}</p>
 */
public class Aufgabe_WZ_05_3 {

    public static void main(String[] args) {
        Arbeitszeitmodell modell = new Arbeitszeitmodell(Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
        EinsatzKalender kalender = new EinsatzKalender(Bundesland.HESSEN, modell);
        Monteur m = new Monteur("Jan Kowalski");

        // Plan: Freitag, 02.10.2026, ist komplett belegt; Samstag/Sonntag frei,
        // aber keine Arbeitstage; Montag, 05.10.2026, ist frei.
        m.getEinsatzplan().belegeGanzenTag(LocalDate.of(2026, 10, 2));

        LocalDate ab = LocalDate.of(2026, 10, 2);             // Freitag
        Duration dauer = Duration.ofMinutes(90);

        System.out.println("Normaler Auftrag ab Fr 02.10.2026: "
                + kalender.naechsterFreierTermin(m, ab, dauer, false));
        System.out.println("Notdienst ab Fr 02.10.2026:        "
                + kalender.naechsterFreierTermin(m, ab, dauer, true));
        // 03.10. = Tag der Deutschen Einheit (Feiertag, zugleich Samstag)
        System.out.println("Ist 03.10.2026 Feiertag? " + kalender.istFeiertag(LocalDate.of(2026, 10, 3)));

        try {
            kalender.naechsterFreierTermin(m, ab, Duration.ZERO, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Dauer 0 -> IllegalArgumentException: " + e.getMessage());
        }
    }
}

/* ======================================================================
 * Lösung b): dokumentierte Erweiterung
 * ====================================================================== */

/**
 * Einsatzkalender für MonteurPlus: erweitert den {@link Kalender} der Bibliothek
 * TerminKern um die Suche nach freien Einsatzterminen von Monteuren.
 *
 * <p>Berücksichtigt die Feiertage des Bundeslands (aus der Basisklasse) und
 * die Arbeitstage laut {@link Arbeitszeitmodell}. Getestet mit TerminKern 2.4.1;
 * ab TerminKern 3.0.0 ist eine Anpassung nötig (Paket umbenannt).</p>
 *
 * <p>Beispiel:</p>
 * <pre>{@code
 * EinsatzKalender k = new EinsatzKalender(Bundesland.HESSEN, modell);
 * Optional<LocalDateTime> termin =
 *         k.naechsterFreierTermin(monteur, LocalDate.now(), Duration.ofMinutes(90), false);
 * }</pre>
 *
 * @since 1.0
 */
class EinsatzKalender extends Kalender {

    /** Anzahl der Tage, die höchstens durchsucht werden (einschließlich Starttag). */
    static final int MAX_TAGE = 30;

    private final Arbeitszeitmodell modell;

    /**
     * Erzeugt einen Einsatzkalender.
     *
     * @param land   Bundesland, dessen gesetzliche Feiertage gelten; nicht {@code null}
     * @param modell Arbeitszeitmodell, das festlegt, an welchen Tagen gearbeitet wird;
     *               nicht {@code null}
     */
    EinsatzKalender(Bundesland land, Arbeitszeitmodell modell) {
        super(land);
        this.modell = modell;
    }

    /**
     * Sucht den frühesten freien Einsatztermin eines Monteurs ab einem Starttag.
     *
     * <p>Geprüft werden höchstens 30 aufeinanderfolgende Tage, beginnend mit
     * {@code ab}. Für normale Aufträge werden nur Arbeitstage laut
     * Arbeitszeitmodell berücksichtigt, die im Bundesland des Kalenders keine
     * Feiertage sind. Bei Notdienst-Aufträgen ({@code notdienst = true}) wird jeder
     * Tag geprüft, also auch Wochenenden und Feiertage. Innerhalb eines Tages
     * liefert der Einsatzplan des Monteurs den frühesten freien Zeitraum der
     * benötigten Dauer.</p>
     *
     * @param m         Monteur, für den ein Termin gesucht wird; nicht {@code null}
     * @param ab        erster Tag der Suche (wird mitgeprüft); nicht {@code null}
     * @param dauer     benötigte Einsatzdauer; muss positiv sein (größer als 0)
     * @param notdienst {@code true}, wenn auch Wochenenden, arbeitsfreie Tage und
     *                  Feiertage berücksichtigt werden sollen (Notdienst-Auftrag)
     * @return Beginn (Datum und Uhrzeit) des frühesten freien Termins oder ein leeres
     *         {@link Optional}, wenn innerhalb von 30 Tagen kein passender Termin frei ist
     * @throws IllegalArgumentException wenn {@code dauer} 0 Minuten lang oder negativ ist
     * @throws NullPointerException     wenn {@code m}, {@code ab} oder {@code dauer}
     *                                  {@code null} ist
     * @since 1.0
     * @see Kalender#istFeiertag(LocalDate)
     */
    public Optional<LocalDateTime> naechsterFreierTermin(
            Monteur m, LocalDate ab, Duration dauer, boolean notdienst) {
        if (dauer.isNegative() || dauer.isZero()) {
            throw new IllegalArgumentException("dauer muss positiv sein: " + dauer);
        }
        LocalDate tag = ab;
        for (int i = 0; i < MAX_TAGE; i++) {
            if (notdienst || (!istFeiertag(tag) && modell.istArbeitstag(tag))) {
                Optional<LocalDateTime> slot =
                        m.getEinsatzplan().freierSlot(tag, dauer);
                if (slot.isPresent()) {
                    return slot;
                }
            }
            tag = tag.plusDays(1);
        }
        return Optional.empty();
    }
}

/* ======================================================================
 * Platzhalter für die Bibliothek TerminKern und die MonteurPlus-Klassen
 * (stark vereinfacht, nur damit das Beispiel läuft)
 * ====================================================================== */

/** Platzhalter für das Bundesland aus TerminKern. */
enum Bundesland { HESSEN }

/** Platzhalter für die Klasse Kalender aus TerminKern (nur feste Feiertage). */
class Kalender {
    private final Bundesland land;
    private static final Set<MonthDay> FESTE_FEIERTAGE = Set.of(
            MonthDay.of(1, 1), MonthDay.of(5, 1), MonthDay.of(10, 3),
            MonthDay.of(12, 25), MonthDay.of(12, 26));

    /**
     * Erzeugt einen Kalender für ein Bundesland.
     *
     * @param land Bundesland
     */
    Kalender(Bundesland land) {
        this.land = land;
    }

    /**
     * Prüft, ob ein Tag ein gesetzlicher Feiertag ist (Platzhalter: nur feste Feiertage).
     *
     * @param tag zu prüfender Tag
     * @return {@code true}, wenn der Tag ein Feiertag ist
     */
    public boolean istFeiertag(LocalDate tag) {
        return FESTE_FEIERTAGE.contains(MonthDay.from(tag));
    }
}

/** Arbeitszeitmodell: legt fest, an welchen Wochentagen nicht gearbeitet wird. */
class Arbeitszeitmodell {
    private final Set<DayOfWeek> freieTage;

    Arbeitszeitmodell(Set<DayOfWeek> freieTage) {
        this.freieTage = freieTage;
    }

    boolean istArbeitstag(LocalDate tag) {
        return !freieTage.contains(tag.getDayOfWeek());
    }
}

/** Monteur mit Einsatzplan. */
class Monteur {
    private final String name;
    private final Einsatzplan einsatzplan = new Einsatzplan();

    Monteur(String name) {
        this.name = name;
    }

    Einsatzplan getEinsatzplan() {
        return einsatzplan;
    }

    @Override
    public String toString() {
        return name;
    }
}

/** Einsatzplan: vereinfachte Belegung, Arbeitsbeginn immer 7:30 Uhr. */
class Einsatzplan {
    private final Map<LocalDate, Boolean> ganzerTagBelegt = new HashMap<>();

    void belegeGanzenTag(LocalDate tag) {
        ganzerTagBelegt.put(tag, true);
    }

    Optional<LocalDateTime> freierSlot(LocalDate tag, Duration dauer) {
        if (ganzerTagBelegt.getOrDefault(tag, false)) {
            return Optional.empty();
        }
        return Optional.of(LocalDateTime.of(tag, LocalTime.of(7, 30)));
    }
}
