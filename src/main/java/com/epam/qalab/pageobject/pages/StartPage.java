package com.epam.qalab.pageobject.pages;
/* 
@author
Marat Lagun
created on 3/2/22   
*/

import com.epam.qalab.pageobject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage extends BasePage {
    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    public StartPage(WebDriver driver) {
        super(driver);
    }

    public StartPage open() {
        driver.get("https://www.amazon.com");
        return this;
    }

    public SearchResultPage search(String input) {
        WebElement searchField = new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("twotabsearchtextbox")));
        searchField.clear();
        searchField.sendKeys(input);
        searchButton.click();
        return new SearchResultPage(driver);

    }
}
