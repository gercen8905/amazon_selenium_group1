package com.epam.qalab.uitests;

import com.epam.qalab.BaseTest;
import com.epam.qalab.BaseTest;
import com.epam.qalab.pageObject.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class ShippingCountryTest extends BaseTest {
    String country = "Bulgaria";

    @AfterTest
    public void tearDown() {
        quit();
    }

    @Test
    public void polandIsPresentTest() {
        HomePage homePage = new HomePage(webDriver);
        String shippingCountry = homePage
                .open()
                .deliverToButtonClick()
                .selectCountry("Bulgaria")
                        .doneButtonClick()
                                .refreshPage()
                                        .choosingCategory()
                                                .choosingItem().getShippingCountry();


        Assert.assertTrue(shippingCountry.contains(country), "The chosen shipping country isn't updated.");
    }
}
