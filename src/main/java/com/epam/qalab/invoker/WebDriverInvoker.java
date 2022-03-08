package com.epam.qalab.invoker;


import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface WebDriverInvoker {
    WebDriver invokeWebDriver();
}
