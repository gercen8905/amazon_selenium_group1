package com.epam.qalab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class FilteringAndSearchingTest {

    WebDriver driver;

    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUp() {
        driver.get("https://www.amazon.com/");
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        driver.quit();
    }


    @Test
    public void filteredByFeaturedBrand() {

        this.choseCategory();

        WebElement anyFeaturedBrand = driver.findElement(By.xpath("//*[text()=\"Featured Brands\"]/parent::*/parent::*/ul/li[1]//a/span"));
        String nameFeaturedBrand = anyFeaturedBrand.getAttribute("textContent");
        anyFeaturedBrand.click();

        List<WebElement> productsDescription = driver.findElements(By.xpath("//*[contains(@cel_widget_id,\"RESULTS\")]//h2"));

        Assert.assertTrue(productsDescription.stream().allMatch(item -> item.getText().contains(nameFeaturedBrand)));
    }

    @Test
    public void filteringByPrice() {

        this.choseCategory();

        WebElement priceRangeOption = driver.findElement(By.xpath("//*[text()='Price']/parent::*/following-sibling::ul/li[1]//a"));
        priceRangeOption.click();

        List<WebElement> productsPrices = driver.findElements(By.xpath("//*[@class=\"a-price\"]/span[1]"));
        ArrayList<Double> prices = new ArrayList<>();

        for (WebElement product:productsPrices) {
            prices.add(Double.parseDouble(product.getAttribute("textContent").substring(1)));
        }
        Assert.assertTrue(prices.stream().allMatch(priceElem -> priceElem <= 25));
    }

    @Test
    public void sortingInsideCategory() {

        this.choseCategory();

        WebElement btnSeeAllResults = driver.findElement(By.linkText("See all results"));
        btnSeeAllResults.click();

        WebElement btnDropDown = driver.findElement(By.xpath("//*[contains(@class,\"a-button-dropdown\")]/span"));
        btnDropDown.click();

        WebElement optionHighToLowPrice = driver.findElement(By.linkText("Price: High to Low"));
        optionHighToLowPrice.click();

        List<WebElement> productsPrices = driver.findElements(By.xpath("//*[@class=\"a-price\"]/span[1]"));

        Double firstElemPrice = Double.parseDouble(productsPrices.get(0).getAttribute("textContent").substring(1));
        Double secondElemPrice = Double.parseDouble(productsPrices.get(1).getAttribute("textContent").substring(1));

        Assert.assertTrue(firstElemPrice > secondElemPrice);
    }

    public void choseCategory() {
        WebElement categoryLink = driver.findElement(By.xpath("(//*[@class=\"a-link-normal quadrant-overlay\"])[1]"));
        categoryLink.click();
    }
}
