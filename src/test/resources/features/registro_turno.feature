Feature: Registro de turno de paciente

  Scenario: El paciente registra un turno con datos validos
    Given que el paciente se encuentra en la pagina de registro de turnos
    When registra un turno con nombre "Carlos Ramirez" y cedula "1098765432"
    Then el sistema confirma el registro exitoso del turno

  Scenario: El sistema muestra error al ingresar letras en la cedula
    Given que el paciente se encuentra en la pagina de registro de turnos
    When registra un turno con nombre "Carlos Ramirez" y cedula "abcxyz"
    Then el sistema muestra el mensaje de formato invalido de cedula
