package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/"},
        glue = {"StepDefinitions"}
)

public class TestRunnerClass extends AbstractTestNGCucumberTests {
    private TestNGCucumberRunner testNGCucumberRunner;
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios(){
        return super.scenarios();
    }
}

