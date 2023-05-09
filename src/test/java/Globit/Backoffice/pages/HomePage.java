package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(){
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy(xpath = "//a[text()='Identities']")
    public WebElement identitiesTab;

    @FindBy(xpath = "//a[text()='Groups']")
    public WebElement groupsTab;

    @FindBy (xpath = "//a[text()='Working groups']")
    public WebElement workingGroupsTab;

    @FindBy(xpath = "//a[text()='Orders']")
    public WebElement ordersTab;

    @FindBy(xpath = "//a[text()='Payments']")
    public WebElement paymentsTab;

    @FindBy (xpath = "//a[text()='Statistics']")
    public WebElement statisticsTab;

    @FindBy(xpath = "//a[text()='Exports']")
    public WebElement exportsTab;

    @FindBy(xpath = "//a[text()='Products']")
    public WebElement productsTab;

    @FindBy (xpath = "//a[text()='Mailings']")
    public WebElement mailingsTab;

    @FindBy(xpath = "//a[text()='Templates']")
    public WebElement templatesTab;

    @FindBy (xpath = "//a[text()='Forms']")
    public WebElement formsTab;

    @FindBy(xpath = "//a[text()='Accounts']")
    public WebElement accountsTab;

    @FindBy(linkText = "Logout")
    public WebElement logoutButton;
}
