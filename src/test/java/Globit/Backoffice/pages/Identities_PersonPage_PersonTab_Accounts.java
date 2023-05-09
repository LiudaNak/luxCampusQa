package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_Accounts {
    public Identities_PersonPage_PersonTab_Accounts() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (linkText = "Add account")
    public WebElement addAccountButton;

    @FindBy (xpath = "//tr[@class='ant-table-row ant-table-row-level-0'][1]//*[3]")
    public WebElement firstAccountEmailElement;

    @FindBy (xpath = "//span[text()='Accounts list']")
    public WebElement accountsListButton;

    @FindBy (xpath = "(//i[@class='bi bi-plus'])[1]")
    public WebElement plusIconForTheFirstAccount;

    @FindBy (xpath = "//button[span='Add']")
    public WebElement addButton;

    @FindBy (xpath = "//button[span='Delete']")
    public WebElement deleteButtonForConfirming;



}
