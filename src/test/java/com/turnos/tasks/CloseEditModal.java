package com.turnos.tasks;

import com.turnos.ui.DoctorEditModal;
import com.turnos.ui.DoctorsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CloseEditModal implements Task {

    private static final Target CLOSE_BUTTON = Target.the("close edit modal button")
            .located(By.xpath("//div[@data-testid='edit-modal-backdrop']//button[normalize-space(.)='Cerrar']"));

    private final String doctorDisplayName;

    public CloseEditModal(String doctorDisplayName) {
        this.doctorDisplayName = doctorDisplayName;
    }

    @Step("{0} abre y cierra el modal de edicion del medico '#doctorDisplayName'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DoctorsPage.editButton(doctorDisplayName)),
                WaitUntil.the(DoctorEditModal.MODAL, isVisible()).forNoMoreThan(10).seconds(),
                Click.on(CLOSE_BUTTON)
        );
    }

    public static Task forDoctor(String doctorDisplayName) {
        return Tasks.instrumented(CloseEditModal.class, doctorDisplayName);
    }
}
