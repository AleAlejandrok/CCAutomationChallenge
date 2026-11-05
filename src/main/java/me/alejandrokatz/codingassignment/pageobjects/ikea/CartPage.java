package me.alejandrokatz.codingassignment.pageobjects.ikea;

import me.alejandrokatz.codingassignment.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private static final By SHOW_DISCOUNT_CODE_INPUT_BUTTON = By.xpath("//div[text()='Apply discount code']");
    private static final By DISCOUNT_CODE_INPUT = By.id("discountcode-input");
    private static final By APPLY_DISCOUNT_CODE_BUTTON = By.xpath("//button[.//span[text()='Apply']]");
    private static final By DISCOUNT_CODE_ERROR_MESSAGE = By.id("discount-code__error");
    private static final By QUANTITY_INPUT = By.cssSelector("[data-skapa^='quantity-stepper']");

    public CartPage(WebDriver driver) {
        super(driver);
        this.url = "https://www.ikea.com/us/en/shoppingcart/";
    }

    public void clickDiscountButton() {
        this.click(SHOW_DISCOUNT_CODE_INPUT_BUTTON);
    }

    public void inputDiscountCode(String code) {
        this.writeText(DISCOUNT_CODE_INPUT, code);
    }

    public void clickApplyDiscountButton() {
        this.click(APPLY_DISCOUNT_CODE_BUTTON);
    }

    public String getDiscountCodeErrorMessage() {
        return this.readText(DISCOUNT_CODE_ERROR_MESSAGE);
    }

    public int getTotalItemCount() {
        List<WebElement> quantityInputs = this.findAllVisible(QUANTITY_INPUT);
        int total = 0;
        for (WebElement input : quantityInputs) {
            String value = input.getAttribute("value");
            if (value != null && !value.isBlank()) {
                total += Integer.parseInt(value.trim());
            }
        }
        return total;
    }
}
