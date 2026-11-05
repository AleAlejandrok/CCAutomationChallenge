package me.alejandrokatz.codingassignment.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String url;

    private static final By RIBBON_SEARCH_INPUT = By.id("ikea-search-input");
    private static final By COOKIE_DISMISS_BUTTON = By.id("onetrust-accept-btn-handler");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(this.url);
        dismissCookieBanner();
    }

    protected void dismissCookieBanner() {
        try {
            WebElement acceptButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(COOKIE_DISMISS_BUTTON));
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
        try {
            element.click();
        } catch (ElementClickInterceptedException ignored) {
            new Actions(driver).scrollToElement(element).perform();
            element.click();
        }
    }

    protected void writeText(By locator, String text) {
        WebElement element = getElement(locator);
        element.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.BACK_SPACE);
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        element.clear();
        element.sendKeys(text);
    }

    protected String readText(By locator) {
        return getElement(locator).getText();
    }

    protected List<WebElement> findAllVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public Object executeJs(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public void scrollIntoViewCenter(WebElement element) {
        executeJs("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    protected void scrollToTop() {
        executeJs("window.scrollTo(0, 0);");
    }

    public void ribbonSearch(String term) {
        scrollToTop();
        writeText(RIBBON_SEARCH_INPUT, term);
        getElement(RIBBON_SEARCH_INPUT).sendKeys(Keys.ENTER);
    }
}
