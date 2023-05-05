package Globit.tests;

import Globit.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Events {
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    EventsPage eventsPage;


    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        eventsPage = new EventsPage();

        startingPage.login();
    }

    @Test
    public void checkDefaultNavigationToAllTabOfEventsPage() {
        //GIVEN
        //wait for the loading of the page
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlContains("allScopes"));
        //WHEN
        String actualClass = eventsPage.allButton.getAttribute("class");
        //THEN
        //wrong class was returned
        System.out.println(actualClass + " - value of class that returned");
        Assertions.assertTrue(actualClass.endsWith("checked"));
    }

    @Test
    public void navigateToCongressOnlineTabOfEventsPage() {
        //GIVEN
        //WHEN
        eventsPage.congressOnlineButton.click();
        String actualClass = eventsPage.congressOnlineButton.getAttribute("class");
        //THEN
        Assertions.assertTrue(actualClass.endsWith("checked"));
    }

    @Test
    public void navigateToAssociationOnlineTabOfEventsPage() {
        //GIVEN
        //WHEN
        eventsPage.associationOnlineButton.click();
        String actualClass = eventsPage.associationOnlineButton.getAttribute("class");

        //THEN
        Assertions.assertTrue(actualClass.endsWith("checked"));
    }

    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
