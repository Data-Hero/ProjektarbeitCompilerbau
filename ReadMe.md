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
