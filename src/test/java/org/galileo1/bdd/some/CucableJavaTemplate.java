package org.galileo1.bdd.some;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "org.galileo1.bdd.stepdefs",
        features = {"target/parallel/features/[CUCABLE:FEATURE].feature"},
        plugin = {"json:target/cucumber-report/[CUCABLE:RUNNER].json"}
)
public class CucableJavaTemplate {
    // [CUCABLE:CUSTOM:comment]
}