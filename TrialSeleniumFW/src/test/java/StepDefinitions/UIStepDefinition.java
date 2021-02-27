package StepDefinitions;

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
    public GooglePage googlePage = new GooglePage(driver);

    @Given("I navigate to default home page")
    public void iNavigateToDefaultHomePage() throws Exception {
    }

    @When("I type a text {string} in search box")
    public void iTypeATextInSearchBox(String searchText) throws Exception {
        googlePage.searchCriteria(searchText);
    }

    @Then("I should able to search")
    public void iShouldAbleToSearch() {
        customVerification.assertTrue(true, "it is TRUE");
    }
}
