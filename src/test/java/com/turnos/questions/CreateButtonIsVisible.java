package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class CreateButtonIsVisible implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return DoctorsPage.CREATE_DOCTOR_BUTTON.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> onPage() {
        return new CreateButtonIsVisible();
    }
}
