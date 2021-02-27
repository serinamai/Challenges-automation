package Supports;

import Reports.Reports;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

public class CustomVerification {
    private StringBuffer verificationErrors;
    public Reports reports = FrameworkInitiation.getReportInstance();

//    public SoftAssert sa = new SoftAssert();

    public CustomVerification() {
        verificationErrors = new StringBuffer();
    }

    public SoftAssert verifyTrue(SoftAssert sa, Boolean b, String msg) {
        try {
//            Assert.assertTrue(b.booleanValue());

            sa.assertTrue(b);
            sa.assertTrue(b.equals(true), "Failed");
//            sa.assertAll();
        } catch (Error e) {

            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
        return sa;
    }

    public void endVerification(SoftAssert sa) {
        try {
            sa.assertAll();
        } catch (Error e) {
            System.out.println("This test case is FAILED");
        }
    }

    public void verifyFalse(String msg, Boolean b) {
        try {
            Assert.assertFalse(b.booleanValue());
        } catch (Error e) {

            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void verifyTrue(String elementDefinition, String elementLocator, Boolean b, String pageURL) {
        try {
            Assert.assertTrue(b.booleanValue());
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log("Verification failed for element: " + elementDefinition + ", Element locator: " + elementLocator + "On page: " + pageURL);
        }
    }

    public void verifyTrue(String elementDefinition, String elementLocator, boolean isElementPresent,
                           int actualElementCount, int expectedElementCount, String pageURL) {

        try {
            Assert.assertTrue(isElementPresent);
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log("Verification failed for element: " + elementDefinition + ", Element locator: " + elementLocator + "On page: " + pageURL);
        }

        try {
            Assert.assertTrue(actualElementCount == expectedElementCount);
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log("Verification count failed for element: " + elementDefinition + ", Element locator: " + elementLocator +
                    ", Expected Element Count: " + expectedElementCount +
                    ", while Actual Element Count: " + actualElementCount +
                    " On page: " + pageURL);
        }
    }

    public void verifyEquals(String msg, String s1, String s2) {
        try {
            Assert.assertEquals(s1, s2);
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void verifyEquals(String msg, Object obj1, Object obj2) {
        try {
            Assert.assertEquals(obj1, obj2);
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void verifyNotEquals(String msg, Object obj1, Object obj2) {
        try {
            Assert.assertNotEquals(obj1, obj2);
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void verifyEquals(String msg, String str1[], String str2[]) {
        try {
            Assert.assertEquals(str1, str2);
        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void verifyEquals(Object str1[], Object str2[], String msg) {
        try {
            Assert.assertEquals(((Object) (str1)), ((Object) (str2)));

        } catch (Error e) {
            verificationErrors.append(e);
            Reporter.log(msg + "");
        }
    }

    public void clearVerificationErrors() {
        verificationErrors = new StringBuffer();

    }

    public void assertTrue(boolean condition,String mgs){
        try{
            Assert.assertTrue(condition);
            reports.logPass(mgs, "");
        } catch (Error e){
            reports.logFail(mgs, "");
            Assert.fail();
        }
    }


}
