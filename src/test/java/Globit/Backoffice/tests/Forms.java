package Globit.Backoffice.tests;

import Globit.Backoffice.pages.HomePage;
import Globit.Backoffice.pages.LoginPage;
import Globit.Backoffice.pages.FormsPages;
import Globit.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Forms {

    LoginPage loginPage;
    static HomePage homePage;
    static StartingPage startingPage;
    static FormsPages formsPages;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        formsPages = new FormsPages();

        StartingPage.login();
        StartingPage.navigateToScope();
        //go to Forms section
        homePage.formsTab.click();
    }

    @Test
    public void searchForForms() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("forms"));
        //GIVEN
        String expectedNameOfSearchedElement = "application";
        //WHEN
        formsPages.searchField.sendKeys(expectedNameOfSearchedElement);
        formsPages.searchIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.searchIcon));
        Thread.sleep(500L);
        String actualNameOfSearchedElement = formsPages.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);
        //THEN
        Assertions.assertTrue(actualNameOfSearchedElement.contains(expectedNameOfSearchedElement));
    }

    @Test
    public void createForm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        //GIVEN
        String formName = "AT for creation";
        formsPages.addFormButton.click();

        formsPages.nameField.sendKeys(formName);

        //pagesField.click();
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='initial']")));
        //WebElement initialFromDropdown = driver.findElement(By.xpath("//div[text()='initial']"));
        //initialFromDropdown.click();
        //nameField.click(); //to take drop-down away
        action.doubleClick(formsPages.saveButton).perform();
        Thread.sleep(500L);

        //go to Forms list
        action.doubleClick(homePage.formsTab).perform();
        //open 100 elements for a page
        formsPages.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.elementFromDropDown100Pages));
        formsPages.elementFromDropDown100Pages.click();

        //THEN
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+formName+"']")));
        WebElement createdForm = startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']"));

        //delete created form
        action.scrollToElement(startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']"))).perform();
        Thread.sleep(500L);
        WebElement deleteIcon = startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']/../../td[4]/button"));
        deleteIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.deleteConfirmation));
        formsPages.deleteConfirmation.click();
    }

    @Test
    public void deleteForm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        //GIVEN
        String formName = "AT for deleting";
        //create new form
        formsPages.addFormButton.click();
        formsPages.nameField.sendKeys(formName);
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.saveButton));
        action.doubleClick(formsPages.saveButton).perform();
        Thread.sleep(500L);

        //go to Forms list
        action.doubleClick(formsPages.forms).perform();
        //open 100 elements for a page
        formsPages.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.elementFromDropDown100Pages));
        formsPages.elementFromDropDown100Pages.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+formName+"']")));

        //WHEN
        //delete created form
        action.scrollToElement(startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']"))).perform();
        Thread.sleep(500L);
        WebElement deleteIcon = startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']/../../td[4]/button"));
        deleteIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.deleteConfirmation));
        formsPages.deleteConfirmation.click();

        //THEN
        //check
        List<WebElement> deletedForm = startingPage.driver.findElements(By.xpath("//a[text()='"+formName+"']"));
        Integer sizeOfDeletedGroup = deletedForm.size();
        System.out.println(sizeOfDeletedGroup);
        Assertions.assertTrue(sizeOfDeletedGroup==1);

    }

    @Test
    public void editForm() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        //GIVEN
        String formName = "AT for editing";
        String formNameEdited = " - Edited";
        //create new form
        formsPages.addFormButton.click();
        formsPages.nameField.sendKeys(formName);
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.saveButton));
        action.doubleClick(formsPages.saveButton).perform();
        Thread.sleep(500L);
        //go to Forms list
        action.doubleClick(homePage.formsTab).perform();
        //open 100 elements for a page
        formsPages.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.elementFromDropDown100Pages));
        formsPages.elementFromDropDown100Pages.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+formName+"']")));
        action.scrollToElement(startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']"))).perform();
        Thread.sleep(500L);

        //find the element and go to Edited button
        WebElement editIconForForm = startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']/../../td[3]/a"));
        editIconForForm.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add transition']")));
        //edit Name field
        formsPages.nameFieldOnEditedPage.sendKeys(formNameEdited);
        //add Pages field
        formsPages.pagesField.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.dropdownElement));
        String valueOfDropdownElement = formsPages.dropdownElement.getText();
        formsPages.dropdownElement.click();
        formsPages.nameFieldOnEditedPage.click(); //to take drop-down away
        formsPages.saveButton.click();
        //check edited form
        String actualValueOfNameField = formsPages.nameFieldOnEditedPage.getAttribute("value");
        Assertions.assertEquals(formName+formNameEdited, actualValueOfNameField);
    }

    @Test
    public void checkSearchField() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        String formName = "AT for searching";
        //create new form
        formsPages.addFormButton.click();
        formsPages.nameField.sendKeys(formName);
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.saveButton));
        action.doubleClick(formsPages.saveButton).perform();
        Thread.sleep(500L);
        //go to Forms list
        action.doubleClick(formsPages.forms).perform();

        //WHEN
        formsPages.searchField.sendKeys(formName);
        formsPages.searchIcon.click();
        //THEN
        String actualFormName = formsPages.searchedElement.getText();
        System.out.println(actualFormName);
        Assertions.assertEquals(formName, actualFormName);

        //after test - delete created form
        WebElement deleteIcon = startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']/../../td[4]/button"));
        deleteIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']/span[text()='Delete']")));
        formsPages.deleteConfirmation.click();
    }

    @Test
    public void navigateToPages() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        //GIVEN
        String formName = "AT for navigating to Pages";
        //create new form
        formsPages.addFormButton.click();
        formsPages.nameField.sendKeys(formName);
        //add Pages field
        formsPages.pagesField.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.dropdownElement));
        String valueOfDropdownElement = formsPages.dropdownElement.getText();
        formsPages.dropdownElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(formsPages.saveButton));
        formsPages.nameField.click();
        //action.doubleClick(saveButton).perform();
        formsPages.saveButton.click();
        Thread.sleep(500L);

        //go to Forms list
        action.doubleClick(formsPages.forms).perform();
        //open 100 elements for a page
        formsPages.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@title='100 / page']")));
        formsPages.elementFromDropDown100Pages.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='"+formName+"']")));
        action.scrollToElement(startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']"))).perform();
        Thread.sleep(500L);

        //find the element and go to Pages
        WebElement pageElement = startingPage.driver.findElement(By.xpath("//a[text()='"+formName+"']/../../td[2]/a"));
        pageElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Form page']")));
        Thread.sleep(500L);
        String pageNameValue = formsPages.fieldForPageName.getAttribute("value");
        System.out.println("pageNameValue is "+pageNameValue);

        //THEN
        Assertions.assertEquals(valueOfDropdownElement, pageNameValue);
        }


    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
