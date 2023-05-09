package Globit.Backoffice.tests;

import Globit.Backoffice.pages.HomePage;
import Globit.Backoffice.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class LoginLogout {
    public static WebDriver driver;

    //Create objects of pages
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;

    @BeforeEach
    public void openUrlAndLogin() {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
       }

    @Test
    public void navigate() throws MalformedURLException {
        //GIVEN
        String expectedPageTitle = "CO CONGRESS ONLINE";
        //WHEN
        String actualPageTitle = startingPage.driver.getTitle();
        //THEN
        Assertions.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Test
    public void login() {
        //GIVEN
        //WHEN
        //login
        loginPage.setEmail(loginPage.email);
        loginPage.setPassword();
        loginPage.passwordInput.submit();

        //THEN
        Assertions.assertTrue(homePage.logoutButton.isEnabled());
        homePage.logoutButton.click();
    }

    @Test
    public void logout() throws InterruptedException {
        //GIVEN
        //login
       startingPage.login();
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(100L));
        //WHEN
        homePage.logoutButton.click();
        //THEN
        //find the Login button
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.emailInput));
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
