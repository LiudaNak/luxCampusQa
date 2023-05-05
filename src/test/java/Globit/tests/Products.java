package Globit.tests;

import Globit.pages.*;
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

public class Products {
    private static WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    ProductsPage productsPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        productsPage = new ProductsPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);
        //go to Products section
        homePage.productsTab.click();
    }

    @Test
    public void createProduct(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("products"));

        productsPage.addProductButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.cancelButton));

        //GIVEN
        String expectedTitle = "AutomationTest";

        //WHEN
        productsPage.titleField.sendKeys(expectedTitle);
        productsPage.descriptionField.sendKeys("AutomationTest_description");
        productsPage.proofRequiredCheckbox.click();

        productsPage.periodField.click();
        productsPage.periodDropdownElement.click();

        productsPage.categoryField.click();
        productsPage.categoryDropdownElement.click();

        productsPage.taxRateField.sendKeys("10");
        productsPage.publicCheckbox.click();
        productsPage.priceField.sendKeys("100");

        productsPage.enablesRolesField.click();
        productsPage.enablesRolesDropdownElement.click();
        productsPage.saveButton.click();

        //THEN
        wait.until(ExpectedConditions.visibilityOf(productsPage.editProductButton));

        String actualTitle = productsPage.titleOfCreateProduct.getText();
        Assertions.assertEquals(expectedTitle, actualTitle);

        //Product can not be deleted from BackOffice
    }

    @Test
    public void editProduct(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        //open 100 elements for a page
        productsPage.paging10PagesElement.click();
        productsPage.elementFromDropDown100Pages.click();

        //open edit button of a Product
        productsPage.editButtonForAutomationTestProduct.click();
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.cancelButton));

        //GIVEN
        /*WebElement titleField = driver.findElement(By.name("title"));
        WebElement descriptionField = driver.findElement(By.name("descriptions[0]"));
        WebElement proofRequiredCheckbox = driver.findElement(By.name("proofRequired"));
        WebElement periodField = driver.findElement(By.name("period"));
        WebElement categoryField = driver.findElement(By.name("category"));
        WebElement taxRateField = driver.findElement(By.name("taxRate"));
        WebElement publicCheckbox = driver.findElement(By.name("public"));
        WebElement priceField = driver.findElement(By.name("price"));
        WebElement enablesRolesField = driver.findElement(By.name("enablesRoles"));
        WebElement saveButton = driver.findElement(By.xpath("//span[text()='Save']"));
        WebElement cancelButton = driver.findElement(By.xpath("//span[text()='Cancel']"));
*/
        //WHEN
        //edit fields
        productsPage.titleField.clear();
        String expectedTitle = "AutomationTest_Edited";
        productsPage.titleField.sendKeys(expectedTitle);

        productsPage.descriptionField.clear();
        productsPage.descriptionField.sendKeys("AT_Edited");

        productsPage.proofRequiredCheckbox.click();

        productsPage.periodField.click();
        WebElement periodDropdownElement = driver.findElement(By.xpath("//div[text()='1 year']"));
        periodDropdownElement.click();

        productsPage.categoryField.click();
        WebElement categoryDropdownElement = driver.findElement(By.xpath("//div[text()='Journal']"));
        categoryDropdownElement.click();

        productsPage.taxRateField.clear();
        productsPage.taxRateField.sendKeys("50");

        productsPage.publicCheckbox.click();

        productsPage.priceField.clear();
        productsPage.priceField.sendKeys("200");

        productsPage.enablesRolesField.click();
        productsPage.memberInGoodStandingRolesDropdownElement.click();

        productsPage.saveButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Edit product']")));

        //THEN
        String actualTitle = productsPage.currentTitle.getText();

        Assertions.assertEquals(expectedTitle, actualTitle);
    }


    @Test
    public void searchForProduct() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        String expectedNameOfSearchedElement = "3-year membership";

        //WHEN
        productsPage.searchField.sendKeys(expectedNameOfSearchedElement);
        productsPage.searchIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.searchIcon));
        Thread.sleep(500L);
        String actualNameOfSearchedElement = productsPage.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);

        //THEN
        Assertions.assertTrue(actualNameOfSearchedElement.contains(expectedNameOfSearchedElement));
    }

    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
