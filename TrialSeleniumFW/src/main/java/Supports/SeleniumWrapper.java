package Supports;

import Reports.Reports;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public String getCurrentURL(){
        String currentURL = rwDriver.getCurrentUrl();
        reports.logInfo("Current page landing: " + currentURL, "");
        return currentURL;
    }

}
