package Supports;

import Reports.Reports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FrameworkInitiation {

    public static WebDriver driver;
    protected static ThreadLocal <Reports> reportInstance = new ThreadLocal <Reports>();
    protected static ThreadLocal <WebDriver> webDriver = new ThreadLocal <>();
    protected static ThreadLocal <ExtentTest> extentTest = new ThreadLocal <ExtentTest>();
    protected static ThreadLocal <ExtentReports> extentReport = new ThreadLocal <ExtentReports>();

    public static void initWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = getChromeBrowser();
                webDriver.set(driver);
                break;
            case "firefox":
                driver = getFirefoxBrowser();
                webDriver.set(driver);
                break;
            default:
                throw new IllegalStateException("Unexpected browser: " + browserName.toLowerCase());
        }
    }

    public static WebDriver getDriverInstance() {
        return webDriver.get();
    }


    public static WebDriver getFirefoxBrowser() {
        String firefoxPath = System.getProperty("user.dir") + "/src/main/java/Drivers/geckodriver-v0.26.0";
        firefoxPath = firefoxPath.replace("/", File.separator);
        System.setProperty("webdriver.gecko.driver", firefoxPath);
        DesiredCapabilities dcap = new DesiredCapabilities();
        dcap.setCapability("pageLoadStrategy", "eager");
        FirefoxOptions options = new FirefoxOptions();

        options.addPreference("marionette", true);
        options.addPreference("browser.download.folderList", 1);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, text/csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, 	application/vnd.ms-excel, application/xml, text/xml,application/vnd.ms-excel, application/download, application/octet-stream, text/csv, application/vnd.ms-excel, application/msexcel, application/x-msexcel, application/excel");
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.download.manager.focusWhenStarting", false);
        options.addPreference("browser.helperApps.alwaysAsk.force", false);
        options.addPreference("pdfjs.disabled", true);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getChromeBrowser() {
        String chromePath = "";
        if ( isWindows() ) {
            chromePath = System.getProperty("user.dir") + "/src/main/java/Drivers/chromedriverForWin.exe";
        } else if ( isMac() ) {
            chromePath = System.getProperty("user.dir") + "/src/main/java/Drivers/chromedriverForMac";
        } else {
            // for another OS
        }
        chromePath = chromePath.replace("/", File.separator);
        System.setProperty("webdriver.chrome.driver", chromePath);
        DesiredCapabilities dcap = new DesiredCapabilities();
        dcap.setCapability("pageLoadStrategy", "eager");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
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

//    public void setReportInstance() {
//        extentReport.set(createExtentReport());
//        reportInstance.set(new Reports(getExtentReport()));
//    }

    public static Reports getReportInstance() {
        return reportInstance.get();
    }

    public void setExtentTest() {
        extentTest.set(getExtentReport().createTest("", ""));
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

    public void getStepText(Scenario scenario) {

    }

}
