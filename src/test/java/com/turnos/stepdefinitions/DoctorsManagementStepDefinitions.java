package com.turnos.stepdefinitions;

import com.turnos.questions.CedulaFieldValue;
import com.turnos.questions.CreateButtonIsVisible;
import com.turnos.questions.DoctorIsInTable;
import com.turnos.questions.DoctorOfficeInTable;
import com.turnos.questions.DoctorShiftInTable;
import com.turnos.questions.EditModalIsVisible;
import com.turnos.questions.NavbarLinkIsVisible;
import com.turnos.questions.PageTitle;
import com.turnos.questions.SaveButtonIsEnabled;
import com.turnos.questions.TableHeaders;
import com.turnos.questions.ToastMessage;
import com.turnos.questions.ValidationMessage;
import com.turnos.tasks.CloseCreateModal;
import com.turnos.tasks.CloseEditModal;
import com.turnos.tasks.CreateDoctorWithSameCedula;
import com.turnos.tasks.DeactivateDoctor;
import com.turnos.tasks.EditDoctorOfficeAndShift;
import com.turnos.tasks.FillDoctorForm;
import com.turnos.tasks.LogIn;
import com.turnos.tasks.NavigateToDoctorsModule;
import com.turnos.tasks.NavigateToSignInPage;
import com.turnos.tasks.OpenEditModalAndClickOutside;
import com.turnos.tasks.OpenEditModalAndPressEscape;
import com.turnos.tasks.RegisterDoctor;
import com.turnos.tasks.SelectReactOption;
import com.turnos.tasks.SubmitSignIn;
import com.turnos.ui.DoctorFormModal;
import com.turnos.ui.DoctorsPage;
import com.turnos.util.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.is;

public class DoctorsManagementStepDefinitions {

    private Actor employee;

    @Given("el usuario se encuentra en la pagina de inicio de sesion")
    public void employeeIsAtSignInPage() {
        employee = OnStage.theActorCalled(Constants.ACTOR_EMPLOYEE);
        employee.attemptsTo(NavigateToSignInPage.forSignIn());
    }

    @When("ingresa el correo {string} y la contrasena {string}")
    public void employeeEntersCredentials(String email, String password) {
        employee.attemptsTo(LogIn.withCredentials(email, password));
    }

    @And("hace clic en el boton de iniciar sesion")
    public void employeeClicksSignIn() {
        employee.attemptsTo(SubmitSignIn.form());
    }

    @And("navega al modulo de gestion de medicos")
    public void employeeNavigatesToDoctorsModule() {
        employee.attemptsTo(NavigateToDoctorsModule.viaNabvar());
    }

    @Then("la barra de navegacion muestra el enlace {string}")
    public void navbarShowsLink(String linkText) {
        employee.should(seeThat(NavbarLinkIsVisible.forLink(linkText), is(true)));
    }

    @Then("la pantalla muestra el titulo {string}")
    public void pageShowsTitle(String expectedTitle) {
        employee.should(seeThat(PageTitle.displayed(), is(expectedTitle)));
    }

    @And("la tabla muestra los encabezados {string}")
    public void tableShowsHeaders(String headersCsv) {
        List<String> expected = Arrays.asList(headersCsv.split(","));
        List<String> actual = TableHeaders.displayed().answeredBy(employee);
        for (String header : expected) {
            boolean found = actual.stream()
                    .anyMatch(h -> h.equalsIgnoreCase(header.trim()));
            if (!found) {
                throw new AssertionError("Header not found (case-insensitive): '" + header + "'. Actual: " + actual);
            }
        }
    }

    @And("el boton {string} es visible")
    public void buttonIsVisible(String buttonText) {
        employee.should(seeThat(CreateButtonIsVisible.onPage(), is(true)));
    }

    @When("registra al medico {string} con cedula {string}, consultorio {string} y franja {string}")
    public void registerDoctorWithOfficeAndShift(String name, String cedula, String office, String shift) {
        employee.attemptsTo(
                Click.on(DoctorsPage.CREATE_DOCTOR_BUTTON),
                FillDoctorForm.withAllFields(name, Constants.uniqueCedula(), office, shift),
                Click.on(DoctorFormModal.SAVE_BUTTON)
        );
    }

    @When("registra al medico {string} con cedula {string} sin consultorio ni franja")
    public void registerDoctorWithoutOfficeOrShift(String name, String cedula) {
        employee.attemptsTo(
                Click.on(DoctorsPage.CREATE_DOCTOR_BUTTON),
                FillDoctorForm.withNameAndCedula(name, Constants.uniqueCedula()),
                Click.on(DoctorFormModal.SAVE_BUTTON)
        );
    }

    @Then("aparece el mensaje flotante {string}")
    public void toastMessageAppears(String expectedMessage) {
        employee.should(seeThat(ToastMessage.text(), containsString(expectedMessage)));
    }

    @And("el medico {string} aparece en la tabla con consultorio {string} y franja {string}")
    public void doctorAppearsInTableWithOfficeAndShift(String doctorDisplayName, String office, String shift) {
        employee.attemptsTo(
                WaitUntil.the(DoctorsPage.doctorRow(doctorDisplayName), isVisible()).forNoMoreThan(15).seconds()
        );
        employee.should(seeThat(DoctorIsInTable.withName(doctorDisplayName), is(true)));
        employee.should(seeThat(DoctorOfficeInTable.forDoctor(doctorDisplayName), containsStringIgnoringCase(office)));
        employee.should(seeThat(DoctorShiftInTable.forDoctor(doctorDisplayName), containsString(shift)));
    }

    @Given("el formulario de creacion de medico esta abierto")
    public void createFormIsOpen() {
        employee.attemptsTo(Click.on(DoctorsPage.CREATE_DOCTOR_BUTTON));
    }

    @When("el usuario toca el campo nombre y sale sin escribir")
    public void touchNameFieldAndLeave() {
        employee.attemptsTo(
                Click.on(DoctorFormModal.NAME_FIELD),
                Click.on(DoctorFormModal.CEDULA_FIELD)
        );
    }

    @When("el usuario escribe {string} en el campo nombre")
    public void writeInNameField(String text) {
        employee.attemptsTo(
                Enter.theValue(text).into(DoctorFormModal.NAME_FIELD),
                Click.on(DoctorFormModal.CEDULA_FIELD)
        );
    }

    @When("el usuario escribe {string} en el campo cedula")
    public void writeInCedulaField(String text) {
        employee.attemptsTo(Enter.theValue(text).into(DoctorFormModal.CEDULA_FIELD));
    }

    @Then("aparece el mensaje de validacion {string}")
    public void validationMessageAppears(String expectedMessage) {
        employee.should(seeThat(ValidationMessage.containing(expectedMessage), is(true)));
    }

    @And("el boton de guardar esta deshabilitado")
    public void saveButtonIsDisabled() {
        employee.should(seeThat(SaveButtonIsEnabled.onCreateModal(), is(false)));
    }

    @Then("el campo cedula muestra solo {string}")
    public void cedulaFieldShowsOnly(String expectedValue) {
        employee.should(seeThat(CedulaFieldValue.current(), is(expectedValue)));
    }

    @When("el usuario ingresa nombre {string}, cedula {string} y selecciona consultorio {string}")
    public void enterNameCedulaAndSelectOffice(String name, String cedula, String office) {
        employee.attemptsTo(
                WaitUntil.the(DoctorFormModal.MODAL, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(name).into(DoctorFormModal.NAME_FIELD),
                Enter.theValue(cedula).into(DoctorFormModal.CEDULA_FIELD),
                SelectReactOption.inOfficeSelect(office)
        );
    }

    @Given("el medico {string} con cedula {string} esta registrado con consultorio {string} y franja {string}")
    public void doctorIsRegistered(String name, String cedula, String office, String shift) {
        employee.attemptsTo(RegisterDoctor.withDetails(name, office, shift));
    }

    @When("el usuario edita al medico {string} asignando consultorio {string} y franja {string}")
    public void editDoctorOfficeAndShift(String doctorDisplayName, String newOffice, String newShift) {
        employee.attemptsTo(EditDoctorOfficeAndShift.forDoctor(doctorDisplayName, newOffice, newShift));
    }

    @When("el usuario abre el modal de edicion del medico {string} y hace clic fuera de el")
    public void openEditModalAndClickOutside(String doctorDisplayName) {
        employee.attemptsTo(OpenEditModalAndClickOutside.forDoctor(doctorDisplayName));
    }

    @Then("el modal de edicion permanece abierto")
    public void editModalRemainsOpen() {
        employee.should(seeThat(EditModalIsVisible.onScreen(), is(true)));
    }

    @When("el usuario confirma la baja del medico {string}")
    public void confirmDoctorDeactivation(String doctorDisplayName) {
        employee.attemptsTo(DeactivateDoctor.andConfirm(doctorDisplayName));
    }

    @When("el usuario cancela la baja del medico {string}")
    public void cancelDoctorDeactivation(String doctorDisplayName) {
        employee.attemptsTo(DeactivateDoctor.andCancel(doctorDisplayName));
    }

    @And("el medico {string} no aparece en la tabla")
    public void doctorIsNotInTable(String doctorDisplayName) {
        employee.attemptsTo(
                WaitUntil.the(DoctorsPage.doctorRow(doctorDisplayName),
                        net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible())
                        .forNoMoreThan(10).seconds()
        );
        employee.should(seeThat(DoctorIsInTable.withName(doctorDisplayName), is(false)));
    }

    @Then("el medico {string} aparece en la tabla")
    public void doctorIsInTable(String doctorDisplayName) {
        employee.should(seeThat(DoctorIsInTable.withName(doctorDisplayName), is(true)));
    }

    @When("el usuario ingresa nombre {string} y cedula {string} en el formulario")
    public void enterNameAndCedulaInForm(String name, String cedula) {
        employee.attemptsTo(
                WaitUntil.the(DoctorFormModal.MODAL, isVisible()).forNoMoreThan(10).seconds(),
                Enter.theValue(name).into(DoctorFormModal.NAME_FIELD),
                Enter.theValue(cedula).into(DoctorFormModal.CEDULA_FIELD)
        );
    }

    @And("el usuario cierra el modal de creacion")
    public void closeCreateModal() {
        employee.attemptsTo(CloseCreateModal.now());
    }

    @And("el usuario toca el campo cedula y sale sin escribir")
    public void touchCedulaFieldAndLeave() {
        employee.attemptsTo(
                Click.on(DoctorFormModal.CEDULA_FIELD),
                Click.on(DoctorFormModal.NAME_FIELD)
        );
    }

    @When("el usuario intenta crear un medico {string} con la misma cedula")
    public void createDoctorWithDuplicateCedula(String name) {
        employee.attemptsTo(CreateDoctorWithSameCedula.andName(name));
    }

    @When("el usuario crea un medico con la cedula reutilizada y nombre {string}")
    public void createDoctorWithReusedCedula(String name) {
        employee.attemptsTo(CreateDoctorWithSameCedula.andName(name));
    }

    @When("el usuario abre y cierra el modal de edicion del medico {string}")
    public void openAndCloseEditModal(String doctorDisplayName) {
        employee.attemptsTo(CloseEditModal.forDoctor(doctorDisplayName));
    }

    @When("el usuario abre el modal de edicion del medico {string} y presiona Escape")
    public void openEditModalAndPressEscape(String doctorDisplayName) {
        employee.attemptsTo(OpenEditModalAndPressEscape.forDoctor(doctorDisplayName));
    }
}
