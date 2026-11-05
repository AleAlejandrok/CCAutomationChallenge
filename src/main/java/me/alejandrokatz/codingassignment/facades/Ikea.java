package me.alejandrokatz.codingassignment.facades;

import me.alejandrokatz.codingassignment.pageobjects.ikea.CartPage;
import me.alejandrokatz.codingassignment.pageobjects.ikea.HomePage;
import me.alejandrokatz.codingassignment.pageobjects.ikea.SearchResultsPage;
import org.openqa.selenium.WebDriver;

public class Ikea {
    public final SearchResultsPage searchResults;
    public final CartPage cart;
    public final HomePage homePage;

    public Ikea(WebDriver driver) {
        this.searchResults = new SearchResultsPage(driver);
        this.cart = new CartPage(driver);
        this.homePage = new HomePage(driver);
    }
}
