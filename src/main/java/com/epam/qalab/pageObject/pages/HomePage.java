package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import com.epam.qalab.pageObject.modules.DeliverToPopUpModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(id = "glow-ingress-line2")
    private WebElement oldLocation;

    @FindBy(id = "GLUXZipUpdateInput")
    private WebElement zipCodeField;

    @FindBy(id = "nav-global-location-popover-link")
    private WebElement deliverToButton;

    @FindBy(id = "glow-ingress-line2")
    private WebElement newLocation;

    @FindBy(xpath = "//a[@aria-label='Keyboards']")
    private WebElement category;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public DeliverToPopUpModule deliverToButtonClick() {
        this.deliverToButton.click();
        return new DeliverToPopUpModule(webDriver);
    }

    public HomePage refreshPage() {
        webDriver.navigate().refresh();
        return this;
    }

    public String getNewLocation() {
        return this.newLocation.getText();
    }

    public CategoryPage choosingCategory() {
        category.click();
        return new CategoryPage(webDriver);
    }
}
