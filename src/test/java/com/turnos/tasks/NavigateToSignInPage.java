package com.turnos.tasks;

import com.turnos.ui.ApplicationUrls;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateToSignInPage implements Task {

    @Step("{0} navega a la pagina de inicio de sesion")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(ApplicationUrls.signinPage()));
    }

    public static Performable forSignIn() {
        return Tasks.instrumented(NavigateToSignInPage.class);
    }
}
