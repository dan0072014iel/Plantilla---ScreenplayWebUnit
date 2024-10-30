package com.demoblaze.questions;

import com.demoblaze.userinterfaces.PaginaUsuarioUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static com.demoblaze.utils.Constants.*;

public class ValidacionLogin implements Question<Boolean> {

    @Override
    @Subject("Se valida si se visualiza el nombre del usuario en la seccion de cuenta")
    public Boolean answeredBy(Actor actor) {

        return PaginaUsuarioUI.MSJ_LOGIN.of(USUARIO).resolveFor(actor).waitUntilVisible().isVisible();
    }

    public static Question<Boolean> validarLogin() {
        return new ValidacionLogin();
    }
}
