package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IdentitiesPage {
    public IdentitiesPage() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    @FindBy (xpath = "//tr[3]/td[3]/a")
    public WebElement user;

    @FindBy (xpath = "//div[@class='country']")
    public WebElement countryOfUsers;

    @FindBy (xpath = "//tr[2]/td[1]")
    public WebElement idOfFirstUser;

    @FindBy (xpath = "//tr[2]/td[3]")
    public WebElement firstNameOfFirstUser;

    @FindBy (xpath = "//tr[2]/td[4]")
    public WebElement lastNameOfFirstUser;

    @FindBy (xpath = "//tr[2]/td[5]")
    public WebElement emailOfFirstUser;

    public static void navigateToUser() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(6000L));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[3]/td[3]/a")));
        WebElement user = StartingPage.driver.findElement(By.xpath("//tr[3]/td[3]/a"));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()",user);
        Thread.sleep(1000L);
    }
}
