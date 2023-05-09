package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailingsPage {
    public MailingsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//span[text()='Add mailing']")
    public WebElement addMailingButton;

    @FindBy (xpath = "//span[text()='Add mail']")
    public WebElement addMailButton;

    @FindBy (xpath = "//span[text()='Basic information']")
    public WebElement basicInformationTab;

    @FindBy (xpath = "//span[text()='Content and preview']")
    public WebElement contentAndPreviewTab;

    @FindBy (xpath = "//span[text()='Attachments']")
    public WebElement attachmentsTab;

    @FindBy (name = "mail.name")
    public WebElement mailingNameField;

    @FindBy (name = "mail.from")
    public WebElement senderEmailField;

    @FindBy (name = "mail.content.subject")
    public WebElement subjectField;

    @FindBy (name = "mail.type")
    public WebElement mailingTypeField;

    @FindBy  (xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement cancelButton;

    @FindBy (xpath = "//a[text()='AutomationTestPerson']/../../td[4]/button")
    public WebElement deleteIconForMail;

    @FindBy (xpath = "//button[span='Delete']")
    public WebElement confirmationDeletionButton;

    @FindBy (xpath = "//div[@title='Person']")
    public WebElement personItemFromDropdown;

    @FindBy (xpath = "//a[text()='Mailings']")
    public WebElement mailingsSection;

    @FindBy (xpath = "//button[span='Delete']")
    public WebElement deleteButtonForConfirmation;

    @FindBy (name = "content")
    public WebElement contentField;

    @FindBy (xpath = "//div[@title='Group']")
    public WebElement groupItemFromDropdown;

    @FindBy (xpath = "//span[text()='New']")
    public WebElement newItemFromContentDropdown;

    @FindBy (xpath = "//input[@type='file']")
    public WebElement uploadButton;

    @FindBy (xpath = "//span[text()='Refresh']")
    public WebElement refreshButton;

    @FindBy (tagName = "body")
    public WebElement previewWindow;

    @FindBy (xpath = "//input[@type='file']")
    public WebElement inputForFileUploading;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td/a")
    public WebElement searchedElement;

    @FindBy (xpath = "//tr/td[2]/span[text()='Person']")
    public WebElement personTypeOfMailings;

    @FindBy (xpath = "//span[text()='Person']/../../td[3]/a")
    public WebElement sendButtonForPersonMailing;

    @FindBy (xpath = "//input[@type='text']")
    public WebElement mailField;

    @FindBy (xpath = "//span[text()='Send Mail']")
    public WebElement sendMailButton;

    @FindBy (xpath = "//tbody/tr[1]/td[2]")
    public WebElement usersEmail;

    @FindBy (xpath = "//span[text()='Person']/../../td[3]/a")
    public WebElement sendButtonForGroupMailing;

    @FindBy (xpath = "//span[text()='Bulk Mail']/../../td[3]/a")
    public WebElement sendButtonForBulkMailing;

    @FindBy (xpath = "//span[text()='Bulk Mail']/../../td[3]/a")
    public WebElement sendButtonForWorkingGroupMailing;

    @FindBy (id = "tinymce")
    public WebElement editField;


}
