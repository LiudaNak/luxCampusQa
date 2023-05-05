package Globit.tests;

import Globit.pages.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Statistics {
    private static WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    StatisticsPage statisticsPage;
    IdentitiesPage identitiesPage;


    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        statisticsPage = new StatisticsPage();
        identitiesPage = new IdentitiesPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);

        //go to Statistics section
        homePage.statisticsTab.click();

    }

    @Test
    public void checkElementInVirchowsArchiveSection(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        wait.until(ExpectedConditions.urlContains("statistics"));

        //check the presence of elements 'Virchows Archive'
        wait.until(ExpectedConditions.visibilityOf(statisticsPage.virchowsArchiveTitle));

        //check the presence of elements 'Total members in good standing'
        wait.until(ExpectedConditions.visibilityOf(statisticsPage.totalMemberInGoodStandingElement));
    }

    @Test
    public void checkElementInStandingStatisticsSection(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));

        //check the presence of elements 'Standing Statistics'
        wait.until(ExpectedConditions.visibilityOf(statisticsPage.standingStatisticsElement));

        //check the presence of elements 'Members In Good Standing'
        wait.until(ExpectedConditions.visibilityOf(statisticsPage.memberInGoodStandingElement));
    }

    @Test
    public void checkCountOfMembersByCountriesSection(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));

        //check the presence of elements 'Count of members by countries'
        wait.until(ExpectedConditions.visibilityOf(statisticsPage.countOfMembersByCountriesElement));
    }


    //not finished
    @Test
    public void goToCountryUsers() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        wait.until(ExpectedConditions.visibilityOf(statisticsPage.worldMapElement));

        /*Point point = mapElement.getLocation();
        System.out.println("Element's Position from left side is: "+point.getX()+" pixels.");
        System.out.println("Element's Position from top is: "+point.getY()+" pixels.");*/
        //Element's Position from left side is: 562 pixels.
        //Element's Position from top is: 439 pixels.

        //scroll down
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].scrollIntoView(true);", statisticsPage.FinlandOnMapElement);
        Thread.sleep(2000L);

        //move to Finland element
        act.moveToElement(statisticsPage.FinlandOnMapElement).perform();
        Thread.sleep(1000L);

        //read the country name
        String countryName = statisticsPage.hoverInfo.getText().toLowerCase();
        System.out.println(countryName);

        //click on the country
        act.doubleClick().perform();
        Thread.sleep(1000L);

        //go to pop-up
        startingPage.driver.switchTo().activeElement();
        //click on the OK button
        statisticsPage.okButtonOnConfirmation.click();
        Thread.sleep(2000L);

        startingPage.driver.switchTo().defaultContent();

        String currentUrl = startingPage.driver.getCurrentUrl().toString().toLowerCase();
        System.out.println(currentUrl);

        String country = identitiesPage.countryOfUsers.getText();

        System.out.println(country);
    }

    //@AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
