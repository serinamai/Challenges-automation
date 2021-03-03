package Supports;

import Reports.Reports;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class CustomVerification {

    public Reports reports = FrameworkInitiation.getReportInstance();

    public void assertTrue(boolean condition, String mgs) {
        try {
            Assert.assertTrue(condition);
            reports.logPass(mgs, "");
        } catch (AssertionError e) {
            reports.logFail(e.getMessage(), "");
            Assert.fail();
        }
    }

    public void assertTrue(boolean condition, String mgs, WebDriver driver) {
        try {
            Assert.assertTrue(condition);
            reports.logPass(mgs, "", driver);
        } catch (AssertionError e) {
            reports.logFail(e.getMessage(), "");
            Assert.fail();
        }
    }

    public void assertFalse(boolean condition, String mgs, WebDriver driver) {
        try {
            Assert.assertFalse(condition);
            reports.logFail(mgs, "", driver);
        } catch (AssertionError e) {
            reports.logFail(e.getMessage(), "");
            Assert.fail();
        }
    }


}
