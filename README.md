# UTM
Theoretische Informatik
## Übung 4 - Turing-Maschinen


### Aufgabe 1. Universelle TM.
Entwickeln Sie (in einer beliebigen Programmiersprache) ein Programm, das eine Universelle
TM emuliert. Das Programm soll die kodierte Funktion einer TM (konkret für die Multiplikation
von zwei natürlichen Zahlen) und eine Eingabe dazu einlesen und verarbeiten. Als Kodierung
verwenden Sie am besten die in der Vorlesung eingeführte Codierung über eine Binärzeichenreihe.
Als Ausgabe (Bildschirm) wird folgendes erwartet:

- a) das korrekte Ergebnis,
- b) die Angabe des aktuellen Zustandes der TM,
- c) das Band mit mindestens 15 Elementen vor und nach dem Lese-/Schreibkopf,
- d) die aktuelle Position des Lese-/Schreibkopfes und
- e) ein Zähler, der die Anzahl der bisher durchgeführten Berechnungsschritte angibt.
- f)  Optional: Grafische Ausgabe des Übergangsdiagramms im Step-Modus, Grafisches
Interface, usw. Bitte nur angehen, wenn Sie wirklich Zeit dazu haben.

Es sind zwei Modi zu realisieren:
- ein Step-Modus: Ausgabe (b bis e) nach jedem einzelnen Berechnungsschritt
- ein Laufmodus: einmalige Ausgabe nach Abschluss der ganzen Berechnung (Also alle Berechnungsschritte ohne Halt / Ausgabe nach Halt der Turing-Maschine durchführen.)


Hinweise: Im Unterricht in der Kalenderwoche 19 (oder wie mit Ihrem Dozenten vereinbart)
präsentieren Sie Ihre TM in maximal 5 Minuten (pro Gruppe): Zeigen Sie kurz auf, wie Sie die
TM emulieren, wie Sie die Multiplikation umgesetzt haben und berechnen Sie vier vorgegebene
Multiplikationsaufgaben (z. B. 2 · 4, 13 · 17, 1 · 27 und 23 · 0).
