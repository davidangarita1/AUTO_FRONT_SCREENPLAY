Feature: Registro de turno de paciente

  Scenario Outline: El paciente registra un turno con datos validos
    Given que el paciente se encuentra en la pagina de registro de turnos
    When registra un turno con nombre "<fullName>" y cedula "<cedula>"
    Then el sistema confirma el registro exitoso del turno

    Examples:
      | fullName       | cedula     |
      | Carlos Ramirez | 1098765432 |

  Scenario Outline: El sistema muestra error al ingresar letras en la cedula
    Given que el paciente se encuentra en la pagina de registro de turnos
    When registra un turno con nombre "<fullName>" y cedula "<cedula>"
    Then el sistema muestra el mensaje de formato invalido de cedula

    Examples:
      | fullName       | cedula |
      | Carlos Ramirez | abcxyz |
