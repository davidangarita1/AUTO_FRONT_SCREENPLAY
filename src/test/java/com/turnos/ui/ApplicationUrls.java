package com.turnos.ui;

import com.turnos.util.Constants;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;

public class ApplicationUrls {

    private static String baseUrl() {
        return EnvironmentSpecificConfiguration
                .from(SystemEnvironmentVariables.currentEnvironmentVariables())
                .getOptionalProperty("webdriver.base.url")
                .orElse(Constants.DEFAULT_BASE_URL);
    }

    public static String registerPage() {
        return baseUrl() + Constants.REGISTER_PATH;
    }

    public static String dashboardPage() {
        return baseUrl() + Constants.DASHBOARD_PATH;
    }
}
