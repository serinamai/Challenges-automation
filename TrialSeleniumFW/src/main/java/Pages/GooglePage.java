package Pages;

import Supports.SeleniumWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class GooglePage extends SeleniumWrapper{
    public GooglePage(WebDriver Driver) {
        super(Driver);
    }

    public WebDriver driver;
    By searchTextBox = By.xpath("//input[@name='q']");

    public void searchCriteria(String searchText) throws Exception {
        type(searchTextBox, searchText);
    }




}
