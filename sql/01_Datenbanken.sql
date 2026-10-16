-- =====================================================================
-- Block 01 · Datenbanken – Beispieldatenbank "VeloManager" (VeloPunkt GmbH)
-- Belegsatz, Anlage 2 und Anlage 3
-- Kompatibel mit MySQL 8 / MariaDB und SQLite 3
--   MySQL:  CREATE DATABASE velomanager; USE velomanager; SOURCE 01_Datenbanken.sql;
--   SQLite: sqlite3 velomanager.db < 01_Datenbanken.sql
-- Hinweis SQLite: Fremdschluessel werden nur mit "PRAGMA foreign_keys = ON;" geprueft.
-- =====================================================================

DROP TABLE IF EXISTS Fahrrad_Archiv;
DROP TABLE IF EXISTS Ausleihe_Archiv;
DROP TABLE IF EXISTS Wartung;
DROP TABLE IF EXISTS Ausleihe;
DROP TABLE IF EXISTS Kunde;
DROP TABLE IF EXISTS Fahrrad;
DROP TABLE IF EXISTS Kategorie;
DROP TABLE IF EXISTS Station;

CREATE TABLE Station (
  StationNr   INTEGER      NOT NULL,
  Name        VARCHAR(40)  NOT NULL,
  PLZ         CHAR(5)      NOT NULL,
  Ort         VARCHAR(40)  NOT NULL,
  PRIMARY KEY (StationNr)
);

CREATE TABLE Kategorie (
  KategorieNr INTEGER      NOT NULL,
  Bezeichnung VARCHAR(30)  NOT NULL,
  Tagespreis  DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (KategorieNr)
);

CREATE TABLE Fahrrad (
  FahrradNr    INTEGER      NOT NULL,
  Rahmennummer VARCHAR(20)  NOT NULL,
  Modell       VARCHAR(30)  NOT NULL,
  KategorieNr  INTEGER      NOT NULL,
  StationNr    INTEGER      NOT NULL,
  Kaufdatum    DATE         NOT NULL,
  Kaufpreis    DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (FahrradNr),
  FOREIGN KEY (KategorieNr) REFERENCES Kategorie (KategorieNr),
  FOREIGN KEY (StationNr)   REFERENCES Station (StationNr)
);

CREATE TABLE Kunde (
  KundenNr  INTEGER     NOT NULL,
  Vorname   VARCHAR(30) NOT NULL,
  Nachname  VARCHAR(40) NOT NULL,
  PLZ       CHAR(5)     NOT NULL,
  Ort       VARCHAR(40) NOT NULL,
  Email     VARCHAR(80),
  PRIMARY KEY (KundenNr)
);

CREATE TABLE Ausleihe (
  AusleiheNr INTEGER      NOT NULL,
  KundenNr   INTEGER      NOT NULL,
  FahrradNr  INTEGER      NOT NULL,
  Beginn     DATE         NOT NULL,
  Ende       DATE,
  Kilometer  INTEGER,
  Betrag     DECIMAL(8,2),
  PRIMARY KEY (AusleiheNr),
  FOREIGN KEY (KundenNr)  REFERENCES Kunde (KundenNr),
  FOREIGN KEY (FahrradNr) REFERENCES Fahrrad (FahrradNr)
);

CREATE TABLE Wartung (
  WartungNr INTEGER      NOT NULL,
  FahrradNr INTEGER      NOT NULL,
  Datum     DATE         NOT NULL,
  Art       VARCHAR(30)  NOT NULL,
  Kosten    DECIMAL(8,2),
  PRIMARY KEY (WartungNr),
  FOREIGN KEY (FahrradNr) REFERENCES Fahrrad (FahrradNr)
);

-- Archivtabellen: gleiche bzw. vereinfachte Struktur, bewusst OHNE Fremdschluessel
CREATE TABLE Ausleihe_Archiv (
  AusleiheNr INTEGER      NOT NULL,
  KundenNr   INTEGER      NOT NULL,
  FahrradNr  INTEGER      NOT NULL,
  Beginn     DATE         NOT NULL,
  Ende       DATE,
  Kilometer  INTEGER,
  Betrag     DECIMAL(8,2),
  PRIMARY KEY (AusleiheNr)
);

CREATE TABLE Fahrrad_Archiv (
  FahrradNr    INTEGER     NOT NULL,
  Rahmennummer VARCHAR(20) NOT NULL,
  Modell       VARCHAR(30) NOT NULL,
  Kategorie    VARCHAR(30) NOT NULL,
  Kaufdatum    DATE        NOT NULL,
  Ausmusterung DATE        NOT NULL,
  GesamtKm     INTEGER,
  PRIMARY KEY (FahrradNr)
);

-- ---------------------------------------------------------------------
-- Daten
-- ---------------------------------------------------------------------
INSERT INTO Station (StationNr, Name, PLZ, Ort) VALUES
  (1, 'Hauptbahnhof', '79098', 'Freiburg'),
  (2, 'Messe',        '79108', 'Freiburg'),
  (3, 'Titisee',      '79822', 'Titisee-Neustadt'),
  (4, 'Kaiserstuhl',  '79235', 'Vogtsburg'),
  (5, 'Schauinsland', '79254', 'Oberried');

INSERT INTO Kategorie (KategorieNr, Bezeichnung, Tagespreis) VALUES
  (1, 'City-E-Bike',     25.00),
  (2, 'Trekking-E-Bike', 30.00),
  (3, 'E-Mountainbike',  39.00),
  (4, 'E-Lastenrad',     45.00),
  (5, 'Kinderrad',       12.00),
  (6, 'Tandem',          35.00);

INSERT INTO Fahrrad (FahrradNr, Rahmennummer, Modell, KategorieNr, StationNr, Kaufdatum, Kaufpreis) VALUES
  (101, 'WAW-23-0017', 'Urban C5', 1, 1, '2023-03-15', 2499.00),
  (102, 'WAW-23-0018', 'Urban C5', 1, 1, '2023-03-15', 2499.00),
  (103, 'KTX-24-1120', 'Tour T7',  2, 2, '2024-04-02', 3199.00),
  (104, 'KTX-24-1121', 'Tour T7',  2, 3, '2024-04-02', 3199.00),
  (105, 'MTN-22-0456', 'Trail X9', 3, 3, '2022-05-20', 4290.00),
  (106, 'MTN-25-0811', 'Trail X9', 3, 4, '2025-03-10', 4490.00),
  (107, 'CRG-24-0033', 'Cargo L2', 4, 1, '2024-06-12', 5390.00),
  (108, 'CRG-25-0090', 'Cargo L2', 4, 2, '2025-04-28', 5590.00),
  (109, 'KID-23-0301', 'Kiddy 20', 5, 3, '2023-05-02',  649.00),
  (110, 'WAW-26-0002', 'Urban C6', 1, 4, '2026-02-20', 2799.00);

INSERT INTO Kunde (KundenNr, Vorname, Nachname, PLZ, Ort, Email) VALUES
  (1001, 'Lena',   'Hoffmann', '79098', 'Freiburg',         'lena.hoffmann@mail.de'),
  (1002, 'Jonas',  'Albrecht', '79102', 'Freiburg',         'j.albrecht@web.de'),
  (1003, 'Mira',   'Schulze',  '79211', 'Denzlingen',       NULL),
  (1004, 'Paul',   'Wagner',   '79822', 'Titisee-Neustadt', 'paul.wagner@gmx.de'),
  (1005, 'Sofia',  'Keller',   '79100', 'Freiburg',         's.keller@posteo.de'),
  (1006, 'Tim',    'Brandt',   '77933', 'Lahr',             'tim.brandt@mail.de'),
  (1007, 'Emma',   'Vogel',    '79098', 'Freiburg',         NULL),
  (1008, 'David',  'Roth',     '01067', 'Dresden',          'd.roth@web.de'),
  (1009, 'Hannah', 'Krüger',   '79194', 'Gundelfingen',     'hannah.k@gmx.de'),
  (1010, 'Felix',  'Neumann',  '79108', 'Freiburg',         'f.neumann@mail.de');

INSERT INTO Ausleihe (AusleiheNr, KundenNr, FahrradNr, Beginn, Ende, Kilometer, Betrag) VALUES
  (5001, 1001, 101, '2025-08-02', '2025-08-03',   64,  50.00),
  (5002, 1002, 105, '2025-09-13', '2025-09-13',   38,  39.00),
  (5003, 1004, 107, '2025-10-04', '2025-10-06',   41, 135.00),
  (5004, 1001, 103, '2026-03-21', '2026-03-22',   92,  60.00),
  (5005, 1003, 109, '2026-04-11', '2026-04-11',   12,  12.00),
  (5006, 1005, 107, '2026-05-01', '2026-05-03',   57, 135.00),
  (5007, 1002, 106, '2026-05-30', '2026-06-01',  118, 117.00),
  (5008, 1006, 104, '2026-06-14', '2026-06-20',  315, 210.00),
  (5009, 1001, 108, '2026-07-18', '2026-07-18',   23,  45.00),
  (5010, 1006, 105, '2026-08-08', '2026-08-09',   76,  78.00),
  (5011, 1007, 102, '2026-08-22', '2026-08-24',   49,  75.00),
  (5012, 1005, 103, '2026-10-08', NULL,         NULL,   NULL);

INSERT INTO Wartung (WartungNr, FahrradNr, Datum, Art, Kosten) VALUES
  (1, 101, '2025-11-20', 'Inspektion',  89.00),
  (2, 105, '2025-11-25', 'Bremsen',    145.50),
  (3, 107, '2026-01-14', 'Inspektion', 119.00),
  (4, 103, '2026-02-03', 'Inspektion',  89.00),
  (5, 105, '2026-04-20', 'Akku-Check',  35.00),
  (6, 109, '2026-05-07', 'Reifen',      42.90),
  (7, 107, '2026-07-01', 'Bremsen',    168.00),
  (8, 104, '2026-09-15', 'Reifen',      55.00),
  (9, 105, '2026-09-30', 'Inspektion',   NULL);

INSERT INTO Ausleihe_Archiv (AusleiheNr, KundenNr, FahrradNr, Beginn, Ende, Kilometer, Betrag) VALUES
  (4001, 1001, 101, '2024-05-10', '2024-05-11',  55, 50.00),
  (4002, 1009, 105, '2024-06-22', '2024-06-23',  81, 78.00),
  (4003, 1002, 102, '2024-07-06', '2024-07-06',  31, 25.00),
  (4004, 1004, 109, '2024-08-15', '2024-08-17',  20, 36.00),
  (4005, 1009, 107, '2024-09-28', '2024-09-28',  18, 45.00),
  (4006, 1006, 103, '2024-10-12', '2024-10-13', 104, 60.00),
  (4007, 1003, 104, '2025-04-19', '2025-04-20',  66, 60.00),
  (4008, 1001, 101, '2025-06-07', '2025-06-08',  64, 50.00);

INSERT INTO Fahrrad_Archiv (FahrradNr, Rahmennummer, Modell, Kategorie, Kaufdatum, Ausmusterung, GesamtKm) VALUES
  (95, 'WAW-21-0004', 'Urban C4', 'City-E-Bike',    '2021-04-01', '2025-12-31', 4120),
  (98, 'MTN-21-0102', 'Trail X8', 'E-Mountainbike', '2021-06-15', '2026-01-15', 2875);
