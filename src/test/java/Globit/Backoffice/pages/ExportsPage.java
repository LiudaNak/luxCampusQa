package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExportsPage {
    public ExportsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//*[@class='bi bi-send'][1]")
    public WebElement receiveExportByEmailButton;

    @FindBy (name = "_field_0")
    public WebElement membershipFeeProductField;

    @FindBy (xpath = "//div[text()='Trainee Online - 1 Year']")
    public WebElement traineeElementFromDropdown;

    @FindBy (name = "recipient")
    public WebElement emailField;

    @FindBy (xpath = "//span[text()='Send by email']")
    public WebElement sendByEmailButton;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[3]/td")
    public WebElement searchedElement;


}
