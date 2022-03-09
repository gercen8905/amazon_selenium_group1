package com.epam.qalab.factory;

import com.epam.qalab.properties.conventors.SupportedBrowserConverter;
import com.epam.qalab.properties.holder.PropertyHolder;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public WebDriver getWebDriver() {
        return SupportedBrowserConverter.valueOfWebBrowser(
                new PropertyHolder().readProperty("browser")).getWebDriver();
    }
}
