package me.alejandrokatz.codingassignment.pageobjects.ikea;

import me.alejandrokatz.codingassignment.pageobjects.BasePage;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        this.URL = "https://www.ikea.com/us/en/";
    }
}
