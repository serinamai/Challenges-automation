package Supports;

import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActions extends FrameworkInitiation {
    private static int minTimeout = 5;

    private static WebDriverWait wait;

//    public static void waitForElementPresent(By by){
//        wait = new WebDriverWait(driver, minTimeout);
//        wait.until(ExpectedConditions.presenceOfElementLocated(by));
//    }
//
//    public static void setText(By by, String text){
//        waitForElementPresent(by);
//        driver.findElement(by).sendKeys(text);
//    }
//    public static void click(By by){
//        waitForElementPresent(by);
//        driver.findElement(by).click();
//    }


}
