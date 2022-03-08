package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[@class='a-size-small a-color-base truncate-2line']")
    private WebElement anyCategory;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public CategoryPage chooseAnyCategory() {
        anyCategory.click();
        return new CategoryPage(webDriver);
    }
}
