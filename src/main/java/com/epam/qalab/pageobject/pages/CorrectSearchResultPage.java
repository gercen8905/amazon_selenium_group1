package com.epam.qalab.pageobject.pages;
/* 
@author
Marat Lagun
created on 3/8/22   
*/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CorrectSearchResultPage extends SearchResultPage {
    @FindBy(xpath = "//*[@class='a-color-state a-text-bold']")
    private WebElement result;

    protected CorrectSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getResult() {
        return result.getText().replace("\"", "");
    }

}
