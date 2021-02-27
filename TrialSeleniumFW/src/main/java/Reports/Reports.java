package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reports {
    public ExtentReports reports;
    public ExtentTest childNode;
    public ExtentTest rootNode;

    public Reports(ExtentReports extentReports) {
        this.reports = extentReports;
    }


    public void setExtentTest(ExtentTest test){
        rootNode = test;
        childNode = test;
    }

    public ExtentTest getExtentTest() {
        return childNode;
    }

    public void logAC(String description){
        childNode = rootNode.createNode(Scenario.class, description);
    }

    public void logPass(String stepName, String msg, WebDriver driver){
        String img = captureToBase64(driver);
        childNode.createNode(stepName).pass(MarkupHelper.createLabel(msg, ExtentColor.GREEN)).pass(MediaEntityBuilder.createScreenCaptureFromBase64String(img).build());
    }

    public void logPass(String stepName, String msg){
        childNode.createNode(stepName).pass(MarkupHelper.createLabel(msg, ExtentColor.GREEN));
    }

    public void logFail(String stepName, String msg, WebDriver driver){
        String img = captureToBase64(driver);
        childNode.createNode(stepName).fail(MarkupHelper.createLabel(msg, ExtentColor.RED)).fail(MediaEntityBuilder.createScreenCaptureFromBase64String(img).build());
    }

    public void logFail(String stepName, String msg){
        childNode.createNode(stepName).fail(MarkupHelper.createLabel(msg, ExtentColor.RED));
    }

    public void logInfo(String stepName, String msg, WebDriver driver){
        String img = captureToBase64(driver);
        childNode.createNode(stepName).info(MarkupHelper.createLabel(msg, ExtentColor.BLUE)).info(MediaEntityBuilder.createScreenCaptureFromBase64String(img).build());
    }

    public void logInfo(String stepName, String msg){
        childNode.createNode(stepName).info(MarkupHelper.createLabel(msg, ExtentColor.BLUE));
    }


    private String captureToBase64(WebDriver webDriver) {
        TakesScreenshot newScreen = (TakesScreenshot) webDriver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot;
    }

}
