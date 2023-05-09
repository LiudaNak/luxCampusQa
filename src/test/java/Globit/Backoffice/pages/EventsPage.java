package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EventsPage {
    public EventsPage() {
        PageFactory.initElements(StartingPage.driver, this);}

    @FindBy (xpath = "(//label['ant-radio-button-wrapper'])[1]")
    public WebElement allButton;

    @FindBy (xpath = "//label['ant-radio-button-wrapper'] [2]")
    public WebElement congressOnlineButton;

    @FindBy (xpath = "//label['ant-radio-button-wrapper'] [3]")
    public WebElement associationOnlineButton;

}
