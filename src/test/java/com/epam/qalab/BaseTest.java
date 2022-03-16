package com.epam.qalab;

import com.epam.qalab.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected final WebDriver driver = new WebDriverFactory().getWebDriver();

    protected void setUpDriver() {
        driver.manage().window().maximize();
    }

    protected void quit() {
        driver.quit();
    }
}
