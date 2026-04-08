package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginPage {

    public static final Target EMAIL_FIELD = Target.the("email field")
            .located(By.cssSelector("input[type='email'][placeholder='Email']"));

    public static final Target PASSWORD_FIELD = Target.the("password field")
            .located(By.cssSelector("input[type='password'][placeholder='Contraseña']"));

    public static final Target SIGN_IN_BUTTON = Target.the("sign in button")
            .located(By.cssSelector("button[type='submit']"));
}
