package com.turnos.ui;

import com.turnos.util.Constants;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public class ApplicationUrls {

    private static String baseUrl() {
        return EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getProperty("webdriver.base.url");
    }

    public static String registerPage() {
        return baseUrl() + Constants.REGISTER_PATH;
    }

    public static String dashboardPage() {
        return baseUrl() + Constants.DASHBOARD_PATH;
    }

    public static String signinPage() {
        return baseUrl() + Constants.SIGNIN_PATH;
    }

    public static String doctorsPage() {
        return baseUrl() + Constants.DOCTORS_PATH;
    }
}
