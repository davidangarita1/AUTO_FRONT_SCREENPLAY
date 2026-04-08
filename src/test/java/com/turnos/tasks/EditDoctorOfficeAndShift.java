package com.turnos.tasks;

import com.turnos.ui.DoctorEditModal;
import com.turnos.ui.DoctorsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class EditDoctorOfficeAndShift implements Task {

    private final String doctorDisplayName;
    private final String newOffice;
    private final String newShift;

    public EditDoctorOfficeAndShift(String doctorDisplayName, String newOffice, String newShift) {
        this.doctorDisplayName = doctorDisplayName;
        this.newOffice = newOffice;
        this.newShift = newShift;
    }

    @Step("{0} edita al medico '#doctorDisplayName' asignando consultorio '#newOffice' y franja '#newShift'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DoctorsPage.editButton(doctorDisplayName)),
                WaitUntil.the(DoctorEditModal.MODAL, isVisible()).forNoMoreThan(10).seconds(),
                SelectReactOption.inEditOfficeSelect(newOffice),
                WaitUntil.the(DoctorEditModal.SHIFT_SELECT, isEnabled()).forNoMoreThan(15).seconds(),
                SelectReactOption.inEditShiftSelect(newShift),
                Click.on(DoctorEditModal.SAVE_BUTTON)
        );
    }

    public static Task forDoctor(String doctorDisplayName, String newOffice, String newShift) {
        return Tasks.instrumented(EditDoctorOfficeAndShift.class, doctorDisplayName, newOffice, newShift);
    }
}
