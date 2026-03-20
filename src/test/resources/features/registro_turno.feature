Feature: Registro de turno de paciente

  Scenario: El paciente registra un turno con datos validos
    Given que el paciente se encuentra en la pagina de registro de turnos
    When registra un turno con nombre "Carlos Ramirez" y cedula "1098765432"
    Then el sistema confirma el registro exitoso del turno

  Scenario: El sistema rechaza el registro de turno con datos vacios
    Given que el paciente se encuentra en la pagina de registro de turnos
    When intenta registrar un turno sin completar el nombre ni la cedula
    Then el sistema muestra un mensaje de error de validacion
