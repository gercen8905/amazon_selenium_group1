package com.epam.qalab.uitests;

import com.epam.qalab.BaseTest;
import com.epam.qalab.pageObject.pages.CategoryPage;
import com.epam.qalab.pageObject.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class FilteringAndSearchingTest extends BaseTest {

    @BeforeMethod
    public CategoryPage openCategoryPage() {
        HomePage homePage = new HomePage(webDriver);
        setUpDriver();
        return homePage.open().choseCategory();
    }

    @AfterTest
    public void afterTest() {
        quit();
    }

    @Test
    public void successfulFilteredByFeaturedBrand() {

        var categoryPage = openCategoryPage();
        String nameFeaturedBrand = categoryPage.getNameFeaturedBrand();
        categoryPage = categoryPage
                .selectFeaturedBrand();

        Assert.assertTrue(categoryPage.getProductsDescription().stream().allMatch(item -> item.getText().contains(nameFeaturedBrand)));
    }

    @Test
    public void successfulFilteringByPrice() {

        ArrayList<Double> elementsPrices = openCategoryPage()
                .selectPriceRangeOption()
                .getPricesOfDisplayedElements();

        Assert.assertTrue(elementsPrices.stream().allMatch(priceElem -> priceElem <= 25));
    }

    @Test
    public void successfulSortingDescendingWithinCategory() {

        CategoryPage categoryPage = openCategoryPage()
                .showAllResult()
                .openSortDropDown()
                .sortHighToLowPrice();

        Assert.assertTrue(categoryPage.getElementPrice(0) > categoryPage.getElementPrice(1));
    }
}
