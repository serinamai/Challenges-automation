package StepDefinitions;

import Pages.OpenWeatherPage;
import Supports.Reports;
import Supports.CustomVerification;
import Supports.FrameworkInitiation;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;

public class UIStepDefinition {

    public WebDriver driver = FrameworkInitiation.getDriverInstance();
    public CustomVerification customVerification = new CustomVerification();
    public OpenWeatherPage openWeatherPage = new OpenWeatherPage(driver);
    public Reports reports = FrameworkInitiation.getReportInstance();

    @Given("I navigate to Open Weather home page")
    public void iNavigateToOpenWeatherHomePage() throws Exception {
        driver.get("https://openweathermap.org");
        openWeatherPage.waitForLoaderInvisible();
    }

    @When("^I search weather (by invalid|by valid) city (.+) name$")
    public void iSearchWeatherByInvalidCityName(String temp, String searchText) throws Exception {
        openWeatherPage.searchCriteria(searchText);
    }

    @Then("it brought to the result page")
    public void itBroughtToTheResultPageAndNoDataReturns() {
        String currentURL = openWeatherPage.getCurrentURL();
        customVerification.assertTrue(currentURL.contains("find?q="), "It is landing as expected page", driver);
    }

    @And("no data returns")
    public void noDataReturns() {
        customVerification.assertTrue(!openWeatherPage.isResultTableVisible(), "No data returns", driver);
    }

    @And("search data returns contains text {string}")
    public void searchDataReturnedContainsText(String city) {
        boolean trigger = false;
        List <String> lstResults = openWeatherPage.getSearchResult();
        for (String item : lstResults) {
            reports.logInfo("The city in result list: " + item, "", driver);
            if ( !(item.toLowerCase().contains(city) && item.toLowerCase().contains("temperature")) ) {
                trigger = true;
                break;
            }
        }
        if ( trigger ) {
            reports.logFail("Result is not expected", "", driver);
            Assert.fail();
        }
    }

    @Then("no data returns in the result page when I search with invalid city")
    public void noDataReturnsWhenSearchingWithInvalidCity(DataTable data) throws Exception {
        boolean trigger = false;
        List <String> lstInvalidCities = data.asList();
        for (String item : lstInvalidCities) {
            openWeatherPage.searchCriteria(item);
            customVerification.assertTrue(openWeatherPage.getCurrentURL().contains("find?q="), "It is landing as expected page", driver);
            if ( openWeatherPage.isResultTableVisible() ) {
                trigger = true;
                break;
            }
        }
        if ( trigger ) {
            reports.logFail("Result is not expected", "", driver);
            Assert.fail();
        }
    }
}
