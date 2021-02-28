package StepDefinitions;

import Reports.Reports;
import Supports.CustomVerification;
import Supports.FrameworkInitiation;
import Supports.Utils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIStepDefinition {
    public Reports reports = FrameworkInitiation.getReportInstance();
    public CustomVerification customVerification = new CustomVerification();
    ShareState shareState;

    public APIStepDefinition(ShareState shareState) {
        this.shareState = shareState;
    }


    @Given("I send request to search weather of {string}")
    public void iSendRequestToSearchWeatherOf(String city) throws IOException {
        sendRequestToSearchWeather(city);
    }

    @Then("Verify the status code is {int}")
    public void verifyTheStatusCodeIs(int statusCode) {
        customVerification.assertTrue(shareState.response.getStatusCode() == statusCode, "Send a request success");
    }

    @And("Verify the response body is correct as expected")
    public void verifyTheResponseBodyIsCorrect(DataTable data) {
        Map <String, String> mapData = data.asMap(String.class, String.class);
        for (Map.Entry <String, String> entry : mapData.entrySet()) {
            String actual = entry.getValue();
            String expected = JsonPath.from(shareState.response.asString()).getString(entry.getKey());
            reports.logInfo("The actual: " + actual + " - The expected: " + expected, "");
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            if ( actual.equals(expected) ) {
                continue;
            } else {
                customVerification.assertTrue(false, "The response body is NOT correct");
                break;
            }
        }
        customVerification.assertTrue(true, "The response body is correct as expected");
    }

    public void sendRequestToSearchWeather(String city) throws IOException {
        String APPID = new Utils().getGlobalProperties("appid");
        String pathFormat = "/weather?q=%s&appid=%s";
        String path = String.format(pathFormat, city.toLowerCase(), APPID);
        shareState.response = given().contentType(ContentType.JSON).get(path);
        reports.logInfo("Send request with given path: " + path, "");
    }
}
