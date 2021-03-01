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

    public void searchCriteria(String searchText) throws Exception {
        type(searchTextBox, searchText);
        if(new Utils().getGlobalProperties("browser").equalsIgnoreCase("firefox")){
            pressKey(searchTextBox, Keys.RETURN);
        } else {
            pressKey(searchTextBox, Keys.ENTER);
        }
        if(isElementPresent(loader)){
            waitUntilElementNotVisible(loader, Duration.ofSeconds(10));
        }
    }

    public boolean isResultTableVisible(){
        return isElementPresent(tblResult);
    }

    public List <String> getSearchResult(){
        return getListWebElementsByLocator(searchRow).stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }

}
