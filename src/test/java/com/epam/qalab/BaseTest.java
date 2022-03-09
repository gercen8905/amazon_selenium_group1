package com.epam.qalab;
/* 
@author
Marat Lagun
created on 3/2/22   
*/

import com.epam.qalab.factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected final WebDriver driver = new WebDriverFactory().getWebDriver();
    String incorrect = "qwe4t5et6789tz3js4jdm1234567890";
    String correct = "laptop";

    protected void setUpDriver() {
        driver.manage().window().maximize();
    }

    protected void quit() {
        driver.quit();
    }
}
