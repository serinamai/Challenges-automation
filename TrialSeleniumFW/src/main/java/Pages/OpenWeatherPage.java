package Pages;

import Supports.SeleniumWrapper;
import Supports.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class OpenWeatherPage extends SeleniumWrapper {

    public OpenWeatherPage(WebDriver Driver) {
        super(Driver);
    }

    By searchTextBox = By.cssSelector("form#nav-search-form input");
    By tblResult = By.cssSelector("div#forecast_list_ul table");
    By searchRow = By.xpath("//table[contains(@class,'table')]//tr");
    By loader = By.cssSelector("div.owm-loader");
    int waitTime = 10;

    public void searchCriteria(String searchText) throws Exception {
        clear(searchTextBox);
        type(searchTextBox, searchText);
        if ( new Utils().getGlobalProperties("browser").equalsIgnoreCase("firefox") ) {
            pressKey(searchTextBox, Keys.RETURN);
        } else {
            pressKey(searchTextBox, Keys.ENTER);
        }
        waitForLoaderInvisible();
    }

    public void waitForLoaderInvisible() {
        if ( isElementPresent(loader) ) {
            waitUntilElementNotVisible(loader, Duration.ofSeconds(waitTime));
        }
    }

    public boolean isResultTableVisible() {
        return isElementPresent(tblResult);
    }

    public List <String> getSearchResult() {
        return getListWebElementsByLocator(searchRow).stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }



}
