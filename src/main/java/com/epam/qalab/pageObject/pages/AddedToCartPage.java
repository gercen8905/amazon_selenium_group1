package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddedToCartPage extends BasePage {

    private final By addedToCart = By.xpath("//span[contains(text(), 'Added to Cart')]");

    @FindBy(xpath = "//span[@id='nav-cart-count' and contains(text(), '1')]")
    private WebElement navCartCount;

    @FindBy(xpath = "//span[@id='nav-cart-count']")
    private WebElement cartButton;

    public AddedToCartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getAddedToCartInfo() {
        return new WebDriverWait(webDriver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(addedToCart)).getText();
    }

    public String getNavCartCount() {
        return navCartCount.getText();
    }

    public CartPage openCart() {
        cartButton.click();
        return new CartPage(webDriver);
    }
}
