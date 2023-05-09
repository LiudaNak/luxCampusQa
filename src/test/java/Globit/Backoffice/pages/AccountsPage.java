package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsPage {
    public AccountsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (linkText = "Add account")
    public WebElement addAccountButton;

    @FindBy (xpath = "//*[text()='Edit account']")
    public WebElement editAccount;

    @FindBy (xpath = "//i[@class='bi bi-pen']")
    public WebElement editAccountButton;

    @FindBy (name = "mail")
    public WebElement mailField;

    @FindBy (xpath = "//span[text()='Change email']")
    public WebElement changeEmailButton;

    @FindBy (name = "firstname")
    public WebElement firstNameField;

    @FindBy (name = "lastname")
    public WebElement lastNameField;

    @FindBy (xpath = "//*[contains(@name,'roles')]")
    public WebElement rolesField;

    @FindBy (name = "isActivation")
    public WebElement activationCheckbox;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy (xpath = "//*[@title='member']")
    public WebElement memberOptionFromDropdown;

    @FindBy (name = "password")
    public WebElement passwordField;

    @FindBy (xpath = "//*[text()='Update existing account']/../../../..")
    public WebElement updateExistingAccount;

    @FindBy (xpath = "(//span[text()='Add account'])[2]/../../../..")
    public WebElement addExistingAccount;

    @FindBy (xpath = "(//*[@class='ant-table-tbody']/tr/td/a)[1]")
    public WebElement firstAccountOnTheList;

    @FindBy (xpath = "//*[text()='Update current account']/../../../..")
    public WebElement updateCurrentAccount;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td/a")
    public WebElement searchedElement;

}
