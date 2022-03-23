package com.epam.qalab.pageObject.pages;

import com.epam.qalab.pageObject.BasePage;
import com.epam.qalab.pageObject.modules.SortDropDownModule;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage {

    @FindBy(xpath = "//*[text()='Featured Brands']/parent::*/parent::*/ul/li[1]//a/span")
    private WebElement anyFeaturedBrand;

    @FindBy(xpath = "//*[contains(@class,'a-button-dropdown')]/span")
    private WebElement btnDropDownSort;

    @FindBy(linkText = "See all results")
    private WebElement btnSeeAllResults;

    @FindBy(xpath = "//*[contains(@cel_widget_id,'RESULTS')]//h2")
    private List<WebElement> productsDescription;

    @FindBy(xpath = "//*[@class='a-price']/span[1]")
    private List<WebElement> productsPrices;

    @FindBy(xpath = "//*[text()='Price']/parent::*/following-sibling::ul/li[1]//a")
    private WebElement priceRangeOption;


    public CategoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    public Double getElementPrice(Integer el) {
        return Double.parseDouble(productsPrices.get(el).getAttribute("textContent").substring(1));
    }

    public String getNameFeaturedBrand() {
        return anyFeaturedBrand.getAttribute("textContent");
    }

    public List<WebElement> getProductsDescription() {
        return productsDescription;
    }

    public ArrayList<Double> getPricesOfDisplayedElements() {
        ArrayList<Double> prices = new ArrayList<>();
        for (WebElement product:this.productsPrices) {
            prices.add(Double.parseDouble(product.getAttribute("textContent").substring(1)));
        }
        return prices;
    }

    public SortDropDownModule openSortDropDown() {
        btnDropDownSort.click();
        return new SortDropDownModule(webDriver);
    }

    public CategoryPage selectFeaturedBrand() {
        anyFeaturedBrand.click();
        return this;
    }

    public CategoryPage selectPriceRangeOption() {
        priceRangeOption.click();
        return this;
    }

    public CategoryPage showAllResult() {
        btnSeeAllResults.click();
        return this;
    }
}
