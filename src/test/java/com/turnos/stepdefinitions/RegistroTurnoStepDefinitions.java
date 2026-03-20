package com.turnos.stepdefinitions;

import com.turnos.questions.CedulaFormatErrorMessage;
import com.turnos.questions.RegistrationSuccessMessage;
import com.turnos.tasks.NavigateToRegisterPage;
import com.turnos.tasks.RegisterTurno;
import com.turnos.tasks.SubmitRegistration;
import com.turnos.util.Constants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.is;

public class RegistroTurnoStepDefinitions {

    private Actor patient;

    @Given("que el paciente se encuentra en la pagina de registro de turnos")
    public void patientIsOnTurnoRegistrationPage() {
        patient = OnStage.theActorCalled(Constants.ACTOR_PATIENT);
        patient.attemptsTo(NavigateToRegisterPage.onRegisterPage());
    }

    @When("registra un turno con nombre {string} y cedula {string}")
    public void patientRegistersTurno(String name, String cedula) {
        patient.attemptsTo(
                RegisterTurno.withDetails(name, cedula),
                SubmitRegistration.form()
        );
    }

    @Then("el sistema confirma el registro exitoso del turno")
    public void systemConfirmsTurnoRegistered() {
        patient.should(seeThat(RegistrationSuccessMessage.isDisplayed(), is(true)));
    }

    @Then("el sistema muestra el mensaje de formato invalido de cedula")
    public void systemShowsCedulaFormatError() {
        patient.should(seeThat(CedulaFormatErrorMessage.isDisplayed(), is(true)));
    }
}
