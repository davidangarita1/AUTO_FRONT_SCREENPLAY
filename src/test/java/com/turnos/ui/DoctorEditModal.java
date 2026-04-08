package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DoctorEditModal {

    public static final Target MODAL = Target.the("edit doctor modal")
            .located(By.cssSelector("[data-testid='edit-modal-backdrop']"));

    public static final Target OFFICE_SELECT = Target.the("edit doctor office select")
            .located(By.cssSelector("#edit-doctor-consultorio"));

    public static final Target SHIFT_SELECT = Target.the("edit doctor shift select")
            .located(By.cssSelector("#edit-doctor-franja"));

    public static final Target SAVE_BUTTON = Target.the("save edit doctor button")
            .located(By.xpath("//div[@data-testid='edit-modal-backdrop']//button[normalize-space(.)='Guardar']"));
}
