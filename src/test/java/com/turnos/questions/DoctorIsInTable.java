package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class DoctorIsInTable implements Question<Boolean> {

    private final String doctorDisplayName;

    public DoctorIsInTable(String doctorDisplayName) {
        this.doctorDisplayName = doctorDisplayName;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return DoctorsPage.doctorRow(doctorDisplayName).resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public static Question<Boolean> withName(String doctorDisplayName) {
        return new DoctorIsInTable(doctorDisplayName);
    }
}
