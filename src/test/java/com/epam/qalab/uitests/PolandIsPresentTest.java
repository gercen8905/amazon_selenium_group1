package com.epam.qalab.uitests;

import com.epam.qalab.BaseTest;
import com.epam.qalab.pageObject.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

public class PolandIsPresentTest extends BaseTest {
    @AfterTest
    public void tearDown() {
        quit();
    }

    @Test
    public void polandIsPresentTest() {
        HomePage homePage = new HomePage(webDriver);
        List<String> listOfCountries = homePage
                .open()
                .deliverToButtonClick()
                .getListOfCountries();

        Assert.assertTrue(listOfCountries.contains("Poland"), "The list of countries doesn't contain Poland.");
    }
}
