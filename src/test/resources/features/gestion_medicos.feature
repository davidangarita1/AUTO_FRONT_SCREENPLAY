Feature: Gestion de Medicos

  Background:
    Given el usuario se encuentra en la pagina de inicio de sesion
    When ingresa el correo "usuario_test@correo.com" y la contrasena "Test1234!"
    And hace clic en el boton de iniciar sesion
    And navega al modulo de gestion de medicos

  @crear_usuario
  Scenario: La barra de navegacion muestra el enlace Gestion Medicos
    Then la barra de navegacion muestra el enlace "Gestión Médicos"

  @crear_usuario
  Scenario: La pantalla de gestion de medicos muestra la estructura correcta
    Then la pantalla muestra el titulo "Gestión de Médicos"
    And la tabla muestra los encabezados "Nombre completo,Cédula,Consultorio,Franja Horaria,Acciones"
    And el boton "Crear médico" es visible

  @crear_usuario @limpiar_medicos
  Scenario Outline: Crear un medico con consultorio y franja horaria
    When registra al medico "<nombre>" con cedula "<cedula>", consultorio "<consultorio>" y franja "<franja>"
    Then aparece el mensaje flotante "Médico creado exitosamente"
    And el medico "Dr. <nombre>" aparece en la tabla con consultorio "<consultorio>" y franja "<franja>"

    Examples:
      | nombre        | cedula  | consultorio | franja      |
      | Carlos Prueba | 7654321 | 1           | 06:00-14:00 |
      | Luis Vargas   | 2345678 | 2           | 14:00-22:00 |

  @crear_usuario @limpiar_medicos
  Scenario Outline: Crear un medico sin consultorio ni franja horaria
    When registra al medico "<nombre>" con cedula "<cedula>" sin consultorio ni franja
    Then aparece el mensaje flotante "Médico creado exitosamente"
    And el medico "Dr. <nombre>" aparece en la tabla con consultorio "Sin asignar" y franja "Sin asignar"

    Examples:
      | nombre       | cedula  |
      | Ana Martinez | 8765432 |

  @crear_usuario
  Scenario: El nombre vacio impide guardar
    Given el formulario de creacion de medico esta abierto
    When el usuario toca el campo nombre y sale sin escribir
    Then aparece el mensaje de validacion "El nombre completo es obligatorio"
    And el boton de guardar esta deshabilitado

  @crear_usuario
  Scenario: El nombre con menos de 3 caracteres impide guardar
    Given el formulario de creacion de medico esta abierto
    When el usuario escribe "Ju" en el campo nombre
    Then aparece el mensaje de validacion "El nombre debe tener mínimo 3 caracteres"
    And el boton de guardar esta deshabilitado

  @crear_usuario
  Scenario Outline: La cedula filtra caracteres no numericos
    Given el formulario de creacion de medico esta abierto
    When el usuario escribe "<entrada>" en el campo cedula
    Then el campo cedula muestra solo "<resultado>"

    Examples:
      | entrada | resultado |
      | ABC123  | 123       |
      | 456XYZ  | 456       |

  @crear_usuario
  Scenario: Consultorio seleccionado sin franja horaria impide guardar
    Given el formulario de creacion de medico esta abierto
    When el usuario ingresa nombre "Juan Garcia", cedula "1234567" y selecciona consultorio "2"
    Then aparece el mensaje de validacion "La franja horaria es obligatoria cuando se asigna un consultorio"
    And el boton de guardar esta deshabilitado

  @crear_usuario @limpiar_medicos
  Scenario Outline: Editar consultorio y franja de un medico
    Given el medico "<nombre>" con cedula "<cedula>" esta registrado con consultorio "<consultorio_inicial>" y franja "<franja_inicial>"
    When el usuario edita al medico "Dr. <nombre>" asignando consultorio "<consultorio_nuevo>" y franja "<franja_nueva>"
    Then aparece el mensaje flotante "Médico guardado exitosamente"
    And el medico "Dr. <nombre>" aparece en la tabla con consultorio "<consultorio_nuevo>" y franja "<franja_nueva>"

    Examples:
      | nombre      | cedula  | consultorio_inicial | franja_inicial | consultorio_nuevo | franja_nueva |
      | Pedro Lopez | 9876543 | 3                   | 06:00-14:00    | 4                 | 14:00-22:00  |
      | Ana Castro  | 1234560 | 1                   | 14:00-22:00    | 2                 | 06:00-14:00  |

  @crear_usuario @limpiar_medicos
  Scenario: Clic fuera del modal de edicion no lo cierra
    Given el medico "Laura Gomez" con cedula "5432198" esta registrado con consultorio "5" y franja "14:00-22:00"
    When el usuario abre el modal de edicion del medico "Dr. Laura Gomez" y hace clic fuera de el
    Then el modal de edicion permanece abierto

  @crear_usuario @limpiar_medicos
  Scenario: Confirmar la baja logica de un medico
    Given el medico "Maria Ruiz" con cedula "6789012" esta registrado con consultorio "6" y franja "06:00-14:00"
    When el usuario confirma la baja del medico "Dr. Maria Ruiz"
    Then aparece el mensaje flotante "Médico dado de baja exitosamente"
    And el medico "Dr. Maria Ruiz" no aparece en la tabla

  @crear_usuario @limpiar_medicos
  Scenario: Cancelar la baja cierra el modal sin cambios
    Given el medico "Sofia Torres" con cedula "3456789" esta registrado con consultorio "7" y franja "14:00-22:00"
    When el usuario cancela la baja del medico "Dr. Sofia Torres"
    Then el medico "Dr. Sofia Torres" aparece en la tabla
