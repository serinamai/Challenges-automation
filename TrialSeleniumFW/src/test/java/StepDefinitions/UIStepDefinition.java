package StepDefinitions;

import Pages.OpenWeatherPage;
import Supports.FrameworkInitiation;
import Pages.GooglePage;
import Supports.CustomVerification;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class UIStepDefinition {

    public WebDriver driver = FrameworkInitiation.getDriverInstance();
    public CustomVerification customVerification = new CustomVerification();
    public OpenWeatherPage openWeatherPage = new OpenWeatherPage(driver);

    @Given("I navigate to Open Weather home page")
    public void iNavigateToOpenWeatherHomePage() throws Exception {
        driver.get("https://openweathermap.org");
    }

    @When("I search weather by invalid city name {string}")
    public void iSearchWeatherByInvalidCityName(String searchText) throws Exception {
        openWeatherPage.searchCriteria(searchText);
    }

    @Then("it brought to the result page and no data returns")
    public void itBroughtToTheResultPageAndNoDataReturns() {
        String currentURL = openWeatherPage.getCurrentURL();
        customVerification.assertTrue(currentURL.contains("find?q="), "It is landing as expected page");
        customVerification.assertTrue(!openWeatherPage.isResultTableVisible(), "No data returns");
    }
}
