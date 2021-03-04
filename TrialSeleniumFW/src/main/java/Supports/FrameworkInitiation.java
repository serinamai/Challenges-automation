package Supports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;

public class FrameworkInitiation {

    protected static ThreadLocal <Reports> reportInstance = new ThreadLocal <Reports>();
    protected static ThreadLocal <WebDriver> webDriver = new ThreadLocal <>();
    protected static ThreadLocal <ExtentTest> extentTest = new ThreadLocal <ExtentTest>();
    protected static ThreadLocal <ExtentReports> extentReport = new ThreadLocal <ExtentReports>();

    public static void initWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                webDriver.set(getChromeBrowser());
                break;
            case "firefox":
                webDriver.set(getFirefoxBrowser());
                break;
            default:
                throw new IllegalStateException("Unexpected browser: " + browserName.toLowerCase());
        }
    }

    public static WebDriver getDriverInstance() {
        return webDriver.get();
    }


    public static WebDriver getFirefoxBrowser() {
        String firefoxPath = "";
        if ( isWindows() ) {
            firefoxPath = System.getProperty("user.dir") + "/src/main/java/Drivers/geckodriver.exe";
        } else if ( isMac() ) {
            firefoxPath = System.getProperty("user.dir") + "/src/main/java/Drivers/geckodriver";
        } else {
            // for another OS
        }
        firefoxPath = firefoxPath.replace("/", File.separator);
        System.setProperty("webdriver.gecko.driver", firefoxPath);
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("marionette", true);
        options.addPreference("geo.enabled", false);
        options.addPreference("geo.provider.use_corelocation", false);
        options.addPreference("geo.prompt.testing", false);
        options.addPreference("geo.prompt.testing.allow", false);
        return new FirefoxDriver(options);
    }

    public static WebDriver getChromeBrowser() {
        String chromePath = "";
        if ( isWindows() ) {
            chromePath = System.getProperty("user.dir") + "/src/main/java/Drivers/chromedriver.exe";
        } else if ( isMac() ) {
            chromePath = System.getProperty("user.dir") + "/src/main/java/Drivers/chromedriver";
        } else {
            // for another OS
        }
        chromePath = chromePath.replace("/", File.separator);
        System.setProperty("webdriver.chrome.driver", chromePath);
        return new ChromeDriver();
    }

    public static String OSName() {
        return System.getProperty("os.name").toLowerCase();
    }

    public static boolean isWindows() {
        return (OSName().indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OSName().indexOf("mac") >= 0);
    }

    public static Reports getReportInstance() {
        return reportInstance.get();
    }

    public ExtentReports getExtentReport() {
        return extentReport.get();
    }

    public void closeReport() {
        extentReport.get().flush();
    }

    public ExtentReports createExtentReport(Scenario scenario) {
        String resultPath = System.getProperty("user.dir") + "/src/main/java/Result/";
        resultPath = resultPath.replace("/", File.separator);
        String currentDateTime = new Utils().getCurrentDateTime();
        String filePath = resultPath + scenario.getName() + "_" + currentDateTime;
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(filePath + ".html");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setDocumentTitle(currentDateTime);
        extent.attachReporter(spark);
        return extent;
    }
}
