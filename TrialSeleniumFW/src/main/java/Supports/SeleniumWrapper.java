package Supports;

import Reports.Reports;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SeleniumWrapper {

    public WebDriver rwDriver;
    public Reports reports = FrameworkInitiation.getReportInstance();
    public SeleniumWrapper(WebDriver Driver) {
        rwDriver = Driver;
    }

    public WebElement getElementByLocator(By Locator){
        return rwDriver.findElement(Locator);
    }

    public void type(By locator, String textToType) throws Exception {
        try {
            ExpectedConditions.elementToBeClickable(locator);
            getElementByLocator(locator).sendKeys(textToType);
            reports.logInfo("Type to element: " + textToType, "Element: " + locator.toString(), rwDriver);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            throw exception;
        }
    }

    public void pressKey(By locator, Keys keys){
        try {
            ExpectedConditions.elementToBeClickable(locator);
            getElementByLocator(locator).sendKeys(keys);
            reports.logInfo("Press to element: " + keys.name(), "Element: " + locator.toString(), rwDriver);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
            throw exception;
        }
    }

    public boolean isElementPresent(By locator){
        try {
            reports.logInfo("Check if the element is present", "Element by locator: " + locator.toString(), rwDriver);
            return rwDriver.findElements(locator).size() != 0;
        } catch (NoSuchElementException exception) {
            System.err.println(exception.getMessage());
            throw exception;
        }
    }

    public List <WebElement> getListWebElementsByLocator(By locator){
        try {
            reports.logInfo("Get a list of Web Elements", "Elements by locator: " + locator.toString(), rwDriver);
            return rwDriver.findElements(locator);
        } catch (NoSuchElementException exception) {
            System.err.println(exception.getMessage());
            throw exception;
        }
    }

    public void waitUntilElementNotVisible(By locator, Duration timeout){
        WebDriverWait wait = new WebDriverWait(rwDriver, timeout);
        wait.until(ExpectedConditions.invisibilityOf(rwDriver.findElement(locator)));
        reports.logInfo("Wait until element is NOT visible", "Elements by locator: " + locator.toString(), rwDriver);
    }

    public String getCurrentURL(){
        String currentURL = rwDriver.getCurrentUrl();
        reports.logInfo("Current page landing: " + currentURL, "");
        return currentURL;
    }

}
