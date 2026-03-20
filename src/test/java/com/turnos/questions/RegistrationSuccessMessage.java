package com.turnos.questions;

import com.turnos.ui.RegistrationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RegistrationSuccessMessage implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return RegistrationPage.SUCCESS_MESSAGE.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> isDisplayed() {
        return new RegistrationSuccessMessage();
    }
}
