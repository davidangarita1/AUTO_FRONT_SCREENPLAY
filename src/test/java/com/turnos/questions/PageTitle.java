package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PageTitle implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return DoctorsPage.PAGE_TITLE.resolveFor(actor).getText().trim();
    }

    public static Question<String> displayed() {
        return new PageTitle();
    }
}
