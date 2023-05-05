package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_WorkingGroups {
    public Identities_PersonPage_PersonTab_WorkingGroups() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//a[text()='Working Groups']")
    public WebElement workingGroupTab;

    @FindBy (xpath = "//*[@class='bi bi-plus']")
    public WebElement addWorkingGroupButton;

    @FindBy (xpath = "//*[contains (@name, 'workingGroups[0].workingGroup')]")
    public WebElement workingGroupField;

    @FindBy (xpath = "//*[contains (@name, 'position')]")
    public WebElement positionField;

    @FindBy (xpath = "//*[contains (@name, 'status')]")
    public WebElement statusField;

    @FindBy (xpath = "//span[text()='Save']")
    public WebElement saveButton;

    @FindBy (xpath = "//*[@class='rc-virtual-list-holder-inner']/div[1]")
   public WebElement firstElementFromWorkingGroupDropdown;

    @FindBy (xpath = "//div[text()='Member']")
    public WebElement memberItemFromPositionDropdown;

    @FindBy (xpath = "//div[text()='Approved']")
    public WebElement approvedItemFromStatusDropdown;

    @FindBy (xpath = "//*[@class='bi bi-trash']")
    public WebElement deleteIcon;

    @FindBy (xpath = "//*[text()='Delete']")
    public WebElement confirmDeleting;

}
