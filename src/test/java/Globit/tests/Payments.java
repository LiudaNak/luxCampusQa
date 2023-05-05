package Globit.tests;

import Globit.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Payments {
    private static WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    PaymentsPage paymentsPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        paymentsPage = new PaymentsPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);

        //go to Payments section
        homePage.paymentsTab.click();
        }

    @Test
    public void navigateToCustomer(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(2000L));
        wait.until(ExpectedConditions.urlContains("payments"));
        //GIVEN
       wait.until(ExpectedConditions.elementToBeClickable(paymentsPage.linkToCustomer));
        //WHEN
        paymentsPage.linkToCustomer.click();
        //THEN
        //check that person's page is opened
        wait.until(ExpectedConditions.urlContains("persons"));
    }


    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }

}
