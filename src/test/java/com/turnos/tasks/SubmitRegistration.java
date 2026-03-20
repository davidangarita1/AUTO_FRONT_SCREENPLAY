package com.turnos.tasks;

import com.turnos.ui.RegistrationPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

public class SubmitRegistration implements Task {

    @Step("{0} envía el formulario de registro")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(RegistrationPage.REGISTER_BUTTON));
    }

    public static Performable form() {
        return Tasks.instrumented(SubmitRegistration.class);
    }
}
