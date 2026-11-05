package me.alejandrokatz.codingassignment.e2e;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IkeaTest extends BaseTest {
    @Test
    @DisplayName("Adding two items and applying an invalid discount code shows an error")
    public void invalidDiscountCodeShowsError() {
        ikea.homePage.open();

        ikea.homePage.ribbonSearch("sofa");
        ikea.searchResults.addNthProductToCart(1); // 1-based position

        ikea.homePage.ribbonSearch("table");
        ikea.searchResults.addNthProductToCart(3);

        ikea.cart.open();
        assertEquals(2, ikea.cart.getTotalItemCount(), "Cart should contain 2 items");

        String randomCode = UUID.randomUUID().toString().replace("-", "").substring(0, 15);
        ikea.cart.clickDiscountButton();
        ikea.cart.inputDiscountCode(randomCode);
        ikea.cart.clickApplyDiscountButton();

        String errorCode = ikea.cart.getDiscountCodeErrorMessage();
        assertEquals("Please enter a valid discount code.", errorCode, "Error message should be 'Please enter a valid discount code.'");
    }
}
