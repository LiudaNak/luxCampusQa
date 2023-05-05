package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class LoginPage {
    public LoginPage(){
        PageFactory.initElements(StartingPage.driver, this);
   }


    @FindBy (name = "username")
    public WebElement emailInput;

    @FindBy(name = "password")
    public WebElement passwordInput;

    public  String email = "l.Nakonechna@globit.com";
    public String password = "123";

    public LoginPage setEmail(String a) {
        emailInput.sendKeys(a);
        return this;
    }

    public LoginPage setPassword() {
        passwordInput.sendKeys(password);
        return this;
    }

}
