package com.demoblaze.runners;

import com.demoblaze.utils.Constants;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

public class CustomCucumberWithSerenity extends Runner {

    private final Class<CucumberWithSerenity> classValue;
    private CucumberWithSerenity cucumber;

    public CustomCucumberWithSerenity(Class<CucumberWithSerenity> classValue) throws Exception {
        this.classValue = classValue;
        System.setProperty("environment", Constants.NAVEGADOR);
        cucumber = new CucumberWithSerenity(classValue);
    }

    @Override
    public Description getDescription() {
        return cucumber.getDescription();
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            cucumber = new CucumberWithSerenity(classValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cucumber.run(notifier);
    }

}