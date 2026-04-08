package com.turnos.tasks;

import com.turnos.ui.DoctorFormModal;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CloseCreateModal implements Task {

    private static final Target CLOSE_BUTTON = Target.the("close create modal button")
            .located(By.xpath("//div[@data-testid='modal-backdrop']//button[normalize-space(.)='Cerrar']"));

    @Step("{0} cierra el modal de creacion")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(CLOSE_BUTTON));
    }

    public static Task now() {
        return Tasks.instrumented(CloseCreateModal.class);
    }
}
