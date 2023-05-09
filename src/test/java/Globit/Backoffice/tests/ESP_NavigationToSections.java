package Globit.Backoffice.tests;

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

import java.time.Duration;

public class ESP_NavigationToSections {
    private static WebDriver driver;

    public ESP_NavigationToSections() {
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
    }

    @Test
    public void testDefaultNavigationToIdentities() {
        WebElement identities = driver.findElement(By.xpath("//a[contains(.,'Identities')]/.."));
        String classOfIdentities = identities.getAttribute("class").toString();
        Assertions.assertTrue(classOfIdentities.endsWith("selected"));
    }

    @Test
    public void navigateToGroups() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Groups')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToWorkingGroup() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Working groups')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }


    @Test
    public void navigateToOrders() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Orders')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToPayments() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Payments')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToStatistics() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Statistics')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToExports() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Exports')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToProducts() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Products')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToMailings() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Mailings')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToTemplates() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Templates')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }

    @Test
    public void navigateToAccounts() {
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Accounts')]/.."));
        groups.click();
        String classOfGroups = groups.getAttribute("class").toString();
        Assertions.assertTrue(classOfGroups.endsWith("selected"));
    }



    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
