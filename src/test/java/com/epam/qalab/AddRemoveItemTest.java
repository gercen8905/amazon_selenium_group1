package com.epam.qalab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

public class AddRemoveItemTest {
    private WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void shouldAddAnyItemToCart() {
        SoftAssert softAssert = new SoftAssert();
        chooseAnyCategory();
        chooseAnyItem();
        addItemToCart();
        softAssert.assertEquals(getAddedToCartInfo(), "Added to Cart", "Adding to Cart doesn't work properly");
        softAssert.assertEquals(getNavCartCount(), "1", "Wrong number of items in the Cart");
        softAssert.assertAll();
    }

    @Test
    public void shouldRemoveItemFromCart() {
        SoftAssert softAssert = new SoftAssert();
        chooseAnyCategory();
        chooseAnyItem();
        addItemToCart();
        openCart();
        deleteItemFromCart();
        softAssert.assertEquals(getHeadingInfo(), "Your Amazon Cart is empty.", "Your Amazon Cart is not empty or not available");
        softAssert.assertEquals(getPrice(), "0.00", "Subtotal is not '0.00'");
        softAssert.assertAll();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    private void chooseAnyCategory() {
        WebElement anyCategory = driver.findElement(By.xpath("//span[@class='a-size-small a-color-base truncate-2line']"));
        anyCategory.click();
    }

    private void chooseAnyItem() {
        WebElement anyItem = driver.findElement(By.xpath("//img[@class=\"s-image\"]"));
        anyItem.click();
    }

    private void addItemToCart() {
        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        addToCartButton.click();
    }

    private String getAddedToCartInfo() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[contains(text(), 'Added to Cart')]"))).getText();
    }

    private String getNavCartCount() {
        return driver.findElement(By.xpath("//span[@id='nav-cart-count' and contains(text(), '1')]")).getText();
    }

    private void deleteItemFromCart() {
        WebElement deleteButton = driver.findElement(By.xpath("//input[@data-action='delete']"));
        deleteButton.click();
    }

    private void openCart() {
        WebElement cartButton = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
        cartButton.click();
    }

    private String getHeadingInfo() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[@class='a-spacing-mini a-spacing-top-base']"))).getText();
    }

    private String getPrice() {
        WebElement currencyPrice = driver.findElement(
                By.xpath("//span [@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']"));
        return currencyPrice.getText().trim().replaceFirst("\\D+", "").replace(",", ".");
    }
}
