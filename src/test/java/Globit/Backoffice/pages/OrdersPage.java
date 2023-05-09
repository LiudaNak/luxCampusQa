package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
    public OrdersPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//tr[3]/td/a")
    public WebElement linkToCustomer;

}
