package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    private final By heading = By.xpath("//h1[@class='a-spacing-mini a-spacing-top-base']");
    private final By deleteButton = By.xpath("//input[@data-action='delete']");

    @FindBy(xpath = "//span [@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']")
    private WebElement currencyPrice;

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public CartPage deleteItemFromCart() {
        WebElement delButton = new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(deleteButton));
        delButton.click();
        return new CartPage(webDriver);
    }

    public String getHeadingInfo() {
        return new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(heading)).getText();
    }

    public String getPrice() {
        return currencyPrice.getText().trim()
                .replaceFirst("\\D+", "").replace(",", ".");
    }
}
