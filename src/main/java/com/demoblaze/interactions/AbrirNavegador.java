package com.demoblaze.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

import static com.demoblaze.utils.Constants.*;

public class AbrirNavegador implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (ENTORNO.equalsIgnoreCase("dev")) {
            actor.attemptsTo(
                    Open.url(URL_DEV)
            );
        } else if (ENTORNO.equalsIgnoreCase("qa")) {
            actor.attemptsTo(
                    Open.url(URL_QA)
            );
        } else if (ENTORNO.equalsIgnoreCase("prep")) {
            actor.attemptsTo(
                    Open.url(URL_PREP)
            );
        } else {
            actor.attemptsTo(
                    Open.url(URL_PROD)
            );
        }
    }

    public static AbrirNavegador abrir() {
        return Tasks.instrumented(AbrirNavegador.class);

    }
}
