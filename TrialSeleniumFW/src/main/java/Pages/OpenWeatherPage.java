package Pages;

import Supports.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;

public class OpenWeatherPage extends SeleniumWrapper {

    public OpenWeatherPage(WebDriver Driver) {
        super(Driver);
    }

    By searchTextBox = By.cssSelector("form#nav-search-form input");
    By tblResult = By.cssSelector("div#forecast_list_ul table");
    By searchRow = By.xpath("//table[contains(@class,'table')]//tr");

    public void searchCriteria(String searchText) throws Exception {
        type(searchTextBox, searchText);
        pressKey(searchTextBox, Keys.ENTER);
    }

    public boolean isResultTableVisible(){
        return isElementPresent(tblResult);
    }

    public List <String> getSearchResult(){
        return getListWebElementsByLocator(searchRow).stream().map(ele -> ele.getText()).collect(Collectors.toList());
    }

}
