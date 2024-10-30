package com.demoblaze.stepdefinitions;

import com.demoblaze.exceptions.ResultadoNoEsperado;
import com.demoblaze.questions.ValidacionLogin;
import com.demoblaze.tasks.LoginTask;
import io.cucumber.java.en.*;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class LoginStepDefinition {


    @When("seleccione Log In e ingrese el user con la clave")
    public void seleccioneLogInEIngreseElUserConLaClave()  {
        theActorInTheSpotlight().attemptsTo(LoginTask.inicioSesion());
    }

    @Then("el visualizara un mensaje de login exitoso")
    public void elVisualizaraUnMensajeDeLoginExitoso() {
        theActorInTheSpotlight().should("Verificación del login exitoso", seeThat(ValidacionLogin.validarLogin(),
                Matchers.equalTo(true)).orComplainWith(ResultadoNoEsperado.class,ResultadoNoEsperado.INICIO_SESION_FALLO));

    }
}
