package me.alejandrokatz.codingassignment.e2e;

import me.alejandrokatz.codingassignment.facades.Ikea;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;
    protected Ikea ikea;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--disable-extensions");

        if (Boolean.getBoolean("headless") || Boolean.parseBoolean(System.getenv("CI"))) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);
        ikea = new Ikea(driver);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
