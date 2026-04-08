package com.turnos.tasks;

import com.turnos.ui.DoctorFormModal;
import com.turnos.ui.DoctorsPage;
import com.turnos.util.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CreateDoctorWithSameCedula implements Task {

    private final String name;

    public CreateDoctorWithSameCedula(String name) {
        this.name = name;
    }

    @Step("{0} intenta crear un medico '#name' con la cedula almacenada")
    @Override
    public <T extends Actor> void performAs(T actor) {
        String cedula = actor.recall("lastCreatedCedula");
        actor.attemptsTo(
                Click.on(DoctorsPage.CREATE_DOCTOR_BUTTON),
                WaitUntil.the(DoctorFormModal.MODAL, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(name).into(DoctorFormModal.NAME_FIELD),
                Enter.theValue(cedula).into(DoctorFormModal.CEDULA_FIELD),
                Click.on(DoctorFormModal.SAVE_BUTTON)
        );
    }

    public static Task andName(String name) {
        return Tasks.instrumented(CreateDoctorWithSameCedula.class, name);
    }
}
