package com.epam.qalab.uitests;

import com.epam.qalab.BaseTest;
import com.epam.qalab.pageObject.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class ZipCodeTest extends BaseTest {

    @AfterTest
    public void tearDown() {
        quit();
    }

    @Test
    public void verifyZipCodeTest() {
        HomePage homePage = new HomePage(webDriver);
        String newLocation = homePage
                .open()
                .deliverToButtonClick()
                .inputZipCode("37219")
                .applyButtonClick()
                .continueButtonClick()
                .refreshPage()
                .getNewLocation();

        Assert.assertTrue(newLocation.contains("Nashville 37219"), "The 'Deliver to' location isn't updated.");
    }
}
