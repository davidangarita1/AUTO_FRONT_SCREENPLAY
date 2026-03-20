package com.turnos.questions;

import com.turnos.ui.RegistrationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CedulaFormatErrorMessage implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return RegistrationPage.CEDULA_FORMAT_ERROR.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> isDisplayed() {
        return new CedulaFormatErrorMessage();
    }
}
