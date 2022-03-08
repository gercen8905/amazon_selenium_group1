package com.epam.qalab.enumeration;

import com.epam.qalab.invoker.WebDriverInvoker;
import com.epam.qalab.invoker.implementations.LocalChromeInvoker;
import com.epam.qalab.invoker.implementations.LocalFirefoxInvoker;
import org.openqa.selenium.WebDriver;

public enum SupportedBrowsers {
    LOCAL_FIREFOX(new LocalFirefoxInvoker()),
    LOCAL_CHROME(new LocalChromeInvoker());

    private WebDriverInvoker webDriverInvoker;

    SupportedBrowsers(WebDriverInvoker webDriverInvoker) {
        this.webDriverInvoker = webDriverInvoker;
    }

    public WebDriver getWebDriver() {
        return webDriverInvoker.invokeWebDriver();
    }
}
