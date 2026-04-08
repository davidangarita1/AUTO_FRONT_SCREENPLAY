package com.turnos.questions;

import com.turnos.ui.Toast;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ToastMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(WaitUntil.the(Toast.MESSAGE, isVisible()).forNoMoreThan(10).seconds());
        return Toast.MESSAGE.resolveFor(actor).getText().trim();
    }

    public static Question<String> text() {
        return new ToastMessage();
    }
}
