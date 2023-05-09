package Globit.Backoffice.tests;

import Globit.Backoffice.pages.HomePage;
import Globit.Backoffice.pages.IdentitiesPage;
import Globit.Backoffice.pages.LoginPage;
import Globit.Backoffice.pages.OrdersPage;
import Globit.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Orders {
    private static WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    OrdersPage orderPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        orderPage = new OrdersPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);

        //go to Orders section
        homePage.ordersTab.click();
    }

    @Test
    public void navigateToCustomer(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("orders"));
        //GIVEN
        wait.until(ExpectedConditions.elementToBeClickable(orderPage.linkToCustomer ));

        //WHEN
        orderPage.linkToCustomer .click();

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
