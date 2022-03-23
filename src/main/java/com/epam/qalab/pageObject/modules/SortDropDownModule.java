package com.epam.qalab.pageObject.modules;

import com.epam.qalab.pageObject.BasePage;
import com.epam.qalab.pageObject.pages.CategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SortDropDownModule extends BasePage {

    private WebElement optionHighToLowPrice;

    public SortDropDownModule(WebDriver webDriver) {
        super(webDriver);
    }

    public CategoryPage sortHighToLowPrice() {
        optionHighToLowPrice = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.linkText("Price: High to Low")));

        optionHighToLowPrice.click();
        return new CategoryPage(webDriver);
    }
}
