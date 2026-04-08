package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DoctorFormModal {

    public static final Target MODAL = Target.the("create doctor modal")
            .located(By.cssSelector("[data-testid='modal-backdrop']"));

    public static final Target NAME_FIELD = Target.the("doctor name field")
            .located(By.cssSelector("#doctor-nombre"));

    public static final Target CEDULA_FIELD = Target.the("doctor cedula field")
            .located(By.cssSelector("#doctor-cedula"));

    public static final Target OFFICE_SELECT = Target.the("doctor office select")
            .located(By.cssSelector("#doctor-consultorio"));

    public static final Target SHIFT_SELECT = Target.the("doctor shift select")
            .located(By.cssSelector("#doctor-franja"));

    public static final Target SAVE_BUTTON = Target.the("save doctor button")
            .located(By.xpath("//div[@data-testid='modal-backdrop']//button[normalize-space(.)='Guardar']"));

    public static Target validationMessage(String text) {
        return Target.the("validation message: " + text)
                .located(By.xpath("//*[contains(normalize-space(.), '" + text + "')]"));
    }
}
