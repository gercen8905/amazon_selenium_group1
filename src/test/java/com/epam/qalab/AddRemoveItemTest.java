package com.epam.qalab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRemoveItemTest {

    @Test
    public void addItemToCartTest() {
        WebDriverManager.chromedriver().browserVersion("98").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.amazon.com/", "The current link is not as expected");

        WebElement anyCategory = driver.findElement(By.xpath("(//span[@class='a-size-small a-color-base truncate-2line'])[2]"));
        anyCategory.click();

        WebElement anyItem = driver.findElement(By.xpath("(//img[@class=\"s-image\"])[1]"));
        anyItem.click();

        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        addToCartButton.click();

        WebElement addedToCart = driver.findElement(By.xpath("//span[contains(text(), 'Added to Cart')]"));
        Assert.assertEquals(addedToCart.getText(), "Added to Cart", "Adding to Cart doesn't work properly");

        WebElement navCartCount = driver.findElement(By.xpath("//span[@id='nav-cart-count' and contains(text(), '1')]"));
        Assert.assertEquals(navCartCount.getText(), "1", "Wrong number of items in the Cart");

        driver.close();
        driver.quit();
    }

    @Test
    public void removeItemFromCartTest() {
        WebDriverManager.chromedriver().browserVersion("98").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.amazon.com/", "The current link is not as expected");

        WebElement anyCategory = driver.findElement(By.xpath("(//span[@class='a-size-small a-color-base truncate-2line'])[2]"));
        anyCategory.click();

        WebElement anyItem = driver.findElement(By.xpath("(//img[@class=\"s-image\"])[1]"));
        anyItem.click();

        WebElement addToCartButton = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        addToCartButton.click();

        WebElement basketButton = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
        basketButton.click();

        WebElement deleteButton = driver.findElement(By.xpath("//input[@data-action='delete']"));
        deleteButton.click();

        WebElement heading = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='a-spacing-mini a-spacing-top-base']")));
        Assert.assertEquals(heading.getText(), "Your Amazon Cart is empty.", "Your Amazon Cart is not empty or not available");

        WebElement currencyPrice = driver.findElement(By.xpath("//span [@class='a-size-medium a-color-base sc-price sc-white-space-nowrap']"));
        String price = currencyPrice.getText().trim().replaceFirst("\\D+", "").replace(",", ".");
        Assert.assertEquals(price, "0.00", "Subtotal is not '0.00'");

        driver.close();
        driver.quit();
    }
}
