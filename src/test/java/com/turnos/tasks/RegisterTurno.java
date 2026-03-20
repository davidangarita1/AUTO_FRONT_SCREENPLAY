package com.turnos.tasks;

import com.turnos.models.RegistrationData;
import com.turnos.ui.RegistrationPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;

public class RegisterTurno implements Task {

    private final RegistrationData data;

    public RegisterTurno(RegistrationData data) {
        this.data = data;
    }

    @Step("{0} diligencia el formulario de registro con nombre '#data.fullName' y cédula '#data.cedula'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(data.getFullName()).into(RegistrationPage.FULL_NAME_FIELD),
                Enter.theValue(data.getCedula()).into(RegistrationPage.CEDULA_FIELD)
        );
    }

    public static RegisterTurno withDetails(String fullName, String cedula) {
        return Tasks.instrumented(RegisterTurno.class, new RegistrationData(fullName, cedula));
    }
}
