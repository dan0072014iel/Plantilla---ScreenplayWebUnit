En este mapeo se puede usar el {0} para dinamizar el valor de un array que viene por defecto en un xpath:
public static final Target SELECT_SONG = Target.the("producto en el carrito").locatedBy("//td[{0}]");

public class SelectRandomSong implements Interaction {

    private String selectedSong;

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<WebElementFacade> listSong = SELECT_SONG.resolveAllFor(actor);
        Random random = new Random();
        int index = random.nextInt(listSong.size());
        //actor.remember("song", listSong.get(index).getText());
        //listSong.get(index).click();
        actor.remember("song", index);
    }
    public static SelectRandomSong selectSong(){
        return instrumented(SelectRandomSong.class);
    }
}

public class SelectSong implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectRandomSong.selectSong(),
                Click.on(SELECT_SONG.of(String.valueOf(actor.recall("song"))))
        );
    }

    public static Performable inicioSesion () {
        return instrumented(LoginTask.class);
    }
}


Metodos para usar una clase question dinamica si la comparten mas de un scenario:

@Before
setTheStage(new OnlineCast());
        String nombreFeature = scenario.getName();
        if (nombreFeature.contains("Inicio de sesion")) {
            Serenity.setSessionVariable("validacionActual").to(QuestionDinamica.LOGIN.getQuestion());
        } else if (nombreFeature.contains("Registrar formulario")) {
            Serenity.setSessionVariable("validacionActual").to(QuestionDinamica.TEXTO_ALERTA.getQuestion());
        }

@Then("{string}{string}")
    public void string(String string, String status) {
        Question<?> question = Serenity.sessionVariableCalled("validacionActual");
        Class<?> type = question.getClass();
        if (type == String.class) {
            @SuppressWarnings("unchecked")
            Question<String> stringQuestion = (Question<String>) question;
            theActorInTheSpotlight().should("Verificación del resultado", seeThat(stringQuestion,
                    containsString("Thanks for the message!!"))
            );
        }else if (type == Boolean.class) {
            @SuppressWarnings("unchecked")
            Question<Boolean> booleanQuestion = (Question<Boolean>) question;
            theActorInTheSpotlight().should("Verificación del resultado", seeThat(booleanQuestion,
                    Matchers.equalTo(Boolean.parseBoolean(status))).orComplainWith(ResultadoNoEsperado.class,ResultadoNoEsperado.MENSAJE_FALLO));
        }
    }

Clase GenericQuestion:
package com.demoblaze.utils;

import net.serenitybdd.screenplay.Question;

public interface GenericQuestion<T> {
    Question<?> getQuestion();
    Class<?> getType();
}

Clase QuestionDinamica:
package com.demoblaze.utils;

import com.demoblaze.questions.ValidacionLogin;
import com.demoblaze.questions.ValidacionTextoAlerta;
import net.serenitybdd.screenplay.Question;

import java.util.function.Supplier;

public enum QuestionDinamica implements GenericQuestion<Object> {
    LOGIN(ValidacionLogin::validarLogin, Boolean.class),
    TEXTO_ALERTA(ValidacionTextoAlerta::validacionAlerta, String.class);

    private final Supplier<Question<?>> questionSupplier;
    private final Class<?> type; // Almacena el tipo de la pregunta

    QuestionDinamica(Supplier<Question<?>> questionSupplier, Class<?> type) {
        this.questionSupplier = questionSupplier;
        this.type = type;
    }

    @Override
    public Question<?> getQuestion() {
        return questionSupplier.get();
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}



Configuracion navegadores:

Gradle:
tasks.withType(Test) {
    //systemProperty 'environment', 'chrome'
    systemProperty 'buildTool', 'gradle'
}

Serenity.conf:
environment = "chrome"


Dependencias opcionales de maven:
<!-- <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <includes>
                        <include>**/*Runner.java</include>
                    </includes>
                </configuration>
            </plugin>-->
<!--<plugin>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok-maven-plugin</artifactId>
                <version>1.18.20.0</version>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>delombok</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>-->
            <!--<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>19</source>
                    <target>19</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>-->
        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>
