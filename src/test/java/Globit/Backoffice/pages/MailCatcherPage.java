package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailCatcherPage {
    public MailCatcherPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//tbody/tr[1]/td[2]")
    public WebElement emailOfLastSentItem;
}
