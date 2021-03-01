package StepDefinitions;

import Pages.OpenWeatherPage;
import Supports.FrameworkInitiation;
import Pages.GooglePage;
import Supports.CustomVerification;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class UIStepDefinition {

    public WebDriver driver = FrameworkInitiation.getDriverInstance();
    public CustomVerification customVerification = new CustomVerification();
    public OpenWeatherPage openWeatherPage = new OpenWeatherPage(driver);

    @Given("I navigate to Open Weather home page")
    public void iNavigateToOpenWeatherHomePage() throws Exception {
        driver.get("https://openweathermap.org");
    }

    @When("^I search weather (by invalid|by valid) city (.+) name$")
    public void iSearchWeatherByInvalidCityName(String temp, String searchText) throws Exception {
        openWeatherPage.searchCriteria(searchText);
    }

    @Then("it brought to the result page")
    public void itBroughtToTheResultPageAndNoDataReturns() {
        String currentURL = openWeatherPage.getCurrentURL();
        customVerification.assertTrue(currentURL.contains("find?q="), "It is landing as expected page");
    }

    @And("no data returns")
    public void noDataReturns(){
        customVerification.assertTrue(!openWeatherPage.isResultTableVisible(), "No data returns");
    }

    @And("search data returns contains text {string}")
    public void searchDataReturnedContainsText(String city){
        List<String> list = openWeatherPage.getSearchResult();
        System.out.println(list);
    }
}
