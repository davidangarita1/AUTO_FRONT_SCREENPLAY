package com.turnos.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class ScenarioHooks implements Task {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void clearStage() {
        OnStage.drawTheCurtain();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Punto de extensión para tareas de configuración de escenario
    }
}

