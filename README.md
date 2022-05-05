# UTM

Theoretische Informatik

## Übung 4 - Turing-Maschinen

### Aufgabe 1. Universelle TM.

Entwickeln Sie (in einer beliebigen Programmiersprache) ein Programm, das eine Universelle TM emuliert. Das Programm
soll die kodierte Funktion einer TM (konkret für die Multiplikation von zwei natürlichen Zahlen) und eine Eingabe dazu
einlesen und verarbeiten. Als Kodierung verwenden Sie am besten die in der Vorlesung eingeführte Codierung über eine
Binärzeichenreihe. Als Ausgabe (Bildschirm) wird folgendes erwartet:

- a) das korrekte Ergebnis,
- b) die Angabe des aktuellen Zustandes der TM,
- c) das Band mit mindestens 15 Elementen vor und nach dem Lese-/Schreibkopf,
- d) die aktuelle Position des Lese-/Schreibkopfes und
- e) ein Zähler, der die Anzahl der bisher durchgeführten Berechnungsschritte angibt.
- f)  ~~Optional: Grafische Ausgabe des Übergangsdiagramms im Step-Modus, Grafisches Interface, usw. Bitte nur angehen,
  wenn Sie wirklich Zeit dazu haben.~~

Es sind zwei Modi zu realisieren:

- ein Step-Modus: Ausgabe (b bis e) nach jedem einzelnen Berechnungsschritt
- ein Laufmodus: einmalige Ausgabe nach Abschluss der ganzen Berechnung (Also alle Berechnungsschritte ohne Halt /
  Ausgabe nach Halt der Turing-Maschine durchführen.)

Hinweise: Im Unterricht in der Kalenderwoche 19 (oder wie mit Ihrem Dozenten vereinbart)
präsentieren Sie Ihre TM in maximal 5 Minuten (pro Gruppe): Zeigen Sie kurz auf, wie Sie die TM emulieren, wie Sie die
Multiplikation umgesetzt haben und berechnen Sie vier vorgegebene Multiplikationsaufgaben (z. B. 2 · 4, 13 · 17, 1 · 27
und 23 · 0).

## Usage
### Allowed symbols / Definitions

| coding  | input   |
|---------|---------|
| `'0' `  | `0`     |
| `'1'`   | `00`    |
| `'_'`   | `000`   |
| `'X'`   | `0000`  |
| `'Y'`   | `00000` |
| `right` | `00`    |
| `left ` | `0`     |

### Example input

```
# 2*2
01010001000010011010010000000000100001011000101000101001100010010000100100110000101000010100110000100010000010010110000100100000100101100000100000100000100000101100000101000000100000100110000010010000000010010011000000101000000101001100000010010000001001001100000010000010000001000001001100000010001000000010101100000001010000000101011000000010010000010010110000000010000010000000010100110000000010010000000001001011000000000101000000000101011000000000100100000000010010110000000001000010100001001100000000001000010000000000100001011000000000010001000000000001000100110000000000010000100000000000100010011000000000001010000000000010001001100000000000100010010001001100000000000100100100010011100100

```

```
# 2*4
0101000100001001101001000000000010000101100010100010100110001001000010010011000010100001010011000010001000001001011000010010000010010110000010000010000010000010110000010100000010000010011000001001000000001001001100000010100000010100110000001001000000100100110000001000001000000100000100110000001000100000001010110000000101000000010101100000001001000001001011000000001000001000000001010011000000001001000000000100101100000000010100000000010101100000000010010000000001001011000000000100001010000100110000000000100001000000000010000101100000000001000100000000000100010011000000000001000010000000000010001001100000000000101000000000001000100110000000000010001001000100110000000000010010010001001110010000

```
```
# 2*0
""001
```

```
# 0*3
""1000
```


```
# 15*3
""0000000000000001000
```



