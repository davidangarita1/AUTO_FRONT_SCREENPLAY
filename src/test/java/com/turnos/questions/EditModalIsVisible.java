package com.turnos.questions;

import com.turnos.ui.DoctorEditModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class EditModalIsVisible implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return DoctorEditModal.MODAL.resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public static Question<Boolean> onScreen() {
        return new EditModalIsVisible();
    }
}
