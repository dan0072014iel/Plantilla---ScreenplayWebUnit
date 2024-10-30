package com.demoblaze.stepdefinitions;

import com.demoblaze.interactions.AbrirNavegador;
import com.demoblaze.utils.Excel;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;


import java.io.IOException;

import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class Hooks {

    private static final String RUTA_EXCEL = "src/main/resources/data/DatosExcel.xlsx";
    private static final String HOJA_EXCEL = "Logs";
    private EnvironmentVariables environmentVariables;

    @Before
    public void configuration(){
        String navigator = environmentVariables.getProperty("environment");
        switch (navigator.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().driverVersion("").browserVersion("").arch64().clearResolutionCache().clearDriverCache().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().driverVersion("").browserVersion("").arch64().clearResolutionCache().clearDriverCache().setup();
                break;
            case "edge":
                WebDriverManager.edgedriver().driverVersion("").browserVersion("").arch64().clearResolutionCache().clearDriverCache().setup();
                break;
            default:
                throw new IllegalArgumentException("Navegador no soportado: " + navigator);
        }
        setTheStage(new OnlineCast());
    }

    @Given("que el usuario se encuentre en la pagina web")
    public void queElUsuarioSeEncuentreEnLaPaginaWeb() {
        theActorCalled("Daniel").wasAbleTo(AbrirNavegador.abrir());
    }

    @After
    public void tearDown(Scenario scenario) {
        String status = scenario.getStatus().name();
        String message = scenario.getName();
        String thread = Thread.currentThread().getName();
        String level = status.equals("FAILED") ? "ERROR" : "INFO";
        String logger = "Funcional";
        String tester = "Daniel Felipe Duarte Castañeda";

        Excel.LogEntry logEntry = new Excel.LogEntry(level, logger, message, status, thread, tester);

        try {
            Excel.escribirDatosEnHojaDeExcel(RUTA_EXCEL, HOJA_EXCEL, logEntry);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo Excel: " + e.getMessage());
            e.printStackTrace();
        }
    }
}