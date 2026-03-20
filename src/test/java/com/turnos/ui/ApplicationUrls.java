package com.turnos.ui;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public class ApplicationUrls {

    private static final String DEFAULT_BASE_URL = "http://localhost:3001";

    private static String baseUrl() {
        return EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getOptionalProperty("webdriver.base.url")
                .orElse(DEFAULT_BASE_URL);
    }

    public static String registerPage() {
        return baseUrl() + "/register";
    }

    public static String dashboardPage() {
        return baseUrl() + "/dashboard";
    }
}
