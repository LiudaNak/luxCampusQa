package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage {
    public Identities_PersonPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//a[text()='Person']")
    public WebElement personTab;

    @FindBy (xpath = "//a[text()='Group']")
    public WebElement groupTab;


}
