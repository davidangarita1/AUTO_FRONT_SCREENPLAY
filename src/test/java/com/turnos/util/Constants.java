package com.turnos.util;

public final class Constants {

    private Constants() {}

    public static final String ACTOR_PATIENT = "Paciente";
    public static final String ACTOR_EMPLOYEE = "Empleado";

    public static final String REGISTER_PATH = "/register";
    public static final String DASHBOARD_PATH = "/dashboard";
    public static final String SIGNIN_PATH = "/signin";
    public static final String DOCTORS_PATH = "/doctors";

    public static final String VALID_FULL_NAME = "Carlos Ramirez";
    public static final String VALID_CEDULA = "1098765432";
    public static final String INVALID_CEDULA_LETTERS = "abcxyz";

    public static final String TEST_USER_EMAIL = "usuario_test@correo.com";
    public static final String TEST_USER_PASSWORD = "Test1234!";
    public static final String TEST_USER_NAME = "Usuario Test";
    public static final String TEST_USER_ROLE = "empleado";

    public static final String ENDPOINT_SIGN_UP = "/auth/signUp";
    public static final String ENDPOINT_SIGN_IN = "/auth/signIn";
    public static final String ENDPOINT_DOCTORS = "/api/v1/doctors";
    public static final String CONTENT_TYPE_JSON = "application/json";

    public static String uniqueCedula() {
        return String.valueOf(System.currentTimeMillis() % 10000000L);
    }
}
