package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_Mails {
    public Identities_PersonPage_PersonTab_Mails() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "(//i[@class='bi bi-eye'])[1]")
    public WebElement previewButtonForFirstEmail;

    @FindBy (xpath = "(//*[@class='bi bi-envelope'])[1]")
    public WebElement sendButtonForFirstEmail;

    @FindBy (xpath = "//span[text()='Send Mail']")
    public WebElement sendMailButton;

    @FindBy (xpath = "//*[contains(text(),'Preview')]")
    public WebElement previewColumn;


}
