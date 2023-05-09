package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchFunctionalityPage {
    public SearchFunctionalityPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//input[@placeholder='Search']")
    public WebElement searchField;

    @FindBy (xpath = "//span[text()='Search']")
    public WebElement searchButtonForSearchingInColumns;

    @FindBy (xpath = "//span[@aria-label='search']")
    public WebElement searchButton;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[1]")
    public WebElement searchForFirstColumn;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[2]")
    public WebElement searchForSecondColumn;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[2]/span")
    public WebElement searchedElementTypeField;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[3]")
    public WebElement searchedElementFirstName;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[4]")
    public WebElement searchedElementLastName;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[5]")
    public WebElement searchedElementEmailField;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[6]/div")
    public  WebElement searchedElementGroupField;

    @FindBy (xpath = "(//div[@class='country'])[1]")
    public WebElement searchedElementCountryField;

    @FindBy (xpath = "//input[@value='member']")
    public WebElement membersRadioButton;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[4]")
    public WebElement searchForForthColumn;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[5]")
    public WebElement searchForFifthColumn;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[6]")
    public WebElement searchForSixthColumn;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[3]/td[2]/a")
    public WebElement searchedElementGroupFieldForGroupsSection;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[3]/td[1]/a")
    public WebElement searchedElementForWorkingGroupsSection;

    @FindBy (xpath = "(//input[@type='search'])[2]")
    public WebElement dropDownArrowOnSearchField;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[7]")
    public WebElement searchForSeventhColumn;
    
    @FindBy (xpath = "//input[@name='totalPrice']")
    public WebElement searchFieldForTotalPriceInOrders;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[2]/span")
    public WebElement searchedElementValueOfSecondColumn;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[1]")
    public WebElement searchedElementValueOfFirstColumn;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[1]/a")
    public WebElement searchedElementCustomerOnPayments;

    @FindBy (xpath = "//input[@name='paymentAmount']"    )
    public WebElement searchFieldForPaymentAmountOnOrders;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[3]")
    public WebElement searchedElementValueOfThirdColumn;

    @FindBy (xpath = "(//i[@class='bi bi-search'])[3]")
    public WebElement searchForThirdColumn;

    @FindBy (xpath = "//input[@name='outstandingAmount']")
    public WebElement searchFieldForOutstandingAmountFieldOnOrders;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[3]/td[4]/span")
    public WebElement searchedElementForOutstandingAmountFieldOnOrders;

    @FindBy (xpath = "//input[@name='reference']")
    public WebElement searchFieldForReferenceOfPayments;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[3]/td[4]/div")
    public WebElement searchedElementForReferenceOfPayments;

    @FindBy (xpath = "//div[@class='ant-select-selector']/div")
    public WebElement fieldForDropdownOpening;
}
