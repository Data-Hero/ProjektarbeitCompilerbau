---
title: NewAwk
separator: <!--s-->
verticalSeparator: <!--v-->
theme: white
revealOptions:
    transition: 'fade'
---
## NewAwk Interpreter

Lines of Code (Java und JavaCC): 3859



Note: test note

<!--s-->

## Wie umgesetzt?

- JavaCC (Lexikalische Analyse, Syntaxanalyse)
- Java (Interpreter)

<!--s-->

## Was umgesetzt?

- Anforderungen
- Kontrollstrukturen
  - if, else-if, else
  - while
  - foreach
  
<!--s-->

## Abweichungen von den Vorgaben

- Aufruf `length(x)`;
  - int: 1
  - double: 1
  - char: 1
  - boolean: 1
  - string: Anzahl der Zeichen
  - Array: Anzahl der Elemente
  
<!--v-->
- Aufruf `isType(string, x);`
- Aufruf `convert(string, x);`
- Operator `@{ ... }` (Smart Switch)
  - `@"hello world" { ... }`
  
<!--v-->
- Array
  - Zugriff
    - Element lesen: `get(arr, i)`
    - Element ersetzen: `set(arr, i, x)`
    - Element hinzufügen: `arr = arr + x`
  - Initialisierung: `string[][] users = [["Schmidt", "Horst"], ["Meier", "Hans"]];`
  
  
<!--v-->  
- Programmaufruf
  - Erste Parameter: Quellcodedatei
  - Zweite Parameter bis n. Parameter: Können in der Quellcodedatei aus dem Array args ausgelesen werden
  

<!--v-->  
- System.print() und System.next()
  - Umsetzung durch `read([path])` und `write(str [, path])` Funktionen
  
<!--v-->  

 - read
  - List Text von Konsole ein
  - Auch spezielle read-Funktionen für die Datentypen
  - Ist der optionale Parameter angegeben, wird statt von der Konsole die angegebene Datei eingelesen

<!--v-->  

- write
  - Gibt den angegebenen String über die Konsole aus
  - Ist der opionale zweite parameter gegeben, so wird der String in die angegebene Datei geschrieben

<!--v-->      
- Aus Zeitmangel nicht umgesetzt
  - Feldauswahl per Boolean
  - Stattdessen: Array per foreach-Schleife durchgehen und Werte abspeichern

<!--v-->  

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


<!--s-->      
## Schwierigkeiten

- Linksrekursion bei Operator @{ } (Smart Switch)
  - ***Lösung***: @ nach vorne ziehen
  
<!--v-->  

- Nullpointer Exceptions bei Syntaxanalyse
  - ***Lösung***: Falls Element null ist, werden der Exception kein Start und End übergeben
<!--v-->  
- Programmabbruch bei Laufzeitfehlern
  - ***Lösung***: ?
<!--v-->  
- Speichern der Funktionen in der Symboltabelle
  - ***Lösung***: Erstellen der abstrakten Klasse ASTDeclaration, von welcher die Klassen ASTFunctionDeclaration und ASTVariableDecleration erben
<!--v-->  
- Setzen der Parameter bei Funktionsaufruf
  - ***Lösung***: Einführung der Klasse ASTParameterDecleration, welche genutzt werden kann um Variablen in einem (Funktions-)Block reinzugeben. Der Variablenwert wird zur laufzeit an den Parameter "gebunden".
<!--v-->  

- Wenig Zeit zum Testen, daher weiterhin kleinere Fehler
  - ***Lösung***: ?
  - Beispiel: Ungenutztes TYPE_LITERAL macht < und > teilweise unbrauchbar
