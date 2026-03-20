package com.turnos.tasks;

import com.turnos.ui.ApplicationUrls;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateToRegisterPage implements Task {

    @Step("{0} navega a la página de registro de turnos")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(ApplicationUrls.registerPage()));
    }

    public static Performable onRegisterPage() {
        return Tasks.instrumented(NavigateToRegisterPage.class);
    }
}
