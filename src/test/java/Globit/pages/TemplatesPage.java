package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.security.auth.x500.X500Principal;

public class TemplatesPage {
    public TemplatesPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//span[text()='Create Template']")
    public WebElement createTemplateButton;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement cancelButton;

    @FindBy (name = "name")
    public WebElement nameField;

    @FindBy (name = "templateAttribute.type")
    public WebElement templateTypeField;

    @FindBy (name = "mime")
    public WebElement formatField;

    @FindBy (xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButtonOnEditPage;

    @FindBy (xpath = "//div[text()='Document']")
    public WebElement documentFromDropdown;

    @FindBy (xpath = "//div[text()='PDF']")
    public WebElement pdfFromDropdown;

    @FindBy (xpath = "//*[contains(@class,'edit-area')]/iframe")
    public WebElement contentField;

    @FindBy (xpath = "//textarea[@id='textArea']")
    public WebElement textArea;

    @FindBy (xpath = "//span[text()='Edit Template']")
    public WebElement editTemplateButton;

    @FindBy (xpath = "//a[text()='Templates']")
    public  WebElement templatesSection;

    @FindBy (xpath = "//td[text()[contains(., 'AutomationTest_DocumentAndPdf')]]/following-sibling::td[4]/button")
    public WebElement deleteIconForAutomationTest_DocumentAndPdfTemplate;

    @FindBy (xpath = "//td[text()='Template_for_editing']/following-sibling::td[4]/button")
    public WebElement deleteIconForTemplateForEditing;

    @FindBy (xpath = "//td[text()='AutomationTest_ExportAndMicrosoftExcel']/following-sibling::td[4]/button")
    public WebElement deleteIconForExportAndMicrosoftExcelTemplate;

    @FindBy (xpath = "//td[text()='AutomationTest_For_Deletion']/following-sibling::td[4]/button")
    public WebElement deleteIconForAutomationTest_For_DeletionTemplate;

    @FindBy (xpath = "//button[span='Delete']/span")
    public WebElement deleteButtonOfConfirmation;

    @FindBy (xpath = "//div[text()='Export']")
    public WebElement exportFromDropdown;

    @FindBy (xpath = "//div[text()='Microsoft Excel']")
    public WebElement microsoftExcelFromDropdown;

    @FindBy (xpath = "//body[@id='tinymce']")
    public WebElement contentFieldOnEditPage;

    @FindBy (xpath = "//td[text()='Template_for_editing']/following-sibling::td[3]/a")
    public WebElement editIconForEditedTemplate;

    @FindBy (xpath = "//input[@class='ant-input']")
    public WebElement searchField;

    @FindBy (xpath = "//button[@class='ant-btn ant-btn-icon-only ant-input-search-button']")
    public WebElement searchIcon;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[2]/td")
    public WebElement searchedElement;
}
