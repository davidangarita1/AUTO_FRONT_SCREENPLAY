package com.turnos.questions;

import com.turnos.ui.DoctorFormModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CedulaFieldValue implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return DoctorFormModal.CEDULA_FIELD.resolveFor(actor).getValue().trim();
    }

    public static Question<String> current() {
        return new CedulaFieldValue();
    }
}
