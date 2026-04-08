package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class DoctorShiftInTable implements Question<String> {

    private final String doctorDisplayName;

    public DoctorShiftInTable(String doctorDisplayName) {
        this.doctorDisplayName = doctorDisplayName;
    }

    @Override
    public String answeredBy(Actor actor) {
        return DoctorsPage.doctorShiftCell(doctorDisplayName).resolveFor(actor).getText().trim();
    }

    public static Question<String> forDoctor(String doctorDisplayName) {
        return new DoctorShiftInTable(doctorDisplayName);
    }
}
