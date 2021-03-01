package StepDefinitions;

import Reports.Reports;
import Supports.FrameworkInitiation;
import Supports.Utils;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import io.restassured.RestAssured;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Hook extends FrameworkInitiation {

    public Reports reports;
    private int counter = 0;

    @BeforeStep
    public void logACStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        TestCaseState tcs = (TestCaseState) f.get(scenario);
        Field f2 = tcs.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r = (TestCase) f2.get(tcs);
        List <PickleStepTestStep> stepDefs = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        PickleStepTestStep currentStepDef = stepDefs.get(counter);
        String currentStep = currentStepDef.getStep().getText();
        String currentGherkin = currentStepDef.getStep().getKeyword();
        reports = reportInstance.get();
        reports.logAC(currentGherkin + currentStep);
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        counter += 1;
    }

    @Before
    public void initFramework(Scenario scenario) throws IOException {
        extentReport.set(createExtentReport(scenario));
        reportInstance.set(new Reports(getExtentReport()));
        extentTest.set(extentReport.get().createTest(Feature.class, scenario.getName()));
        getReportInstance().setExtentTest(extentTest.get());
        Collection <String> lstTagName = scenario.getSourceTagNames();
        if ( lstTagName.contains("@UI") ) {
            extentTest.get().assignCategory("UI",new Utils().getGlobalProperties("browser"), System.getProperty("os.name"));
            initWebDriver(new Utils().getGlobalProperties("browser"));
            driver = getDriverInstance();
            driver.get(new Utils().getGlobalProperties("url"));
        } else {
            extentTest.get().assignCategory("API");
            String scheme = new Utils().getGlobalProperties("scheme");
            String host = new Utils().getGlobalProperties("host");
            String basePath = new Utils().getGlobalProperties("path");
            String baseURL = scheme.concat("://").concat(host);
            RestAssured.baseURI = baseURL;
            RestAssured.basePath = basePath;
        }
    }

    @After
    public void teardown(Scenario scenario) {
        Status result = scenario.getStatus();
        if ( result == Status.FAILED ) {
            reports.getExtentTest().fail(MarkupHelper.createLabel(scenario.getName() + " Test cases is FAILED", ExtentColor.RED));
        } else if ( result == Status.PASSED ) {
            reports.getExtentTest().pass(MarkupHelper.createLabel(scenario.getName() + " Test cases is PASSED", ExtentColor.GREEN));
        } else {
            reports.getExtentTest().skip(MarkupHelper.createLabel(scenario.getName() + " Test cases is SKIPPED", ExtentColor.YELLOW));
        }
        if ( driver != null ) {
            driver.quit();
        }
        closeReport();
    }
}
