package com.demoblaze.utils;

public class Constants {

    private Constants() {
    }

    public static final String USUARIO = CallData.extractToLogin().get(0).get("Usuario");
    public static final String CLAVE = CallData.extractToLogin().get(0).get("Clave");

    public static final String URL_DEV = CallData.extractToVariables().get(0).get("Url Dev");
    public static final String URL_QA = CallData.extractToVariables().get(0).get("Url QA");
    public static final String URL_PREP = CallData.extractToVariables().get(0).get("Url Prep");
    public static final String URL_PROD = CallData.extractToVariables().get(0).get("Url Prod");
    public static final String ENTORNO = CallData.extractToVariables().get(0).get("Entorno");
    public static final String NAVEGADOR = CallData.extractToVariables().get(0).get("Navegador");

    public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String RUTA_EXCEL1 = "src/main/resources/Data/DatosExcel.xlsx";

}
