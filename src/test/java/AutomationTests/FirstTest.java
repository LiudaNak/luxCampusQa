package AutomationTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {
    private static WebDriver driver;

    public FirstTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Chromedriver\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100L));

    }

    @Test
    public void testSearchResultPresent_seleniumInput() {
        //GIVEN
        driver.get("https://www.google.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        //WHEN
        input.sendKeys("QA automation");
        input.sendKeys(Keys.ENTER);
        //THEN

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ulc")));

    }

    @Test
    public void testSearchResultPresent_JsInput() {
        //GIVEN
        driver.get("https://www.google.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        WebElement input = driver.findElement(By.xpath(".//input[@name='q']"));
        //WHEN
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // set the text
        jsExecutor.executeScript("arguments[0].value='QA'", input);
        input.sendKeys(Keys.ENTER);

        //THEN
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));
        driver.close();
        driver.quit();

    }

    @Test
    public void shouldNavigate() throws MalformedURLException {
        //GIVEN
        String testUrl = "https://www.google.com/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        FluentWait<WebDriver> drivedFluentWait = new FluentWait<>(driver).withTimeout(Duration.ofMillis(3000L))
                .pollingEvery(Duration.ofMillis(3000L));
        String expectedPageTitle = "Google";
        //WHEN
        driver.navigate().to(new URL(testUrl));
        String actualPageTitle = driver.getTitle();
        //THEN
        //Assertions.assertEquals(expectedPageTitle, actualPageTitle);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//ul")));

drivedFluentWait.until(e -> e.findElement(By.xpath(".//ul")));
    }

    @Test
    public void shouldSearch_byId() {
        //GIVEN
        String expectedHeaderText = "Available relative locators";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.id("not existing"));
        //THEN
        Assertions.assertTrue(headerElements.isEmpty());
    }

    @Test
    public void shouldSearch_byName() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement emailInputElement = driver.findElement(By.name("email"));
        //THEN
        //Assertions.assertTrue(emailInputElement.isEnabled());
        wait.until(ExpectedConditions.visibilityOf(emailInputElement));
    }

    @Test
    public void shouldSearch_byClassName() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement submitButtonElement = driver.findElement(By.className("btn-primary"));
        //THEN
        //Assertions.assertTrue(submitButtonElement.isEnabled());
        wait.until(ExpectedConditions.visibilityOf(submitButtonElement));
    }

    @Test
    public void shouldSearch_byClassName_multipleElements() {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        List<WebElement> inputElements = driver.findElements(By.className("form-control"));

        //THEN
        Assertions.assertEquals(2, inputElements.size());
   }

    @Test
    public void shouldSearch_byTagName() {
        //GIVEN
        int expectedUlsCount = 35;
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> headerElements = driver.findElements(By.tagName("ul"));
        //THEN
        Assertions.assertEquals(expectedUlsCount, headerElements.size());
    }

    @Test
    public void shouldSearch_byLink() {
        //GIVEN
        String expectedUrl = "https://www.selenium.dev/documentation/about/contributing/";
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        WebElement linkElement = driver.findElement(By.partialLinkText("contribution"));
        linkElement.click();
        String actualCurrentUrl = driver.getCurrentUrl();
        //THEN
        Assertions.assertEquals(expectedUrl, actualCurrentUrl);

    }

    @Test
    public void shouldSearch_byCssSelector() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        WebElement inputElement =
                driver.findElement(By.cssSelector("input[type='Password']"));
        inputElement.sendKeys("verySecurePassword");
        //THEN
        Thread.sleep(1000L);

    }

    @Test
    public void shouldSearch_byCssSelector_parentChild() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector("ul > li"));
        //THEN
        Assertions.assertEquals(177, elements.size());

    }

    @Test
    public void shouldSearch_byCssSelector_complexStructure() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/elements/locators/");
        List<WebElement> elements =
                driver.findElements(By.cssSelector("#tabs-10 > li:nth-child(2) > a"));
        //THEN
        Assertions.assertEquals(1, elements.size());
        boolean isElementActive = elements.get(0).getAttribute("class").contains("active");
        Assertions.assertFalse(isElementActive);

    }

    @Test
    public void byXpath() {
        //GIVEN
        //WHEN
        driver.get("https://www.selenium.dev/documentation/webdriver/");
        WebElement headerElement = driver.findElement(By.xpath("(//div[@class='entry'])[3]//a"));
        headerElement.click();
        String currentUrl = driver.getCurrentUrl();
        //THEN
        org.assertj.core.api.Assertions.assertThat(currentUrl).endsWith("browser/");
    }

    /*@Test
    public void byRelativeLocator() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");
        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("emailTest@test.com");
        //THEN
        Thread.sleep(4000L);

    }



   /* @Test
    public void testClick() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");

        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");

        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");
        String classAttributeValue = passwordInput.getAttribute("class");
        String text = passwordInput.getText();

        //THEN
        boolean isEnabled = passwordInput.isEnabled();
        Assertions.assertTrue(isEnabled);
        boolean isDisplayed = passwordInput.isDisplayed();
        Assertions.assertTrue(isDisplayed);

        //WHEN
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]"));
        submitButton.click();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @Test
    public void testSubmitForm() {
        //GIVEN
        //WHEN
        driver.get("http://online-sh.herokuapp.com/login");

        By emailLocator = RelativeLocator.with(By.tagName("input")).above(By.id("exampleInputPassword1"));
        WebElement emailInput = driver.findElement(emailLocator);
        emailInput.sendKeys("test@test.com");


        By passwordLocator = RelativeLocator.with(By.id("exampleInputPassword1"));
        WebElement passwordInput = driver.findElement(passwordLocator);
        passwordInput.sendKeys("test");

        passwordInput.submit();

        //THEN
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }
*/
    @Test
    public void clickCheckbox() throws InterruptedException {
        //GIVEN
        //WHEN
        driver.manage().window().maximize();
        driver.get("https://mdbootstrap.com/docs/standard/forms/checkbox/");
        WebElement checkboxElementInput = driver.findElement(By.xpath("//label[contains(.,' Default checkbox')]//preceding-sibling::input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", checkboxElementInput);

        //THEN
        Assertions.assertFalse(checkboxElementInput.isSelected());

        //WHEN
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", checkboxElementInput);
        Thread.sleep(3000L);
        //THEN
        Assertions.assertTrue(checkboxElementInput.isSelected());

    }

    @AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
