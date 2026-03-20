package com.turnos.util;

public final class Constants {

    public static final String ACTOR_PATIENT = "Paciente";

    public static final String DEFAULT_BASE_URL = "http://localhost:3001";

    public static final String REGISTER_PATH = "/register";
    public static final String DASHBOARD_PATH = "/dashboard";

    public static final String SELECTOR_FULL_NAME = "input[placeholder='Nombre completo']";
    public static final String SELECTOR_CEDULA = "input[placeholder='Cédula']";
    public static final String SELECTOR_REGISTER_BUTTON = "button[class*='button']";
    public static final String SELECTOR_SUCCESS_MESSAGE = "[class*='success']";
    public static final String CEDULA_FORMAT_ERROR_TEXT = "La cédula solo puede contener números";

    public static final String VALID_FULL_NAME = "Carlos Ramirez";
    public static final String VALID_CEDULA = "1098765432";
    public static final String INVALID_CEDULA_LETTERS = "abcxyz";

}
