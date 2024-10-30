package com.demoblaze.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaInicioUI {

    private PaginaInicioUI() {

    }

    public static final Target BTN_LOGIN = Target.the("Log In").locatedBy("//a[@id='login2']");
    public static final Target TXT_USUARIO = Target.the("usuario").locatedBy("//input[@id='loginusername']");
    public static final Target TXT_CLAVE = Target.the("ingresar clave").locatedBy("//input[@id='loginpassword']");
    public static final Target BTN_INGRESAR = Target.the("boton ingresar").locatedBy("//button[@onclick='logIn()']");
}