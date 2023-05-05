package AutomationTests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Project {
    private static WebDriver driver;

    public Project() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100L));
    }

    @BeforeEach
    public void openURL() {
        driver.navigate().to("http://online-sh.herokuapp.com");
        WebElement emailInput = driver.findElement(By.id("exampleInputEmail1"));
        emailInput.sendKeys("test@test.com");
        WebElement passwordInput = driver.findElement(By.id("exampleInputPassword1"));
        passwordInput.sendKeys("123");
        passwordInput.submit();
    }

    @Test
    public void shouldNavigate() throws MalformedURLException {
        //GIVEN
        //String testURL = "http://online-sh.herokuapp.com";
        String expectedPageTitle = "Online Shop";
        //WHEN
        //driver.navigate().to(new URL(testURL));
        String actualPageTitle = driver.getTitle();
        //THEN
        Assertions.assertEquals(expectedPageTitle, actualPageTitle);
    }

    @Test
    public void login() throws InterruptedException {
        //GIVEN
        Thread.sleep(1000L);
        //WHEN
        //find the Logout button
        WebElement logoutButton = driver.findElement(By.cssSelector("a[href='/logout']"));
        //THEN
        Assertions.assertTrue(logoutButton.isEnabled());
    }

    @Test
    public void logout() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        //WHEN
        WebElement logoutButton = driver.findElement(By.cssSelector("a[href='/logout']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", logoutButton);
        //THEN
        //find the email input
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("exampleInputEmail1")));
    }

    @Test
    public void addNewProduct() {
        //GIVEN
        //WHEN
        //go to Add new Product page
        WebElement addNewProductButton = driver.findElement(By.xpath("//a[contains(., 'Add new product')]"));
        addNewProductButton.click();
        //fill in form
        WebElement productNameInput = driver.findElement(By.id("exampleInputProduct1"));
        productNameInput.sendKeys("testProduct");

        WebElement productPriceInput = driver.findElement(By.id("exampleInputPrice1"));
        productPriceInput.sendKeys("100");
        productPriceInput.submit();
        //find new product on the page
        WebElement testProduct = driver.findElement(By.xpath("//td[contains(., 'testProduct')]"));
        //THEN
        Assertions.assertTrue(testProduct.isEnabled());
    }

    @Test
    public void registerNewUser() {
        //GIVEN
        driver.navigate().to("http://online-sh.herokuapp.com/registration");
        //WHEN
        //THEN
    }


    @Test
    public void updateProduct() {

    }

    @Test
    public void deleteProduct() {

    }


    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
