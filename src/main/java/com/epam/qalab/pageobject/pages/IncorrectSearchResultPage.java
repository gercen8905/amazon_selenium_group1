package com.epam.qalab.pageobject.pages;
/* 
@author
Marat Lagun
created on 3/8/22   
*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IncorrectSearchResultPage extends SearchResultPage {
    @FindBy(xpath = "//*[@class='a-row']/descendant::span[1]")
    private WebElement noResults;
    @FindBy(xpath = "//*[@class='a-row']/descendant::span[2]")
    private WebElement actualWithDot;

    protected IncorrectSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getActualWithDot() {
        return actualWithDot.getText() + ".";
    }

    public String getNoResultsText() {
        return noResults.getText();
    }
}
