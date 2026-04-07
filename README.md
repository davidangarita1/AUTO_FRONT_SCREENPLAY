# AUTO_FRONT_SCREENPLAY

Proyecto de automatización de pruebas UI para el Sistema de Turnos, construido con **Java 21 + Serenity BDD 5.3.2 + Cucumber + Gradle** usando el patrón **Screenplay**.

## Prerrequisitos

1. **Java 21** instalado y configurado en `JAVA_HOME`
2. **Google Chrome** instalado (el WebDriver es descargado automáticamente por Serenity)
3. **La aplicación bajo prueba** corriendo en `http://localhost:3001` (frontend Next.js)
4. **La API backend** corriendo en `http://localhost:3000`

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
        │       └── util/            ← Utilidades y constantes
        └── resources/
            ├── serenity.conf
            ├── logback-test.xml
            └── features/
                ├── registro_turno.feature
                └── gestion_medicos.feature
```

## Arquitectura — Patrón Screenplay

| Capa | Clase | Responsabilidad |
|---|---|---|
| `ui/` | `LoginPage`, `DoctorsPage`, `DoctorFormModal`, `DoctorEditModal`, `ConfirmDeleteModal`, `Toast` | Targets de elementos UI (sin `@FindBy`) |
| `tasks/` | `NavigateToSignInPage`, `LogIn`, `SubmitSignIn`, `NavigateToDoctorsModule`, `FillDoctorForm`, `SelectReactOption`, `RegisterDoctor`, `EditDoctorOfficeAndShift`, `OpenEditModalAndClickOutside`, `DeactivateDoctor` | Acciones compuestas del actor |
| `questions/` | `NavbarLinkIsVisible`, `PageTitle`, `TableHeaders`, `CreateButtonIsVisible`, `ToastMessage`, `DoctorIsInTable`, `DoctorOfficeInTable`, `DoctorShiftInTable`, `ValidationMessage`, `SaveButtonIsEnabled`, `EditModalIsVisible`, `CedulaFieldValue` | Verificaciones del estado de la UI |
| `hooks/` | `ScenarioHooks`, `UserSetupHook`, `DoctorCleanupHook` | Ciclo de vida de escenarios |

## Tags y hooks

| Tag | Hook | Descripción |
|---|---|---|
| `@crear_usuario` | `UserSetupHook.@Before` | Crea el usuario de prueba via API antes del escenario |
| `@limpiar_medicos` | `DoctorCleanupHook.@Before` + `@After` | Elimina todos los médicos del sistema antes y después del escenario via API |

## Escenarios cubiertos

### Registro de turno (existente)

| Escenario | Tipo |
|---|---|
| Registro de turno con datos válidos | Scenario Outline |
| Error al ingresar letras en cédula | Scenario Outline |

### Gestión de Médicos (HU-01 a HU-04)

| Escenario | HU | Tipo |
|---|---|---|
| Barra de navegación muestra el enlace Gestión Médicos | HU-01 | Scenario |
| Pantalla muestra estructura correcta | HU-01 | Scenario |
| Crear médico con consultorio y franja horaria | HU-02 | Scenario Outline (2 filas) |
| Crear médico sin consultorio ni franja | HU-02 | Scenario Outline (1 fila) |
| Nombre vacío impide guardar | HU-02 | Scenario |
| Nombre con menos de 3 caracteres impide guardar | HU-02 | Scenario |
| Cédula filtra caracteres no numéricos | HU-02 | Scenario Outline (2 filas) |
| Consultorio sin franja impide guardar | HU-02 | Scenario |
| Editar consultorio y franja de un médico | HU-03 | Scenario Outline (2 filas) |
| Clic fuera del modal de edición no lo cierra | HU-03 | Scenario |
| Confirmar la baja lógica de un médico | HU-04 | Scenario |
| Cancelar la baja cierra el modal sin cambios | HU-04 | Scenario |

## Estrategia de datos de prueba

- Las cédulas se generan dinámicamente con `System.currentTimeMillis() % 10000000L` para evitar conflictos con registros soft-deleted de ejecuciones anteriores.
- Los selects de consultorio y franja horaria usan interacción JavaScript (`nativeInputValueSetter + dispatchEvent`) para activar el onChange de React.
- El hook `@limpiar_medicos` borra todos los médicos via API (`DELETE /api/v1/doctors/:id`) antes y después de cada escenario que los crea.

## Cómo ejecutar las pruebas

```bash
./gradlew clean test
```

El task `aggregate` se ejecuta automáticamente al finalizar `test`, generando el reporte de Serenity.

## Dónde ver el reporte

Abrir en el navegador:

```
target/site/serenity/index.html
```

## Configuración

La URL base y la URL de la API se configuran en `src/test/resources/serenity.conf`:

```hocon
environments {
  default {
    webdriver.base.url = "http://localhost:3001"
    api.base.url = "http://localhost:3000"
  }
}
```

## Stack técnico

| Componente | Tecnología |
|---|---|
| Lenguaje | Java 21 |
| Build tool | Gradle (Groovy DSL) |
| Framework | Serenity BDD 5.3.2 |
| Test runner | Cucumber 7.34.2 + JUnit Platform |
| Patrón | Screenplay |
| Browser | Chrome (WebDriver automático) |
| Assertions | AssertJ 3.23.1 + Hamcrest |
