package com.devxschool.steps;

import com.devxschool.utils.config.Config;
import com.devxschool.utils.db.DataBaseUtils;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;


public class Hooks {

    //Implement DB Connection Steps here
    private static boolean isExecuted = false;

    public static Scenario scenarioInfo; // instance to reuse for all project for feature files

    @Before
    public void testExecutionSetup(Scenario scenario) throws Exception { // Scenario interface  comes from Cucumber
        scenarioInfo = scenario; // assign from the local
        if (!isExecuted) {
            //DataBaseUtils.connectToDatabase();
            // RestAssured.baseURI = Config.getPropertiesValue("food_delivery_base_")
            isExecuted = true;
            System.out.println("tag " + scenario.getSourceTagNames()); // will print current tag you run
            boolean isToRun = scenario.getSourceTagNames().toString().contains("api");
            if (isToRun) {
                System.out.println("i want to run api");
            } else {
                System.out.println("no api tag");
            }
        }
        System.out.println("this is our print " + chooseEnvironment());
    }
    private static String chooseEnvironment () {
        String systemProperties = System.getProperty("environment"); // setup in terminal , to run thought terminal
        if (null == systemProperties) {
            systemProperties = Config.getPropertiesValue("environment");
        }
        return systemProperties;
    }




    @After
    public void testFinishExecutionSetup() throws Exception {
        if (isExecuted) {
            DataBaseUtils.closeConnection();
            isExecuted = false;
        }
    }
}
