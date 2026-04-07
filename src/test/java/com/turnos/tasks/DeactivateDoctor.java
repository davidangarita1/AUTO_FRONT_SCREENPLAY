package com.turnos.tasks;

import com.turnos.ui.ConfirmDeleteModal;
import com.turnos.ui.DoctorsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class DeactivateDoctor implements Task {

    private final String doctorDisplayName;
    private final boolean confirm;

    public DeactivateDoctor(String doctorDisplayName, boolean confirm) {
        this.doctorDisplayName = doctorDisplayName;
        this.confirm = confirm;
    }

    @Step("{0} interactua con el modal de baja del medico '#doctorDisplayName'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DoctorsPage.deleteButton(doctorDisplayName)),
                WaitUntil.the(ConfirmDeleteModal.MODAL, isVisible()).forNoMoreThan(10).seconds()
        );
        if (confirm) {
            actor.attemptsTo(Click.on(ConfirmDeleteModal.ACCEPT_BUTTON));
        } else {
            actor.attemptsTo(Click.on(ConfirmDeleteModal.CANCEL_BUTTON));
        }
    }

    public static Task andConfirm(String doctorDisplayName) {
        return Tasks.instrumented(DeactivateDoctor.class, doctorDisplayName, true);
    }

    public static Task andCancel(String doctorDisplayName) {
        return Tasks.instrumented(DeactivateDoctor.class, doctorDisplayName, false);
    }
}
