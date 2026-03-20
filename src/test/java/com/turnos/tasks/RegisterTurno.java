package com.turnos.tasks;

import com.turnos.ui.RegistrationPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;

public class RegisterTurno implements Task {

    private final String fullName;
    private final String cedula;

    public RegisterTurno(String fullName, String cedula) {
        this.fullName = fullName;
        this.cedula = cedula;
    }

    @Step("{0} diligencia el formulario de registro con nombre '#fullName' y cédula '#cedula'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(fullName).into(RegistrationPage.FULL_NAME_FIELD),
                Enter.theValue(cedula).into(RegistrationPage.CEDULA_FIELD)
        );
    }

    public static RegisterTurno withDetails(String fullName, String cedula) {
        return Tasks.instrumented(RegisterTurno.class, fullName, cedula);
    }
}
