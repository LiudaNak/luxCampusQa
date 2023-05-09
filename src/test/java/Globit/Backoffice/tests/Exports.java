package Globit.Backoffice.tests;

import Globit.Backoffice.pages.*;
import Globit.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Exports {
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    ExportsPage exportsPage;
    MailCatcherPage mailCatcherPage;


    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        exportsPage = new ExportsPage();
        mailCatcherPage = new MailCatcherPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);

        //go to Exports section
        homePage.exportsTab.click();
    }

    @Test
    public void receiveExportByEmail() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("exports"));
        wait.until(ExpectedConditions.elementToBeClickable(exportsPage.receiveExportByEmailButton));

        //GIVEN
        exportsPage.receiveExportByEmailButton.click();
        String expectedEmail = "automation_test@test.com";

        //WHEN
        //send the export
        exportsPage.membershipFeeProductField.click();
        exportsPage.traineeElementFromDropdown.click();
        exportsPage.emailField.sendKeys(expectedEmail);
        wait.until(ExpectedConditions.elementToBeClickable(exportsPage.sendByEmailButton));
        exportsPage.sendByEmailButton.click();
        Thread.sleep(8000L);

        //THEN
        //go to MailCatcher
        startingPage.driver.navigate().to("http://dev.congress-online.com:1080/");
        wait.until(ExpectedConditions.elementToBeClickable(mailCatcherPage.emailOfLastSentItem));
        //find email of last received letter
        String actualEmail = mailCatcherPage.emailOfLastSentItem.getText().toString();
        Assertions.assertEquals("<"+expectedEmail+">", actualEmail);
    }

    @Test
    public void searchForExport() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        String expectedNameOfSearchedElement = "In Good Standing";
        wait.until(ExpectedConditions.urlContains("exports"));

        //WHEN
        exportsPage.searchField.sendKeys(expectedNameOfSearchedElement);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", exportsPage.searchIcon);
        wait.until(ExpectedConditions.elementToBeClickable(exportsPage.searchIcon));
        Thread.sleep(1000L);
        String actualNameOfSearchedElement = exportsPage.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);

        //THEN
        Assertions.assertTrue(actualNameOfSearchedElement.contains(expectedNameOfSearchedElement));
    }


    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
