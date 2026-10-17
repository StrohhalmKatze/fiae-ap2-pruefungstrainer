/* Übungen AP2 – Darkmode-Umschalter und digitale Antwortfelder.
   Antworten werden im Browser gespeichert (Text: localStorage, Bilder: IndexedDB).
   Sicherung über „Exportieren“ (JSON-Datei), Wiederherstellen über „Importieren“. */
(function () {
  "use strict";

  var THEME_KEY = "ap2-theme";
  var PREFIX = "ap2:";
  var root = document.documentElement;

  function lsGet(k) { try { return localStorage.getItem(k); } catch (e) { return null; } }
  function lsSet(k, v) { try { localStorage.setItem(k, v); return true; } catch (e) { return false; } }
  function lsDel(k) { try { localStorage.removeItem(k); } catch (e) { /* nichts */ } }
  function lsKeys() {
    var out = [];
    try { for (var i = 0; i < localStorage.length; i++) out.push(localStorage.key(i)); } catch (e) { /* nichts */ }
    return out;
  }

  /* ---------- Darkmode ---------- */
  var mq = window.matchMedia ? window.matchMedia("(prefers-color-scheme: dark)") : null;
  function urlTheme() { var m = /[?&]theme=(dark|light)/.exec(location.search); return m ? m[1] : null; }
  function currentTheme() { return urlTheme() || lsGet(THEME_KEY) || (mq && mq.matches ? "dark" : "light"); }
  root.setAttribute("data-theme", currentTheme());   // sofort setzen, damit nichts hell aufblitzt

  var themeBtn = null;
  function applyTheme() { root.setAttribute("data-theme", currentTheme()); updateThemeBtn(); }
  function updateThemeBtn() {
    if (!themeBtn) return;
    var dark = root.getAttribute("data-theme") === "dark";
    themeBtn.textContent = dark ? "☀ Hell" : "☾ Dunkel";
    themeBtn.title = dark ? "Zum hellen Design wechseln" : "Zum dunklen Design wechseln";
  }
  if (mq) {
    var onSys = function () { if (!lsGet(THEME_KEY)) applyTheme(); };
    if (mq.addEventListener) mq.addEventListener("change", onSys); else if (mq.addListener) mq.addListener(onSys);
  }

  /* ---------- Hilfsfunktionen ---------- */
  function fileName() {
    var p = decodeURIComponent(location.pathname);
    return p.substring(p.lastIndexOf("/") + 1).replace(/\.html?$/i, "");
  }
  var FILE = fileName();

  function el(tag, cls, text) {
    var e = document.createElement(tag);
    if (cls) e.className = cls;
    if (text != null) e.textContent = text;
    return e;
  }

  function containerKey(node) {
    var a = node.closest(".aufgabe");
    if (a) return a.id || "aufgabe";
    return "frei";
  }

  var statusEl = null, statusTimer = null;
  function zeigeStatus(text) {
    if (!statusEl) return;
    statusEl.textContent = text;
    clearTimeout(statusTimer);
    statusTimer = setTimeout(function () { statusEl.textContent = "Antworten werden im Browser gespeichert"; }, 2500);
  }
  function gespeichert() {
    var d = new Date();
    zeigeStatus("✓ gespeichert " + String(d.getHours()).padStart(2, "0") + ":" + String(d.getMinutes()).padStart(2, "0"));
  }

  /* ---------- Bildspeicher (IndexedDB, Rückfall localStorage) ---------- */
  var dbPromise = null;
  function db() {
    if (dbPromise) return dbPromise;
    dbPromise = new Promise(function (res, rej) {
      try {
        var r = indexedDB.open("ap2-uebungen", 1);
        r.onupgradeneeded = function () { r.result.createObjectStore("bilder"); };
        r.onsuccess = function () { res(r.result); };
        r.onerror = function () { rej(r.error); };
      } catch (e) { rej(e); }
    });
    return dbPromise;
  }
  function idbReq(mode, fn) {
    return db().then(function (d) {
      return new Promise(function (res, rej) {
        var tx = d.transaction("bilder", mode);
        var req = fn(tx.objectStore("bilder"));
        tx.oncomplete = function () { res(req ? req.result : undefined); };
        tx.onerror = function () { rej(tx.error); };
        tx.onabort = function () { rej(tx.error); };
      });
    });
  }
  var bilder = {
    get: function (k) {
      return idbReq("readonly", function (s) { return s.get(k); })
        .catch(function () { var v = lsGet(k); return v ? JSON.parse(v) : undefined; });
    },
    set: function (k, v) {
      return idbReq("readwrite", function (s) { return s.put(v, k); })
        .catch(function () { if (!lsSet(k, JSON.stringify(v))) throw new Error("Speicher voll"); });
    },
    del: function (k) {
      return idbReq("readwrite", function (s) { return s.delete(k); }).catch(function () { lsDel(k); });
    },
    alle: function () {
      var out = {};
      return idbReq("readonly", function (s) { return s.getAllKeys(); })
        .then(function (keys) {
          return Promise.all((keys || []).map(function (k) {
            return bilder.get(k).then(function (v) { out[k] = v; });
          }));
        })
        .catch(function () { /* nur localStorage-Rückfall */ })
        .then(function () { return out; });
    }
  };

  /* ---------- Textfelder ---------- */
  var CODE_HINT = /pseudocode|sql|java|quelltext|code|formel|json|xml|skript|methode|funktion|befehl|abfrage|anweisung|yaml|dtd|xsd|struktogramm/i;

  function autosize(ta, minH) {
    ta.style.height = "auto";
    ta.style.height = Math.max(minH, ta.scrollHeight) + "px";
  }

  function setupTextfelder() {
    var counters = {};
    var felder = document.querySelectorAll(".antwort");
    felder.forEach(function (div) {
      var ck = containerKey(div);
      counters[ck] = (counters[ck] || 0) + 1;
      var key = PREFIX + FILE + ":" + ck + ":t" + counters[ck];
      var minH = Math.max(40, Math.round(div.getBoundingClientRect().height) - 2);

      var ta = el("textarea", "ap2-eingabe");
      ta.setAttribute("spellcheck", "false");
      ta.setAttribute("aria-label", "Antwort");
      ta.value = lsGet(key) || "";

      var kontext = "";
      var prev = div.previousElementSibling;
      if (prev) kontext = prev.textContent;
      var mono = lsGet(key + ":mono");
      var istCode = mono != null ? mono === "1" : CODE_HINT.test(kontext);
      if (istCode) ta.classList.add("ap2-code");

      var monoBtn = el("button", "ap2-mono no-print", istCode ? "</>" : "Aa");
      monoBtn.type = "button";
      monoBtn.title = "Schriftart umschalten (Fließtext / Code)";
      monoBtn.addEventListener("click", function () {
        var on = !ta.classList.contains("ap2-code");
        ta.classList.toggle("ap2-code", on);
        monoBtn.textContent = on ? "</>" : "Aa";
        lsSet(key + ":mono", on ? "1" : "0");
        autosize(ta, minH);
        ta.focus();
      });

      div.classList.add("ap2-aktiv");
      div.style.minHeight = (minH + 2) + "px";
      div.appendChild(ta);
      div.appendChild(monoBtn);
      if (ta.value === "") div.classList.add("ap2-leer");
      autosize(ta, minH);

      var t = null;
      ta.addEventListener("input", function () {
        autosize(ta, minH);
        div.classList.toggle("ap2-leer", ta.value === "");
        clearTimeout(t);
        t = setTimeout(function () {
          if (ta.value === "") lsDel(key); else if (!lsSet(key, ta.value)) { zeigeStatus("⚠ Speichern fehlgeschlagen"); return; }
          gespeichert();
        }, 300);
      });
      ta.addEventListener("keydown", function (e) {
        if (e.key === "Tab" && !e.shiftKey && !e.ctrlKey && !e.altKey && ta.classList.contains("ap2-code")) {
          e.preventDefault();
          var s = ta.selectionStart, en = ta.selectionEnd;
          ta.value = ta.value.slice(0, s) + "    " + ta.value.slice(en);
          ta.selectionStart = ta.selectionEnd = s + 4;
          ta.dispatchEvent(new Event("input"));
        }
      });
    });
    window.addEventListener("beforeprint", function () {
      document.querySelectorAll("textarea.ap2-eingabe").forEach(function (ta) {
        ta.style.height = "auto"; ta.style.height = ta.scrollHeight + "px";
      });
    });
    return felder.length;
  }

  /* ---------- Tabellenzellen und Lücken ---------- */
  function setupZellen() {
    var counters = {};
    var n = 0;
    document.querySelectorAll(".aufgabe td, .aufgabe .luecke").forEach(function (cell) {
      if (cell.textContent.trim() !== "" || cell.querySelector("img,svg,input,textarea")) return;
      var ck = containerKey(cell);
      counters[ck] = (counters[ck] || 0) + 1;
      var key = PREFIX + FILE + ":" + ck + ":z" + counters[ck];
      cell.setAttribute("contenteditable", "true");
      cell.classList.add("ap2-zelle");
      var v = lsGet(key);
      if (v) cell.textContent = v;
      var t = null;
      cell.addEventListener("input", function () {
        clearTimeout(t);
        t = setTimeout(function () {
          var txt = cell.innerText.replace(/\n$/, "");
          if (txt.trim() === "") { lsDel(key); cell.textContent = ""; } else lsSet(key, txt);
          gespeichert();
        }, 300);
      });
      cell.addEventListener("paste", function (e) {
        e.preventDefault();
        var text = (e.clipboardData || window.clipboardData).getData("text");
        document.execCommand("insertText", false, text);
      });
      n++;
    });
    return n;
  }

  /* ---------- Bildzonen (Zeichenflächen) ---------- */
  var aktiveZone = null;

  function bildVerkleinern(file) {
    return new Promise(function (res, rej) {
      var fr = new FileReader();
      fr.onerror = function () { rej(fr.error); };
      fr.onload = function () {
        var img = new Image();
        img.onerror = function () { rej(new Error("Kein Bild")); };
        img.onload = function () {
          var max = 1600, w = img.naturalWidth, h = img.naturalHeight, s = Math.min(1, max / w);
          if (s === 1 && fr.result.length < 1500000) { res(fr.result); return; }
          var c = document.createElement("canvas");
          c.width = Math.round(w * s); c.height = Math.round(h * s);
          var g = c.getContext("2d");
          g.fillStyle = "#fff"; g.fillRect(0, 0, c.width, c.height);
          g.drawImage(img, 0, 0, c.width, c.height);
          res(c.toDataURL("image/jpeg", 0.85));
        };
        img.src = fr.result;
      };
      fr.readAsDataURL(file);
    });
  }

  function richteZoneEin(zone, key) {
    zone.classList.add("ap2-bildzone");
    zone.setAttribute("tabindex", "0");
    var hinweis = el("div", "ap2-hinweis no-print");
    hinweis.appendChild(document.createTextNode("Bild einfügen: hier klicken und Strg+V drücken, Bilddatei hierher ziehen oder "));
    var wahl = el("button", "ap2-wahl", "Datei wählen");
    wahl.type = "button";
    hinweis.appendChild(wahl);
    var input = el("input");
    input.type = "file"; input.accept = "image/*"; input.multiple = true; input.hidden = true;
    var liste = el("div", "ap2-bilder");
    zone.appendChild(hinweis); zone.appendChild(input); zone.appendChild(liste);

    var daten = [];
    function zeichne() {
      liste.innerHTML = "";
      daten.forEach(function (src, i) {
        var fig = el("div", "ap2-bild");
        var img = el("img"); img.src = src; img.alt = "Eingefügtes Bild " + (i + 1);
        var entf = el("button", "ap2-bild-entf no-print", "Bild entfernen");
        entf.type = "button";
        entf.addEventListener("click", function (e) {
          e.stopPropagation();
          if (!confirm("Dieses Bild entfernen?")) return;
          daten.splice(i, 1); speichere();
        });
        fig.appendChild(img); fig.appendChild(entf); liste.appendChild(fig);
      });
      zone.classList.toggle("ap2-mit-bild", daten.length > 0);
      zone.classList.toggle("ap2-leer", daten.length === 0);
    }
    function speichere() {
      zeichne();
      var p = daten.length ? bilder.set(key, daten.slice()) : bilder.del(key);
      p.then(gespeichert).catch(function () { zeigeStatus("⚠ Bild konnte nicht gespeichert werden"); });
    }
    function fuegeHinzu(files) {
      var imgs = Array.prototype.filter.call(files || [], function (f) { return /^image\//.test(f.type); });
      if (!imgs.length) return;
      Promise.all(imgs.map(bildVerkleinern)).then(function (urls) {
        daten = daten.concat(urls); speichere();
      }).catch(function () { zeigeStatus("⚠ Bild konnte nicht gelesen werden"); });
    }
    zone._ap2Add = fuegeHinzu;

    wahl.addEventListener("click", function (e) { e.stopPropagation(); input.click(); });
    input.addEventListener("change", function () { fuegeHinzu(input.files); input.value = ""; });
    zone.addEventListener("click", function () { aktiveZone = zone; zone.focus(); });
    zone.addEventListener("focus", function () { aktiveZone = zone; });
    zone.addEventListener("dragover", function (e) { e.preventDefault(); zone.classList.add("ap2-ziehen"); });
    zone.addEventListener("dragleave", function () { zone.classList.remove("ap2-ziehen"); });
    zone.addEventListener("drop", function (e) {
      e.preventDefault(); zone.classList.remove("ap2-ziehen");
      fuegeHinzu(e.dataTransfer && e.dataTransfer.files);
    });

    bilder.get(key).then(function (v) { daten = Array.isArray(v) ? v : []; zeichne(); })
      .catch(function () { zeichne(); });
  }

  function setupBildzonen() {
    var counters = {};
    var zonen = [];
    document.querySelectorAll(".zeichenflaeche, .aufgabe .zeichnen").forEach(function (z) {
      if (z.classList.contains("zeichnen")) {
        var extra = el("div", "ap2-extra");
        z.appendChild(extra);
        zonen.push(extra);
      } else zonen.push(z);
    });
    // Zusätzliche Bildfelder: pro Teilaufgabe, die ausdrücklich zum Zeichnen auffordert,
    // aber keine eigene Zeichenfläche hat. Das Feld kommt hinter die zugehörige Vorlage
    // (Abbildung/Rahmen) bzw. ans Ende dieser Teilaufgabe.
    var ZEICHNEN = new RegExp(
      "(^|[^a-zäöüß])(zeichnen|skizzieren)(?![a-zäöüß])" +
      "|beschriften sie" +
      "|(ergänz|erstell|erweiter|vervollständig|trag|stell)\\w*\\s+sie\\b[^.]{0,90}" +
      "(diagramm|netzplan|mockup|datenmodell|relationenmodell|er-modell|graphen|grafik|wireframe|vorlage|struktogramm|programmablaufplan|skizze|entwurf)", "i");
    var hatFlaeche = function (n) {
      return n.matches(".zeichenflaeche, .zeichnen, .ap2-extra") || !!n.querySelector(".zeichenflaeche, .zeichnen, .ap2-extra");
    };
    var istTeil = function (n) { return n.matches(".teilaufgabe") || !!n.querySelector(".teilaufgabe"); };
    var hatVorlage = function (n) { return n.matches("figure, svg") || !!n.querySelector("svg"); };
    var extras = [];
    document.querySelectorAll(".aufgabe").forEach(function (a) {
      var kinder = Array.prototype.filter.call(a.children, function (n) { return !n.matches(".kopf, .quelle"); });
      var teile = kinder.filter(istTeil);
      var abschnitte = [];
      if (teile.length === 0) abschnitte.push({ start: null, rest: kinder });
      else teile.forEach(function (t, i) {
        var von = kinder.indexOf(t) + 1;
        var bis = i + 1 < teile.length ? kinder.indexOf(teile[i + 1]) : kinder.length;
        abschnitte.push({ start: t, rest: kinder.slice(von, bis) });
      });
      abschnitte.forEach(function (ab) {
        var teilEl = ab.start ? (ab.start.matches(".teilaufgabe") ? ab.start : ab.start.querySelector(".teilaufgabe")) : null;
        var text = (teilEl ? teilEl.textContent : "") + " " +
          ab.rest.filter(function (n) { return n.matches("p, ul, ol"); }).map(function (n) { return n.textContent; }).join(" ");
        if (!ZEICHNEN.test(text.replace(/\s+/g, " "))) return;
        if ((ab.start && hatFlaeche(ab.start)) || ab.rest.some(hatFlaeche)) return;
        var nach = null;
        if (ab.start && hatVorlage(ab.start)) nach = ab.start;
        ab.rest.forEach(function (n) { if (hatVorlage(n)) nach = n; });
        if (!nach) nach = ab.rest.length ? ab.rest[ab.rest.length - 1] : (ab.start || kinder[kinder.length - 1]);
        if (!nach) return;
        var extra = el("div", "ap2-extra");
        nach.parentNode.insertBefore(extra, nach.nextSibling);
        extras.push(extra);
      });
    });
    zonen = zonen.concat(extras);
    zonen.forEach(function (z) {
      var ck = containerKey(z);
      counters[ck] = (counters[ck] || 0) + 1;
      richteZoneEin(z, PREFIX + FILE + ":" + ck + ":b" + counters[ck]);
    });
    document.addEventListener("paste", function (e) {
      if (!aktiveZone || !document.contains(aktiveZone)) return;
      var t = e.target;
      if (t && (t.tagName === "TEXTAREA" || t.isContentEditable)) return;
      var items = (e.clipboardData && e.clipboardData.files) || [];
      if (items.length) { e.preventDefault(); aktiveZone._ap2Add(items); }
    });
    return zonen.length;
  }

  /* ---------- Export / Import / Löschen ---------- */
  function heute() { return new Date().toISOString().slice(0, 10); }

  function exportieren() {
    var data = { format: "ap2-antworten", version: 1, exportiert: new Date().toISOString(), texte: {}, bilder: {} };
    lsKeys().forEach(function (k) { if (k && k.indexOf(PREFIX) === 0) data.texte[k] = lsGet(k); });
    bilder.alle().then(function (b) {
      Object.keys(b).forEach(function (k) { if (k.indexOf(PREFIX) === 0) data.bilder[k] = b[k]; });
      var blob = new Blob([JSON.stringify(data)], { type: "application/json" });
      var a = el("a");
      a.href = URL.createObjectURL(blob);
      a.download = "AP2-Antworten-" + heute() + ".json";
      document.body.appendChild(a); a.click();
      setTimeout(function () { URL.revokeObjectURL(a.href); a.remove(); }, 1000);
      zeigeStatus("✓ " + Object.keys(data.texte).length + " Texte, " + Object.keys(data.bilder).length + " Bildfelder exportiert");
    });
  }

  function importieren(file) {
    var fr = new FileReader();
    fr.onload = function () {
      var data;
      try { data = JSON.parse(fr.result); } catch (e) { alert("Die Datei ist keine gültige Antwort-Sicherung."); return; }
      if (!data || data.format !== "ap2-antworten") { alert("Die Datei ist keine gültige Antwort-Sicherung."); return; }
      var nT = 0, jobs = [];
      Object.keys(data.texte || {}).forEach(function (k) { if (k.indexOf(PREFIX) === 0 && lsSet(k, data.texte[k])) nT++; });
      Object.keys(data.bilder || {}).forEach(function (k) { if (k.indexOf(PREFIX) === 0) jobs.push(bilder.set(k, data.bilder[k])); });
      Promise.all(jobs).then(function () {
        alert(nT + " Texte und " + jobs.length + " Bildfelder importiert. Die Seite wird neu geladen.");
        location.reload();
      });
    };
    fr.readAsText(file);
  }

  function loescheDatei() {
    if (!confirm("Alle Antworten in dieser Datei (" + FILE + ") löschen?\nTipp: vorher „Exportieren“ als Sicherung.")) return;
    var p = PREFIX + FILE + ":";
    lsKeys().forEach(function (k) { if (k && k.indexOf(p) === 0) lsDel(k); });
    bilder.alle().then(function (b) {
      return Promise.all(Object.keys(b).filter(function (k) { return k.indexOf(p) === 0; }).map(bilder.del));
    }).then(function () { location.reload(); });
  }

  /* ---------- Leiste ---------- */
  function baueLeiste(mitAntworten) {
    var bar = el("div", "ap2-leiste no-print");
    if (FILE && FILE !== "index") {
      var start = el("a", "ap2-start");
      start.href = "index.html";
      start.title = "Zur Startseite mit allen Blöcken";
      start.appendChild(document.createTextNode("⌂"));
      start.appendChild(el("span", "ap2-wort", " Startseite"));
      bar.appendChild(start);
    }
    if (mitAntworten) {
      statusEl = el("span", "ap2-status", "Antworten werden im Browser gespeichert");
      statusEl.title = "Gespeichert wird in diesem Browser auf diesem PC. Zur Sicherung „Exportieren“ nutzen.";
      bar.appendChild(statusEl);
      var ex = el("button", null, "Exportieren"); ex.type = "button";
      ex.title = "Alle gespeicherten Antworten (alle Blöcke) als Datei sichern";
      ex.addEventListener("click", exportieren);
      var imIn = el("input"); imIn.type = "file"; imIn.accept = ".json,application/json"; imIn.hidden = true;
      imIn.addEventListener("change", function () { if (imIn.files[0]) importieren(imIn.files[0]); imIn.value = ""; });
      var im = el("button", null, "Importieren"); im.type = "button";
      im.title = "Gesicherte Antworten aus einer Datei wiederherstellen";
      im.addEventListener("click", function () { imIn.click(); });
      var del = el("button", null, "Leeren"); del.type = "button";
      del.title = "Alle Antworten in dieser Datei löschen";
      del.addEventListener("click", loescheDatei);
      bar.appendChild(ex); bar.appendChild(im); bar.appendChild(imIn); bar.appendChild(del);
    }
    themeBtn = el("button", "ap2-theme"); themeBtn.type = "button";
    themeBtn.addEventListener("click", function () {
      var dark = root.getAttribute("data-theme") === "dark";
      lsSet(THEME_KEY, dark ? "light" : "dark");
      applyTheme();
    });
    bar.appendChild(themeBtn);
    document.body.appendChild(bar);
    document.body.classList.add("ap2-mit-leiste");
    updateThemeBtn();
  }

  /* ---------- Diagramme, die im Darkmode hell bleiben (Mockups) ---------- */
  var HELL = /mockup|wireframe|screenshot|smartphone|bildschirm|benutzeroberfl|registrierungs(seite|formular|maske)|app-ansicht/i;
  function markiereHelleDiagramme() {
    var alle = /^09_/.test(FILE);
    document.querySelectorAll("svg").forEach(function (svg) {
      var text = "";
      var fig = svg.closest("figure");
      if (fig) { var fc = fig.querySelector("figcaption"); if (fc) text = fc.textContent; }
      if (!text) { var an = svg.closest(".anlage"); if (an) { var h = an.querySelector("h4"); if (h) text = h.textContent; } }
      if (alle || HELL.test(text) || svg.hasAttribute("data-hell")) svg.classList.add("ap2-hell");
    });
  }

  /* ---------- Rücksprung zum Inhaltsverzeichnis (nur Seiten mit nav#inhalt) ---------- */
  function ergaenzeInhaltslinks() {
    var nav = document.getElementById("inhalt");
    if (!nav) return;
    nav.querySelectorAll('a[href^="#"]').forEach(function (a) {
      var ziel = document.getElementById(a.getAttribute("href").slice(1));
      if (!ziel || !/^H[23]$/.test(ziel.tagName) || ziel.querySelector(".st-hoch")) return;
      var hoch = el("a", "st-hoch no-print", "↑ Inhalt");
      hoch.href = "#inhalt";
      hoch.title = "Zurück zum Inhaltsverzeichnis";
      ziel.appendChild(hoch);
    });
  }

  /* ---------- Breite Tabellen in einem eigenen, seitlich scrollbaren Bereich ----------
     Sonst wird am Handy die ganze Seite breiter als der Bildschirm (Leiste und Pfeil rutschen raus). */
  // Erklär-Tabellen dürfen am Handy zu Karten werden; Anlagen, Daten-, Tabellenblatt-
  // und Aufgabentabellen bleiben Tabellen wie in der Prüfung.
  function istStapelbar(tab) {
    if (tab.closest(".aufgabe, .anlage")) return false;
    if (/\b(daten|sheet|leer|ankreuz|ausfuellen|eng|syntax)\b/.test(tab.className)) return false;
    if (tab.querySelector("[rowspan], [colspan], table")) return false;
    var zeilen = tab.rows;
    if (zeilen.length < 2 || zeilen[0].cells.length < 3) return false;
    for (var i = 0; i < zeilen[0].cells.length; i++) if (zeilen[0].cells[i].tagName !== "TH") return false;
    return true;
  }
  function beschrifteZellen(tab) {
    var kopf = [].map.call(tab.rows[0].cells, function (c) { return c.textContent.trim(); });
    tab.rows[0].classList.add("ap2-kopfzeile");
    for (var r = 1; r < tab.rows.length; r++) {
      [].forEach.call(tab.rows[r].cells, function (c, i) {
        if (i > 0 && kopf[i] && c.textContent.trim() !== "") c.setAttribute("data-label", kopf[i]);
      });
    }
  }

  // Nur bei schmalen Bildschirmen (Handy): Am PC bleibt der Aufbau unverändert, damit
  // Layout und Druck exakt wie bisher sind. Wird ein Fenster schmal gezogen, folgt der Umbau dann.
  function macheTabellenScrollbar() {
    var schmal = window.matchMedia("(max-width: 560px)");
    if (schmal.matches) packeTabellenEin(schmal);
    else if (schmal.addEventListener) schmal.addEventListener("change", function warte(e) {
      if (!e.matches) return;
      schmal.removeEventListener("change", warte);
      packeTabellenEin(schmal);
    });
  }

  function packeTabellenEin(schmal) {
    var paare = [];
    document.querySelectorAll("table").forEach(function (tab) {
      if (tab.parentElement.classList.contains("ap2-tabelle")) return;
      var huelle = el("div", "ap2-tabelle");
      tab.parentNode.insertBefore(huelle, tab);
      huelle.appendChild(tab);
      var hinweis = el("div", "ap2-wischen no-print", "↔ Tabelle seitlich wischen");
      huelle.parentNode.insertBefore(hinweis, huelle.nextSibling);
      var stapelbar = istStapelbar(tab);
      if (stapelbar) beschrifteZellen(tab);
      paare.push([huelle, hinweis, tab, stapelbar]);
    });
    var pruefe = function () {
      paare.forEach(function (p) {
        if (p[3]) {
          p[2].classList.remove("ap2-gestapelt");
          if (schmal.matches && p[0].scrollWidth > p[0].clientWidth + 1) p[2].classList.add("ap2-gestapelt");
        }
        p[1].hidden = p[0].scrollWidth <= p[0].clientWidth + 1;
      });
    };
    pruefe();
    window.addEventListener("resize", pruefe);
    document.addEventListener("toggle", pruefe, true);   // eingeklappte Bereiche (details) auf der Startseite
  }

  /* ---------- Schwebender Pfeil „Nach oben“ (erscheint nach etwas Scrollen) ---------- */
  function baueNachObenKnopf() {
    var b = el("a", "ap2-nachoben no-print", "↑");
    b.href = "#";
    b.title = "Zum Seitenanfang";
    b.setAttribute("aria-label", "Zum Seitenanfang");
    b.addEventListener("click", function (e) {
      e.preventDefault();
      if (location.hash) history.replaceState(null, "", location.pathname + location.search);
      window.scrollTo(0, 0);
    });
    document.body.appendChild(b);
    var zeigen = function () { b.classList.toggle("ap2-sichtbar", window.scrollY > 600); };
    window.addEventListener("scroll", zeigen, { passive: true });
    zeigen();
  }

  /* ---------- Start ---------- */
  function start() {
    markiereHelleDiagramme();
    ergaenzeInhaltslinks();
    baueNachObenKnopf();
    var n = 0;
    if (document.querySelector(".aufgabe")) {
      n += setupTextfelder();
      n += setupZellen();
      n += setupBildzonen();
    }
    macheTabellenScrollbar();
    baueLeiste(n > 0);
  }
  if (document.readyState === "loading") document.addEventListener("DOMContentLoaded", start);
  else start();
})();
