package com.turnos.tasks;

import com.turnos.ui.LoginPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;

public class LogIn implements Task {

    private final String email;
    private final String password;

    public LogIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Step("{0} ingresa el correo '#email' y la contrasena")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(email).into(LoginPage.EMAIL_FIELD),
                Enter.theValue(password).into(LoginPage.PASSWORD_FIELD)
        );
    }

    public static LogIn withCredentials(String email, String password) {
        return Tasks.instrumented(LogIn.class, email, password);
    }
}
