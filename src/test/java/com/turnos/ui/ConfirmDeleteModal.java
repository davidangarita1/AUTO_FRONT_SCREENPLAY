package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ConfirmDeleteModal {

    public static final Target MODAL = Target.the("confirm delete modal")
            .located(By.cssSelector("[data-testid='confirm-delete-overlay']"));

    public static final Target MESSAGE = Target.the("confirmation message text")
            .located(By.xpath("//div[@data-testid='confirm-delete-overlay']//*[contains(normalize-space(.), 'Dr.')]"));

    public static final Target ACCEPT_BUTTON = Target.the("accept deactivation button")
            .located(By.xpath("//div[@data-testid='confirm-delete-overlay']//button[normalize-space(.)='Aceptar']"));

    public static final Target CANCEL_BUTTON = Target.the("cancel deactivation button")
            .located(By.xpath("//div[@data-testid='confirm-delete-overlay']//button[normalize-space(.)='Cancelar']"));
}
