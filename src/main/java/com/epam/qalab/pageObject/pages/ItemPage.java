package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends BasePage {

    private final By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");

    public ItemPage(WebDriver webDriver) {
        super(webDriver);
    }

    public AddedToCartPage addItemToCart() {
        WebElement addButton = new WebDriverWait(webDriver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        addButton.click();
        return new AddedToCartPage(webDriver);
    }


}
