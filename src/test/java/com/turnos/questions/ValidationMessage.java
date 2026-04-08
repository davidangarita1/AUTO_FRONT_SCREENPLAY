package com.turnos.questions;

import com.turnos.ui.DoctorFormModal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidationMessage implements Question<Boolean> {

    private final String expectedText;

    public ValidationMessage(String expectedText) {
        this.expectedText = expectedText;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return DoctorFormModal.validationMessage(expectedText).resolveFor(actor).isVisible();
        } catch (Exception e) {
            return false;
        }
    }

    public static Question<Boolean> containing(String text) {
        return new ValidationMessage(text);
    }
}
