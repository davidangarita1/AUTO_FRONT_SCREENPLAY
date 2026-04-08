package com.turnos.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DoctorsPage {

    public static final Target NAVBAR_DOCTORS_LINK = Target.the("doctors module navbar link")
            .located(By.xpath("//a[normalize-space(.)='Gestión Médicos']"));

    public static final Target PAGE_TITLE = Target.the("doctors page title")
            .located(By.xpath("//h1[normalize-space(.)='Gestión de Médicos']"));

    public static final Target TABLE_HEADERS = Target.the("table headers")
            .located(By.cssSelector("table th"));

    public static final Target CREATE_DOCTOR_BUTTON = Target.the("create doctor button")
            .located(By.xpath("//button[normalize-space(.)='Crear médico']"));

    public static Target doctorRow(String doctorDisplayName) {
        return Target.the("doctor row for " + doctorDisplayName)
                .located(By.xpath("//td[normalize-space(.)='" + doctorDisplayName + "']"));
    }

    public static Target doctorOfficeCell(String doctorDisplayName) {
        return Target.the("office cell for " + doctorDisplayName)
                .located(By.xpath("//tr[.//td[normalize-space(.)='" + doctorDisplayName + "']]//td[3]"));
    }

    public static Target doctorShiftCell(String doctorDisplayName) {
        return Target.the("shift cell for " + doctorDisplayName)
                .located(By.xpath("//tr[.//td[normalize-space(.)='" + doctorDisplayName + "']]//td[4]"));
    }

    public static Target editButton(String doctorDisplayName) {
        return Target.the("edit button for " + doctorDisplayName)
                .located(By.cssSelector("button[aria-label='Editar " + doctorDisplayName + "']"));
    }

    public static Target deleteButton(String doctorDisplayName) {
        return Target.the("delete button for " + doctorDisplayName)
                .located(By.cssSelector("button[aria-label='Dar de baja " + doctorDisplayName + "']"));
    }
}
