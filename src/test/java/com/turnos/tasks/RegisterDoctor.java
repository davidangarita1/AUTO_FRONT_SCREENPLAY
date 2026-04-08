package com.turnos.tasks;

import com.turnos.ui.DoctorFormModal;
import com.turnos.ui.DoctorsPage;
import com.turnos.util.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegisterDoctor implements Task {

    private final String name;
    private final String office;
    private final String shift;

    public RegisterDoctor(String name, String office, String shift) {
        this.name = name;
        this.office = office;
        this.shift = shift;
    }

    @Step("{0} registra al medico '#name' con consultorio '#office' y franja '#shift'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        String cedula = Constants.uniqueCedula();
        actor.remember("lastCreatedCedula", cedula);
        actor.attemptsTo(
                Click.on(DoctorsPage.CREATE_DOCTOR_BUTTON),
                FillDoctorForm.withAllFields(name, cedula, office, shift),
                Click.on(DoctorFormModal.SAVE_BUTTON),
                WaitUntil.the(DoctorsPage.doctorRow("Dr. " + name), isVisible()).forNoMoreThan(15).seconds()
        );
    }

    public static Task withDetails(String name, String office, String shift) {
        return Tasks.instrumented(RegisterDoctor.class, name, office, shift);
    }
}
