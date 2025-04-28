# Maven JUnit Project in VS Code

Diese Anleitung führt dich durch die Schritte zur Einrichtung eines Maven-Projekts mit JUnit-Tests in Visual Studio Code.

## Voraussetzungen

- [Java JDK](https://adoptium.net/) (Version 8 oder höher)
- [Maven](https://maven.apache.org/download.cgi)
- [Visual Studio Code](https://code.visualstudio.com/download)
- VS Code Extensions:
  - [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
  - [Maven for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-maven)

## 1. Umgebungsvariablen einrichten

### JAVA_HOME einrichten

1. Überprüfe deine Java-Installation:
   ```
   java -version
   ```

2. Finde den Installationspfad von Java:
   ```
   where java
   ```

3. Setze die JAVA_HOME-Umgebungsvariable:
   - Windows: 
     - Öffne "Systemsteuerung" > "System" > "Erweiterte Systemeinstellungen" > "Umgebungsvariablen"
     - Erstelle eine neue Systemvariable namens `JAVA_HOME` mit dem Wert des Java-Installationspfads (z.B. `C:\Program Files\Eclipse Adoptium\jdk-21.0.7+6`)
   - Temporär in der Eingabeaufforderung:
     ```
     set JAVA_HOME=C:\Pfad\zur\Java\Installation
     ```

## 2. Maven-Projekt erstellen

### Methode 1: VS Code-Befehlspalette

1. Öffne VS Code
2. Drücke `Ctrl+Shift+P` (Windows/Linux) oder `Cmd+Shift+P` (Mac)
3. Tippe "Maven: Create Maven Project" und wähle es aus
4. Wähle "maven-archetype-quickstart" (Version 1.4)
5. Gib die GroupId ein (z.B. `com.example`)
6. Gib die ArtifactId ein (z.B. `demo`)
7. Wähle einen Speicherort für dein Projekt

### Methode 2: Terminal

1. Öffne ein Terminal
2. Navigiere zu deinem gewünschten Projektverzeichnis
3. Führe folgenden Befehl aus:
   ```
   mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DgroupId=com.example -DartifactId=demo -DinteractiveMode=false
   ```
4. Öffne das erstellte Projektverzeichnis in VS Code

## 3. Java-Version in pom.xml konfigurieren

Öffne die `pom.xml`-Datei und füge den folgenden `<properties>`-Abschnitt hinzu oder aktualisiere ihn:

```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
</properties>
```

Passe die Java-Version (17 im Beispiel) nach Bedarf an.

## 4. JUnit-Abhängigkeiten hinzufügen

Aktualisiere den `<dependencies>`-Abschnitt in der `pom.xml`:

```xml
<dependencies>
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.9.3</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.9.3</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-params</artifactId>
        <version>5.9.3</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Füge auch das Maven Surefire Plugin hinzu:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
    </plugins>
</build>
```

## 5. Beispielklasse erstellen

Erstelle eine Java-Klasse im Verzeichnis `src/main/java/com/example` (oder deinem konfigurierten Paket):

```java
package com.example;

public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) a / b;
    }
}
```

## 6. Testklasse erstellen

Erstelle eine Testklasse im Verzeichnis `src/test/java/com/example`:

```java
package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    
    private final Calculator calculator = new Calculator();
    
    @Test
    public void testAdd() {
        assertEquals(5, calculator.add(2, 3), "2 + 3 sollte 5 ergeben");
    }
    
    @Test
    public void testSubtract() {
        assertEquals(2, calculator.subtract(5, 3), "5 - 3 sollte 2 ergeben");
    }
    
    @Test
    public void testMultiply() {
        assertEquals(15, calculator.multiply(3, 5), "3 * 5 sollte 15 ergeben");
    }
    
    @Test
    public void testDivide() {
        assertEquals(2.5, calculator.divide(5, 2), "5 / 2 sollte 2.5 ergeben");
    }
    
    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0), 
                    "Division durch Null sollte eine ArithmeticException werfen");
    }
}
```

## 7. Tests ausführen

Es gibt mehrere Möglichkeiten, Tests auszuführen:

### Methode 1: VS Code Test-Explorer

1. Klicke auf das Test-Symbol in der linken Seitenleiste
2. Expandiere dein Projekt und finde die Tests
3. Klicke auf den Play-Button neben einzelnen Tests oder Testklassen

### Methode 2: Maven-Befehl im Terminal

1. Öffne ein Terminal in VS Code (`Terminal > New Terminal`)
2. Navigiere zum Projektverzeichnis
3. Führe aus:
   ```
   mvn test
   ```

### Methode 3: Direkt in der Testdatei

1. Öffne die Testdatei
2. Klicke auf die "Run Test" oder "Debug Test" Links, die über den Testmethoden erscheinen

## 8. Fehlerbehebung

### Problem: JAVA_HOME nicht gefunden

Wenn du die Fehlermeldung "JAVA_HOME not found in your environment" erhältst:
- Stelle sicher, dass die JAVA_HOME-Umgebungsvariable korrekt gesetzt ist
- Starte VS Code neu, damit die Änderungen wirksam werden

### Problem: Veraltete Java-Version

Wenn die Fehlermeldung "Quelloption X wird nicht mehr unterstützt" erscheint:
- Aktualisiere die Java-Version in der pom.xml wie in Schritt 3 beschrieben

### Problem: JUnit-Assertions nicht gefunden

Wenn die Methode "assertEquals" oder andere Assertions nicht gefunden werden:
- Überprüfe den Import von statischen Assertions in deiner Testklasse
- Stelle sicher, dass die JUnit-Abhängigkeiten korrekt in der pom.xml eingerichtet sind

## 9. Erweiterte JUnit-Features

### Parametrisierte Tests

Füge `junit-jupiter-params` als Abhängigkeit hinzu und verwende:

```java
@ParameterizedTest
@CsvSource({
    "1, 1, 2",
    "5, 3, 8",
    "10, -5, 5"
})
public void testAdd(int a, int b, int expected) {
    assertEquals(expected, calculator.add(a, b));
}
```

### Test-Lifecycle-Hooks

Verwende `@BeforeEach`, `@AfterEach`, `@BeforeAll` und `@AfterAll` für Setup und Cleanup:

```java
@BeforeEach
public void setUp() {
    // Code, der vor jedem Test ausgeführt wird
}

@AfterEach
public void tearDown() {
    // Code, der nach jedem Test ausgeführt wird
}
```

### Gruppieren von Tests

Organisiere Tests mit verschachtelten Klassen:

```java
@Nested
class AdditionTests {
    @Test
    void testPositiveNumbers() { /* ... */ }
    
    @Test
    void testNegativeNumbers() { /* ... */ }
}
```

## 10. Nächste Schritte

- Füge Testabdeckung mit JaCoCo hinzu
- Richte Continuous Integration mit GitHub Actions ein
- Lerne über Mocking-Frameworks wie Mockito
- Erkunde Property-Based Testing mit jqwik
