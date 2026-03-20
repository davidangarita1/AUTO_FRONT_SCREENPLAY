package com.turnos.ui;

import com.turnos.util.Constants;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationPage {

    public static final Target FULL_NAME_FIELD = Target.the("full name field")
            .located(By.cssSelector(Constants.SELECTOR_FULL_NAME));

    public static final Target CEDULA_FIELD = Target.the("cedula field")
            .located(By.cssSelector(Constants.SELECTOR_CEDULA));

    public static final Target REGISTER_BUTTON = Target.the("register turno button")
            .located(By.cssSelector(Constants.SELECTOR_REGISTER_BUTTON));

    public static final Target SUCCESS_MESSAGE = Target.the("success message")
            .located(By.cssSelector(Constants.SELECTOR_SUCCESS_MESSAGE));

    public static final Target CEDULA_FORMAT_ERROR = Target.the("mensaje error formato cédula")
            .located(By.xpath("//*[contains(text(),'" + Constants.CEDULA_FORMAT_ERROR_TEXT + "')]"));
}
