package Globit.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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

public class Identities_NavigationToTabs {
    private static WebDriver driver;

    public Identities_NavigationToTabs() {

            System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chromedriver\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            options.setHeadless(false);
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300L));
        }

        @BeforeEach
        public void openUrlAndLogin() throws InterruptedException {
            driver.navigate().to(StartingPage.linkToDevEnvironment);
            //login
            WebElement emailInput = driver.findElement(By.name("username"));
            emailInput.sendKeys("l.Nakonechna@globit.com");
            WebElement passwordInput = driver.findElement(By.name("password"));
            passwordInput.sendKeys("123");
            passwordInput.submit();
            Thread.sleep(2000L);
            //navigate to ESP
            driver.findElement(By.xpath("//a[contains (@href, '/ao/"+StartingPage.scope+"')]")).click();
            Thread.sleep(2000L);
        }
        @Test
        public void navigateToPerson() throws InterruptedException {
        //GIVEN
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
            //navigate to a person
            WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
            //find user's name
            String userName = user.getText();
           //WHEN
            user.click();
            //find page title
            WebElement title = driver.findElement(By.xpath("//div[@class='page__title']"));
            String pageTitle = title.getText();
            System.out.println(userName + " user name");
            //why page title is "I"??????
            System.out.println(pageTitle + " page title");
            //THEN
            //check that user's name is in the page title
            Assertions.assertTrue(pageTitle.contains(userName));

        }

        @Test
        public void checkThatOverviewIsDefaultTab(){
        //GIVEN
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
            WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
            //WHEN
            user.click();
            //find tab element
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Overview']/..")));
            WebElement overviewTab = driver.findElement(By.xpath("//a[text()='Overview']/.."));
            String overviewTabClass = overviewTab.getAttribute("class");
            System.out.println(overviewTabClass + " - class");
            //THEN
            //check that class contains "selected"
            Assertions.assertTrue(overviewTabClass.endsWith("selected"));
        }

        @Test
        public void navigateToPersonsTabOfIdentities() {
        //GIVEN
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
            WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
            //WHEN
            user.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Person']/..")));
            WebElement personTab = driver.findElement(By.xpath("//a[text()='Person']/.."));
            personTab.click();
            String personTabClass = personTab.getAttribute("class").toString();
            //THEN
            //check that class contains "selected"
            Assertions.assertTrue(personTabClass.endsWith("selected"));
        }

    @Test
    public void navigateToGroupTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Group']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Group']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToMembershipTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Membership']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Membership']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToWorkingGroupTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Working Groups']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Working Groups']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToOrdersTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Orders']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Orders']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToPaymentsTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Payments']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Payments']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToMailsTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mails']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Mails']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToAccountsTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Accounts']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Accounts']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToDocumentsTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Documents']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='Documents']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

    @Test
    public void navigateToSystemTabOfIdentities() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        WebElement user = driver.findElement(By.xpath("//tr[1]/td[3]/a"));
        //WHEN
        user.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='System']/..")));
        WebElement personTab = driver.findElement(By.xpath("//a[text()='System']/.."));
        personTab.click();
        String personTabClass = personTab.getAttribute("class").toString();
        //THEN
        //check that class contains "selected"
        Assertions.assertTrue(personTabClass.endsWith("selected"));
    }

        @AfterEach
        public void cleanUp() {
            driver.close();
            driver.quit();
        }
    }
