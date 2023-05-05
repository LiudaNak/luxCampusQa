package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormsPages {
    public FormsPages() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[1]/a")
    public WebElement searchedElement;

    @FindBy (xpath = "//span[text()='Add Form']")
    public WebElement addFormButton;

    @FindBy (name = "name")
    public WebElement nameField;

    @FindBy (xpath = "//input[@class='ant-select-selection-search-input']")
    public WebElement pagesField;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy (xpath = "//i[@class='bi bi-plus']/..")
    public WebElement addTransaction;

    @FindBy (xpath = "(//*[@title='10 / page'])[1]")
    public WebElement paging10PagesElement;

    @FindBy (xpath = "//*[@title='100 / page']")
    public WebElement elementFromDropDown100Pages;

    @FindBy (xpath = "//button[@type='button']/span[text()='Delete']")
    public WebElement deleteConfirmation;

    @FindBy (name = "name")
    public WebElement nameFieldOnEditedPage;

    @FindBy (xpath = "//div[@class='rc-virtual-list-holder-inner']/div[1]/div")
    public WebElement dropdownElement;

    @FindBy (xpath = "//a[contains(.,'Forms')]/..")
    public WebElement forms;

    @FindBy (xpath = "//span[text()='Name']/../../../div[2]/div/div/input")
    public WebElement fieldForPageName;
}
