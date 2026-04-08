package com.turnos.tasks;

import com.turnos.ui.DoctorFormModal;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillDoctorForm implements Task {

    private final String name;
    private final String cedula;
    private final String office;
    private final String shift;

    public FillDoctorForm(String name, String cedula, String office, String shift) {
        this.name = name;
        this.cedula = cedula;
        this.office = office;
        this.shift = shift;
    }

    @Step("{0} diligencia el formulario de medico con nombre '#name'")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DoctorFormModal.MODAL, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(name).into(DoctorFormModal.NAME_FIELD),
                Enter.theValue(cedula).into(DoctorFormModal.CEDULA_FIELD)
        );
        if (office != null && !office.isEmpty()) {
            actor.attemptsTo(
                    SelectReactOption.inOfficeSelect(office),
                    WaitUntil.the(DoctorFormModal.SHIFT_SELECT, isEnabled()).forNoMoreThan(15).seconds(),
                    SelectReactOption.inShiftSelect(shift)
            );
        }
    }

    public static FillDoctorForm withNameAndCedula(String name, String cedula) {
        return new FillDoctorForm(name, cedula, null, null);
    }

    public static FillDoctorForm withAllFields(String name, String cedula, String office, String shift) {
        return new FillDoctorForm(name, cedula, office, shift);
    }
}
