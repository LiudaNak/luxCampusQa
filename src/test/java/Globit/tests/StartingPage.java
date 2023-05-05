package Globit.tests;

import Globit.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartingPage {
    public static WebDriver driver;
    static LoginPage loginPage = new LoginPage();

    static String linkToStagingEnvironment = "https://staging.congress-online.com/backoffice/";
    static String linkToDevEnvironment = "https://dev.congress-online.com/backoffice/develop/";
    static String linkToMailCatcher = "http://dev.congress-online.com:1080/";

    static String scope = "ESP";

    public static void driverInitialization(){
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(true);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        options.addArguments("--remote-allow-origins=*"); //added to 111 chromedriver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300L));
    }

    public static void login() throws InterruptedException {
        WebElement emailInput = driver.findElement(By.name("username"));
        emailInput.sendKeys(loginPage.email);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(loginPage.password);
        passwordInput.submit();
        Thread.sleep(1500L);
    }

    public static void navigateToScope() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(6000L));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[contains (@href, '/ao/"+scope+"')]"))));
        driver.findElement(By.xpath("//a[contains (@href, '/ao/"+scope+"')]")).click();
        Thread.sleep(1000L);
    }

}
