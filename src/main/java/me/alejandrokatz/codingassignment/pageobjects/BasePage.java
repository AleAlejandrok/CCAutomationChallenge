package me.alejandrokatz.codingassignment.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String URL;

    private static final By RIBBON_SEARCH_INPUT = By.id("ikea-search-input");
    private static final By COOKIE_DISMISS_BUTTOn = By.id("onetrust-accept-btn-handler");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(this.URL);
        dismissCookieBanner();
    }

    protected void dismissCookieBanner() {
        try {
            WebElement acceptButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(COOKIE_DISMISS_BUTTOn));
            acceptButton.click();
        } catch (Exception ignored) {}
    }

    protected void waitForVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement getElement(By locator) {
        waitForVisibility(locator);
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        WebElement element = getElement(locator);
        try{
            element.click();
        }
        catch (ElementClickInterceptedException ignored) {
            new Actions(driver).scrollToElement(element).perform();
            element.click();
        }
    }

    protected void writeText(By locator, String text) {
        WebElement element = this.getElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String readText(By locator) {
        return this.getElement(locator).getText();
    }

    protected boolean isVisible(By locator) {
        return this.getElement(locator).isDisplayed();
    }

    protected List<WebElement> findAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    protected void scrollToTop() {
        new Actions(driver)
            .scrollByAmount(0, -10000)
            .perform();
    }

    public void ribbonSearch(String term) {
        scrollToTop();
        writeText(RIBBON_SEARCH_INPUT, term);
        driver.findElement(RIBBON_SEARCH_INPUT).sendKeys(Keys.ENTER);
    }
}
