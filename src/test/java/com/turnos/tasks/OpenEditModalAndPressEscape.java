package com.turnos.tasks;

import com.turnos.ui.DoctorEditModal;
import com.turnos.ui.DoctorsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenEditModalAndPressEscape implements Task {

    private final String doctorDisplayName;

    public OpenEditModalAndPressEscape(String doctorDisplayName) {
        this.doctorDisplayName = doctorDisplayName;
    }

    @Step("{0} abre el modal de edicion del medico '#doctorDisplayName' y presiona Escape")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DoctorsPage.editButton(doctorDisplayName)),
                WaitUntil.the(DoctorEditModal.MODAL, isVisible()).forNoMoreThan(10).seconds()
        );
        WebElement modal = DoctorEditModal.MODAL.resolveFor(actor).getElement();
        modal.sendKeys(Keys.ESCAPE);
    }

    public static Task forDoctor(String doctorDisplayName) {
        return Tasks.instrumented(OpenEditModalAndPressEscape.class, doctorDisplayName);
    }
}
