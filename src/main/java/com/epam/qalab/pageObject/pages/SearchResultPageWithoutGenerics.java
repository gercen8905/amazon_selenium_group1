package com.epam.qalab.pageobject.pages;
/* 
@author
Marat Lagun
created on 3/16/22   
*/

import com.epam.qalab.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchResultPageWithoutGenerics extends BasePage {
    @FindBy(xpath = "//*[@class='a-color-state a-text-bold']")
    private WebElement result;

    @FindBy(xpath = "//*[@class='a-row']/descendant::span[1]")
    private WebElement noResults;

    @FindBy(xpath = "//*[@class='a-row']/descendant::span[2]")
    private WebElement actualWithDot;

    protected SearchResultPageWithoutGenerics(WebDriver webDriver) {
        super(webDriver);
    }

    public String getActualWithDot() {
        return actualWithDot.getText() + ".";
    }

    public String getNoResultsText() {
        return noResults.getText();
    }

    public String getResult() {
        return result.getText().replace("\"", "");


    }
}
