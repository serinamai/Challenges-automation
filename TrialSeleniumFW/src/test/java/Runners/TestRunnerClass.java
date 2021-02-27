package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/GUITestCases.feature"},
        glue = {"StepDefinitions"}
)

public class TestRunnerClass extends AbstractTestNGCucumberTests {
}

