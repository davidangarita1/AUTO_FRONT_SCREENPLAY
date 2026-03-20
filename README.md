# AUTO_FRONT_SCREENPLAY

Proyecto de automatización de pruebas UI para el Sistema de Turnos, construido con **Java 17 + Serenity BDD 5.3.2 + Cucumber + Gradle** usando el patrón **Screenplay**.

## Prerrequisitos

1. **Java 17** instalado y configurado en `JAVA_HOME`
2. **Google Chrome** instalado (el WebDriver es descargado automáticamente por Serenity)
3. **La aplicación bajo prueba** corriendo en `http://localhost:3001`:
   - Sistema de Turnos construido en Next.js
   - La página `/register` debe estar disponible

## Estructura del proyecto

```
AUTO_FRONT_SCREENPLAY/
├── build.gradle
├── settings.gradle
├── gradle/wrapper/
└── src/
    └── test/
        ├── java/
        │   └── com/turnos/
        │       ├── hooks/           ← Hooks de ciclo de vida de Cucumber
        │       ├── questions/       ← Preguntas para verificar estado de la UI
        │       ├── runners/         ← Runner de Cucumber con Serenity
        │       ├── stepdefinitions/ ← Step Definitions de Cucumber
        │       ├── tasks/           ← Tareas (Tasks) con responsabilidad única
        │       ├── ui/              ← Targets de UI y URLs
        │       ├── models/          ← Modelos de datos
        │       └── util/            ← Utilidades
        └── resources/
            ├── serenity.conf
            ├── logback-test.xml
            └── features/
                └── registro_turno.feature
```

## Escenarios cubiertos

1. **Flujo positivo** (`registro_turno.feature`): Verifica que un paciente puede registrar un turno con nombre y cédula válidos.
2. **Flujo negativo** (`registro_turno.feature`): Verifica que el sistema muestra un mensaje de error cuando se ingresan letras en el campo de cédula.

## Cómo ejecutar las pruebas

```bash
./gradlew clean test
```

## Dónde ver el reporte

Abrir en el navegador:

```
target/site/serenity/index.html
```

## Configuración

La URL base de la aplicación se configura en `src/test/resources/serenity.conf`:

```hocon
environments {
  default {
    webdriver.base.url = "http://localhost:3001"
  }
}
```

## Stack técnico

| Componente | Tecnología |
|---|---|
| Lenguaje | Java 17 |
| Build tool | Gradle (Groovy DSL) |
| Framework | Serenity BDD 5.3.2 |
| Test runner | Cucumber + JUnit Platform |
| Patrón | Screenplay |
| Browser | Chrome |
