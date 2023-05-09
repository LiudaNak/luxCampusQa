package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_System {
    public Identities_PersonPage_PersonTab_System() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//i[@class='bi bi-trash']")
    public WebElement deleteButton;

    @FindBy (xpath = "//*[text()='Export person']/../../../div[2]/div/div/button")
    public WebElement exportPersonButton;

    @FindBy (xpath = "//*[text()='Export vCard']/../../../div[2]/div/div/button")
    public WebElement exportVCard;

}
