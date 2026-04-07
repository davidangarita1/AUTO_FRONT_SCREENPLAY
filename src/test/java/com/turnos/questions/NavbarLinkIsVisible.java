package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class NavbarLinkIsVisible implements Question<Boolean> {

    private final String linkText;

    public NavbarLinkIsVisible(String linkText) {
        this.linkText = linkText;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return DoctorsPage.NAVBAR_DOCTORS_LINK.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> forLink(String linkText) {
        return new NavbarLinkIsVisible(linkText);
    }
}
