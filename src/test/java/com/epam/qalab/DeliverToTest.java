package com.epam.qalab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DeliverToTest {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");
    }

    @Test
    public void verifyZipCodeUpdate() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement oldLocation = driver.findElement(By.id("glow-ingress-line2"));

        deliverToButtonClick();

        WebElement zipCodeField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("GLUXZipUpdateInput")));
        zipCodeField.sendKeys("37219");

        WebElement applyButton = driver.findElement(By.xpath("//*[@data-action='GLUXPostalUpdateAction']"));
        applyButton.click();

        WebElement continueButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='a-popover-footer']//input[@id='GLUXConfirmClose']")));
        continueButton.click();

        wait.until(ExpectedConditions.stalenessOf(oldLocation));
        String actualLocation = driver.findElement(By.id("glow-ingress-line2")).getText();

        Assert.assertTrue(actualLocation.contains("Nashville 37219"), "The 'Deliver to' location isn't updated.");
    }

    @Test
    public void verifyPolandIsPresent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        deliverToButtonClick();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-dropdown-container']")));
        Select select = new Select(driver.findElement(By.xpath("//select[@class='a-native-dropdown']")));
        List<String> list = new ArrayList<>();
        for (WebElement element: select.getOptions()) {
            list.add(element.getText());
        }

        Assert.assertTrue(list.contains("Poland"), "The list of countries doesn't contain Poland.");
    }

    @Test
    public void verifyShippingToCountry() {
        String country = "Bulgaria";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement oldLocation = driver.findElement(By.id("glow-ingress-line2"));

        deliverToButtonClick();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='a-dropdown-container']")));
        Select select = new Select(driver.findElement(By.xpath("//select[@class='a-native-dropdown']")));
        select.selectByVisibleText(country);

        WebElement done = driver.findElement(By.xpath("//button[@name='glowDoneButton']"));
        done.click();

        wait.until(ExpectedConditions.stalenessOf(oldLocation));
        WebElement category = driver.findElement(By.xpath("//a[@aria-label='Keyboards']"));
        category.click();

        WebElement keyboardItem = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'s-main-slot ')]/div[@data-component-type='s-search-result'][1]//h2/a")));
        keyboardItem.click();

        String depositToInfo = driver
                .findElement(By.xpath("//*[@id='exports_desktop_qualifiedBuybox_tlc_feature_div']/span[@class='a-size-base a-color-secondary']")).getText();
        Assert.assertTrue(depositToInfo.contains(country), "The chosen shipping country isn't updated.");
    }

    private void deliverToButtonClick() {
        WebElement deliverToButton = driver.findElement(By.id("nav-global-location-popover-link"));
        deliverToButton.click();
    }

    @AfterMethod
    public void preparePage() {
        driver.navigate().refresh();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
