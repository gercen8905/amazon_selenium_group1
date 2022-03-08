package com.epam.qalab;

import com.epam.qalab.pageObject.pages.AddedToCartPage;
import com.epam.qalab.pageObject.pages.CartPage;
import com.epam.qalab.pageObject.pages.HomePage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddRemoveItemTest extends BaseTest {

    @BeforeTest
    public void setUp() {
        setUpDriver();
    }

    @AfterTest
    public void tearDown() {
        quit();
    }

    @Test
    public void shouldAddAnyItemToCart() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(webDriver);
        AddedToCartPage addedToCartPage = homePage
                .open()
                .chooseAnyCategory()
                .chooseAnyItem()
                .addItemToCart();
        softAssert.assertEquals(addedToCartPage.getAddedToCartInfo(), "Added to Cart",
                "Adding to Cart doesn't work properly");
        softAssert.assertEquals(addedToCartPage.getNavCartCount(), "1", "Wrong number of items in the Cart");
        softAssert.assertAll();
    }

    @Test
    public void shouldRemoveItemFromCart() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(webDriver);
        CartPage cartPage = homePage
                .open()
                .chooseAnyCategory()
                .chooseAnyItem()
                .addItemToCart()
                .openCart()
                .deleteItemFromCart();
        softAssert.assertEquals(cartPage.getHeadingInfo(), "Your Amazon Cart is empty.",
                "Your Amazon Cart is not empty or not available");
        softAssert.assertEquals(cartPage.getPrice(), "0.00", "Subtotal is not '0.00'");
        softAssert.assertAll();
    }

}
