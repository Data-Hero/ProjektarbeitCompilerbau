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
- Semantische Analyse
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
- types (Custom type)

## Eingebaute Funktionen

### isDatatype
`boolean isDatatype(x, datatype)`

#### Beschreibung
Gibt zurück, ob der angegebene Wert dem angegebenen Datentypen entspricht.

#### Parameter
- `x`: Wert
- `datatype`: Erwarteter Datentyp

#### Rückgabewert
`true` falls der Wert dem Datentypen entspricht und `false` falls dies nicht der Fall ist.

#### Beispiele
- `isDatatype("test", string) == true`
- `isDatatype(1, string) == false`
- `isDatatype("1", int) == false`
- `isDatatype(1, int) == true`

### toDatatype
`datatype toDatatype(x, datatype)`

#### Beschreibung
Konvertiert den gegebenen Wert in den angegebenen Datentypen.

#### Parameter
- `x`: Wert
- `datatype`: Datentyp zu welchem der angegebene Wert konvertiert wird

#### Rückgabewert
Falls die Konvertierung möglich ist, wird der Wert mit dem entsprechenden Datentypen zurück gegeben.
Falls die Konvertierung fehl schlägt wird `NULL` zurückgegeben.

#### Beispiele
- `toDatatype("1", int) == 1`
- `toDatatype(1, String) == "1"`

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
`@{  }`

#### Beschreibung
???

#### Parameter
???

#### Rückgabewert
???

#### Beispiel
```
string test = "a.b.c.d";
string[] x = test@{
    :punct: { return this; }
};

// x = [".", ".", "."]
```

### Besonderes Verhalten

#### Addition von Arrays
Bei der Addition von zwei Arrays werden die Arrays mengenmäßig vereinigt. 
`["a", "b", "c"] + ["c", "d", "e", "f"] == ["a", "b", "c", "d", "e", "f"]`

#### Subtraktion von Arrays
Bei der Subtraktion von Arrays werden die überschneidenden Elemente aus dem ersten Array entfernt.
`["a", "b", "c", "d", "e", "f"] - ["a", "b", "c"] == ["d", "e", "f"]`

#### Addition von Strings und anderen Datentypen
Bei der Addition von Strings und anderen Datentypen, werden die anderen Datentypen implizit zu Strings konvertiert.
`"Hallo" + 5 == "Hallo5"`