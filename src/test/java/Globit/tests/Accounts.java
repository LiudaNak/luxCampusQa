package Globit.tests;

import Globit.pages.AccountsPage;
import Globit.pages.HomePage;
import Globit.pages.IdentitiesPage;
import Globit.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Accounts {
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    AccountsPage accountsPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        accountsPage = new AccountsPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);
        //go to Accounts section
       homePage.accountsTab.click();
    }

    @Test
    public void createAccount(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("accounts"));
        //GIVEN
        int randNum = (int) (Math.random() * 1000000);
        accountsPage.addAccountButton.click();
        //new email is needed each time, because created one can not be deleted from UI
        accountsPage.mailField.sendKeys("automationtest+"+randNum+"@test.com");
        accountsPage.mailField.submit();

        //WHEN
        accountsPage.firstNameField.sendKeys("AT");
        accountsPage.lastNameField.sendKeys("AT");
        accountsPage.rolesField.click();
        accountsPage.memberOptionFromDropdown.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", accountsPage.activationCheckbox);
        accountsPage.passwordField.sendKeys("123");
        accountsPage.saveButton.click();
        //THEN
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.editAccount));
    }

    @Test
    public void editExistingAccount(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(2000L));
        //GIVEN
        //go to add account page
        accountsPage.addAccountButton.click();
        //WHEN
        accountsPage.mailField.sendKeys("automationtest@test.com");
        accountsPage.mailField.submit();
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.changeEmailButton));
        //THEN
        String classOfAddExistingAccount = accountsPage.updateExistingAccount.getAttribute("class").toString();
        Assertions.assertTrue(classOfAddExistingAccount.endsWith("active"));
    }

    @Test
    public void editNonExistingAccount(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(2000L));

        //GIVEN
        //go to Add Account page
        accountsPage.addAccountButton.click();
        //WHEN
        accountsPage.mailField.sendKeys("automationtest123@test.com");
        accountsPage.mailField.submit();
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.changeEmailButton));

        //THEN
        String classOfAddExistingAccount = accountsPage.addExistingAccount.getAttribute("class").toString();
        System.out.println(classOfAddExistingAccount+ " ");
        Assertions.assertTrue(classOfAddExistingAccount.endsWith("active"));
    }

    @Test
    public void editCurrentAccount() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //GIVEN
        String expectedFirstName = "AutomationTest - First Name";
        String expectedLastName = "Automation Test - Last Name";
        //WHEN
        //go to first account from the list
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.firstAccountOnTheList));
        accountsPage.firstAccountOnTheList.click();
        //go to Edit account page
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.editAccountButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", accountsPage.editAccount);
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.changeEmailButton));
        //check that "Update current account" section is active
        String classOfUpdateCurrentAccount = accountsPage.updateCurrentAccount.getAttribute("class");
        Assertions.assertTrue(classOfUpdateCurrentAccount.endsWith("active"));

        //take current fields' values
        String initialFirstNameField = accountsPage.firstNameField.getAttribute("value");
        String initialLastNameField = accountsPage.lastNameField.getAttribute("value");
        //edit elements
        accountsPage.firstNameField.clear();
        accountsPage.firstNameField.sendKeys(expectedFirstName);
        accountsPage.lastNameField.clear();
        accountsPage.lastNameField.sendKeys(expectedLastName);
        accountsPage.saveButton.click();
        //go to Edit account one more time
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.editAccountButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", accountsPage.editAccountButton);
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.changeEmailButton));

        //THEN
        //check that fields are expected
        String actualFirstName = accountsPage.firstNameField.getAttribute("value");
        String actualLastName = accountsPage.lastNameField.getAttribute("value");
        System.out.println(actualFirstName);
        System.out.println(actualLastName);
        Assertions.assertEquals(expectedFirstName, actualFirstName);
        Assertions.assertEquals(expectedLastName, actualLastName);

        //after test
        //return the previous fields values
        accountsPage.firstNameField.clear();
        accountsPage.firstNameField.sendKeys(initialFirstNameField);
        accountsPage.lastNameField.clear();
        accountsPage.lastNameField.sendKeys(initialLastNameField);
        accountsPage.saveButton.click();
    }

    @Test
    public void searchForAccounts() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        String expectedNameOfSearchedElement = "l.nakonechna@globit.com";

        //WHEN
        accountsPage.searchField.sendKeys(expectedNameOfSearchedElement);
        accountsPage.searchIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(accountsPage.searchIcon));
        Thread.sleep(500L);
        String actualNameOfSearchedElement = accountsPage.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);

        //THEN
        Assertions.assertTrue(actualNameOfSearchedElement.contains(expectedNameOfSearchedElement));
    }

    //@AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
