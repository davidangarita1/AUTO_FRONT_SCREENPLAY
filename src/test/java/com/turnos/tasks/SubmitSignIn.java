package com.turnos.tasks;

import com.turnos.ui.DoctorsPage;
import com.turnos.ui.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SubmitSignIn implements Task {

    @Step("{0} hace clic en el boton de iniciar sesion")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LoginPage.SIGN_IN_BUTTON),
                WaitUntil.the(DoctorsPage.NAVBAR_DOCTORS_LINK, isVisible()).forNoMoreThan(15).seconds()
        );
    }

    public static Performable form() {
        return Tasks.instrumented(SubmitSignIn.class);
    }
}
