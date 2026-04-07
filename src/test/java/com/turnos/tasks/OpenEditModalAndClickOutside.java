package com.turnos.tasks;

import com.turnos.ui.DoctorEditModal;
import com.turnos.ui.DoctorsPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenEditModalAndClickOutside implements Task {

    private final String doctorDisplayName;

    public OpenEditModalAndClickOutside(String doctorDisplayName) {
        this.doctorDisplayName = doctorDisplayName;
    }

    @Step("{0} abre el modal de edicion del medico '#doctorDisplayName' y hace clic fuera de el")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(DoctorsPage.editButton(doctorDisplayName)),
                WaitUntil.the(DoctorEditModal.MODAL, isVisible()).forNoMoreThan(10).seconds()
        );
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        WebElement backdrop = DoctorEditModal.MODAL.resolveFor(actor).getElement();
        int halfWidth = backdrop.getSize().getWidth() / 2;
        int halfHeight = backdrop.getSize().getHeight() / 2;
        new Actions(driver)
                .moveToElement(backdrop, -halfWidth + 5, -halfHeight + 5)
                .click()
                .perform();
    }

    public static Task forDoctor(String doctorDisplayName) {
        return Tasks.instrumented(OpenEditModalAndClickOutside.class, doctorDisplayName);
    }
}
