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

## Kategorien

| Kategorie | Kriterium |
|---|---|
| **Immer** | in mindestens 8 von 9 Prüfungen |
| **Meistens** | in 6–7 von 9 Prüfungen |
| **Oft** | in 4–5 von 9 Prüfungen |
| **Wahrscheinlich** | in 2–3 von 9 Prüfungen, davon mindestens einmal in den letzten 4 (S24–S26) |
| **Sonstige** | einmalig, oder 2–3 Mal, aber nur in älteren Prüfungen |

**Trendpfeile:** ↑ = in mindestens 3 der letzten 4 Prüfungen, aber vorher seltener. ↓ = in den letzten 3 Prüfungen nicht mehr. Ohne Pfeil = gleichmäßig verteilt.

„Ø Pkt“ = durchschnittliche Punktzahl in den Prüfungen, in denen das Thema vorkam (von insgesamt 200 Punkten für Teil 1 und 2).

*Ergänzung:* ⚠ = einmalig, aber in einer der letzten beiden Prüfungen. Manche Teilaufgaben gehören zu mehreren Themen oder Blöcken, deshalb ergeben die Summen in den Tabellen mehr als 200.

---

## Das feste Gerüst der Prüfung

Bevor es um einzelne Themen geht: Der Aufbau ist erstaunlich stabil.

**Teil 2** (4 Aufgaben, meist je 25 Punkte):
- **Aufgabe 4 ist in allen 9 Prüfungen SQL** (21–30 Punkte).
- **Aufgabe 3 ist in 6 von 9 Prüfungen ein Datenmodell in 3NF** (16–25 Punkte). In den übrigen 3 geht es um Testen.
- **Aufgabe 1 oder 2 ist immer ein großer Algorithmus** in Pseudocode (25–30 Punkte, meist ohne Teilaufgaben).
- Die verbleibende Aufgabe ist meist ein **UML-Diagramm** (Aktivität, Zustand, Sequenz oder Klasse) oder **Testen**.

**Teil 1** (4 Aufgaben, 20–30 Punkte):
- **Aufgabe 1 ist seit S23 durchgehend Projektmanagement** (6 Prüfungen in Folge): Stakeholder, Risiko, Vorgehensmodell, Anforderungen.
- **In jedem Teil 1 muss mindestens ein UML-Diagramm gezeichnet werden** (9/9).
- Außerdem fast immer IT-Sicherheit, dazu wechselnd Schnittstellen, Datenformate, GUI oder Datenbanktheorie.

**Datenbanken sind der größte Brocken:** Datenmodell, SQL und DB-Theorie bringen zusammen im Schnitt **54 von 200 Punkten** (27 %).

*Ergänzung:* Als Block zusammengefasst kommen **Schnittstellen & Datenformate in 8 von 9 Prüfungen** vor (siehe Abschnitt „Themenblöcke“).

---

## Überblick: Punkte pro Prüfung

| Thema | W21 | S22 | W22 | S23 | W23 | S24 | W24 | W25 | S26 | Σ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Algorithmus in Pseudocode/Code | 25 | 38 | 32 | 47 | 25 | 35 | 58 | 30 | 34 | 9/9 |
| Arrays / Listen durchlaufen | 25 | 34 | 20 | 25 | 25 | 35 | 40 | 34 | 30 | 9/9 |
| ER-/Relationenmodell + 3NF | 41 | 20 | 22 | 16 | 32 | 10 | 16 | 24 | 20 | 9/9 |
| SQL gesamt | 25 | 28 | 28 | 25 | 25 | 30 | 21 | 25 | 25 | 9/9 |
| UML-Verhaltensdiagramm (Akt./Zust./Seq.) | 15 | 9 | 25 | 35 | 40 | 25 | 15 | 25 | 16 | 9/9 |
| Projektmanagement (irgendein Thema) | 9 | 36 | 11 | 28 | 22 | 15 | 20 | 30 | 32 | 9/9 |
| IT-Sicherheit / Datenschutz | 12 | 13 | 18 | 8 | – | 15 | 4 | 10 | 13 | 8/9 |
| Testen | 13 | 16 | 7 | 16 | 6 | 25 | – | 25 | – | 7/9 |
| Aktivitätsdiagramm | 15 | – | 14 | 25 | 15 | 14 | – | – | 16 | 6/9 |
| Klassendiagramm | 7 | 13 | – | 9 | – | 5 | 25 | – | 12 | 6/9 |
| Zustandsdiagramm | – | – | 11 | 10 | – | – | 15 | 25 | – | 4/9 |
| Use-Case-Diagramm | 14 | – | – | – | 6 | 14 | – | 18 | – | 4/9 |
| Sequenzdiagramm | – | 9 | – | – | 25 | 11 | – | – | – | 3/9 |
| JSON / XML | – | – | 13 | 13 | 14 | 3 | 8 | 15 | – | 6/9 |
| REST / HTTP | 2 | – | – | – | – | 25 | 10 | – | 12 | 4/9 |
| OOP / Entwurfsmuster | – | 30 | 10 | 22 | – | 5 | 10 | – | 16 | 6/9 |
| GUI / Mockup / Barrierefreiheit | 16 | – | 7 | – | 8 | 13 | – | – | 18 | 5/9 |
| Englischer Fachtext | – | 9 | – | – | 1 | 12 | – | 9 | 3 | 5/9 |

(Teilaufgaben mit mehreren Themen zählen bei jedem dieser Themen voll mit, daher ergeben die Spalten mehr als 200.)

---

## 🟥 IMMER (8–9 von 9)

| Thema | Vorkommen | Ø Pkt | Wo | Typische Aufgabe |
|---|---|---|---|---|
| **Algorithmus in Pseudocode schreiben** | 9/9 | 36 | T2 immer, in 4 Prüfungen auch T1 | Methode über ein Array oder eine Liste von Objekten: **zählen mit Bedingung, Summe/Durchschnitt, filtern, Minimum/Maximum suchen**. Oft ist ein Klassen- oder Methodengerüst mit Gettern vorgegeben. |
| **Arrays / Listen durchlaufen** | 9/9 | 30 | T2 | Früher **2D-Arrays** (W21, S22, S23: Tag × Stunde, Monat × Verbraucher). Seit S24 eher **Objektlisten** `List<T>` mit `add/get/size/contains` (↑). |
| **SQL: SELECT mit JOIN** | 9/9 | 15 | T2 Aufg. 4 | Join über 2–4 Tabellen mit WHERE und ORDER BY. **„Auch X ohne Y anzeigen“ heißt LEFT JOIN** (5 Mal: S22, W22, S23, W23, S26). Häufig Datumsfunktionen wie YEAR(), MONTH(). |
| **SQL: Aggregatfunktionen + GROUP BY** | 8/9 | 13 | T2 Aufg. 4 | COUNT/SUM/AVG/MIN/MAX je Gruppe, dazu HAVING (W21, W25). |
| **Relationales Datenmodell / ER-Modell** | 9/9 | 21 | T2 Aufg. 3 (6×), T1 (5×) | Aus Beispieldaten, einem Formular oder Text ein **Relationenmodell mit PK/FK und Kardinalitäten** zeichnen bzw. ein gegebenes ERM erweitern. m:n-Beziehungen werden aufgelöst. |
| **Normalisierung (3NF)** | 8/9 | 19 | T2, T1 | Fast immer heißt es „in 3. Normalform“. Zusätzlich: Anomalien und Redundanz erklären (S23, W25). |
| **UML-Verhaltensdiagramm** | 9/9 | 23 | T1 und T2 | Mindestens eines aus Aktivitäts-, Zustands- oder Sequenzdiagramm, **oft als ganze 25-Punkte-Aufgabe**. Details siehe unten. |
| **Projektmanagement (irgendein Thema)** | 9/9 | 23 | T1, meist Aufg. 1 | Der Inhalt wechselt (Stakeholder, Risiko, Vorgehensmodell, Anforderungen), aber eine PM-Frage kommt immer. |
| **IT-Sicherheit** | 8/9 | 12 | T1 | Fehlte nur in W23. Schwerpunkt Kryptografie und Schutzziele, siehe unten. |

---

## 🟧 MEISTENS (6–7 von 9)

| Thema | Vorkommen | Ø Pkt | Typische Aufgabe |
|---|---|---|---|
| **Testarten** | 7/9 | 9 | Black-Box vs. White-Box, Regressionstest, Unit-Test, Usability-Test, Testkonzept. **Fehlte in W24 und S26.** |
| **SQL: INSERT / UPDATE / DELETE** | 7/9 | 9 | **Archivierungsmuster:** `INSERT INTO archiv SELECT …` und danach `DELETE` (W22, S24, S26). Außerdem UPDATE mit Join oder Unterabfrage. |
| **Projektbegriffe / Stakeholder** | 7/9 | 8 | **Stakeholder-Analyse** (Befürchtungen und Gegenmaßnahmen), Meilenstein, Lessons Learned, Change Request, Projekterfolg prüfen. |
| **Aktivitätsdiagramm** | 6/9 | 17 | Aus Text erstellen, **mit Swimlanes, Verzweigungen und Fork/Join** (Parallelität). Der Klassiker unter den Diagrammen. |
| **Klassendiagramm** | 6/9 | 12 | Attribute mit Datentypen, Sichtbarkeiten, Vererbung/abstrakte Klasse, **Aggregation vs. Komposition**, Multiplizitäten deuten. |
| **JSON / XML** | 6/9 | 11 | XML mit DTD/XSD („wohlgeformt vs. gültig“, Fehler finden), JSON aus Tabellen erzeugen, JSON als Ausgabeformat. |
| **Gegebenen Code analysieren / Fehler finden** | 6/9 | 11 | Fehlerhafte Funktion durchspielen und korrigieren. **Typische Fehler:** falsche Initialisierung (0 statt `werte[0]`), Off-by-one bei der Schleifengrenze, `>` statt `>=`. |
| **DB-Konzepte** | 6/9 | 10 | **NoSQL vs. relational** (3 Mal), ACID, Anomalien, Redundanz, Index. |
| **Kryptografie** | 6/9 | 9 | **Hybride Verschlüsselung / TLS** (am häufigsten), digitale Signatur, Zertifikat/PKI, Hash, RSA. Typische Fangfrage: Verschlüsselung sichert keine Integrität (S26). |
| **Schutzziele** | 6/9 | 5 | Vertraulichkeit, Integrität, Verfügbarkeit (BSI) einer Situation zuordnen. |

---

## 🟨 OFT (4–5 von 9)

| Thema | Vorkommen | Ø Pkt | Trend | Typische Aufgabe |
|---|---|---|---|---|
| **Listen / Datenstrukturen** | 5/9 | 23 | ↑ | `List<T>` mit vorgegebenen Methoden. Ersetzt zunehmend die Arrays. |
| **GUI / Mockup / Barrierefreiheit** | 5/9 | 12 | | **Mockup skizzieren** bzw. verbessern, Barrierefreiheit (Anforderungen und Maßnahmen), Usability. |
| **OOP-Konzepte** | 5/9 | 12 | | **Polymorphie** (S23, S26), abstrakte Klasse, Vererbung, Interface, Generics. |
| **Unit-Test / Überdeckungsmaße** | 5/9 | 10 | | Was ist ein Unit-Test? **Anweisungs-, Zweig- und Pfadüberdeckung**, Mindestzahl der Testfälle. |
| **Agile Methoden / Scrum / User Stories** | 5/9 | 8 | | Scrum-Rollen, -Artefakte und -Events, klassisch vs. agil, User Story und INVEST. |
| **Englischer Fachtext** | 5/9 | 7 | ↑ | Fachtext lesen und auf Deutsch erklären (NoSQL, User Story, Coverage, eRechnung, LoRa). |
| **Anforderungen (funktional / nicht-funktional)** | 5/9 | 5 | ↓ | Je X Beispiele beschreiben. Zuletzt in S24. |
| **SQL-Unterabfrage** | 5/9 | 8 | | NOT IN / NOT EXISTS, korrelierte Unterabfrage. |
| **SQL-DDL** | 5/9 | 4 | | CREATE TABLE, ALTER TABLE ADD/DROP, CREATE INDEX, Datentypen wählen. |
| **Minimum / Maximum / lineare Suche** | 4/9 | 21 | | Nächster Nachbar (W23), bester Tisch (W25), Teilnehmer suchen (S24). Zusätzlich Min/Max in S22 und in SQL. |
| **Zustandsdiagramm** | 4/9 | 15 | | Zustände mit Bedingungen bzw. Guards (Auslastung leer/niedrig/hoch, Raumstatus, Roboter). |
| **Use-Case-Diagramm** | 4/9 | 13 | | Akteure, **include/extend**, Generalisierung. |
| **REST / HTTP** | 4/9 | 12 | ↑ | REST-Konzept, **HTTP-Methoden (CRUD)**, Statuscodes, URL-Parameter. |
| **Qualitätsmerkmale (ISO 25010)** | 4/9 | 7 | | Zuverlässigkeit, Effizienz, Wartbarkeit usw. beschreiben. |
| **Risikoanalyse** | 4/9 | 6 | ↑ | Risiko mit Ursache, Auswirkung und Gegenmaßnahme in eine Tabelle eintragen. Seit S23 in 4 von 6 Prüfungen. |
| **Schreibtischtest** | 4/9 | 5 | | Wertetabelle bzw. Trace eines gegebenen Algorithmus ausfüllen. |

---

## 🟩 WAHRSCHEINLICH (2–3 von 9, zuletzt kürzlich)

| Thema | Vorkommen | Zuletzt | Typische Aufgabe |
|---|---|---|---|
| **Entwurfsmuster** | 3/9 | W24 | **Observer** (S22, W24), Fabrikmethode (S23), Musterkategorien. |
| **Sequenzdiagramm** | 3/9 | S24 | Mit alt-Fragment, Nachrichten und Rückantworten. |
| **Klassische Vorgehensmodelle / Prototyping** | 3/9 | W25 | Wasserfall vs. agil, Prototyping. |
| **Speicherbedarf berechnen / Zahlensysteme** | 3/9 | S26 | **Fast identische Aufgabe in W23 (GiB) und S26 (TiB):** Speicherbedarf von Drohnenbildern berechnen. Rechnung mit 1024er-Präfixen üben. |
| **IoT / Sensorik / Funk** | 3/9 | S26 | LoRa, LPWAN, Verschlüsselung auf schwachen Sensoren. Die Szenarien werden zunehmend IoT-lastig. |
| **Äquivalenzklassen / Testfälle** | 3/9 | W25 | Testdaten für Äquivalenzklassen und Grenzwerte angeben. |
| **Authentifizierung** | 3/9 | S24 | OAuth2/SSO, 2FA, sicherer Passwort-Reset. |
| **Sortieralgorithmus** | 2/9 | S24 | Bubblesort, **mit übergebener Vergleichsfunktion** (Comparator). |
| **Netzwerkkonzepte** | 2/9 | S26 | LAN/SAN/LPWAN zuordnen, LoRa. |
| **Codequalität / QS-Maßnahmen** | 2/9 | S24 | Code-Reviews, Dokumentationsrichtlinien. |
| **Zeichenketten verarbeiten** | 2/9 | W25 | Zeichen prüfen, Spalte per SUBSTRING aufteilen. |
| **Datenschutz / DSGVO** | 2/9 | W25 | Einwilligung, Datenschutzerklärung, keine echten Daten auf Prüfseiten. |
| **Exceptions / Fehlerbehandlung** | 2/9 | W25 | Fehlerfall bzw. `null` behandeln. |

---

## ⬜ SONSTIGE

**Mehrfach, aber zuletzt vor S24:**
- **Change-Management / Akzeptanz der Betroffenen** (3/9: S22, S23, W23): Befürchtungen des Personals, Workshop. Steht der Stakeholder-Analyse sehr nahe, sollte man also mitkönnen.
- **Struktogramm / PAP** (2/9: W21, S23)
- **Kosten / Make-or-Buy** (2/9: S22, S23): Klassische Kalkulation spielt praktisch keine Rolle.

**Einmalig, aber in den letzten beiden Prüfungen (beobachten ⚠):**
- **Netzplan** (S26, **19 Punkte**): Vorgangsknoten mit FAZ/FEZ/SAZ/SEZ, Puffer, kritischer Pfad.
- **eRechnung** (W25, ca. 18 Punkte): EU-Richtlinie, XML/XSD.
- **Machbarkeitsanalyse** (W25)

**Einmalig:**
- Teil 1:
  - Tabellenkalkulation (S23, 16 P: SVERWEIS, WENN, WOCHENTAG)
  - Ticketsystem (S22)
  - MAC-Adresse, Ethernet-Frame, Subnetz-Skript (W24)
  - Compiler vs. Interpreter, Klassenbibliotheken (W21)
  - Open Source und Lizenzen (W22)
  - CI/CD, 3-Schichten-Architektur, IT-Infrastruktur (W22)
  - KI-Einführung, Green IT (W23)
  - Versionsverwaltung/Git (W21)
  - Systemnutzen (S24)
- Teil 2:
  - Datenqualität beim Datenimport (W24, 13 P)
  - Rekursion (W22, 12 P)
  - SQL UNION (S24)
  - Makro-Malware in Mail-Anhängen (S24)
  - Trigger / Stored Procedure (S22)
  - Nebenläufigkeit beim Blockieren von Terminen (W21)
- SQL-Rechte, GRANT / CREATE USER (S23)

---

# Ergänzung: Belegsatz

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
| ER-Modell | Entität, Beziehung, Attribut | **Kardinalitäten** · **Notation des Relationenmodells (PK/FK)**, obwohl 3NF in jeder Prüfung vorkommt |

**Pseudocode-Konventionen (S24):** `wenn … dann … sonst … Ende wenn`, `zähle … von … bis … Ende zähle`, `solange … Ende solange`, `wiederhole … solange`, Zuweisung `:=`, Typen integer, double, string, array. Daran sieht man, welcher Stil erwartet wird.

**Konsequenz:** Symbole und Pfeilarten muss man nicht auswendig lernen. Lernen muss man das Fehlende aus der rechten Spalte, und Algorithmen im Pseudocode-Stil der Prüfung üben.

---

# Ergänzung: Gliederung nach Themenblöcken

Dieselben Themen wie oben, hier aber nach Fachgebiet gruppiert.

**Abweichungen zur Einteilung oben:**
- **ISO 25010** (Qualitätsmerkmale) zählt hier zu *Testen & Qualitätssicherung* statt zum Projektmanagement. Deshalb kommt Projektmanagement hier auf 8/9 statt 9/9, denn W21 hatte sonst kein PM-Thema.
- **Change-Management, Machbarkeitsanalyse und Systemnutzen** gehören hier zum Projektmanagement.
- **„Code analysieren / Fehler finden“** gehört hier zu Testen, weil diese Aufgaben fast immer auf einen Schreibtischtest folgen.

## Überblick: Themenblöcke

| Block | Vorkommen | Kategorie | Ø Pkt | Wo |
|---|---|---|---|---|
| Datenbanken | 9/9 | **Immer** | 54 | Teil 1 (7×) + Teil 2 (9×) |
| Algorithmen & Programmierung | 9/9 | **Immer** | 42 | Teil 2 (9×), Teil 1 (5×) |
| UML-Modellierung | 9/9 | **Immer** | 38 | Teil 1 (9×) + Teil 2 (6×) |
| Projektmanagement | 8/9 | **Immer** | 24 | nur Teil 1 |
| Testen & Qualitätssicherung | 8/9 | **Immer** | 21 | Teil 1 (7×) + Teil 2 (6×) |
| Schnittstellen & Datenformate | 8/9 | **Immer** | 15 | Teil 1 |
| IT-Sicherheit & Datenschutz | 8/9 | **Immer** | 12 | Teil 1 |
| Objektorientierung & Entwurfsmuster | 6/9 | **Meistens** | 16 | Teil 1 (4×) + Teil 2 (2×) |
| Benutzeroberfläche | 5/9 | **Oft** | 12 | Teil 1 |
| Netzwerk, IoT & Infrastruktur | 4/9 | **Oft** | 13 | Teil 1 |
| Entwicklungsumgebung & Werkzeuge | 2/9 | **Sonstige** | 21 | nur W21, W22 |

## Punkte pro Prüfung nach Block

| Block | W21 | S22 | W22 | S23 | W23 | S24 | W24 | W25 | S26 | Σ |
|---|---:|---:|---:|---:|---:|---:|---:|---:|---:|---:|
| Datenbanken | 66 | 59 | 55 | 41 | 82 | 40 | 50 | 49 | 45 | 9/9 |
| ↳ davon SQL | 25 | 28 | 28 | 25 | 25 | 30 | 21 | 25 | 25 | 9/9 |
| ↳ davon Datenmodell (ERM/3NF) | 41 | 20 | 22 | 16 | 32 | 10 | 16 | 24 | 20 | 9/9 |
| Algorithmen & Programmierung | 37 | 38 | 47 | 47 | 32 | 35 | 58 | 42 | 39 | 9/9 |
| UML-Modellierung | 42 | 22 | 25 | 49 | 46 | 44 | 40 | 43 | 28 | 9/9 |
| Projektmanagement | – | 36 | 11 | 20 | 28 | 17 | 20 | 30 | 27 | 8/9 |
| Testen & Qualitätssicherung | 29 | 20 | 17 | 33 | 6 | 31 | – | 29 | 5 | 8/9 |
| Schnittstellen & Datenformate | 2 | – | 13 | 13 | 14 | 25 | 18 | 24 | 12 | 8/9 |
| IT-Sicherheit & Datenschutz | 12 | 13 | 18 | 8 | – | 15 | 4 | 10 | 13 | 8/9 |
| Objektorientierung & Entwurfsmuster | – | 30 | 10 | 22 | – | 5 | 10 | – | 16 | 6/9 |
| Benutzeroberfläche | 16 | – | 7 | – | 8 | 13 | – | – | 18 | 5/9 |
| Netzwerk, IoT & Infrastruktur | – | – | 8 | 4 | – | – | 26 | – | 12 | 4/9 |
| Entwicklungsumgebung & Werkzeuge | 20 | – | 21 | – | – | – | – | – | – | 2/9 |

## Die Blöcke im Detail

### 1. Datenbanken (9/9 · Immer · Ø 54 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| ER-/Relationenmodell | 9/9 | Immer | alle | Aus Beispieldaten, Formular oder Text ein **Relationenmodell mit PK/FK und Kardinalitäten** zeichnen bzw. ein ERM erweitern. m:n-Beziehungen auflösen. Meist Teil 2, Aufgabe 3. |
| Normalisierung (3NF) | 8/9 | Immer | alle außer S24 | Fast immer heißt es „in 3. Normalform“. Dazu Anomalien und Redundanz erklären (S23, W25). |
| SQL: SELECT mit JOIN | 9/9 | Immer | alle | Join über 2–4 Tabellen mit WHERE und ORDER BY. **„Auch X ohne Y anzeigen“ heißt LEFT JOIN** (S22, W22, S23, W23, S26). Häufig Datumsfunktionen wie YEAR(), MONTH(). |
| SQL: Aggregatfunktionen + GROUP BY | 8/9 | Immer | alle außer W24 | COUNT/SUM/AVG/MIN/MAX je Gruppe, dazu HAVING (W21, W25). |
| SQL: INSERT/UPDATE/DELETE | 7/9 | Meistens | W21, S22, W22, W23, S24, W25, S26 | **Archivieren:** `INSERT INTO archiv SELECT …`, danach `DELETE` (W22, S24, S26). UPDATE mit Join oder Unterabfrage. |
| DB-Theorie | 6/9 | Meistens | S22, W22, S23, W23, W24, W25 | **NoSQL vs. relational** (3×), ACID, Anomalien, Redundanz, Index. |
| SQL: Unterabfrage | 5/9 | Oft | W21, S22, W22, W23, S26 | NOT IN / NOT EXISTS, korrelierte Unterabfrage. |
| SQL: DDL | 5/9 | Oft | W21, S22, W22, W25, S26 | CREATE TABLE, ALTER TABLE ADD/DROP, CREATE INDEX, Datentypen wählen. |
| SQL: UNION | 1/9 | Sonstige | S24 | |
| SQL: GRANT / CREATE USER | 1/9 | Sonstige | S23 | |
| SQL: Trigger / Stored Procedure | 1/9 | Sonstige | S22 | |
| Datenqualität beim Import | 1/9 | Sonstige | W24 | Probleme manuell gepflegter Altdaten, Bereinigung. |

**Steht nicht im Belegsatz, selbst lernen:** Kardinalitäten im ER-Modell und die Notation des Relationenmodells mit PK/FK. Der SQL-Syntax-Auszug liegt dagegen immer bei.

### 2. Algorithmen & Programmierung (9/9 · Immer · Ø 42 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| Algorithmus in Pseudocode schreiben | 9/9 | Immer | alle | Methode über ein Array oder eine Liste von Objekten: **zählen mit Bedingung, Summe/Durchschnitt, filtern, Minimum/Maximum suchen**. Oft mit vorgegebenem Klassen- oder Methodengerüst. 25–30 Punkte in Teil 2. |
| Arrays oder Listen durchlaufen | 9/9 | Immer | alle | Arrays 8/9 (früher oft **2D-Arrays**: Tag × Stunde, Monat × Verbraucher). Listen 5/9 ↑ (S22, W22, S24, W25, S26: `List<T>` mit `add/get/size/contains`). |
| Minimum/Maximum, lineare Suche | 4/9 | Oft | W21, W23, S24, W25 | Nächster Nachbar (W23), bester Tisch (W25), Teilnehmer suchen (S24). |
| Speicherbedarf berechnen / Zahlensysteme | 3/9 | Wahrscheinlich | W22, W23, S26 | **W23 (GiB) und S26 (TiB) fast identisch:** Speicherbedarf von Drohnenbildern mit 1024er-Präfixen. W22: Dezimal → Binär. |
| Sortieren | 2/9 | Wahrscheinlich | W22, S24 | Bubblesort **mit übergebener Vergleichsfunktion**. |
| Zeichenketten verarbeiten | 2/9 | Wahrscheinlich | W21, W25 | Zeichen prüfen, Spalte per SUBSTRING aufteilen. |
| Exceptions / Fehlerbehandlung | 2/9 | Wahrscheinlich | W21, W25 | Fehlerfall bzw. `null` behandeln. |
| Rekursion | 1/9 | Sonstige | W22 | Rekursion vs. Iteration. |
| Nebenläufigkeit | 1/9 | Sonstige | W21 | Konkurrierende Zugriffe beim Blockieren von Terminen. |

**Pseudocode-Stil:** Der Belegsatz von S24 zeigt die erwarteten Konventionen (`wenn … dann … Ende wenn`, `zähle … von … bis`, `:=`). Darin üben.

### 3. UML-Modellierung (9/9 · Immer · Ø 38 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| Irgendein Verhaltensdiagramm | 9/9 | Immer | alle | Mindestens eines aus Aktivitäts-, Zustands- oder Sequenzdiagramm, **oft als ganze 25-Punkte-Aufgabe**. |
| Aktivitätsdiagramm | 6/9 | Meistens | W21, W22, S23, W23, S24, S26 | Aus Text erstellen, **mit Swimlanes, Verzweigungen und Fork/Join**. Der Klassiker. |
| Klassendiagramm | 6/9 | Meistens | W21, S22, S23, S24, W24, S26 | Attribute mit Datentypen, Sichtbarkeiten, Vererbung/abstrakte Klasse, **Aggregation vs. Komposition**, Multiplizitäten. |
| Zustandsdiagramm | 4/9 | Oft | W22, S23, W24, W25 | Zustände mit Guards (Auslastung, Raumstatus, Roboter). |
| Use-Case-Diagramm | 4/9 | Oft | W21, W23, S24, W25 | Akteure, **include/extend**, Generalisierung. |
| Sequenzdiagramm | 3/9 | Wahrscheinlich | S22, W23, S24 | Mit alt-Fragment, Nachrichten und Rückantworten. |
| Struktogramm / PAP | 2/9 | Sonstige ↓ | W21, S23 | |

**Steht nicht im Belegsatz, selbst lernen:**
- Wann Aggregation, wann Komposition
- Multiplizitäten wie `0..1` und `1..*`
- Abhängigkeit (gestrichelter Pfeil)
- Systemgrenze im Use-Case-Diagramm
- `loop`, `opt` und `par` im Sequenzdiagramm
- Unterzustände im Zustandsdiagramm

### 4. Projektmanagement (8/9 · Immer · Ø 24 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| Stakeholder / Projektbegriffe | 7/9 | Meistens | S22, S23, W23, S24, W24, W25, S26 | **Stakeholder-Analyse** (Interessen, Befürchtungen, Gegenmaßnahmen), Meilenstein, Lessons Learned, Change Request, Projekterfolg prüfen. |
| Agil / Scrum / User Stories | 5/9 | Oft | S22, W22, W23, W24, W25 | Scrum-Rollen, -Artefakte und -Events, klassisch vs. agil, User Story und INVEST. |
| Anforderungen (funktional / nicht-funktional) | 5/9 | Oft ↓ | S22, W22, S23, W23, S24 | Je X Beispiele beschreiben. |
| Risikoanalyse | 4/9 | Oft ↑ | S23, S24, W25, S26 | Risiko mit Ursache, Auswirkung und Gegenmaßnahme in eine Tabelle eintragen. |
| Klassische Vorgehensmodelle / Prototyping | 3/9 | Wahrscheinlich | S22, W24, W25 | Wasserfall vs. agil, Prototyping. |
| Change-Management / Akzeptanz | 3/9 | Sonstige | S22, S23, W23 | Befürchtungen des Personals, Workshop. Steht der Stakeholder-Analyse sehr nahe. |
| Kosten / Make-or-Buy | 2/9 | Sonstige ↓ | S22, S23 | Klassische Kalkulation spielt praktisch keine Rolle. |
| Netzplan | 1/9 | Sonstige ⚠ | S26 | **19 Punkte:** Vorgangsknoten mit FAZ/FEZ/SAZ/SEZ, Puffer, kritischer Pfad. |
| Machbarkeitsanalyse | 1/9 | Sonstige ⚠ | W25 | Technische, wirtschaftliche, zeitliche Kriterien. |
| Nutzen eines Systems beschreiben | 1/9 | Sonstige | S24 | |

Pro Prüfung werden meist 2–4 dieser Unterthemen abgefragt.

### 5. Testen & Qualitätssicherung (8/9 · Immer · Ø 21 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| Testarten | 7/9 | Meistens | W21, S22, W22, S23, W23, S24, W25 | Black-Box vs. White-Box, Regressionstest, Unit-Test, Usability-Test, Testkonzept. |
| Code analysieren / Fehler finden | 6/9 | Meistens | W21, S22, W22, S23, S24, W25 | Fehlerhafte Funktion durchspielen und korrigieren. **Typische Fehler:** falsche Initialisierung (0 statt `werte[0]`), Off-by-one bei der Schleifengrenze, `>` statt `>=`. |
| Unit-Test / Überdeckungsmaße | 5/9 | Oft | S22, W22, S23, S24, W25 | Was ist ein Unit-Test? **Anweisungs-, Zweig- und Pfadüberdeckung**, Mindestzahl der Testfälle. |
| Qualitätsmerkmale (ISO 25010) | 4/9 | Oft | W21, S23, S24, S26 | Zuverlässigkeit, Effizienz, Wartbarkeit usw. beschreiben. |
| Schreibtischtest | 4/9 | Oft | S22, W22, S23, W25 | Wertetabelle bzw. Trace eines gegebenen Algorithmus ausfüllen. |
| Äquivalenzklassen / Testfälle | 3/9 | Wahrscheinlich | S22, S23, W25 | Testdaten für Äquivalenzklassen und Grenzwerte angeben. |
| Codequalität / Reviews | 2/9 | Wahrscheinlich | W21, S24 | Code-Reviews, Dokumentationsrichtlinien, QS-Maßnahmen. |

Testen im engeren Sinn fehlte in W24 und S26. S26 zählt hier nur wegen ISO 25010.

### 6. Schnittstellen & Datenformate (8/9 · Immer · Ø 15 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| JSON / XML | 6/9 | Meistens | W22, S23, W23, S24, W24, W25 | XML mit DTD/XSD („wohlgeformt vs. gültig“, Fehler finden), JSON aus Tabellen erzeugen, JSON als Ausgabeformat. |
| REST / HTTP | 4/9 | Oft ↑ | W21, S24, W24, S26 | REST-Konzept, **HTTP-Methoden (CRUD)**, Statuscodes, URL-Parameter, HTTP-Response bauen. |
| eRechnung | 1/9 | Sonstige ⚠ | W25 | EU-Richtlinie, XML/XSD-Validierung. |

### 7. IT-Sicherheit & Datenschutz (8/9 · Immer · Ø 12 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| Kryptografie | 6/9 | Meistens | W21, S22, W22, S23, W25, S26 | **Hybride Verschlüsselung / TLS** (am häufigsten), digitale Signatur, Zertifikat/PKI, Hash, RSA. Fangfrage in S26: Verschlüsselung sichert keine Integrität. |
| Schutzziele | 6/9 | Meistens | S22, W22, S23, S24, W25, S26 | Vertraulichkeit, Integrität, Verfügbarkeit (BSI) einer Situation zuordnen. |
| Authentifizierung | 3/9 | Wahrscheinlich | S22, W22, S24 | OAuth2/SSO, Zwei-Faktor, sicherer Passwort-Reset. |
| Datenschutz / DSGVO | 2/9 | Wahrscheinlich | W24, W25 | Einwilligung, Datenschutzerklärung, keine echten Daten auf Prüfseiten. |
| Angriffe | 1/9 | Sonstige | S24 | Makro-Malware in Mail-Anhängen. |

### 8. Objektorientierung & Entwurfsmuster (6/9 · Meistens · Ø 16 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| OOP-Konzepte | 5/9 | Oft | S22, W22, S23, S24, S26 | **Polymorphie** (S23, S26), abstrakte Klasse, Vererbung, Interface, Generics. |
| Entwurfsmuster | 3/9 | Wahrscheinlich | S22, S23, W24 | **Observer** (S22, W24), Fabrikmethode (S23), Musterkategorien. |

Das Klassendiagramm steht unter UML-Modellierung, wird aber oft zusammen mit diesem Block abgefragt.

### 9. Benutzeroberfläche (5/9 · Oft · Ø 12 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| GUI / Mockup / Barrierefreiheit / Usability | 5/9 | Oft | W21, W22, W23, S24, S26 | **Mockup skizzieren** oder verbessern, Barrierefreiheit (Anforderungen und Maßnahmen), Usability. |

### 10. Netzwerk, IoT & Infrastruktur (4/9 · Oft · Ø 13 Pkt)

| Unterthema | Vorkommen | Kategorie | Prüfungen | Typische Aufgabe |
|---|---|---|---|---|
| IoT / Sensorik / Funk | 3/9 | Wahrscheinlich | S23, W24, S26 | LoRa, LPWAN, Verschlüsselung auf schwachen Sensoren. Die Szenarien werden zunehmend IoT-lastig. |
| Netzwerkkonzepte | 2/9 | Wahrscheinlich | W24, S26 | LAN, SAN und LPWAN zuordnen, LoRa. |
| MAC-Adresse / Ethernet-Frame / Subnetz | 1/9 | Sonstige | W24 | |
| IT-Infrastruktur | 1/9 | Sonstige | W22 | |

### 11. Entwicklungsumgebung & Werkzeuge (2/9 · Sonstige)

Jedes dieser Themen kam genau einmal vor, alle in W21 oder W22: Git, CI/CD, 3-Schichten-Architektur, Compiler vs. Interpreter, Klassenbibliotheken, Open Source.

### Ohne eigenen Block

| Thema | Vorkommen | Kategorie | Prüfungen | Hinweis |
|---|---|---|---|---|
| Englischer Fachtext | 5/9 | Oft ↑ | S22, W23, S24, W25, S26 | Fachtext lesen und auf Deutsch erklären. Kommt in jedem Block vor (NoSQL, User Story, Coverage, eRechnung, LoRa). |
| Tabellenkalkulation | 1/9 | Sonstige | S23 | 16 Punkte: SVERWEIS, WENN, WOCHENTAG. |
| Ticketsystem | 1/9 | Sonstige | S22 | |
| KI | 1/9 | Sonstige | W23 | Nur als Kontext. |
| Green IT | 1/9 | Sonstige | W23 | |

## Schnellübersicht nach Kategorie (mit Block)

**Immer**
- Algorithmus in Pseudocode, Arrays/Listen durchlaufen *(Algorithmen)*
- ER-/Relationenmodell, 3NF, SQL mit JOIN, SQL mit GROUP BY *(Datenbanken)*
- Irgendein UML-Verhaltensdiagramm *(UML)*
- Diese Blöcke kommen immer vor, aber das konkrete Unterthema wechselt: Projektmanagement, Testen & QS, Schnittstellen & Datenformate, IT-Sicherheit

**Meistens**
- Stakeholder *(PM)*
- Aktivitätsdiagramm, Klassendiagramm *(UML)*
- SQL INSERT/UPDATE/DELETE, DB-Theorie *(Datenbanken)*
- Testarten, Code analysieren *(Testen)*
- JSON/XML *(Schnittstellen)*
- Kryptografie, Schutzziele *(Sicherheit)*

**Oft**
- Agil/Scrum, Anforderungen ↓, Risikoanalyse ↑ *(PM)*
- Zustandsdiagramm, Use-Case-Diagramm *(UML)*
- SQL-Unterabfrage, SQL-DDL *(Datenbanken)*
- Min/Max-Suche *(Algorithmen)*
- Unit-Test/Überdeckung, ISO 25010, Schreibtischtest *(Testen)*
- REST/HTTP ↑ *(Schnittstellen)*
- OOP-Konzepte *(OOP)*
- GUI/Mockup *(Benutzeroberfläche)*
- Englischer Fachtext ↑

**Wahrscheinlich**
- Klassische Vorgehensmodelle/Prototyping *(PM)*
- Sequenzdiagramm *(UML)*
- Speicherbedarf berechnen, Sortieren, Zeichenketten, Exceptions *(Algorithmen)*
- Äquivalenzklassen, Codequalität *(Testen)*
- Authentifizierung, DSGVO *(Sicherheit)*
- Entwurfsmuster *(OOP)*
- IoT/Funk, Netzwerkkonzepte *(Netzwerk)*

**Sonstige**
- Netzplan ⚠, Machbarkeitsanalyse ⚠, Change-Management, Kosten/Make-or-Buy, Systemnutzen *(PM)*
- Struktogramm/PAP ↓ *(UML)*
- UNION, GRANT, Trigger/Stored Procedure, Datenqualität *(Datenbanken)*
- Rekursion, Nebenläufigkeit *(Algorithmen)*
- eRechnung ⚠ *(Schnittstellen)*
- Angriffe *(Sicherheit)*
- MAC/Ethernet/Subnetz, IT-Infrastruktur *(Netzwerk)*
- Entwicklungsumgebung & Werkzeuge
- Tabellenkalkulation, Ticketsystem, KI, Green IT

---

## Fazit für Winter 2026

1. **Sicher drankommen werden** ein 25–30-Punkte-Algorithmus über Objekt-Arrays oder -Listen, eine SQL-Aufgabe mit JOIN, GROUP BY und LEFT JOIN, ein Relationenmodell in 3NF, mindestens ein UML-Verhaltensdiagramm, eine PM-Frage in Teil 1 Aufgabe 1 und eine Frage zu IT-Sicherheit bzw. Kryptografie. Das sind zusammen schon über die Hälfte der Punkte.
2. **Testen** kam in den letzten beiden Sommerprüfungen nicht vor (W24, S26), ist aber sonst ein Dauerbrenner (7/9). Unit-Test, Überdeckungsmaße und Schreibtischtest also unbedingt können.
3. **Steigende Trends:** Objektlisten statt Arrays, REST/HTTP, englische Fachtexte, Risikoanalyse, IoT-Szenarien.
4. **Fallende Trends:** Struktogramm/PAP, Kalkulation, klassische Anforderungslisten, Sortieralgorithmen.

*Ergänzungen zum Fazit:*

5. **Schnittstellen & Datenformate** (REST, JSON, XML) kommen als Block ebenfalls fast sicher dran (8/9).
6. **Belegsatz:** Symbole muss man nicht auswendig lernen. Selbst lernen muss man Aggregation vs. Komposition, Multiplizitäten, `loop`/`opt`, die Systemgrenze, ERM-Kardinalitäten und die Notation des Relationenmodells.

> **Vorsicht bei der Aussagekraft:** 9 Prüfungen sind eine kleine Stichprobe, und die IHK veröffentlicht keine Themenrotation. „Wahrscheinlich“ ist eine statistische Tendenz, keine Garantie. Die Einordnung beruht auf visuell gelesenen Scans; die Lösungen zu S23 und W25 stammen vom u-form-Verlag, nicht von der IHK.


---

## Ergänzung: Korrekturen nach Neuauswertung der Originalprüfungen (14.10.2026)

Der gesamte Text oberhalb dieses Abschnitts ist unverändert geblieben. Wo er von den folgenden Angaben abweicht, gelten die Angaben dieses Abschnitts.

### Methode und Regeln

Alle 9 Prüfungen (W21–S26, Teil 1 und Teil 2, ohne Teil 3) wurden Teilaufgabe für Teilaufgabe neu gelesen (mit den Lösungen, wo vorhanden) und jedem Unterthema der Blöcke zugeordnet. Aufgaben und Punkte der alten Rohdaten stimmen; die Abweichungen liegen in der Themenzuordnung. Die Tabellen oben lassen sich aus den Rohdaten-Tags nachrechnen: Ein Thema zählte dort, sobald ein Tag auf der Aufgabe stand, auch wenn die Aufgabe das Thema nicht verlangte.

Ein Thema zählt für eine Prüfung nur, wenn es ausdrücklich gefragt war oder die Aufgabe ohne dieses Wissen nicht lösbar ist. Zusätzlich gelten zwei Regeln:

1. **OOP/Getter ist Voraussetzung.** Der Aufruf von Gettern und der Umgang mit Objekten in Algorithmusaufgaben zählt nicht als OOP-Vorkommen. Pseudocode-Aufgaben, die ein gegebenes Klassendiagramm nur lesen, zählen nicht als Klassendiagramm-Vorkommen. „Klassendiagramm erstellen oder ergänzen“ (z. B. W21 T1 3b, W24 T1 3a) zählt beim Klassendiagramm (Block 3), nicht bei OOP.
2. **Nur sichere Belege zählen.** Grenzfälle (Aufgabe passt nur teilweise oder nur als Voraussetzung) fallen aus Zahl, Prüfungsliste und Ø-Punkten heraus; sie stehen nur im Tooltip der Etiketten in den Übungsdateien.

Die Kategorien Immer/Meistens/Oft/Wahrscheinlich/Sonstige ersetzen in den Übungsdateien die Etiketten *Prüfungskern* (ab 3 Prüfungen), *Kam vor* (1–2) und *Hintergrund* (0), jeweils mit Zahl und Prüfungen, z. B. „Prüfungskern – 3/9 – S26, W24, W22“.

### Korrekturen bei Vorkommen und Prüfungslisten

| Thema (Block) | alt | neu | geänderte Prüfungen | Grund (Prüfung, Aufgabe) |
|---|---|---|---|---|
| DB-Theorie (01) | 6/9 (Meistens): S22, W22, S23, W23, W24, W25 | 6/9: S22, W22, S23, W23, W25, S26 | entfällt: W24; kommt dazu: S26 | W24 T2 3ba (Datenqualität beim Import) gehört zu „Datenqualität beim Import“, nicht zu DB-Theorie. S26 T2 4cb verlangt die Löschreihenfolge wegen Fremdschlüsseln, also referenzielle Integrität. |
| SQL: Unterabfrage (01) | 5/9 (Oft): W21, S22, W22, W23, S26 | 2/9: S22, W23 | entfällt: W21, W22, S26 | Sicher nur S22 T2 4bb (NOT IN/NOT EXISTS) und W23 T2 4d (korrelierte Unterabfrage). W22 T2 4b ist INSERT … SELECT plus DELETE, keine Unterabfrage. W21 T2 4c (UPDATE für Hersteller CCC) und S26 T2 4cb (DELETE „mit Unterabfrage oder WHERE“) lassen die Unterabfrage offen: Grenzfälle, nicht gezählt. |
| Minimum/Maximum, lineare Suche (02) | 4/9 (Oft): W21, W23, S24, W25 | 5/9: W21, S22, W23, S24, W25 | kommt dazu: S22 | S22 T2 1 verlangt Minimum und Maximum im 2D-Array (30 P); die Themenanalyse nannte S22 nur im Text, zählte es aber nicht mit. |
| Exceptions/Fehlerbehandlung (02) | 2/9 (Wahrscheinlich): W21, W25 | 1/9: W25 | entfällt: W21 | W21 T1 3c ist die Ursache einer HTTP-404-Meldung, keine Exception-Aufgabe. Übrig bleibt W25 (null/Exception im Algorithmus). |
| Klassendiagramm (03) | 6/9 (Meistens): W21, S22, S23, S24, W24, S26 | 5/9: W21, S22, S23, W24, S26 | entfällt: S24 | S24 T1 3ba–3bc sind Pseudocode-Methoden zu einem gegebenen Klassendiagramm (nur lesen) und zählen nicht als Klassendiagramm-Aufgabe. |
| Struktogramm/PAP (03) | 2/9 (Sonstige): W21, S23 | 1/9: W21 | entfällt: S23 | S23 T1 2bc/2bd erlaubten „Pseudocode oder Struktogramm“; die Struktogramm-Notation war nicht verlangt. |
| Stakeholder/Projektbegriffe (04) | 7/9 (Meistens): S22, S23, W23, S24, W24, W25, S26 | 5/9: S22, W23, S24, W24, S26 | entfällt: S23, W25 | S23 T1 1a (Konflikt Arbeitsplatzverlust) ist Change-Management, Stakeholder nur Grenzfall. W25 T1 1a ist die Machbarkeitsanalyse (eigenes Thema); W25 T1 1b/1c enthalten keine Stakeholder-Frage. |
| Anforderungen (04) | 5/9 (Oft): S22, W22, S23, W23, S24 | 4/9: S22, W22, S23, S24 | entfällt: W23 | W23 T1 2ab (Aufbau einer User Story) fragt keine funktionalen/nicht-funktionalen Anforderungen. |
| Risikoanalyse (04) | 4/9 (Oft): S23, S24, W25, S26 | 3/9: S23, W25, S26 | entfällt: S24 | S24 T1 1d ist eine Stakeholderanalyse (Befürchtungen und Maßnahmen), keine Risikotabelle; Grenzfall, nicht gezählt. |
| Testarten (05) | 7/9 (Meistens): W21, S22, W22, S23, W23, S24, W25 | 4/9: W21, W23, S24, W25 | entfällt: S22, W22, S23 | S22 T2 2aa (Unit-Test), W22 T2 2ac und S23 T2 3ba (Anweisungsüberdeckung) stehen bei Unit-Test/Überdeckung, S23 T1 1e (QS-Maßnahmen) bei Codequalität. Testarten im engeren Sinn: W21, W23, S24, W25. |
| Code analysieren/Fehler finden (05) | 6/9 (Meistens): W21, S22, W22, S23, S24, W25 | 5/9: W21, S22, W22, S23, W25 | entfällt: S24 | S24 T2 3c (alle Pfade notieren) ist Pfadüberdeckung, kein Fehlerfinden. |
| Unit-Test/Überdeckungsmaße (05) | 5/9 (Oft): S22, W22, S23, S24, W25 | 6/9: W21, S22, W22, S23, S24, W25 | kommt dazu: W21 | W21 T1 4cb verlangt, „nur automatische Code-Reviews und Unit-Tests“ zu bewerten, also Unit-Test-Wissen. |
| Qualitätsmerkmale ISO 25010 (05) | 4/9 (Oft): W21, S23, S24, S26 | 3/9: W21, S23, S26 | entfällt: S24 | S24 T1 1b (drei qualitätssichernde Maßnahmen) ist Qualitätssicherung, nicht ISO 25010. |
| Äquivalenzklassen/Testfälle (05) | 3/9 (Wahrscheinlich): S22, S23, W25 | 2/9: S22, S23 | entfällt: W25 | W25 T2 3ba gab die Testdaten vor („mit fünf vorgegebenen Testdaten testen“); es mussten keine Testfälle gebildet werden. |
| Codequalität/Reviews (05) | 2/9 (Wahrscheinlich): W21, S24 | 3/9: W21, S23, S24 | kommt dazu: S23 | S23 T1 1e (zwei QS-Maßnahmen) gehört hierher. |
| Schutzziele (07) | 6/9 (Meistens): S22, W22, S23, S24, W25, S26 | 5/9: S22, W22, S24, W25, S26 | entfällt: S23 | S23 T1 4b (Signieren statt Verschlüsseln) nennt kein Schutzziel ausdrücklich; Grenzfall, nicht gezählt. |

### Korrekturen in den Punktetabellen

Nur Zellen der beiden Punktetabellen oben, deren Wert sich ändert (alle anderen nachgerechneten Zellen stimmen; nicht nachgerechnet wurde die Zeile „Projektmanagement (irgendein Thema)“ der ersten Tabelle). Verglichen wird aufgabenweise: Zählt die alte Tabelle eine Aufgabe, die das Thema nicht verlangt, oder fehlt eine verlangte Aufgabe.

| Tabelle | Zeile | Prüfung | alt | neu | Grund |
|---|---|---|---:|---:|---|
| Überblick: Punkte pro Prüfung | Algorithmus in Pseudocode/Code | S22 | 38 | 34 | nur alt gezählt: T2 2bb (4 P) |
| Überblick: Punkte pro Prüfung | Arrays / Listen durchlaufen | W22 | 20 | 27 | nur neu gezählt: T2 1d (7 P) |
| Überblick: Punkte pro Prüfung | Arrays / Listen durchlaufen | S26 | 30 | 34 | nur neu gezählt: T2 2ba (4 P) |
| Überblick: Punkte pro Prüfung | Testen | S23 | 16 | 10 | nur alt gezählt: T1 1e (4 P), T2 3aa (2 P); nur neu gezählt: T2 3a (Datenverlaufstabelle) (0 P) |
| Überblick: Punkte pro Prüfung | Klassendiagramm | S22 | 13 | 17 | nur neu gezählt: T1 3bc (4 P) |
| Überblick: Punkte pro Prüfung | Klassendiagramm | S24 | 5 | 0 | nur alt gezählt: T1 3ba (5 P) |
| Überblick: Punkte pro Prüfung | JSON / XML | W25 | 15 | 6 | nur alt gezählt: T1 2ba (3 P), T1 2bb (6 P) |
| Überblick: Punkte pro Prüfung | OOP / Entwurfsmuster | W22 | 10 | 15 | nur neu gezählt: T2 1b (5 P) |
| Überblick: Punkte pro Prüfung | OOP / Entwurfsmuster | S24 | 5 | 17 | nur neu gezählt: T2 2a (12 P) |
| Punkte pro Prüfung nach Block | Algorithmen & Programmierung | W21 | 37 | 35 | nur alt gezählt: T1 3c (2 P) |
| Punkte pro Prüfung nach Block | Algorithmen & Programmierung | S22 | 38 | 34 | nur alt gezählt: T2 2bb (4 P) |
| Punkte pro Prüfung nach Block | UML-Modellierung | S22 | 22 | 26 | nur neu gezählt: T1 3bc (4 P) |
| Punkte pro Prüfung nach Block | UML-Modellierung | S23 | 49 | 44 | nur alt gezählt: T1 2bc (5 P) |
| Punkte pro Prüfung nach Block | UML-Modellierung | S24 | 44 | 39 | nur alt gezählt: T1 3ba (5 P) |
| Punkte pro Prüfung nach Block | Testen & Qualitätssicherung | W22 | 17 | 11 | nur alt gezählt: T1 3cc (6 P) |
| Punkte pro Prüfung nach Block | Objektorientierung & Entwurfsmuster | W22 | 10 | 15 | nur neu gezählt: T2 1b (5 P) |
| Punkte pro Prüfung nach Block | Objektorientierung & Entwurfsmuster | S24 | 5 | 17 | nur neu gezählt: T2 2a (12 P) |

### Korrigierte Textaussagen

- **Algorithmusaufgabe in Teil 2** („Aufgabe 1 oder 2 ist immer ein großer Algorithmus … 25–30 Punkte, meist ohne Teilaufgaben“): richtig sind **20–30 Punkte**. S24 Teil 2, Aufgabe 2 hat nur 20 Punkte (2a 12, 2b 8). Ohne Teilaufgaben waren W21, S22, S23, W23 und W24 (5 von 9), mit Teilaufgaben W22, S24, W25 und S26.
- **Fazit Nr. 2** („Testen kam in den letzten beiden Sommerprüfungen nicht vor (W24, S26)“): richtig ist: **Testen fehlte in W24 und S26.** W24 ist eine Winterprüfung; S24 (Sommer) enthielt Testen (Teil 2, Aufgabe 3, 25 Punkte), ebenso W25.
- **Block 5, Testen & QS, Spalte „Wo“** („Teil 1 (7×) + Teil 2 (6×)“): richtig ist **Teil 1 (6×) + Teil 2 (6×)**. Die 7. Prüfung mit Teil 1 war W22 Teil 1, Aufgabe 3cc (XML-Fehler finden), die dort als Code-Analyse gezählt wurde.
- **Block 2, Algorithmen, Spalte „Wo“** („Teil 2 (9×), Teil 1 (5×)“): richtig ist **Teil 2 (9×), Teil 1 (4×)** (S22 3cb, S23 2bc/2bd, S24 3ba–3bc, W24 2c). Die 5. Prüfung war W21 Teil 1, Aufgabe 3c (HTTP-404-Ursache), die als Fehlerbehandlung gezählt wurde.
- **Block 8, OOP & Entwurfsmuster, Spalte „Wo“** („Teil 1 (4×) + Teil 2 (2×)“): richtig ist **Teil 1 (4×) + Teil 2 (3×)**; die dritte Prüfung mit Teil 2 ist S24 (Aufgabe 2a, Vergleichsfunktion als Parameter). Das Vorkommen bleibt 6/9, OOP-Konzepte bleiben 5/9 (S22, W22, S23, S24, S26).
- **SQL: SELECT mit JOIN** („‚Auch X ohne Y anzeigen‘ heißt LEFT JOIN (5 Mal: S22, W22, S23, W23, S26)“): richtig sind **4 Mal (S22, W22, S23, S26)**. In W23 Teil 2, Aufgabe 4d nutzt die Musterlösung eine korrelierte Unterabfrage; LEFT JOIN ist dort nur eine genannte Alternative.

### Neu gerechnete Ø-Punkte je Block

Formel: Summe der Punkte aller dem Block zugeordneten Teilaufgaben über die 9 Prüfungen, geteilt durch 9 (von 200 Punkten); eine Teilaufgabe zählt pro Block höchstens einmal, nur sichere Belege. Die alten Ø-Werte der Blöcke, die nicht in jeder Prüfung vorkommen, waren Mittelwerte über die Prüfungen mit Vorkommen und sind dadurch höher.

| Block | alt (Ø von 200) | neu (Ø von 200) | Summe über 9 Prüfungen |
|---|---:|---:|---:|
| 01 Datenbanken | 54 | 54 | 487 |
| 02 Algorithmen | 42 | 41 | 369 |
| 03 UML | 38 | 37 | 333 |
| 04 Projektmanagement | 24 | 21 | 189 |
| 05 Testen & QS | 21 | 18 | 164 |
| 06 Schnittstellen | 15 | 13 | 121 |
| 07 IT-Sicherheit | 12 | 10 | 93 |
| 08 OOP & Entwurfsmuster | 16 | 12 | 110 |
| 09 Benutzeroberfläche | 12 | 7 | 62 |
| 10 Netzwerk/IoT | 13 | 6 | 50 |
| 11 Werkzeuge | 21 | 5 | 41 |

### Geprüft und verworfen

Die Zuordnung von W25 Teil 1, Aufgabe 3aa/3ab (Transportverschlüsselung, hybrides Verfahren; 4 Punkte) zu „Schnittstellen & Datenformate“ wurde geprüft und verworfen. Die Aufgaben gehören zu IT-Sicherheit. Block 6 bleibt in W25 bei 24 Punkten wie in der Tabelle oben.
