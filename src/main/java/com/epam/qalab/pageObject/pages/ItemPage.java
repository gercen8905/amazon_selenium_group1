package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ItemPage extends BasePage {
    @FindBy(xpath = "//*[@id='exports_desktop_qualifiedBuybox_tlc_feature_div']/span[@class='a-size-base a-color-secondary']")
    private WebElement depositToInfo;

    public ItemPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getShippingCountry() {
        String shippingCountry = depositToInfo.getText();
        return shippingCountry;
    }

}
