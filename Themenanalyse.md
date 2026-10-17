# Themenanalyse AP2 Fachinformatiker Anwendungsentwicklung

Grundlage: **9 Prüfungen** nach AO 2020, jeweils **Teil 1** (Planen eines Softwareproduktes) und **Teil 2** (Entwicklung und Umsetzung von Algorithmen). Teil 3 (WiSo) ist nicht berücksichtigt.

| Kürzel | Prüfung | Szenario |
|---|---|---|
| W21 | Winter 2021/22 | Online-Terminbuchung Arztpraxis |
| S22 | Sommer 2022 | Energieversorger: Stromableser-App, Smart Meter, Ticketsystem |
| W22 | Winter 2022/23 | Bildungsträger: Raum- und Kursbuchung (Webanwendung) |
| S23 | Sommer 2023 | Parkanlage „Wilder Garten“: Besucher-App, Sensoren |
| W23 | Winter 2023/24 | Weinbau: Messgerät, Drohnen, Cloud-DB |
| S24 | Sommer 2024 | E-Service-Plattform für Aktionärsversammlungen |
| W24 | Winter 2024/25 | Nahverkehr Kiel: Fahrplan-App, Netzwerk |
| W25 | Winter 2025/26 | Gastronomie: Reservierung, eRechnung, Bewertungen |
| S26 | Sommer 2026 | Landwirtschaft: Smart Farming, Sensoren, Tierbestand |

**Fehlt:** Sommer 2025. W21 war die erste Prüfung im aktuellen Format, ältere Prüfungen haben eine andere Struktur.

---

## So wurde ausgewertet

- **Jede Teilaufgabe einzeln.** Für jede der 9 Prüfungen wurden alle Teilaufgaben aus Teil 1 und Teil 2 mit Thema, Punkten und verlangter Leistung erfasst und, wo vorhanden, mit den Lösungen abgeglichen. Jede Teilaufgabe ist mindestens einem Unterthema zugeordnet, alle 200 Punkte jeder Prüfung sind also erfasst.
- **Was zählt.** Ein Unterthema zählt für eine Prüfung nur, wenn es dort ausdrücklich gefragt war oder die Aufgabe ohne dieses Wissen nicht lösbar ist. Gezählt werden nur eindeutige Belege, Grenzfälle nicht.
- **Voraussetzungen zählen nicht doppelt.** Getter aufrufen und mit Objekten arbeiten gehört zur Algorithmusaufgabe (Block 2) und zählt nicht als Objektorientierung. Eine Pseudocode-Aufgabe, die ein gegebenes Klassendiagramm nur liest, zählt nicht als Klassendiagramm-Aufgabe.
- **Gleiche Gliederung wie der Prüfungstrainer.** 12 Themenblöcke mit 80 Unterthemen. Die Kürzel (DB-01, ALG-01 …) und Etiketten stimmen mit den Übungsdateien überein.

### Etiketten

| Etikett | Bedeutung |
|---|---|
| **Prüfungskern** | in mindestens 3 von 9 Prüfungen gefragt |
| **Kam vor** | in 1 oder 2 Prüfungen gefragt |
| **Hintergrund** | bisher nicht gefragt (nur bei einzelnen Abschnitten in den Übungsdateien) |

Hinter dem Etikett stehen Anzahl und Prüfungen, neueste zuerst, z. B. „Prüfungskern – 6/9 – S26, W25, W23, S23, W22, S22“. Ab 7 Prüfungen steht „alle außer …“.

### Zeichen und Punkte

- **↑ steigend:** in mindestens 3 der letzten 4 Prüfungen (S24, W24, W25, S26), vorher seltener.
- **↓ fallend:** mehrmals vorgekommen, aber in den letzten 3 Prüfungen (W24, W25, S26) nicht mehr.
- **⚠ neu:** einmalig, aber in einer der letzten beiden Prüfungen (W25, S26).
- **Ø Pkt bei Blöcken:** Punkte im Mittel über alle 9 Prüfungen (von 200). Jede Teilaufgabe zählt pro Block einmal.
- **Ø Pkt bei Unterthemen:** Punkte im Mittel über die Prüfungen, in denen das Unterthema vorkam.

Teilaufgaben, die mehrere Unterthemen oder Blöcke betreffen, zählen bei jedem voll mit. Deshalb ergeben die Spalten der Punktetabellen mehr als 200.

---

## Das feste Gerüst der Prüfung

Der Aufbau ist erstaunlich stabil.

**Teil 2** (4 Aufgaben, meist je 25 Punkte):
- **Aufgabe 4 ist in allen 9 Prüfungen SQL** (21–30 Punkte).
- **Aufgabe 3 ist in 6 von 9 Prüfungen ein Datenmodell in 3NF** (W21, S22, W22, W23, W24, S26). In den übrigen 3 geht es um Testen (S23, S24, W25).
- **Aufgabe 1 oder 2 ist immer ein großer Algorithmus** in Pseudocode mit **20–30 Punkten**. In 5 Prüfungen war es eine einzige Aufgabe ohne Teilaufgaben (W21, S22, S23, W23, W24), in 4 Prüfungen war sie aufgeteilt (W22, S24, W25, S26).
- **Die andere der Aufgaben 1 und 2** ist meist ein UML-Diagramm (W21, S23, W23, S24, W25, S26) oder Testen (S22, W22). In W24 war es eine zweite Programmieraufgabe mit HTTP-Response.

**Teil 1** (4 Aufgaben, 20–30 Punkte):
- **Aufgabe 1 ist seit S23 durchgehend Projektmanagement** (6 Prüfungen in Folge): Stakeholder, Risiko, Vorgehensmodell, Anforderungen.
- **In jedem Teil 1 muss mindestens ein UML-Diagramm gezeichnet oder ergänzt werden** (9/9).
- **Außerdem fast immer IT-Sicherheit** (8/9), dazu wechselnd Schnittstellen und Datenformate, Benutzeroberfläche, Netzwerk oder Datenbanktheorie.

**Datenbanken sind der größte Brocken:** Datenmodell, SQL und DB-Theorie bringen im Schnitt **54 von 200 Punkten** (27 %). Zusammen mit Algorithmen und UML sind es im Schnitt **130 von 200 Punkten** (je nach Prüfung 112 bis 160).

---

## Überblick: Themenblöcke

| # | Block | Etikett | Ø Pkt | Wo |
|---:|---|---|---:|---|
| 01 | Datenbanken | Prüfungskern – 9/9 – alle | 54 | Teil 2 (9×) + Teil 1 (7×) |
| 02 | Algorithmen & Programmierung | Prüfungskern – 9/9 – alle | 41 | Teil 2 (9×) + Teil 1 (4×) |
| 03 | UML-Modellierung | Prüfungskern – 9/9 – alle | 37 | Teil 1 (9×) + Teil 2 (6×) |
| 04 | Projektmanagement | Prüfungskern – 8/9 – alle außer W21 | 21 | nur Teil 1 |
| 05 | Testen & Qualitätssicherung | Prüfungskern – 8/9 – alle außer W24 | 18 | Teil 1 (6×) + Teil 2 (6×) |
| 06 | Schnittstellen & Datenformate | Prüfungskern – 8/9 – alle außer S22 | 13 | Teil 1 (8×) + Teil 2 (1×) |
| 07 | IT-Sicherheit & Datenschutz | Prüfungskern – 8/9 – alle außer W23 | 10 | Teil 1 (8×) + Teil 2 (1×) |
| 08 | Objektorientierung & Entwurfsmuster | Prüfungskern – 6/9 – S26, W24, S24, S23, W22, S22 | 12 | Teil 1 (4×) + Teil 2 (3×) |
| 09 | Benutzeroberfläche | Prüfungskern – 5/9 – S26, S24, W23, W22, W21 | 7 | nur Teil 1 |
| 10 | Netzwerk, IoT & Infrastruktur | Prüfungskern – 4/9 – S26, W24, S23, W22 | 6 | nur Teil 1 |
| 11 | Entwicklungsumgebung & Werkzeuge | Kam vor – 2/9 – W22, W21 | 5 | nur Teil 1 |
| 12 | Querschnitt (Englisch, Tabellenkalkulation, Ticketsystem, KI, Green IT) | je Unterthema, zusammen 6/9 | 8 | Teil 1 (5×) + Teil 2 (1×) |

## Punkte pro Prüfung nach Block

| Block | W21 | S22 | W22 | S23 | W23 | S24 | W24 | W25 | S26 | Σ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| 01 Datenbanken | 66 | 59 | 55 | 41 | 82 | 40 | 50 | 49 | 45 | 9/9 |
| ↳ davon SQL | 25 | 28 | 28 | 25 | 25 | 30 | 21 | 25 | 25 | 9/9 |
| ↳ davon Datenmodell (ER, 3NF) | 41 | 20 | 22 | 16 | 32 | 10 | 16 | 24 | 20 | 9/9 |
| 02 Algorithmen & Programmierung | 35 | 34 | 47 | 47 | 32 | 35 | 58 | 42 | 39 | 9/9 |
| 03 UML-Modellierung | 42 | 26 | 25 | 44 | 46 | 39 | 40 | 43 | 28 | 9/9 |
| 04 Projektmanagement | – | 36 | 11 | 20 | 28 | 17 | 20 | 30 | 27 | 8/9 |
| 05 Testen & Qualitätssicherung | 29 | 20 | 11 | 33 | 6 | 31 | – | 29 | 5 | 8/9 |
| 06 Schnittstellen & Datenformate | 2 | – | 13 | 13 | 14 | 25 | 18 | 24 | 12 | 8/9 |
| 07 IT-Sicherheit & Datenschutz | 12 | 13 | 18 | 8 | – | 15 | 4 | 10 | 13 | 8/9 |
| 08 Objektorientierung & Entwurfsmuster | – | 30 | 15 | 22 | – | 17 | 10 | – | 16 | 6/9 |
| 09 Benutzeroberfläche | 16 | – | 7 | – | 8 | 13 | – | – | 18 | 5/9 |
| 10 Netzwerk, IoT & Infrastruktur | – | – | 8 | 4 | – | – | 26 | – | 12 | 4/9 |
| 11 Entwicklungsumgebung & Werkzeuge | 20 | – | 21 | – | – | – | – | – | – | 2/9 |
| 12 Querschnitt | – | 21 | – | 16 | 11 | 12 | – | 9 | 3 | 6/9 |

## Punkte pro Prüfung: wichtige Unterthemen

| Unterthema | W21 | S22 | W22 | S23 | W23 | S24 | W24 | W25 | S26 | Σ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Algorithmus in Pseudocode/Code | 25 | 34 | 32 | 47 | 25 | 35 | 58 | 30 | 34 | 9/9 |
| Arrays / Listen durchlaufen | 25 | 34 | 27 | 25 | 25 | 35 | 40 | 34 | 34 | 9/9 |
| Datenmodell (ER-/Relationenmodell, 3NF) | 41 | 20 | 22 | 16 | 32 | 10 | 16 | 24 | 20 | 9/9 |
| SQL gesamt | 25 | 28 | 28 | 25 | 25 | 30 | 21 | 25 | 25 | 9/9 |
| UML-Verhaltensdiagramm (Aktivität, Zustand, Sequenz) | 15 | 9 | 25 | 35 | 40 | 25 | 15 | 25 | 16 | 9/9 |
| Aktivitätsdiagramm | 15 | – | 14 | 25 | 15 | 14 | – | – | 16 | 6/9 |
| Klassendiagramm | 7 | 17 | – | 9 | – | – | 25 | – | 12 | 5/9 |
| Zustandsdiagramm | – | – | 11 | 10 | – | – | 15 | 25 | – | 4/9 |
| Use-Case-Diagramm | 14 | – | – | – | 6 | 14 | – | 18 | – | 4/9 |
| Sequenzdiagramm | – | 9 | – | – | 25 | 11 | – | – | – | 3/9 |
| JSON / XML | – | – | 13 | 13 | 14 | 3 | 8 | 6 | – | 6/9 |
| REST / HTTP | 2 | – | – | – | – | 25 | 10 | – | 12 | 4/9 |
| Englischer Fachtext | – | 9 | – | – | 1 | 12 | – | 9 | 3 | 5/9 |

---

## Die Blöcke im Detail

### 01 · Datenbanken (Prüfungskern – 9/9 · Ø 54 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| DB-01 · ER-/Relationenmodell | Prüfungskern – 9/9 – alle | 21 | Aus Beispieldaten, Formular oder Text ein **Relationenmodell mit PK/FK und Kardinalitäten** zeichnen bzw. ein gegebenes ERM erweitern. m:n-Beziehungen auflösen. Meist Teil 2, Aufgabe 3. |
| DB-02 · Normalisierung 1NF–3NF | Prüfungskern – 8/9 – alle außer S24 | 19 | Fast immer heißt es „in 3. Normalform“. Dazu Anomalien und Redundanz erklären (S23, W25). |
| DB-03 · SQL: SELECT mit JOIN | Prüfungskern – 9/9 – alle | 21 | Join über 2–4 Tabellen mit WHERE und ORDER BY. **„Auch X ohne Y anzeigen“ heißt LEFT JOIN** (S22, W22, S23, S26). Häufig Datumsfunktionen wie YEAR(), MONTH(). |
| DB-04 · SQL: Aggregatfunktionen, GROUP BY und HAVING | Prüfungskern – 8/9 – alle außer W24 | 13 | COUNT/SUM/AVG/MIN/MAX je Gruppe, dazu HAVING (W21, W25). |
| DB-05 · SQL: INSERT, UPDATE, DELETE | Prüfungskern – 7/9 – alle außer W24, S23 | 9 | **Archivieren:** `INSERT INTO archiv SELECT …`, danach `DELETE` (W22, S24, S26). UPDATE mit Join oder Bedingung. |
| DB-06 · Datenbank-Theorie: ACID, Transaktion, Integrität, Index, NoSQL | Prüfungskern – 6/9 – S26, W25, W23, S23, W22, S22 | 10 | **NoSQL vs. relational** (W23 mit 25 P), Anomalien (W25), referenzielle Integrität beim Löschen (S26), ACID, Index. |
| DB-08 · SQL: DDL (CREATE, ALTER, DROP, INDEX) | Prüfungskern – 5/9 – S26, W25, W22, S22, W21 | 4 | CREATE TABLE, ALTER TABLE ADD/DROP, CREATE INDEX, Datentypen wählen. |
| DB-07 · SQL: Unterabfragen | Kam vor – 2/9 – W23, S22 ↓ | 6 | NOT IN / NOT EXISTS (S22), korrelierte Unterabfrage (W23). In W21 und S26 war eine Unterabfrage möglich, aber nicht nötig. |
| DB-09 · SQL: UNION | Kam vor – 1/9 – S24 | 12 | UNION ALL (12 P). |
| DB-10 · SQL: Benutzer und Rechte | Kam vor – 1/9 – S23 | 4 | CREATE USER, GRANT. |
| DB-11 · Trigger, Stored Procedure, View | Kam vor – 1/9 – S22 | 4 | Stored Procedure und Trigger beschreiben. |
| DB-12 · Datenqualität beim Import von Altdaten | Kam vor – 1/9 – W24 | 13 | Probleme manuell gepflegter Altdaten und ihre Folgen beim Import, Bereinigung. |

**Steht nicht im Belegsatz, selbst lernen:** Kardinalitäten im ER-Modell und die Notation des Relationenmodells mit PK/FK. Der SQL-Syntax-Auszug liegt dagegen immer bei.

### 02 · Algorithmen & Programmierung (Prüfungskern – 9/9 · Ø 41 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| ALG-01 · Algorithmus in Pseudocode schreiben | Prüfungskern – 9/9 – alle | 36 | Methode über ein Array oder eine Liste von Objekten: **zählen mit Bedingung, Summe/Durchschnitt, filtern, Minimum/Maximum suchen**. Meist mit Klassendiagramm, Gettern, Beispieltabelle und Methodensignatur. 20–30 Punkte in Teil 2. |
| ALG-02 · Arrays und Listen durchlaufen | Prüfungskern – 9/9 – alle | 31 | Früher oft **2D-Arrays** (W21, S22, S23: Tag × Stunde, Verbraucher × Monat). Inzwischen überwiegend **Objektlisten** `List<T>` mit `add/get/size/contains` (S22, W22, S24, W25, S26). |
| ALG-03 · Minimum/Maximum, lineare Suche | Prüfungskern – 5/9 – W25, S24, W23, S22, W21 | 24 | Min/Max im 2D-Array (S22), nächster Nachbar (W23), Teilnehmer suchen (S24), bester Tisch (W25). |
| ALG-04 · Speicherbedarf berechnen / Zahlensysteme | Prüfungskern – 3/9 – S26, W23, W22 | 10 | **W23 (GiB) und S26 (TiB) fast identisch:** Speicherbedarf von Drohnenbildern mit 1024er-Präfixen. W22: Dezimal → Binär, Datentypen festlegen. |
| ALG-05 · Sortieren | Kam vor – 2/9 – S24, W22 ↓ | 17 | Sortieralgorithmus **mit übergebener Vergleichsfunktion** (Comparator). |
| ALG-06 · Zeichenketten verarbeiten | Kam vor – 2/9 – W25, W21 | 7 | Passwortprüfung (W21, Fehler im Struktogramm finden), Spalte per SUBSTRING aufteilen (W25). |
| ALG-07 · Exceptions / Fehlerbehandlung | Kam vor – 1/9 – W25 ⚠ | 10 | Fehlerfall bzw. `null` im Algorithmus behandeln. |
| ALG-08 · Rekursion | Kam vor – 1/9 – W22 | 12 | Rekursiven Algorithmus durchspielen, Fehler finden, Rekursion vs. Iteration. |
| ALG-09 · Nebenläufigkeit | Kam vor – 1/9 – W21 | 4 | Konkurrierende Zugriffe beim Blockieren von Terminen. |

**Pseudocode-Stil:** Der Belegsatz von S24 zeigt die erwarteten Konventionen (`wenn … dann … Ende wenn`, `zähle … von … bis`, `:=`). Darin üben.

### 03 · UML-Modellierung (Prüfungskern – 9/9 · Ø 37 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| UML-01 · Verhaltensdiagramme allgemein / Diagrammwahl | Prüfungskern – 9/9 – alle | 35 | In jeder Prüfung mindestens ein UML-Diagramm, **oft als ganze 25-Punkte-Aufgabe**. Den passenden Diagrammtyp zur Situation erkennen. |
| UML-02 · Aktivitätsdiagramm | Prüfungskern – 6/9 – S26, S24, W23, S23, W22, W21 | 16 | Aus Text erstellen oder ergänzen, **mit Swimlanes, Verzweigungen und Fork/Join**. Der Klassiker. |
| UML-03 · Klassendiagramm | Prüfungskern – 5/9 – S26, W24, S23, S22, W21 | 14 | Klassen mit Attributen, Datentypen und Sichtbarkeiten erstellen, Vererbung/abstrakte Klasse, **Aggregation vs. Komposition**, Multiplizitäten deuten. |
| UML-04 · Zustandsdiagramm | Prüfungskern – 4/9 – W25, W24, S23, W22 | 15 | Zustände mit Guards (Auslastung, Raumstatus, Roboter). |
| UML-05 · Use-Case-Diagramm | Prüfungskern – 4/9 – W25, S24, W23, W21 | 13 | Akteure, **include/extend**, Generalisierung. |
| UML-06 · Sequenzdiagramm | Prüfungskern – 3/9 – S24, W23, S22 ↓ | 15 | Mit alt-Fragment, Nachrichten und Rückantworten. |
| UML-07 · Struktogramm / Programmablaufplan | Kam vor – 1/9 – W21 | 6 | Fehler in einem gegebenen Struktogramm finden. Sonst war die Darstellungsform frei wählbar. |

**Steht nicht im Belegsatz, selbst lernen:**
- Wann Aggregation, wann Komposition
- Multiplizitäten wie `0..1` und `1..*`
- Abhängigkeit (gestrichelter Pfeil)
- Systemgrenze im Use-Case-Diagramm
- `loop`, `opt` und `par` im Sequenzdiagramm
- Unterzustände im Zustandsdiagramm

### 04 · Projektmanagement (Prüfungskern – 8/9 · Ø 21 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| Operatoren und Antworttechnik | Prüfungskern – 9/9 – alle | – | Keine eigene Aufgabe, aber in jeder Textaufgabe: Die Operatoren (nennen, beschreiben, erläutern, begründen) bestimmen, wie ausführlich die Antwort sein muss. |
| PM-01 · Stakeholder und Projektbegriffe | Prüfungskern – 5/9 – S26, W24, S24, W23, S22 ↑ | 8 | **Stakeholder-Analyse** (Interessen, Befürchtungen, Gegenmaßnahmen), Meilenstein, Lessons Learned, Change Request. |
| PM-02 · Agile Vorgehensweisen, Scrum, User Stories | Prüfungskern – 5/9 – W25, W24, W23, W22, S22 | 8 | Scrum-Rollen, -Artefakte und -Events, klassisch vs. agil, User Story und INVEST. |
| PM-03 · Anforderungen | Prüfungskern – 4/9 – S24, S23, W22, S22 ↓ | 6 | Funktionale und nicht-funktionale Anforderungen, je X Beispiele beschreiben. |
| PM-04 · Risikoanalyse | Prüfungskern – 3/9 – S26, W25, S23 | 7 | Risiko mit Ursache, Auswirkung und Gegenmaßnahme beschreiben bzw. in eine Tabelle eintragen. Kam in den letzten beiden Prüfungen. |
| PM-05 · Klassische Vorgehensmodelle und Prototyping | Prüfungskern – 3/9 – W25, W24, S22 | 10 | Kategorien von Vorgehensmodellen beschreiben, Wasserfall vs. agil, Prototyping. |
| PM-06 · Change-Management und Akzeptanz | Prüfungskern – 3/9 – W23, S23, S22 ↓ | 7 | Befürchtungen der Betroffenen und Gegenmaßnahmen, Workshop. Steht der Stakeholder-Analyse sehr nahe. |
| PM-07 · Kosten, Make-or-Buy, Nutzwertanalyse | Kam vor – 2/9 – S23, S22 ↓ | 4 | Klassische Kalkulation spielt praktisch keine Rolle. |
| PM-08 · Netzplan | Kam vor – 1/9 – S26 ⚠ | 19 | Vorgangsknoten mit FAZ/FEZ/SAZ/SEZ, Puffer, kritischer Pfad. |
| PM-09 · Machbarkeitsanalyse | Kam vor – 1/9 – W25 ⚠ | 8 | Kriterien einer Machbarkeitsanalyse benennen und erläutern. |
| PM-10 · Nutzen eines Systems beschreiben | Kam vor – 1/9 – S24 | 8 | Vorteile eines neuen Systems beschreiben. |

Pro Prüfung werden meist 2–4 dieser Unterthemen abgefragt, fast immer in Teil 1, Aufgabe 1.

### 05 · Testen & Qualitätssicherung (Prüfungskern – 8/9 · Ø 18 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| TQ-03 · Unit-Test / Überdeckungsmaße | Prüfungskern – 6/9 – W25, S24, S23, W22, S22, W21 | 9 | Was ist ein Unit-Test? **Anweisungs-, Zweig- und Pfadüberdeckung**, Mindestzahl der Testfälle, alle Pfade notieren. |
| TQ-02 · Code analysieren / Fehler finden | Prüfungskern – 5/9 – W25, S23, W22, S22, W21 | 9 | Fehlerhafte Funktion durchspielen und korrigieren. **Typische Fehler:** falsche Initialisierung (0 statt `werte[0]`), Off-by-one bei der Schleifengrenze, `>` statt `>=`. |
| TQ-01 · Testarten | Prüfungskern – 4/9 – W25, S24, W23, W21 | 9 | Black-Box vs. White-Box, Regressionstest, Usability-Test, Testkonzept. |
| TQ-05 · Schreibtischtest | Prüfungskern – 4/9 – W25, S23, W22, S22 | 5 | Wertetabelle bzw. Trace eines gegebenen Algorithmus ausfüllen. |
| TQ-04 · Qualitätsmerkmale ISO/IEC 25010 | Prüfungskern – 3/9 – S26, S23, W21 | 4 | Zuverlässigkeit, Effizienz, Wartbarkeit usw. beschreiben. |
| TQ-07 · Codequalität / Reviews | Prüfungskern – 3/9 – S24, S23, W21 ↓ | 7 | Qualitätssichernde Maßnahmen, Code-Reviews, Dokumentationsrichtlinien. |
| TQ-06 · Äquivalenzklassen / Testfälle | Kam vor – 2/9 – S23, S22 ↓ | 4 | Testdaten für Äquivalenzklassen und Grenzwerte angeben. |

**Testen im engeren Sinn** (Testarten, Unit-Test, Schreibtischtest, Code analysieren) fehlte in W24 und S26. S26 zählt im Block nur wegen ISO 25010.

### 06 · Schnittstellen & Datenformate (Prüfungskern – 8/9 · Ø 13 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| SD-02 · JSON | Prüfungskern – 4/9 – W24, S24, W23, S23 | 10 | Tabellendaten in JSON umwandeln (W23), JSON-Sensordaten in ein Relationenmodell überführen (S23), JSON als Ausgabeformat eines Skripts (W24). |
| SD-04 · HTTP-Request/Response & Statuscodes | Prüfungskern – 4/9 – S26, W24, S24, W21 ↑ | 8 | Bestandteile eines HTTP-Requests, Statuscodes 2XX/4XX/5XX deuten, HTTP-Response im Code erzeugen (W24), Ursache für „404“ (W21). |
| SD-01 · XML | Kam vor – 2/9 – W25, W22 | 10 | XML mit DTD bzw. XSD, „wohlgeformt vs. gültig“, Fehler im XML-Dokument finden. |
| SD-03 · REST-Grundlagen & HTTP-Methoden | Kam vor – 2/9 – S26, S24 | 14 | REST-Konzept erläutern, **CRUD den HTTP-Methoden zuordnen**, weitere HTTP-Methoden beschreiben. |
| SD-05 · eRechnung | Kam vor – 1/9 – W25 ⚠ | 24 | Englischer Text zur EU-Richtlinie (Hauptziel, Aspekte), Prüfung gegen die Spezifikation, XSD. |

Zusammengefasst kamen JSON/XML in 6 und REST/HTTP in 4 Prüfungen vor. REST/HTTP war in 3 der letzten 4 Prüfungen dabei.

### 07 · IT-Sicherheit & Datenschutz (Prüfungskern – 8/9 · Ø 10 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| SEC-01 · Verschlüsselungsverfahren | Prüfungskern – 6/9 – S26, W25, S23, W22, S22, W21 | 8 | **Hybride Verschlüsselung / TLS** (am häufigsten), symmetrisch vs. asymmetrisch, RSA, Verschlüsselung auf schwachen Sensoren. |
| SEC-02 · Hash, digitale Signatur, Zertifikate/PKI | Prüfungskern – 5/9 – S26, S23, W22, S22, W21 | 5 | Signieren statt verschlüsseln (S23), SSL-Zertifikate (S22), Warnmeldung „nicht vertrauenswürdige Quelle“ (W21). |
| SEC-03 · Schutzziele & Schutzbedarf | Prüfungskern – 5/9 – S26, W25, S24, W22, S22 ↑ | 5 | Vertraulichkeit, Integrität, Verfügbarkeit (BSI) einer Situation zuordnen. Typische Fangfrage: Verschlüsselung allein sichert keine Integrität (S26). |
| SEC-04 · Authentifizierung | Prüfungskern – 3/9 – S24, W22, S22 ↓ | 5 | OAuth2/SSO, sicheres Zurücksetzen von Passwörtern. |
| SEC-05 · Datenschutz / DSGVO | Kam vor – 2/9 – W25, W24 | 5 | Einwilligung und Datenschutzerklärung (W24), keine echten Daten auf fremden Prüfseiten (W25). |
| SEC-06 · Angriffe & Gegenmaßnahmen | Kam vor – 1/9 – S24 | 5 | Makro-Malware in Mail-Anhängen und sichere Alternative. |

### 08 · Objektorientierung & Entwurfsmuster (Prüfungskern – 6/9 · Ø 12 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| OOP-01 · Grundkonzepte: Klasse, Objekt, Kapselung | Prüfungskern – 4/9 – S26, S24, S23, S22 | 12 | Klassen mit Attributen, Methoden und Sichtbarkeiten entwerfen und in Pseudocode implementieren. |
| OOP-03 · Polymorphie, Überladen/Überschreiben, Generics | Prüfungskern – 4/9 – S26, S24, S23, W22 | 14 | **Polymorphie und dynamisches Binden** (S23, S26), Vergleichsfunktion als Parameter (W22, S24), generische Klassen (W22). |
| OOP-02 · Vererbung, abstrakte Klassen und Interfaces | Prüfungskern – 3/9 – S26, S23, S22 | 10 | Abstrakte Klasse und Vererbung im Klassendiagramm (S23, S26), Beziehungen der Klassen im Beobachter-Muster (S22). |
| OOP-04 · Entwurfsmuster: Grundlagen, Kategorien, Erzeugungsmuster | Kam vor – 2/9 – W24, S23 | 10 | Fabrikmethode (S23), Vorteile von Entwurfsmustern und Musterkategorien (W24). |
| OOP-05 · Verhaltens- und Strukturmuster | Kam vor – 2/9 – W24, S22 | 14 | **Observer** (S22 mit Klassen- und Sequenzdiagramm, W24 als Musterwahl). |

Das Klassendiagramm steht unter UML-Modellierung, wird aber oft zusammen mit diesem Block abgefragt. Getter-Aufrufe in Algorithmusaufgaben gehören zu Block 02.

### 09 · Benutzeroberfläche (Prüfungskern – 5/9 · Ø 7 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| GUI-01 · Mockup, Wireframe & GUI-Elemente | Prüfungskern – 4/9 – S26, S24, W23, W22 | 7 | **Mockup skizzieren** oder verbessern, Darstellungsform wählen und begründen, passende GUI-Elemente. |
| GUI-02 · Barrierefreiheit | Prüfungskern – 3/9 – S26, W22, W21 | 5 | Anforderungen an barrierefreie Software und passende Maßnahmen. |
| GUI-03 · Usability & Software-Ergonomie | Kam vor – 2/9 – S24, W21 ↓ | 12 | Usability und User Experience als Akzeptanzkriterien, Mängel einer Oberfläche erkennen. |

### 10 · Netzwerk, IoT & Infrastruktur (Prüfungskern – 4/9 · Ø 6 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| NET-01 · IoT / Sensorik / Funk | Prüfungskern – 3/9 – S26, W24, S23 | 8 | LoRa, LPWAN, Datenübertragung batteriebetriebener Sensoren. Die Szenarien werden zunehmend IoT-lastig. |
| NET-02 · Netzwerkkonzepte | Kam vor – 2/9 – S26, W24 | 6 | LAN, SAN und LPWAN zuordnen, LoRa. |
| NET-03 · Adressierung, Frames & Protokolle | Kam vor – 1/9 – W24 | 17 | MAC-Adresse, Ethernet-Frame, Skript für IP- und MAC-Adressen im Subnetz. |
| NET-04 · IT-Infrastruktur, Virtualisierung & Cloud | Kam vor – 1/9 – W22 | 8 | Schwachstellen einer IT-Infrastruktur, Anforderungen an die neue Infrastruktur. |

### 11 · Entwicklungsumgebung & Werkzeuge (Kam vor – 2/9 · Ø 5 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| WZ-01 · Versionsverwaltung (Git) | Kam vor – 1/9 – W21 | 4 | Einsatz einer Versionsverwaltung bewerten. |
| WZ-02 · CI/CD & Deployment | Kam vor – 1/9 – W22 | 6 | Automatisierte Kompilierung und Bereitstellung beschreiben. |
| WZ-03 · Softwarearchitektur | Kam vor – 1/9 – W22 | 6 | 3-Schichten-Architektur einer Webanwendung. |
| WZ-04 · Compiler vs. Interpreter | Kam vor – 1/9 – W21 | 6 | Unterschied, Beispiele, Vor- und Nachteile. |
| WZ-05 · Klassenbibliotheken, Frameworks & Dokumentation | Kam vor – 1/9 – W21 | 10 | Vor- und Nachteil von Klassenbibliotheken. |
| WZ-06 · Open Source & Lizenzen | Kam vor – 1/9 – W22 | 9 | Open Source erläutern, Lizenzbedingungen beim Einsatz von Bibliotheken. |

Alle Themen dieses Blocks kamen nur in W21 und W22 vor, seitdem nicht mehr.

### 12 · Querschnitt (zusammen 6/9 · Ø 8 Pkt)

| Unterthema | Etikett | Ø Pkt | Typische Aufgabe |
|---|---|---:|---|
| QU-01 · Englischer Fachtext | Prüfungskern – 5/9 – S26, W25, S24, W23, S22 ↑ | 7 | Fachtext lesen und auf Deutsch erklären. Kommt in vielen Blöcken vor (User Story, Coverage, eRechnung, LoRa). |
| QU-02 · Tabellenkalkulation | Kam vor – 1/9 – S23 | 16 | SVERWEIS, WENN, WOCHENTAG. |
| QU-03 · Ticketsystem / IT-Service | Kam vor – 1/9 – S22 | 12 | Vor- und Nachteile eines Ticketsystems. |
| QU-04 · Künstliche Intelligenz | Kam vor – 1/9 – W23 | 6 | Workshop zur Einführung eines KI-Systems. In W23 und S26 außerdem Teil des Szenarios. |
| QU-05 · Green IT | Kam vor – 1/9 – W23 | 4 | Begriff und Ziele von Green IT. |

---

## Schnellübersicht nach Etikett

**Prüfungskern, in (fast) jeder Prüfung (8–9/9)**
- ER-/Relationenmodell, Normalisierung, SQL mit JOIN, SQL mit GROUP BY *(Datenbanken)*
- Algorithmus in Pseudocode, Arrays/Listen durchlaufen *(Algorithmen)*
- Mindestens ein UML-Diagramm *(UML)*
- Operatoren und Antworttechnik
- Diese Blöcke kommen fast immer vor, das konkrete Unterthema wechselt: Projektmanagement, Testen & QS, Schnittstellen & Datenformate, IT-Sicherheit

**Prüfungskern (3–7/9)**
- SQL INSERT/UPDATE/DELETE, DB-Theorie, SQL-DDL *(Datenbanken)*
- Min/Max-Suche, Speicherbedarf/Zahlensysteme *(Algorithmen)*
- Aktivitäts-, Klassen-, Zustands-, Use-Case- und Sequenzdiagramm ↓ *(UML)*
- Stakeholder ↑, Agil/Scrum, Anforderungen ↓, Risikoanalyse, klassische Vorgehensmodelle, Change-Management ↓ *(PM)*
- Unit-Test/Überdeckung, Code analysieren, Testarten, Schreibtischtest, ISO 25010, Codequalität ↓ *(Testen)*
- JSON, HTTP-Request/Response ↑ *(Schnittstellen)*
- Verschlüsselung, Hash/Signatur/PKI, Schutzziele ↑, Authentifizierung ↓ *(Sicherheit)*
- OOP-Grundkonzepte, Polymorphie/Generics, Vererbung/Interfaces *(OOP)*
- Mockup/GUI-Elemente, Barrierefreiheit *(Benutzeroberfläche)*
- IoT/Funk *(Netzwerk)*
- Englischer Fachtext ↑ *(Querschnitt)*

**Kam vor (1–2/9)**
- Unterabfragen ↓, UNION, Benutzer/Rechte, Trigger/Stored Procedure, Datenqualität *(Datenbanken)*
- Sortieren ↓, Zeichenketten, Exceptions ⚠, Rekursion, Nebenläufigkeit *(Algorithmen)*
- Struktogramm/PAP *(UML)*
- Kosten/Make-or-Buy ↓, Netzplan ⚠, Machbarkeitsanalyse ⚠, Systemnutzen *(PM)*
- Äquivalenzklassen ↓ *(Testen)*
- XML, REST-Grundlagen, eRechnung ⚠ *(Schnittstellen)*
- DSGVO, Angriffe *(Sicherheit)*
- Entwurfsmuster: Grundlagen und Erzeugungsmuster, Observer und weitere Muster *(OOP)*
- Usability ↓ *(Benutzeroberfläche)*
- Netzwerkkonzepte, Adressierung/Frames, IT-Infrastruktur *(Netzwerk)*
- Alle Werkzeug-Themen (nur W21 und W22)
- Tabellenkalkulation, Ticketsystem, KI, Green IT *(Querschnitt)*

---

## Belegsatz: was du nachschlagen kannst und was nicht

Alle 9 Prüfungen hatten laut Deckblatt einen Belegsatz. Er ist ein **reines Nachschlagewerk zur Notation**, enthält also weder Szenario noch Lösungshinweise. Eine offizielle Garantie, dass es im Winter 2026 wieder so ist, gibt es nicht. Es ist nur in allen 9 Prüfungen so gewesen.

**Er verrät die Diagrammtypen:** Der Belegsatz enthält nur die Notationen, die in der Prüfung gebraucht werden, dazu immer einen Auszug der SQL-Syntax. Ausnahme: In W25 stand das Klassendiagramm darin, ohne dass es eine Klassendiagramm-Aufgabe gab. Er zeigt also mindestens die benötigten Typen, manchmal einen mehr. **Tipp: In der Prüfung zuerst den Belegsatz durchblättern.**

| Prüfung | Inhalt des Belegsatzes |
|---|---|
| W21 | UML Use-Case, Aktivität, Klasse · SQL |
| S22 | UML Sequenz, Klasse, Aktivität · Pseudocode · PAP · SQL |
| W22 | UML Zustand, Aktivität · Pseudocode · Struktogramm · SQL |
| S23 | UML Zustand, Klasse, Aktivität · Tabellenkalkulation · Struktogramm · SQL (hinten im Heft) |
| W23 | laut Deckblatt vorhanden, Datei fehlt |
| S24 | ER-Modell · UML Aktivität, Sequenz, Klasse, Use-Case · Pseudocode-Konventionen · SQL |
| W24 | laut Deckblatt vorhanden, Datei fehlt (Teil 2 zusätzlich mit heraustrennbarem Blatt der DB-Tabellen) |
| W25 | UML Use-Case, Klasse, Zustand · SQL |
| S26 | laut Deckblatt vorhanden, Datei fehlt (Teil 2 zusätzlich mit heraustrennbarem Blatt der DB-Tabellen) |

Die Belegsätze von S24 und W25 haben praktisch dasselbe Layout, das ist also eine feste Vorlage. Was darin steht:

| Diagramm | Steht im Belegsatz | Fehlt, also selbst lernen |
|---|---|---|
| Klassendiagramm | Vererbung, Assoziation, gerichtete Assoziation, Aggregation, Komposition, Implementierung, Multiplizität (nur `1` und `*`), Syntax für Attribute und Methoden, Sichtbarkeiten `+ # - ~`, `{abstract}`, `<<interface>>` | **Wann Aggregation, wann Komposition** · Multiplizitäten wie `0..1` und `1..*` · Abhängigkeit (gestrichelter Pfeil) |
| Use-Case | Akteur, Generalisierung, `<<include>>`/`<<extend>>` mit Richtung, Condition, Extension Point | **Systemgrenze** |
| Aktivität | Start, Ende, Ablaufende, Aktion, Swimlanes, Fork/Join, Entscheidung und Zusammenführung mit Guards, Konnektor | praktisch vollständig |
| Sequenz | synchron, asynchron, Antwort, Lebenslinie, Aktivierung, Selbstaufruf, create, Destruktion, `alt` mit `[else]` | **`loop`, `opt`, `par`** |
| Zustand | `ereignis[bedingung]/aktion`, entry/do/exit, `after(Zeitdauer)`, Start, Ende | Unterzustände |
| ER-Modell | Entität, Beziehung, Attribut | **Kardinalitäten** · **Notation des Relationenmodells (PK/FK)**, obwohl 3NF in fast jeder Prüfung vorkommt |

**Pseudocode-Konventionen (S24):** `wenn … dann … sonst … Ende wenn`, `zähle … von … bis … Ende zähle`, `solange … Ende solange`, `wiederhole … solange`, Zuweisung `:=`, Typen integer, double, string, array. Daran sieht man, welcher Stil erwartet wird.

**SQL-Auszug:** Er war nicht in jeder Prüfung gleich. In W21 und W22 fehlte zum Beispiel HAVING, im W21-Belegsatz außerdem IN und die Unterabfrage in FROM.

**Konsequenz:** Symbole und Pfeilarten muss man nicht auswendig lernen. Lernen muss man das Fehlende aus der rechten Spalte, und Algorithmen im Pseudocode-Stil der Prüfung üben.

---

## Fazit für Winter 2026

1. **Sicher drankommen werden** ein Algorithmus mit 20–30 Punkten über Objekt-Arrays oder -Listen, eine SQL-Aufgabe mit JOIN und GROUP BY (LEFT JOIN in 4 von 9 Prüfungen), ein Relationenmodell in 3NF, mindestens ein UML-Diagramm, eine PM-Frage in Teil 1, Aufgabe 1 und eine Frage zu IT-Sicherheit, meist zur Verschlüsselung. Datenbanken, Algorithmen und UML allein bringen im Schnitt rund zwei Drittel der Punkte.
2. **Testen** fehlte in W24 und S26 (S26 nur mit ISO 25010), war sonst aber in jeder Prüfung dabei. Unit-Test und Überdeckungsmaße (6/9), Code analysieren und Schreibtischtest also unbedingt können.
3. **Steigende Trends:** Objektlisten statt Arrays, Stakeholder-Analyse, Schutzziele, HTTP und REST, englische Fachtexte, IoT-Szenarien. Die Risikoanalyse kam in den letzten beiden Prüfungen.
4. **Fallende Trends:** Sortieralgorithmen, SQL-Unterabfragen, Sequenzdiagramm, klassische Anforderungslisten, Change-Management, Kalkulation, Äquivalenzklassen, Authentifizierung. Fallend heißt nur „zuletzt nicht gefragt“, nicht „kommt nicht mehr“.
5. **Neu in den letzten beiden Prüfungen:** Netzplan (S26, 19 Punkte), eRechnung (W25, 24 Punkte), Machbarkeitsanalyse und Exceptions (W25).
6. **Schnittstellen & Datenformate** kommen als Block fast sicher dran (8/9), das konkrete Thema wechselt zwischen JSON, XML, REST und HTTP.
7. **Belegsatz:** Symbole muss man nicht auswendig lernen. Selbst lernen muss man Aggregation vs. Komposition, Multiplizitäten, `loop`/`opt`, die Systemgrenze, ERM-Kardinalitäten und die Notation des Relationenmodells.

> **Vorsicht bei der Aussagekraft:** 9 Prüfungen sind eine kleine Stichprobe, und die IHK veröffentlicht keine Themenrotation. Alle Etiketten und Trends sind statistische Tendenzen, keine Garantie. Die Auswertung beruht auf gescannten Prüfungen. Lösungen lagen nur zu S23, W23, S24 und W25 vor, die zu S23 und W25 stammen vom u-form-Verlag, nicht von der IHK.
