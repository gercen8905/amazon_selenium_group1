package com.epam.qalab;
/* 
@author
Marat Lagun
created on 2/25/22   
*/

import com.epam.qalab.pageobject.pages.CorrectSearchResultPage;
import com.epam.qalab.pageobject.pages.IncorrectSearchResultPage;
import com.epam.qalab.pageobject.pages.SearchResultPage;
import com.epam.qalab.pageobject.pages.StartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;


public class SearchFieldTest extends BaseTest {
    String incorrect = "qwe4t5et6789tz3js4jdm1234567890";
    String correct = "laptop";

    @BeforeTest
    @Override
    protected void setUpDriver() {
        super.setUpDriver();
    }

    @AfterTest
    @Override
    protected void quit() {
        super.quit();
    }

    @Test
    public void incorrectInputShouldReturnNoResults() {
        StartPage startPage = new StartPage(driver);
        SearchResultPage search = startPage.open().search(incorrect);
        Assert.assertEquals(search.getInstance(IncorrectSearchResultPage.class).getNoResultsText() + " "
                        + search.getInstance(IncorrectSearchResultPage.class).getActualWithDot(),
                "No results for " + incorrect + ".",
                "Nevertheless something was found");
    }

    @Test
    public void searchedProductShouldBeDisplayedInSearchResults() {
        StartPage startPage = new StartPage(driver);
        SearchResultPage search = startPage.open().search(correct);
        Assert.assertEquals(search.getInstance(CorrectSearchResultPage.class).getResult(), correct,
                "Name of product is not displayed correctly");
    }

    @Test
    public void foundProductsShouldContainSearchedWord() {
        StartPage startPage = new StartPage(driver);
        startPage.open().search(correct);
        List<WebElement> searchResult =
                driver.findElements(with(By.xpath("//*[contains(@class,'normal')]"))
                        .above(By.xpath("//i[contains(@class,'a-icon-star-small')]")));
        Assert.assertTrue(searchResult.size() > 0,
                "There is no name of product in the results of search");
    }
}
