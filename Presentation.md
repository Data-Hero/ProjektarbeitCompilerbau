# Präsentation

NewAwk Interpreter

Lines of Code (Java und JavaCC): 3859

## Wie umgesetzt?

- JavaCC (Lexikalische Analyse, Syntaxanalyse)
- Java (Interpreter)

## Was umgesetzt?

- Anforderungen
- Kontrollstrukturen
  - if, else-if, else
  - while
  - foreach

## Abweichungen von den Vorgaben

- Aufruf `length(x)`;
  - int: 1
  - double: 1
  - char: 1
  - boolean: 1
  - string: Anzahl der Zeichen
  - Array: Anzahl der Elemente
- Aufruf `isType(string, x);`
- Aufruf `convert(string, x);`
- Operator `@{ ... }` (Smart Switch)
  - `@"hello world" { ... }`
- Array
  - Zugriff
    - Element lesen: `get(arr, i)`
    - Element ersetzen: `set(arr, i, x)`
    - Element hinzufügen: `arr = arr + x`
  - Initialisierung: `string[][] users = [["Schmidt", "Horst"], ["Meier", "Hans"]];`
- Programmaufruf
  - Erste Parameter: Quellcodedatei
  - Zweite Parameter bis n. Parameter: Können in der Quellcodedatei aus dem Array args ausgelesen werden
- System.print() und System.next()
  - Umsetzung durch `read([path])` und `write(str [, path])` Funktionen
  - read
    - List Text von Konsole ein
    - Auch spezielle read-Funktionen für die Datentypen
    - Ist der optionale Parameter angegeben, wird statt von der Konsole die angegebene Datei eingelesen
  - write
    - Gibt den angegebenen String über die Konsole aus
    - Ist der opionale zweite parameter gegeben, so wird der String in die angegebene Datei geschrieben
- Aus Zeitmangel nicht umgesetzt
  - Feldauswahl per Boolean
  - Stattdessen: Array per foreach-Schleife durchgehen und Werte abspeichern
```
int[] values = [1, 2, 3, 4, 5, 6];
int[] returnValues;

foreach (int value in values) {
  if (value > 1 && value < 6) {
    returnValues = returnValues + value;
  }
}

write(returnValues); // [2, 3, 4, 5]
```

## Schwierigkeiten

- Linksrekursion bei Operator @{ } (Smart Switch)
  - Lösung: @ nach vorne ziehen
- Nullpointer Exceptions bei Syntaxanalyse
  - Lösung: Falls Element null ist, werden der Exception kein Start und End übergeben
- Programmabbruch bei Laufzeitfehlern
  - Lösung: ?
- Speichern der Funktionen in der Symboltabelle
  - Lösung: Erstellen der abstrakten Klasse ASTDeclaration, von welcher die Klassen ASTFunctionDeclaration und ASTVariableDecleration erben
- Setzen der Parameter bei Funktionsaufruf
  - Lösung: Einführung der Klasse ASTParameterDecleration, welche genutzt werden kann um Variablen in einem (Funktions-)Block reinzugeben. Der Variablenwert wird zur laufzeit an den Parameter "gebunden".
- Wenig Zeit zum Testen, daher weiterhin kleinere Fehler
  - Lösung: ?
  - Beispiel: Ungenutztes TYPE_LITERAL macht < und > teilweise unbrauchbar