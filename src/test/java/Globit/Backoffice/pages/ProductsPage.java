package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
    public ProductsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//span[text()='Add Product']")
    public WebElement addProductButton;

    @FindBy (xpath = "//span[text()='Edit product']")
    public WebElement editProductButton;

    @FindBy (name = "title")
    public WebElement titleField;

    @FindBy (name = "descriptions[0]")
    public WebElement descriptionField;

    @FindBy (name = "proofRequired")
    public WebElement proofRequiredCheckbox;

    @FindBy (name = "period")
    public WebElement periodField;

    @FindBy (name = "category")
    public WebElement categoryField;

    @FindBy (name = "taxRate")
    public WebElement taxRateField;

    @FindBy (name = "public")
    public WebElement publicCheckbox;

    @FindBy (name = "price")
    public WebElement priceField;

    @FindBy (name = "enablesRoles")
    public WebElement enablesRolesField;

    @FindBy (xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement cancelButton;

    @FindBy (xpath = "//div[text()='1 month']")
    public WebElement periodDropdownElement;

    @FindBy (xpath = "//div[text()='Membership']")
    public WebElement categoryDropdownElement;

    @FindBy (xpath = "//div[text()='_app']")
    public WebElement enablesRolesDropdownElement;

    @FindBy (xpath = "//span[text()='Title']/../following-sibling::div")
    public WebElement titleOfCreateProduct;

    @FindBy (xpath = "(//*[@title='10 / page'])[1]")
    public WebElement paging10PagesElement;

    @FindBy (xpath = "//*[@title='100 / page']")
    public WebElement elementFromDropDown100Pages;


    @FindBy (xpath = "//a[text()='AutomationTest']/../../td[11]/a")
    public WebElement editButtonForAutomationTestProduct;

    @FindBy (xpath = "//div[text()='memberInGoodStanding']")
    public WebElement memberInGoodStandingRolesDropdownElement;

    @FindBy (xpath = "//span[text()='Title']/../following-sibling::div")
    public WebElement currentTitle;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td/a")
    public WebElement searchedElement;


}
