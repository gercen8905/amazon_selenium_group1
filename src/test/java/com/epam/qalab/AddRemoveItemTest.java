package com.epam.qalab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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
        Assert.assertEquals(addedToCart.getText(), "Added to Cart");

        WebElement navCartCount = driver.findElement(By.xpath("//span[@id='nav-cart-count' and contains(text(), '1')]"));
        Assert.assertEquals(navCartCount.getText(), "1");

        driver.close();
        driver.quit();
    }
}
