package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Toast {

    public static final Target MESSAGE = Target.the("toast notification message")
            .located(By.cssSelector("div[role='alert']"));
}
