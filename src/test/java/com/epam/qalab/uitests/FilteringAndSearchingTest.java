package com.epam.qalab.uitests;

import com.epam.qalab.BaseTest;
import com.epam.qalab.pageObject.pages.CategoryPage;
import com.epam.qalab.pageObject.pages.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class FilteringAndSearchingTest extends BaseTest {
    private CategoryPage categoryPage;

    @BeforeMethod
    public void openCategoryPage() {
        HomePage homePage = new HomePage(webDriver);
        setUpDriver();
        categoryPage = homePage.open().choseCategory();
    }

    @AfterTest
    public void afterTest() {
        quit();
    }

    @Test
    public void successfulFilteredByFeaturedBrand() {

        String nameFeaturedBrand = categoryPage.getNameFeaturedBrand();
        List<WebElement> productsDescriptions = categoryPage
                .selectFeaturedBrand()
                .getProductsDescription();

        Assert.assertTrue(productsDescriptions.stream().allMatch(item -> item.getText().contains(nameFeaturedBrand)),
                "Not each description contains featured brand");
    }

    @Test
    public void successfulFilteringByPrice() {
        // this test check only the main price, when there is an option with lower price test will be failed

        List<Double> elementsPrices = categoryPage
                .selectPriceRangeOption()
                .getPricesOfDisplayedElements();

        Assert.assertTrue(elementsPrices.stream().allMatch(priceElem -> priceElem <= 25),
                "Not each price is <= 25");
    }

    @Test
    public void successfulSortingDescendingWithinCategory() {

        categoryPage
                .showAllResult()
                .openSortDropDown()
                .sortHighToLowPrice();

        Assert.assertTrue(categoryPage.getElementPrice(0) > categoryPage.getElementPrice(1),
                "Sorting doesn't work correctly");
    }
}