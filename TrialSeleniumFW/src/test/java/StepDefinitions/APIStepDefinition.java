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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APIStepDefinition {
    public Reports reports = FrameworkInitiation.getReportInstance();
    public CustomVerification customVerification = new CustomVerification();
    public String APPID = new Utils().getGlobalProperties("appid");


    ShareState shareState;

    public APIStepDefinition(ShareState shareState) throws IOException {
        this.shareState = shareState;
    }


    @Given("I send request to search weather by city name is {string}")
    public void iSendRequestToSearchWeatherByCity(String city) throws IOException {
        sendRequestToSearchWeatherByCityName(city);
    }

    @Given("I send request to search weather by zip code is {string}")
    public void iSendRequestToSearchWeatherByZipCode(String zipCode) throws IOException {
        sendRequestToSearchWeatherByZipCode(zipCode);
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
            if(entry.getKey().equals("weather")){
                List <String> weatherInfo = Collections.singletonList(JsonPath.from(shareState.response.asString()).getString(entry.getKey()));
                reports.logInfo("The actual result: " + actual + " - The expected result: " + weatherInfo, "");
                if(weatherInfo.isEmpty()){
                    break;
                }
             } else {
                String expected = JsonPath.from(shareState.response.asString()).getString(entry.getKey());
                reports.logInfo("The actual result: " + actual + " - The expected result: " + expected, "");
                if ( actual.equals(expected) ) {
                    continue;
                } else {
                    customVerification.assertTrue(false, "The response body is NOT correct");
                    break;
                }
            }
        }
        customVerification.assertTrue(true, "The response body is correct as expected");
    }

    public void sendRequestToSearchWeatherByCityName(String city) throws IOException {
        String pathFormat = "/weather?q=%s&appid=%s";
        String path = String.format(pathFormat, city.toLowerCase(), APPID);
        shareState.response = given().contentType(ContentType.JSON).get(path);
        reports.logInfo("Send request with given path: " + path, "");
    }

    public void sendRequestToSearchWeatherByZipCode(String zipCode) throws IOException {
        String pathFormat = "/weather?zip=%s&appid=%s";
        String path = String.format(pathFormat, zipCode.toLowerCase(), APPID);
        shareState.response = given().contentType(ContentType.JSON).get(path);
        reports.logInfo("Send request with given path: " + path, "");
    }
}
