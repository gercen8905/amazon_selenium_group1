package com.epam.qalab;

import org.openqa.selenium.WebDriver;
import com.epam.qalab.factory.WebDriverFactory;

public class BaseTest {
    protected final WebDriver webDriver = new WebDriverFactory().getWebDriver();

    protected void setUpDriver() {
        webDriver.manage().window().maximize();
    }

    protected void quit() {
        webDriver.quit();
    }
}
