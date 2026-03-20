package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationPage {

    public static final Target FULL_NAME_FIELD = Target.the("full name field")
            .located(By.cssSelector("input[placeholder='Nombre completo']"));

    public static final Target CEDULA_FIELD = Target.the("cedula field")
            .located(By.cssSelector("input[placeholder='C\u00e9dula']"));

    public static final Target REGISTER_BUTTON = Target.the("register turno button")
            .located(By.cssSelector("button[class*='button']"));

    public static final Target SUCCESS_MESSAGE = Target.the("success message")
            .located(By.cssSelector("[class*='success']"));

    public static final Target CEDULA_FORMAT_ERROR = Target.the("mensaje error formato cédula")
            .located(By.xpath("//*[contains(text(),'La c\u00e9dula solo puede contener n\u00fameros')]"));
}
