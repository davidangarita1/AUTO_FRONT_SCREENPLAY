package com.turnos.questions;

import com.turnos.ui.DoctorFormModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class SaveButtonIsEnabled implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return DoctorFormModal.SAVE_BUTTON.resolveFor(actor).isEnabled();
    }

    public static Question<Boolean> onCreateModal() {
        return new SaveButtonIsEnabled();
    }
}
