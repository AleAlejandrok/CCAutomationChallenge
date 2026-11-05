package me.alejandrokatz.codingassignment.pageobjects.ikea;

import me.alejandrokatz.codingassignment.pageobjects.BasePage;
import org.openqa.selenium.*;

import java.util.List;

public class SearchResultsPage extends BasePage {

    private static final By PRODUCT_CARD = By.cssSelector("[data-testid='plp-product-card']");
    private static final By ADD_TO_CART_BUTTON = By.cssSelector("button:has(use[href='#plp-add_to_cart-'])");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        this.url = "https://www.ikea.com/us/en/search/";
    }

    public void addNthProductToCart(int n) {
        wait.until(driver -> {
            try {
                List<WebElement> cards = driver.findElements(PRODUCT_CARD);
                if (cards.size() < n) {
                    return null;
                }

                WebElement card = cards.get(n - 1);
                WebElement addToCartButton = card.findElement(ADD_TO_CART_BUTTON);
                scrollIntoViewCenter(addToCartButton);

                cards = driver.findElements(PRODUCT_CARD);
                card = cards.get(n - 1);
                addToCartButton = card.findElement(ADD_TO_CART_BUTTON);
                addToCartButton.click();
                return true;
            } catch (StaleElementReferenceException e) {
                return null;
            }
        });
    }
}
