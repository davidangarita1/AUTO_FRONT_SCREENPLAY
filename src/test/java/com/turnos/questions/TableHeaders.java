package com.turnos.questions;

import com.turnos.ui.DoctorsPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.stream.Collectors;

public class TableHeaders implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        List<WebElementFacade> headers = DoctorsPage.TABLE_HEADERS.resolveAllFor(actor);
        return headers.stream()
                .map(h -> h.getText().trim())
                .collect(Collectors.toList());
    }

    public static Question<List<String>> displayed() {
        return new TableHeaders();
    }
}
