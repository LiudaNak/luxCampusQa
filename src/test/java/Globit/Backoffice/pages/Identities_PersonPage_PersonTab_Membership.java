package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_Membership {
    public Identities_PersonPage_PersonTab_Membership() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (name = "membership.applicationDate")
    public WebElement applicationDateField;

    @FindBy (name = "membership.approveDate")
    public WebElement approvalDateField;

    @FindBy (name = "membership.startDate")
    public WebElement membershipStateDateField;

    @FindBy (name = "membership.beginResidencyDate")
    public WebElement beginResidencyDateField;

    @FindBy (name = "membership.endResidencyDate")
    public WebElement endResidencyDateField;

    @FindBy (name = "membership.interest")
    public WebElement interestField;

    @FindBy (linkText = "Change membership")
    public WebElement changeMembershipButton;

    @FindBy (name = "membershipProductsHref")
    public WebElement membershipField;

    @FindBy (xpath = "//div[@class='rc-virtual-list-holder-inner']/div[1]")
    public WebElement firstElementFromTheDropdown;

    @FindBy (xpath = "//span[text()='OK']")
    public WebElement okButton;

    @FindBy (xpath = "//a[text()='Today']")
    public WebElement todayButton;

    @FindBy (xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy (xpath = "//span[@class='ant-picker-suffix'][1]")
    public WebElement closeIcon;

    @FindBy (xpath = "(//*[text()='1'])[1]")
    public WebElement date2022_11_01;


}
