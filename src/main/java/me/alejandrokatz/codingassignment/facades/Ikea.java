package me.alejandrokatz.codingassignment.facades;

import me.alejandrokatz.codingassignment.pageobjects.ikea.*;
import org.openqa.selenium.WebDriver;

public class Ikea {
    public final SearchResults searchResults;
    public final Cart cart;
    public final HomePage homePage;


    public Ikea(WebDriver driver) {
        searchResults = new SearchResults(driver);
        cart = new Cart(driver);
        homePage = new HomePage(driver);
    }
}
