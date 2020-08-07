# NewAwk
Ein aus SarenCurrie/javacc-maven-template erstelltes Projekt mit JavaCC zur Erstellung einer Sprache. Diese soll in einfachen imperativen Programmen Stringumformungen auf als Parametern übergebenen Dateien durchführen. Mit `mvn install` wird javacc und jjtree ausgeführt um die Eingabe zu parsen.


## Installation
```
git clone git@github.com:Data-Hero/ProjektarbeitCompilerbau.git
cd ProjektarbeitCompilerbau
mvn clean install
```

### Codeerstellung
When running `mvn install` or `mvn generate-sources`, any `.jj` files saved in `src/main/javacc` will be compiled into `.java` sources. These will be saved under `target/generated-sources/javacc/{package-name}`. IntelliJ will include this in your classpath automatically (so you can refernce these classes in your source code), you may need to add these manually if you are using Eclise.

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
- zusätzliche Anfo:
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
