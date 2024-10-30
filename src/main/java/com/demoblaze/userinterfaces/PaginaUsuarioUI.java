package com.demoblaze.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class PaginaUsuarioUI {

    private PaginaUsuarioUI() {

    }

    public static final Target MSJ_LOGIN = Target.the("mensaje de login exitoso").locatedBy("//a[text()='Welcome {0}']");

}
