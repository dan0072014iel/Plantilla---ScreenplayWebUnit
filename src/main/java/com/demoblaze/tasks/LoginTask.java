package com.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

import static com.demoblaze.userinterfaces.PaginaInicioUI.*;
import static com.demoblaze.utils.Constants.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginTask implements Task {

    @Override
    @Step("seleccione Log In e ingrese el user con la clave")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(BTN_LOGIN),
                Enter.theValue(USUARIO).into(TXT_USUARIO),
                Enter.theValue(CLAVE).into(TXT_CLAVE),
                Click.on(BTN_INGRESAR)
        );

    }

    public static Performable inicioSesion () {
        return instrumented(LoginTask.class);
    }
}
