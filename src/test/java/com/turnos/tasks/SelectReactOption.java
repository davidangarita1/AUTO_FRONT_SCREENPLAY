package com.turnos.tasks;

import com.turnos.ui.DoctorEditModal;
import com.turnos.ui.DoctorFormModal;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class SelectReactOption implements Task {

    private final Target select;
    private final String value;

    public SelectReactOption(Target select, String value) {
        this.select = select;
        this.value = value;
    }

    @Step("{0} selecciona la opcion '#value' en el selector React")
    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = select.resolveFor(actor).getElement();
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript(
                "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(" +
                "window.HTMLSelectElement.prototype, 'value').set;" +
                "nativeInputValueSetter.call(arguments[0], arguments[1]);" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element, value
        );
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static SelectReactOption withValue(String value, Target select) {
        return Tasks.instrumented(SelectReactOption.class, select, value);
    }

    public static SelectReactOption inOfficeSelect(String office) {
        return Tasks.instrumented(SelectReactOption.class, DoctorFormModal.OFFICE_SELECT, office);
    }

    public static SelectReactOption inShiftSelect(String shift) {
        return Tasks.instrumented(SelectReactOption.class, DoctorFormModal.SHIFT_SELECT, shift);
    }

    public static SelectReactOption inEditOfficeSelect(String office) {
        return Tasks.instrumented(SelectReactOption.class, DoctorEditModal.OFFICE_SELECT, office);
    }

    public static SelectReactOption inEditShiftSelect(String shift) {
        return Tasks.instrumented(SelectReactOption.class, DoctorEditModal.SHIFT_SELECT, shift);
    }
}
