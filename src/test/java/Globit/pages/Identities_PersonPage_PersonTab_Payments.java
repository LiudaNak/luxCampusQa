package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_Payments {
    public Identities_PersonPage_PersonTab_Payments() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (linkText = "Add payment")
    public WebElement addPaymentButton;

    @FindBy (name = "paymentDate")
    public WebElement dateField;

    @FindBy (name = "amount")
    public WebElement amountField;

    @FindBy (name = "paymentMethod")
    public WebElement paymentMethodField;

    @FindBy (name = "order.$ref")
    public WebElement assignmentField;

    @FindBy (xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy (xpath = "//span[text()='Cancel']")
    public WebElement cancelButton;

    @FindBy (linkText = "Now")
    public WebElement nowLink;

    @FindBy (xpath = "//div[@title='Other Payment']")
    public WebElement otherPaymentItemFromDropdown;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[last()]/td[last()]/button")
    public WebElement trashIconForThePayment;

    @FindBy (xpath = "//button/span[text()='Delete']")
    public WebElement deleteButtonOnConfirmation;

    }
