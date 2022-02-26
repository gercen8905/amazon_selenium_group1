package com.epam.qalab;
/* 
@author
Marat Lagun
created on 2/25/22   
*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class SearchFieldTest {
    WebDriver driver;
    String exec = "src/test/resources/chromedriver";
    WebElement searchField;
    String incorrect = "qwe4t5et6789tz3js4jdm1234567890";
    String correct = "laptop";

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", exec);
        driver = new ChromeDriver();
        driver.get("https://www.amazon.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void incorrectInputShouldReturnNoResults() {
        search(incorrect);
        String noResults = driver.findElement(By.xpath("//*[@class=\"a-row\"]/descendant::span[1]")).getText();
        String actualWithDot = driver.findElement(By.xpath("//*[@class=\"a-row\"]/descendant::span[2]")).getText() + ".";
        Assert.assertEquals(noResults + " " + actualWithDot, "No results for " + incorrect + ".",
                "Nevertheless something was found");
    }

    @Test
    public void searchedProductShouldBeDisplayedInSearchResults() {
        search(correct);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class=\"a-color-state a-text-bold\"]"))
                        .getText().replace("\"", ""), correct,
                "Name of product is not displayed correctly");
    }

    @Test
    public void foundProductsShouldContainSearchedWord() {
        search(correct);
        List<WebElement> searchResult =
                driver.findElements(By.xpath("//*[contains(@class,\"normal\") and contains(text()," + incorrect.toUpperCase() + ")]"));
        System.out.println(searchResult.size());
        Assert.assertTrue(searchResult.size() > 0, "There is no name of product in the results of search");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    private void search(String input) {
        searchField = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchField.clear();
        searchField.sendKeys(input);
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();
    }
}
