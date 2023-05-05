package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentsPage {
    public PaymentsPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//tr[2]/td/a")
    public WebElement linkToCustomer;

}
