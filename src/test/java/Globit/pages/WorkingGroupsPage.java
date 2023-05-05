package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class WorkingGroupsPage {
    public WorkingGroupsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (linkText = "Add working group")
    public WebElement addWorkingGroupButton;

    @FindBy (xpath = "//span[text()='Edit working group']")
    public WebElement editWorkingGroup;

    @FindBy (name = "title")
    public WebElement nameOfTheGroupField;

    @FindBy (xpath = "//i[@class='bi bi-person']")
    public WebElement ImageField;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement canselButton;

    @FindBy (xpath = "//a[text()='AutomationTest']")
    public List<WebElement> addedWorkingGroupList;

    @FindBy (xpath = "//a[text()='AutomationTest']/../../td[7]/button")
    public WebElement trashIconForGroupAutomationTest;

    @FindBy (xpath = "//a[text()='testForDeleting']/../../td[7]/button")
    public WebElement trashIconForGroupForDeleting;

    @FindBy (xpath = "//a[text()='Automation Test for adding Chair']/../../td[7]/button")
    public WebElement deleteIconForAddingChairGroup;

    @FindBy (xpath = "//a[text()='testForDeleting']")
    public List <WebElement> deletedWorkingGroup;

    @FindBy (xpath = "//span[text()='Ok']")
    public WebElement okButtonOnConfirmationDeletion;

    @FindBy (xpath = "(//*[@title='10 / page'])[1]")
    public WebElement paging10PagesElement;

    @FindBy (xpath = "//*[@title='100 / page']")
    public WebElement element100PagesFromDropDown;

    @FindBy (xpath = "//a[text()='testForEdit']")
    public WebElement workingGroupForEdit;

    @FindBy (xpath = "//span[text()='Title']/../../div[2]")
    public WebElement nameOfTheWorkingGroup;

    @FindBy (xpath = "//a[text()='Automation Test for adding Chair']")
    public WebElement workingGroupForAddingChair;

    @FindBy (xpath = "//a[text()='Automation Test for adding Member']")
    public WebElement workingGroupForAddingMember;

    @FindBy (xpath = "//a[text()='Automation Test for checking disabled button']")
    public WebElement workingGroupForDisabledButton;


    @FindBy (xpath = "//span[text()='Add Chair / Co-Chair']")
    public WebElement addChairCoChairButton;

    @FindBy (xpath = "//input[1]")
    public WebElement addChairCoChairField;

    @FindBy (xpath = "//div[@class='rc-virtual-list-holder-inner']/div[1]/div")
    public WebElement firstElementFromDropdown;

    @FindBy (xpath = "//div[@name='position']")
    public WebElement positionField;

    @FindBy (xpath = "//div[text()='Chair']")
    public WebElement chairElementFromDropdown;

    @FindBy (xpath = "//div[text()='Co-Chair']")
    public WebElement coChairElementFromDropdown;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[3]/a")
    public WebElement addedFirstName;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[4]/a")
    public WebElement addedLastName;

    @FindBy (xpath = "//span[text()='Add Member']")
    public WebElement addMemberButton;

    @FindBy (xpath = "//input[1]" )
    public WebElement addMemberField;

    @FindBy (xpath = "//div[@name='status']")
    public WebElement statusField;

    @FindBy (xpath = "//div[text()='Approved']")
    public WebElement approvedElementFromDropdown;

    @FindBy (xpath = "//a[text()='Automation Test for adding Member']/../../td[7]/button")
    public WebElement deleteIconForEditMemberGroup;

    @FindBy (xpath = "//a[text()='Automation Test for checking disabled button']/../../td[7]/button")
    public WebElement deleteIconForCheckingDisableButtonGroup;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[1]/a")
    public WebElement searchedElement;


}
