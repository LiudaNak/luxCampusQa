package Globit.Backoffice.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationToCreationPages {
    private static WebDriver driver;

    public NavigationToCreationPages() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000L));
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
    public void openAddMemberPage(){
        //GIVEN
        WebElement actionsButton = driver.findElement(By.xpath("//span[text()='Actions']"));
        //WHEN
        actionsButton.click();
        WebElement addMemberButton = driver.findElement(By.xpath("//span[text()='Add member']"));
        addMemberButton.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Add member"));
    }

    @Test
    public void openAddContactPage(){
        //GIVEN
        WebElement actionsButton = driver.findElement(By.xpath("//span[text()='Actions']"));
        //WHEN
        actionsButton.click();
        WebElement addMemberButton = driver.findElement(By.xpath("//span[text()='Add contact']"));
        //WHEN
        addMemberButton.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Add contact"));
    }

    @Test
    public void openAddGroupPage(){
        //GIVEN
        //navigate to groups
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Groups')]/.."));
        groups.click();
        WebElement addGroupButton = driver.findElement(By.linkText("Add group"));
        addGroupButton.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Add group"));
    }

    @Test
    public void openAddWorkingGroupPage(){
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(10000L));
        //navigate to working group
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Working group')]/.."));
        groups.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ant-btn ant-btn-primary']")));
        WebElement addWorkingGroupButton = driver.findElement(By.xpath("//a[@class='ant-btn ant-btn-primary']"));
       // ((JavascriptExecutor) driver).executeScript("argument[0].click()", addWorkingGroupButton);
        addWorkingGroupButton.submit();
        WebElement modalPageTitle = driver.findElement(By.className("ant-modal-title"));
        String textOfPageTitle = modalPageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Add Working Group"));
    }

    @Test
    public void openCreateProductPage(){
        //GIVEN
        //navigate to Product
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Products')]/.."));
        groups.click();
        WebElement addProduct = driver.findElement(By.linkText("Add Product"));
        addProduct.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Create product"));
    }

    @Test
    public void openCreateMailingPage(){
        //GIVEN
        //navigate to Mailings
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Mailings')]/.."));
        groups.click();
        WebElement addMailing = driver.findElement(By.linkText("Add mailing"));
        addMailing.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Create Mailing"));
    }

    @Test
    public void openCreateTemplatePage(){
        //GIVEN
        //navigate to Templates
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Templates')]/.."));
        groups.click();
        WebElement addTemplate = driver.findElement(By.linkText("Create Template"));
        addTemplate.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Create Template"));
    }

    @Test
    public void openAddAccountPage(){
        //GIVEN
        //navigate to Account
        WebElement groups = driver.findElement(By.xpath("//a[contains(.,'Accounts')]/.."));
        groups.click();
        WebElement addAccount = driver.findElement(By.linkText("Add account"));
        addAccount.click();
        WebElement pageTitle = driver.findElement(By.className("page__title"));
        String textOfPageTitle = pageTitle.getText().toString();
        System.out.println(textOfPageTitle);
        //THEN
        Assertions.assertTrue(textOfPageTitle.contains("Add account"));
    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
