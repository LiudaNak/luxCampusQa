package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Identities_PersonPage_PersonTab_Group {
    public Identities_PersonPage_PersonTab_Group() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (className = "ant-select-selector")
    public WebElement groupField;

    @FindBy (xpath = "//div[@class='rc-virtual-list-holder-inner']/div[1]")
    public WebElement firstGroupInTheList;

    @FindBy (css = "button[type='submit']")
    public WebElement saveButton;

    @FindBy (xpath = "//*[@class=\"ant-select-selector\"]//span")
    public WebElement addedGroup;

    @FindBy (xpath = "//span[@class='ant-select-selection-item-remove']")
    public WebElement closeIconForGroup;

    @FindBy (className = "ant-select-selection-overflow-item")
    public List<WebElement> classesOfAddedGroups;

}
