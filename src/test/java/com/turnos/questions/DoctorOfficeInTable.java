package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class DoctorOfficeInTable implements Question<String> {

    private final String doctorDisplayName;

    public DoctorOfficeInTable(String doctorDisplayName) {
        this.doctorDisplayName = doctorDisplayName;
    }

    @Override
    public String answeredBy(Actor actor) {
        return DoctorsPage.doctorOfficeCell(doctorDisplayName).resolveFor(actor).getText().trim();
    }

    public static Question<String> forDoctor(String doctorDisplayName) {
        return new DoctorOfficeInTable(doctorDisplayName);
    }
}
