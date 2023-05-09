package Globit.Backoffice.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationToScopes {
    private static WebDriver driver;

    public NavigationToScopes() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(300L));
    }

    public void select100ElementsForAPage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1000L));
        //navigate to the "10/page" button
        WebElement buttonFor10Pages = driver.findElement(By.className("ant-select-selection-item"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click", buttonFor10Pages);
        Thread.sleep(500);
        WebElement buttonFor100Pages = driver.findElement(By.xpath("//div[text()='100 / page']"));
        //on the drop-down, navigate to the "100/page" button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", buttonFor100Pages);
        buttonFor100Pages.click();
    }

    public void goToNextPageOfPagination(List<WebElement> linkToScope) {
        if (linkToScope.size()==0) {
            driver.findElement(By.className("ant-pagination-next")).click();
        }
    }


    @BeforeEach
    public void openUrlAndLogin() {
        driver.navigate().to(StartingPage.linkToDevEnvironment);
        //login
        WebElement emailInput = driver.findElement(By.name("username"));
        emailInput.sendKeys("l.Nakonechna@globit.com");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("123");
        passwordInput.submit();
    }

    @Test
    public void navigateToESP() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ESP";
        Thread.sleep(2000L);
        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/ao/ESP']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/ao/ESP']"));
        //WHEN
        linkToScope.click();
        //THEN
        // find "ESP" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToADHD() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ADHD";
        Thread.sleep(2000L);
        //WHEN
        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/ao/ADHD']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/ao/ADHD']"));
        //WHEN
        linkToScope.click();
        //THEN
        // find "ADHD" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToADHD2021() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ADHD2021";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/ADHD2021']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/ADHD2021']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "ADHD2021" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToDGKJP2021() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "DGKJP2021";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/DGKJP2021']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/DGKJP2021']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "DGKJP2021" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToDPG2021() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "DPG2021";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/DPG2021']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/DPG2021']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "DPG2021" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToDPT2020() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "DPT2020";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/DPT2020']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/DPT2020']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "DPT2020" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToECP2019() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ECP2019";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/ECP2019']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/ECP2019']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "ECP2019" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToECP2020() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ECP2020";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/ECP2020']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/ECP2020']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "ECP2020" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToECP2022() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ECP2022";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/ECP2022']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/ECP2022']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "ECP2022" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToESSPD2020() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "ESSPD2020";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/ESSPD2020']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/ESSPD2020']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "ESSPD2020" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToIACAPAP() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(300L));
        String expectedLabel = "IACAPAP";
        Thread.sleep(2000L);

        //WHEN
        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/ao/IACAPAP']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/ao/IACAPAP']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "IACAPAP" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToSMH2020() throws InterruptedException {
        //GIVEN
        String expectedLabel = "SMH2020";
        Thread.sleep(2000L);

        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/SMH2020']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/SMH2020']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "SMH2020" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToWFSBP() throws InterruptedException {
        //GIVEN
        String expectedLabel = "WFSBP";
        Thread.sleep(2000L);
        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/ao/WFSBP']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/ao/WFSBP']"));

        //WHEN
        //navigate to WFSBP
        linkToScope.click();
        //THEN
        // find "WFSBP" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToWFSBP2021() throws InterruptedException {
        //GIVEN
        String expectedLabel = "WFSBP2021";
        Thread.sleep(2000L);
        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/WFSBP2021']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/WFSBP2021']"));

        //WHEN
        //navigate to WFSBP2021
        linkToScope.click();
        //THEN
        // find "WFSBP2021" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }

    @Test
    public void navigateToWHITELABEL() throws InterruptedException {
        //GIVEN
        String expectedLabel = "WHITELABEL";
        Thread.sleep(2000L);
        List <WebElement> linksToScope = driver.findElements(By.cssSelector("a[href='/backoffice/develop/co/WHITELABEL']"));
        goToNextPageOfPagination(linksToScope);
        WebElement linkToScope = driver.findElement(By.cssSelector("a[href='/backoffice/develop/co/WHITELABEL']"));

        //WHEN
        linkToScope.click();
        //THEN
        // find "WHITELABEL" label
        String actualLabel = driver.findElement(By.cssSelector("div.sidebar__scope")).getText();
        Assertions.assertEquals(expectedLabel, actualLabel);
    }


    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
