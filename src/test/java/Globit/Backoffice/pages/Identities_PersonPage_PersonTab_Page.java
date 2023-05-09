package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_Page {
    public Identities_PersonPage_PersonTab_Page() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//a[text()='Membership']/..")
    public WebElement membershipTab;

    @FindBy (xpath = "//a[text()='Group']/..")
    public WebElement groupTab;

    @FindBy (xpath = "(//a[text()='Orders'])[2]")
    public WebElement ordersTab;

    @FindBy (xpath = "(//a[text()='Payments'])[2]")
    public WebElement paymentsTab;

    @FindBy (xpath = "//a[text()='Mails']")
    public WebElement mailsTab;

    @FindBy (xpath = "(//a[text()='Accounts'])[2]")
    public WebElement accountsTab;

    @FindBy (xpath = "//a[text()='Documents']")
    public WebElement documentsTab;

    @FindBy (xpath = "//a[text()='System']")
    public WebElement systemTab;

    @FindBy (name = "academicTitle")
    public WebElement titleField;

    @FindBy (name = "prename")
    public WebElement firstNameField;

    @FindBy (name = "surname")
    public WebElement lastNameField;

    @FindBy (xpath = "//*[@name='gender']/div/span[2]")
    public WebElement genderField;

    @FindBy (xpath = "//div[text()='Female']")
    public WebElement femaleFromGenderDropdown;

    @FindBy (xpath = "//div[text()='Male']")
    public WebElement maleFromGenderDropdown;

    @FindBy (name = "collectedContacts.primaryEmail")
    public WebElement emailAddressField;

    @FindBy (xpath = "//span[text()='Add Email']")
    public WebElement addEmailButtonForAdditionalEmail;

    @FindBy (name = "collectedContacts.additionalEmails[0]")
    public WebElement additionalEmailField;

    @FindBy (xpath = "//input[@class='profile-image-uploader__input']")
    public WebElement profileImageField;

    @FindBy (xpath = "//img[@class='profile-image__image']")
    public WebElement profileField;

    @FindBy (xpath = "//button[@class='profile-image-uploader__close-btn profile-image-uploader__close-btn--visible']")
    public WebElement closeButtonForImage;

    @FindBy (xpath = "//span[text()='Add Tag']")
    public WebElement addTagButton;

    @FindBy (name = "tags[0]")
    public WebElement tagField;

    @FindBy (xpath = "(//i[@class='bi bi-x'])[2]")
    public WebElement closeIconForTagField;

    @FindBy (name = "notes")
    public WebElement notesField;

    @FindBy (xpath = "//div[@name='workplaceCategory']/div/span[2]")
    public WebElement workplaceCategoryField;

    @FindBy (xpath ="//div[@name='speciality']/div/span[2]")
    public WebElement specialityField;

    @FindBy (xpath = "//div[@name='degree']/div/span[2]")
    public WebElement degreeField;

    @FindBy (xpath = "//div[@name='yearOfDegree']/div/span[2]")
    public WebElement yearOfDegreeField;

    @FindBy (xpath = "//div[@name='specialisation']/div/span[2]")
    public WebElement specialisationField;

    @FindBy (name = "pathologistDeclaration")
    public WebElement pathologistDeclarationCheckbox;

    @FindBy (xpath = "//span[text()='Add contact']")
    public WebElement addContact;

    @FindBy (xpath = "//span[text()='Address']")
    public WebElement addressButton;

    @FindBy (xpath = "//span[@aria-label='close']")
    public WebElement closeIconForAddressTab;

    @FindBy (xpath = "//input[contains(@name, 'label')]")
    public WebElement labelField;

    @FindBy (name = "collectedContacts.contacts[0].address.organization[0]")
    public WebElement organizationField;

    @FindBy (name = "collectedContacts.contacts[0].address.department")
    public WebElement departmentField;

    @FindBy (name = "collectedContacts.contacts[0].address.lines[0]")
    public WebElement addressField;

    @FindBy (name = "collectedContacts.contacts[0].address.zip")
    public WebElement postalCodeField;

    @FindBy (name="collectedContacts.contacts[0].address.city")
    public WebElement cityField;

    @FindBy (xpath = "//*[@name ='collectedContacts.contacts[0].address.country']/div/span[2]")
    public WebElement countryField;

    @FindBy (xpath = "//span[text()='Phone']")
    public WebElement phoneButton;

    @FindBy (name = "collectedContacts.contacts[0].phone")
    public WebElement phoneField;

    @FindBy (name = "collectedContacts.contacts[0].isMain")
    public WebElement mainPhoneCheckbox;

    @FindBy (xpath = "//span[@aria-label='close']")
    public WebElement closeIconForPhoneTab;

    @FindBy (xpath = "//span[text()='Fax']")
    public WebElement faxButton;

    @FindBy (xpath = "//input[contains(@name, 'fax')]")
    public WebElement faxField;

    @FindBy (name = "collectedContacts.contacts[0].isMain")
    public WebElement mainFaxCheckbox;

    @FindBy (xpath = "//span[@aria-label='close']")
    public WebElement closeIconForFaxTab;
    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy (linkText = "Discard changes")
    public WebElement discardChangesButton;

    }
