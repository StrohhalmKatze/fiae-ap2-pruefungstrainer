# Prüfungstrainer FIAE AP2

[![Online](https://img.shields.io/badge/online-GitHub%20Pages-2ea44f?logo=github)](https://strohhalmkatze.github.io/fiae-ap2-pruefungstrainer/)
[![Prüfungsgrundlage](https://img.shields.io/badge/Grundlage-9%20AP2--Pr%C3%BCfungen-blue)](Themenanalyse.md)
[![Sprache](https://img.shields.io/badge/Sprache-Deutsch-lightgrey)](#)
[![Lizenz: MIT](https://img.shields.io/badge/Lizenz-MIT-yellow)](LICENSE)

Lern- und Übungsmaterial für die **Abschlussprüfung Teil 2 (AP2)** der Ausbildung **Fachinformatiker/in für Anwendungsentwicklung** (Ausbildungsordnung 2020). Abgedeckt sind die beiden schriftlichen Prüfungsbereiche *Planen eines Softwareproduktes* und *Entwicklung und Umsetzung von Algorithmen*. Wirtschafts- und Sozialkunde ist nicht enthalten.

**[Prüfungstrainer im Browser öffnen →](https://strohhalmkatze.github.io/fiae-ap2-pruefungstrainer/)**

Keine Installation, kein Konto. Läuft am PC, am Tablet und am Smartphone, auf Wunsch auch offline.

---

## Inhaltsverzeichnis

- [Überblick](#überblick)
- [Themenblöcke](#themenblöcke)
- [Aufbau eines Blocks](#aufbau-eines-blocks)
- [Prüfungsrelevanz-Etiketten](#prüfungsrelevanz-etiketten)
- [Funktionen](#funktionen)
- [Nutzung](#nutzung)
- [Datengrundlage und Methodik](#datengrundlage-und-methodik)
- [Datenschutz](#datenschutz)
- [Projektstruktur](#projektstruktur)
- [Hinweise und Haftungsausschluss](#hinweise-und-haftungsausschluss)
- [Feedback](#feedback)
- [Lizenz](#lizenz)

---

## Überblick

| | |
|---|---|
| **Themenblöcke** | 12, unterteilt in 80 Unterthemen |
| **Aufgaben** | 237 mit zusammen 2.634 Punkten, jeweils mit Musterlösung |
| **Grundlage** | Auswertung von 9 AP2-Prüfungen, Winter 2021/22 bis Sommer 2026 |
| **Programmierung** | Algorithmen in Pseudocode (IHK-Stil) und lauffähigem Java |
| **Datenbanken** | SQL-Beispieldatenbank für MySQL/MariaDB und SQLite |
| **Ausgabe** | Interaktiv im Browser oder als A4-Ausdruck |

## Themenblöcke

Die Blöcke sind danach sortiert, wie stark ihr Inhalt in den ausgewerteten Prüfungen gefragt war. *Ø Punkte* gibt an, wie viele der 200 Punkte (Teil 1 + Teil 2) im Mittel auf den Block entfielen.

| # | Block | Prüfungsrelevanz | Ø Punkte | Aufgaben | Punkte |
|---:|---|---|---:|---:|---:|
| 01 | Datenbanken | Prüfungskern · 9/9 | 54 | 36 | 345 |
| 02 | Algorithmen & Programmierung | Prüfungskern · 9/9 | 41 | 27 | 277 |
| 03 | UML-Modellierung | Prüfungskern · 9/9 | 37 | 21 | 252 |
| 04 | Projektmanagement | Prüfungskern · 8/9 | 21 | 30 | 332 |
| 05 | Testen & Qualitätssicherung | Prüfungskern · 8/9 | 18 | 21 | 254 |
| 06 | Schnittstellen & Datenformate | Prüfungskern · 8/9 | 13 | 15 | 174 |
| 07 | IT-Sicherheit & Datenschutz | Prüfungskern · 8/9 | 10 | 18 | 202 |
| 08 | Objektorientierung & Entwurfsmuster | Prüfungskern · 6/9 | 12 | 15 | 178 |
| 09 | Benutzeroberfläche | Prüfungskern · 5/9 | 7 | 9 | 105 |
| 10 | Netzwerk, IoT & Infrastruktur | Prüfungskern · 4/9 | 6 | 12 | 147 |
| 11 | Entwicklungsumgebung & Werkzeuge | Kam vor · 2/9 | 5 | 18 | 198 |
| 12 | Querschnitt (Englisch, Tabellenkalkulation, Ticketsystem, KI, Green IT) | je Unterthema | 8 | 15 | 170 |

## Aufbau eines Blocks

Jeder Themenblock besteht aus zwei Dateien und vier Teilen:

1. **Erklärungssatz.** Ein Lernskript mit den Grundlagen, typischen Fallen und dem Bezug auf echte Prüfungsaufgaben („So wird es in der Prüfung gefragt“).
2. **Belegsatz.** Notationshilfen und Anlagen wie in der echten Prüfung, z. B. Pseudocode-Konventionen, UML-Notation und SQL-Syntax.
3. **Aufgaben.** Drei Aufgaben je Unterthema in steigender Schwierigkeit: ★ Grundlagen, ★★ Anwendung, ★★★ Prüfungsniveau.
4. **Lösungssatz** (eigene Datei). Musterlösungen mit Bewertungshinweisen. Algorithmen stehen in Pseudocode **und** in Java, SQL-Lösungen beziehen sich auf die Beispieldatenbank.

## Prüfungsrelevanz-Etiketten

Jedes Unterthema und jeder Abschnitt im Erklärungssatz trägt ein Etikett. Es zeigt, in wie vielen der 9 Prüfungen der Inhalt direkt gebraucht wurde, also ausdrücklich gefragt war oder für die Lösung nötig war.

| Etikett | Bedeutung |
|---|---|
| **Prüfungskern** – z. B. `8/9 – alle außer S24` | in mindestens 3 Prüfungen gefragt |
| **Kam vor** – z. B. `1/9 – S26` | in 1 oder 2 Prüfungen gefragt |
| **Hintergrund** – `0/9` | bisher nicht gefragt, hilft aber beim Verständnis |

Gezählt werden nur eindeutig belegte Prüfungen. Grenzfälle und die genauen Fundstellen (Teil, Aufgabe, Punkte) stehen im Tooltip des Etiketts.

## Funktionen

| Funktion | Beschreibung |
|---|---|
| **Antworten eintippen** | Antwortfelder und leere Tabellenzellen lassen sich direkt ausfüllen. Für Code gibt es eine Monospace-Ansicht. |
| **Skizzen einfügen** | In Zeichenflächen lassen sich Bilder einfügen, z. B. ein Foto der eigenen Skizze (Strg+V, Drag & Drop oder Dateiauswahl). |
| **Automatisches Speichern** | Eingaben werden laufend im Browser gespeichert. |
| **Export und Import** | Alle Antworten lassen sich als JSON-Datei sichern und auf einem anderen Gerät wiederherstellen. |
| **Darkmode** | Umschaltbar über den Knopf oben rechts, sonst nach Systemeinstellung. |
| **Druckansicht** | Sauberes A4-Layout, eigene Antworten erscheinen im Ausdruck. |

## Nutzung

### Online

Einfach die [Startseite](https://strohhalmkatze.github.io/fiae-ap2-pruefungstrainer/) öffnen und einen Block wählen.

### Offline

1. Repository über **Code → Download ZIP** herunterladen.
2. ZIP entpacken.
3. `index.html` im Browser öffnen.

Entwickelt und getestet mit Google Chrome. Microsoft Edge sollte identisch funktionieren, andere Browser sind ungetestet.

### Java-Lösungen

Die 58 Java-Lösungen liegen nach Block sortiert in [`java/`](java/). Sie benötigen JDK 11 oder neuer und lassen sich ohne vorheriges Kompilieren starten:

```bash
java java/02_Algorithmen/Aufgabe_ALG_01_3.java
```

### SQL-Beispieldatenbank

Die Datenbank für Block 01 liegt in [`sql/01_Datenbanken.sql`](sql/01_Datenbanken.sql) und ist mit MySQL 8, MariaDB und SQLite 3 kompatibel:

```bash
sqlite3 velomanager.db < sql/01_Datenbanken.sql
```

## Datengrundlage und Methodik

Ausgewertet wurden Teil 1 und Teil 2 von 9 AP2-Prüfungen:

| Kürzel | Prüfung | Kürzel | Prüfung |
|---|---|---|---|
| W21 | Winter 2021/22 | W23 | Winter 2023/24 |
| S22 | Sommer 2022 | S24 | Sommer 2024 |
| W22 | Winter 2022/23 | W24 | Winter 2024/25 |
| S23 | Sommer 2023 | W25 | Winter 2025/26 |
| | | S26 | Sommer 2026 |

Die Prüfung Sommer 2025 ist nicht enthalten.

Jede Teilaufgabe wurde einzeln mit Thema, Punkten und verlangter Leistung erfasst und, soweit vorhanden, mit den Lösungshinweisen abgeglichen. Ein Thema zählt für eine Prüfung nur, wenn es dort ausdrücklich gefragt war oder die Aufgabe ohne dieses Wissen nicht lösbar ist.

Die Analyseergebnisse von Claude stehen in **[Themenanalyse.md](Themenanalyse.md)**: welche Themen wie oft gefragt wurden, mit wie vielen Punkten, und was sich daraus für kommende Prüfungen ableiten lässt.

## Datenschutz

- Der Trainer lädt keine externen Skripte, Schriftarten oder Tracker.
- Alle Eingaben bleiben auf dem eigenen Gerät: Texte im `localStorage`, Bilder in der `IndexedDB` des Browsers. Es wird nichts an einen Server übertragen.
- Das Hosting übernimmt GitHub Pages. Dafür gelten die Datenschutzbestimmungen von GitHub.

> **Hinweis:** Wer die Browserdaten löscht, verliert auch die gespeicherten Antworten. Deshalb regelmäßig über **Exportieren** sichern.

## Projektstruktur

```text
├── index.html                   Startseite mit Übersicht aller Blöcke
├── NN_<Block>.html              Erklärungssatz, Belegsatz und Aufgaben (12 Dateien)
├── NN_<Block>_Loesungen.html    Lösungssatz (12 Dateien)
├── style.css                    Layout für Bildschirm und A4-Druck, Darkmode
├── uebungen.js                  Antwortfelder, Speichern, Export/Import, Darkmode
├── java/                        Java-Lösungen, nach Block sortiert (58 Dateien)
├── sql/                         Beispieldatenbank für Block 01
├── Themenanalyse.md             Auswertung der 9 Prüfungen
└── LICENSE
```

## Hinweise und Haftungsausschluss

- **Kein offizielles Material.** Dieses Projekt steht in keiner Verbindung zur IHK oder zur ZPA.
- **Mit KI erstellt.** Die Inhalte wurden vollständig mit KI (Claude) erstellt. Alle Angaben erfolgen ohne Gewähr. Rechtliche Angaben, z. B. zu eRechnung, Barrierefreiheit oder dem AI Act, sind nicht juristisch geprüft.
- **Keine Garantie.** Die Häufigkeiten beruhen auf 9 Prüfungen. Sie zeigen eine Tendenz, aber keine Garantie dafür, was in einer kommenden Prüfung gefragt wird.
- **Keine Originalprüfungen.** Die Aufgaben sind neu formuliert und an die Originale nur angelehnt. Die originalen IHK-Prüfungen sind urheberrechtlich geschützt und deshalb nicht enthalten. Verweise wie „S23 T1 A3“ beziehen sich auf diese Originale.

## Feedback

Fehler gefunden oder einen Verbesserungsvorschlag? Bitte ein [Issue](https://github.com/StrohhalmKatze/fiae-ap2-pruefungstrainer/issues) eröffnen.

## Lizenz

Veröffentlicht unter der [MIT-Lizenz](LICENSE).
