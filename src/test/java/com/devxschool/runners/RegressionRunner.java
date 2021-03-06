package com.devxschool.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.devxschool.steps"},
        //tags = {"@Regression", "~@ignore"},
        tags = {"@cart_item"},
        plugin = {"json:target/cucumber.json"}, //is responsible for creating(generating) junit reports in json format
        //which is needed for cucumber reports to create the statistics of the tests.
        format = {"pretty", "html:target/reports"} ,//format option is used for
        //generating cucumber html reports from cucumber.json file
        //html -is a face of the webpage. it's used for creating webpages. static webpage.
        dryRun = false
)

public class RegressionRunner {
}
