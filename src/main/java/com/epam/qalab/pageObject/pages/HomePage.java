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

public class HomePage extends BasePage {
    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    @FindBy(id = "glow-ingress-line2")
    private WebElement oldLocation;

    @FindBy(id = "GLUXZipUpdateInput")
    private WebElement zipCodeField;

    @FindBy(id = "nav-global-location-popover-link")
    private WebElement deliverToButton;

    @FindBy(xpath = "//*[@data-action='GLUXPostalUpdateAction']")
    private WebElement applyButton;

    @FindBy(xpath = "//div[@class='a-popover-footer']//input[@id='GLUXConfirmClose']")
    private WebElement continueButton;

    @FindBy(id = "glow-ingress-line2")
    private WebElement newLocation;

    @FindBy(xpath = "//span[@class='a-dropdown-container']")
    private WebElement dropDownContainer;

    @FindBy(xpath = "//select[@class='a-native-dropdown']")
    private WebElement select;

    @FindBy(xpath = "//button[@name='glowDoneButton']")
    private WebElement doneButton;

    @FindBy(xpath = "//a[@aria-label='Keyboards']")
    private WebElement category;


    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage open() {
        webDriver.get("https://www.amazon.com/");
        return this;
    }

    public HomePage deliverToButtonClick() {
        this.deliverToButton.click();
        return this;
    }

    public HomePage inputZipCode(String zipCode) {
        this.wait.until(ExpectedConditions.visibilityOf(this.zipCodeField));
        this.zipCodeField.sendKeys(zipCode);
        return this;
    }

    public HomePage applyButtonClick() {
        this.applyButton.click();
        return this;
    }

    public HomePage continueButtonClick() {
        this.wait.until(ExpectedConditions.visibilityOf(this.continueButton));
        continueButton.click();
        return this;
    }

    public HomePage refreshPage() {
        webDriver.navigate().refresh();
        return this;
    }

    public String getNewLocation() {
        return this.newLocation.getText();
    }

    public List<String> getListOfCountries() {
        wait.until(ExpectedConditions.elementToBeClickable(dropDownContainer));
        Select dropdown = new Select(select);
        List<String> list = new ArrayList<>();
        for (WebElement element: dropdown.getOptions()) {
            list.add(element.getText());
        }
        return list;
    }

    public HomePage selectCountry(String country) {
        wait.until(ExpectedConditions.elementToBeClickable(dropDownContainer));
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText(country);
        return this;
    }

    public HomePage doneButtonClick() {
        this.doneButton.click();
        return this;
    }

    public CategoryPage choosingCategory() {
        category.click();
        return new CategoryPage(webDriver);
    }

}
