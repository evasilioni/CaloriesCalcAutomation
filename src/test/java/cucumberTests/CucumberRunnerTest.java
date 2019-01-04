package cucumberTests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
                plugin = {"pretty",
                        "html:target/cucumber-htmlreport",
                        "json:target/cucumber-report.json"},
                glue = "stepImplementations")
public class CucumberRunnerTest {
}
