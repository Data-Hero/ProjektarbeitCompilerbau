# NewAwk
Ein aus SarenCurrie/javacc-maven-template erstelltes Projekt mit JavaCC zur Erstellung einer Sprache. Diese soll in einfachen imperativen Programmen Stringumformungen auf als Parametern übergebenen Dateien durchführen. Mit `mvn install` wird javacc und jjtree ausgeführt um die Eingabe zu parsen.


## Installation
```
git clone git@github.com:Data-Hero/ProjektarbeitCompilerbau.git
cd ProjektarbeitCompilerbau
mvn clean install
```

### Codeerstellung
When running `mvn install` or `mvn generate-sources`, any `.jj` files saved in `src/main/javacc` will be compiled into `.java` sources. These will be saved under `target/generated-sources/javacc/{package-name}`. IntelliJ will include this in your classpath automatically (so you can reference these classes in your source code), you may need to add these manually if you are using Eclise.

### IntelliJ Setup
Todo

#### Nutzung
Todo

#### Task

![#c5f015](https://placehold.it/15/c5f015/000000?text=+) erledigt ![#f03c15]

- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)  Lexikalische Analyse (Scanner)
- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)  Syntax-Analyse (Parser)
- ![#c5f015](https://placehold.it/15/c5f015/000000?text=+)  Semantische Analyse
- Fehlerbehandlung
- Codeerzeugung/Interpreter (Codegenerator)
- zusätzliche Info:
  - Datentypen und Operatoren
    - Als Datentypen benutzen wir
      – int, double, char, boolean, String
      – Multidimensionale Felder auf diesen Datentypen
    - Operatoren auf int, double, char: +, -, *, /, %
    - Operatoren auf boolean: &&, ||, !
    - Vergleichsoperationen: ==, >=, !=, <=, <, >
    - Zuordnung: =
  - String
    - “Hello“ + 5 == “Hello5“
    - “Hello“.length() = 5
    - isInt(), isDouble(), etc.
    - toInt(), toDouble(), etc.
    - toString()
    - Operator @{ … }: Der String wird gemäß den Regeln in den geschweiften Klammern zerlegt
  - Felder
    - x.length = 5
    - Feldzugriff über Index (0,1,2,…) 
    - x = [1,2,3,4,5]: x[2] -> 3 & x[ i[0]%2 == 0 ] -> 1,3,5
    - y = { { 1,2,3 }, { 4,5,6 }, { 7,8,9 } }: y[i[0] % 2 == 1][i[0] == i[1]] -> { {5} }

## Bestandteile

### Datentypen

- int
- double
- char
- boolean
- string
- Arrays: string[], int[], int[][], etc.
- void (Als Rückgabewert)

## Eingebaute Funktionen



### isType
`boolean isType(x, DATATYPE)`

#### Beschreibung
Gibt zurück, ob der angegebene Wert dem angegebenen Datentypen entspricht.

#### Parameter
- `x`: Wert
- `datatype`: Erwarteter Datentyp

#### Rückgabewert
`true` falls der Wert dem Datentypen entspricht und `false` falls dies nicht der Fall ist.

#### Beispiele
- `isType(string, "test") == true`
- `isType(string, 1) == false`
- `isType(int, "1") == false`
- `isType(int, 1) == true`



### convert
`DATATYPE convert(DATATYPE, VALUE)`

#### Beschreibung
Konvertiert den gegebenen Wert in den angegebenen Datentypen.

#### Parameter
- `x`: Wert
- `datatype`: Datentyp zu welchem der angegebene Wert konvertiert wird

#### Rückgabewert
Der Wert wird im angegebenen Datentypen zurückgegeben.

#### Beispiele
- `convert(int, "1") == 1`
- `convert(string, 1) == "1"`

### length
`int length(x)`

#### Beschreibung
Ermittelt die Länge des Wertes und gibt diese zurück. 

#### Parameter
- `x`: Wert

#### Rückgabewert
Gibt die Länge des Wertes zurück.
- int: 1
- double: 1
- char: 1
- boolean: 1
- string: Anzahl der Zeichen
- type: Anzahl der Optionen
- Custom Datatype: 1
- Array: Anzahl der Elemente

#### Beispiele
- `length("hallo") == 5`
- `length("a") == 1`
- `length(5) == 1`
- `length([a, b, c]) == 3`



### Smart Switch
`String @String{  }`

#### Beschreibung
Baut eine Zeichenkette auf Basis von regulären Ausdrücken zusammen.
Die einzelnen Blöcke werden nur aufgerufen, wenn die gesamte Zeichenkette mit dem Ausdruck übereinstimmt.
Die Rückgabewerte werden dabei konkatiniert.

Bei den regulären Ausdrücken gibt es eine Besonderheit: Das Ausrufezeichen "!" muss mittels des Backslashes "\" escaped werden.
Ein einfaches Ausrufezeichen "!" am Anfang eines regulären Ausdrucks kann genutzt werden, um den gesamten Ausdruck zu negieren.

#### Parameter
-

#### Rückgabewert
String

#### Beispiel
```
string p = @"test"{
    "[t].*" { return "h"; }
    "[t].*" { return "a"; }
    "[t].*" { return "n"; }
    "![t].*" { return "z"; }
    "[t].*" { return "s"; }
};

// p == "hans"
```



### write
`void write(str [, path])`

#### Beschreibung
Schreibt den String in die Konsolenausgabe oder optional in die angegebene Datei.
Bei der angabe eines Dateipfades wird keine Konsolenausgabe geschrieben.

#### Parameter
- `str`: Zu schreibender String
- `path`: (Optional) Pfad der Datei, in welche geschrieben werden soll

#### Rückgabewert
-

#### Beispiele
- `write("test");`
- `write("test", "path/to/file");`



### read
`string read(path)`

#### Beschreibung
List eine Datei ein und gibt deren Inhalt zurück.

#### Parameter
- `path`: Pfad der Datei, in welche gelesen werden soll

#### Rückgabewert
-

#### Beispiele
- `string str = read("path/to/file");`



### Besonderes Verhalten

#### Reihenfolge der Elemente
Funktionsdefinitionen müssen immer als erstes geschrieben werden!
Erst darauf folgen die Statements.

#### Initialisieren eines Arrays
`string[] = ["a", "b", "c"]`
`string[][] = [["a", "b", "c"], ["test"]]`

#### Addition von Arrays
Bei der Addition von zwei Arrays werden die Arrays mengenmäßig vereinigt. 
`["a", "b", "c"] + ["c", "d", "e", "f"] == ["a", "b", "c", "d", "e", "f"]`

#### Subtraktion von Arrays
Bei der Subtraktion von Arrays werden die überschneidenden Elemente aus dem ersten Array entfernt.
`["a", "b", "c", "d", "e", "f"] - ["a", "b", "c"] == ["d", "e", "f"]`

#### Addition von Strings und anderen Datentypen
Bei der Addition von Strings und anderen Datentypen, werden die anderen Datentypen implizit zu Strings konvertiert.
`"Hallo" + 5 == "Hallo5"`

### Weiteres

#### Foreach
##### Syntax
``
string[] arr = ["1", "2", "3"];
foreach (string e in arr) {
    write(e);
}
``

#### Funktionen
##### Syntax
``
int plusOne(int x) {
    int y = x + 1;
    return y;
}
.
.
.
int x = plusOne(1); // == 2
``