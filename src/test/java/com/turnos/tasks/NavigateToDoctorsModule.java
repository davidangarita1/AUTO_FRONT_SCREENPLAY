package com.turnos.tasks;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToDoctorsModule implements Task {

    @Step("{0} navega al modulo de gestion de medicos")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DoctorsPage.NAVBAR_DOCTORS_LINK),
                WaitUntil.the(DoctorsPage.PAGE_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
    }

    public static Performable viaNabvar() {
        return Tasks.instrumented(NavigateToDoctorsModule.class);
    }
}
