package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GroupsPage {
    public GroupsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (linkText = "Add group")
    public WebElement addGroupButton;

    @FindBy (xpath = "//span[text()='Persons']")
    public WebElement personsTab;

    @FindBy (name = "title")
    public WebElement nameOfTheGroupField;

    @FindBy (xpath = "//i[@class='bi bi-person']")
    public WebElement ImageField;

    @FindBy (name = "description")
    public WebElement descriptionField;

    @FindBy (name = "collective")
    public WebElement collectiveMemberCheckBox;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement canselButton;

    @FindBy (xpath = "//span[text()='Edit group']")
    public WebElement editGroupButton;

    @FindBy (xpath = "//a[text()='AutomationTestForCreatingNewGroup']/../../td[7]/button")
    public WebElement trashIconForAutomationTestForCreatingNewGroup;

    @FindBy (xpath = "//a[text()='AutomationTestForDeletingGroup']/../../td[7]/button")
    public WebElement trashIconForAutomationTestForDeletingGroup;
    @FindBy (xpath = "//a[text()='AutomationTestForNonCollective']/../../td[7]/button")
    public WebElement trashIconForAutomationTestForNonCollectiveGroup;

    @FindBy (xpath = "//a[text()='Automation Test - Edited title']/../../td[7]/button")
    public WebElement trashIconForEditedGroup;

    @FindBy (xpath = "//span[text()='Ok']")
    public WebElement okButtonOnConfirmationDeletion;

    @FindBy (xpath = "//a[text()='AutomationTest for deletion']")
    public List<WebElement> arrayOfDeletedGroups;

    @FindBy (xpath = "//i[@class='bi bi-pen']")
    public WebElement editButton;

    @FindBy (name = "title")
    public WebElement nameOfTheGroupFieldOfEditPage;

    @FindBy(xpath = "//i[@class='bi bi-person']")
    public WebElement imageFieldOfEditPage;

    @FindBy (name = "description")
    public WebElement descriptionFieldOfEditPage;

    @FindBy (name = "collective")
    public WebElement collectiveMemberCheckBoxOfEditPage;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButtonOfEditPage;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement canselButtonOfEditPage;

    @FindBy (xpath = "//span[text()='Title']/..//following-sibling::div")
    public WebElement editedTitle;

    @FindBy (xpath = "//span[text()='Description']/..//following-sibling::div")
    public WebElement editedDescription;

    @FindBy (xpath = "//span[text()='Collective Members']/../following-sibling::div/i")
    public WebElement collectiveMembershipStatus;

    @FindBy (xpath = "//span[text()='Add new person']")
    public WebElement addNewPersonButton;

    @FindBy (xpath = "//*[contains(@class, 'cell')][1]")
    public WebElement idField;

    @FindBy (xpath = "//*[contains(@class, 'cell')][2]")
    public WebElement firstNameField;

    @FindBy (xpath = "//*[contains(@class, 'cell')][3]")
    public WebElement lastNameField;

    @FindBy (xpath = "//*[contains(@class, 'cell')][4]")
    public WebElement emailField;

    @FindBy (xpath = "//i[@class='bi bi-trash']")
    public WebElement deleteIcon;

    @FindBy (xpath = "//span[text()='Send']")
    public WebElement sendButton;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[2]/a")
    public WebElement searchedElement;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[2]/a")
    public WebElement nameOfTheFirstGroup;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[last()]/td[4]")
    public WebElement emailOfTheLastUser;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td[5]/a")
    public WebElement sendingMailButton;

    @FindBy (xpath = "//button/i[@class='bi bi-envelope']")
    public WebElement sendingMailButtonInsideGroup;

    @FindBy (xpath = "//i[@class='bi bi-eye']")
    public WebElement previewButton;

    @FindBy (xpath = "//*[contains(text(),'Preview:')]")
    public WebElement previewLetterForNewMemberTitle;

    @FindBy (xpath = "(//*[contains(@class, 'cell')])[5]")
    public WebElement membershipField;

    @FindBy (xpath = "//select[@class='select']")
    public WebElement activeField;

    @FindBy (xpath = "//option[@value='1-year membership']")
    public WebElement oneYearMembershipPoint;

    @FindBy (xpath = "(//*[contains(@class, 'cell')])[6]")
    public WebElement virchowsField;

    @FindBy (xpath = "//option[@value='Virchows Archiv (Printed, 1 Year)']")
    public WebElement virchowsArchivPoint;

    @FindBy (xpath = "//option[@value='Journal of Hematopathology (printed version)']")
    public WebElement firstPointHematopatology;

    @FindBy (xpath = "(//*[contains(@class, 'cell')])[7]")
    public WebElement hematopathologyField;

    @FindBy (xpath = "(//*[contains(@class, 'cell')])[8]")
    public WebElement startDateField;

    @FindBy (xpath = "//div[@aria-label='day-10']")
    public WebElement pointInCalendar;

    @FindBy (xpath = "//a[text()='AutomationTestForCollectiveGroup']/../../td[7]/button")
    public WebElement trashIconForAddPersonToCollectivePersonGroup;

    @FindBy (xpath = "//a[contains(text(),'AutomationTest')]/../../td[7]/button")
    public WebElement notDeletedGroup;
}
