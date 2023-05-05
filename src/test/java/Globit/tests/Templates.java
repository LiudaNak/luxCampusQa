package Globit.tests;

import Globit.pages.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Templates {
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    TemplatesPage templatesPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        templatesPage = new TemplatesPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);

        //go to Templates section
        homePage.templatesTab.click();
        }

    @Test
    public void createTemplate_DocumentAndPdf(){
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("templates"));

        //GIVEN
        String expectedTemplateName = "AutomationTest_DocumentAndPdf";
        templatesPage.createTemplateButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.cancelButton));

        //WHEN
        templatesPage.nameField.sendKeys(expectedTemplateName);
        templatesPage.templateTypeField.click();
        templatesPage.documentFromDropdown.click();

        templatesPage.formatField.click();
        templatesPage.pdfFromDropdown.click();

        act.sendKeys(templatesPage.contentField, "test").perform();
        templatesPage.contentField.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", templatesPage.saveButton);
        //THEN
        wait.until(ExpectedConditions.visibilityOf(templatesPage.editTemplateButton));

        //delete created template
        //go to list of Templates
        templatesPage.templatesSection.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.deleteIconForAutomationTest_DocumentAndPdfTemplate));

        templatesPage.deleteIconForAutomationTest_DocumentAndPdfTemplate.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.deleteButtonOfConfirmation));
        templatesPage.deleteButtonOfConfirmation.click();
    }

    @Test
    public void createTemplate_ExportAndMicrosoftExcel(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        String expectedTemplateName = "AutomationTest_ExportAndMicrosoftExcel";

        templatesPage.createTemplateButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.cancelButton));
        templatesPage.nameField.sendKeys(expectedTemplateName);
        templatesPage.templateTypeField.click();
        templatesPage.exportFromDropdown.click();
        templatesPage.formatField.click();
        templatesPage.microsoftExcelFromDropdown.click();

        templatesPage.formatField.submit();

        wait.until(ExpectedConditions.visibilityOf(templatesPage.editTemplateButton));

        //delete created template
        //go to list of Templates
        templatesPage.templatesSection.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.createTemplateButton));

        templatesPage.deleteIconForExportAndMicrosoftExcelTemplate.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.deleteButtonOfConfirmation));
        templatesPage.deleteButtonOfConfirmation.click();
    }

    @Test
    public void editTemplate() throws InterruptedException {
        //there is a bug in BackOffice that Save button is not active immediately after editing
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        //Create new template
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.createTemplateButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", templatesPage.createTemplateButton);
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.cancelButton));

        templatesPage.nameField.sendKeys("Template_for_editing");
        templatesPage.templateTypeField.click();
        Thread.sleep(300L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", templatesPage.documentFromDropdown);

        templatesPage.formatField.click();
        Thread.sleep(300L);
        templatesPage.pdfFromDropdown.click();

        startingPage.driver.switchTo().frame("textArea_ifr");
        Thread.sleep(500L);
        String contentValueWhileCreation = "1";
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", templatesPage.contentFieldOnEditPage);
        templatesPage.contentFieldOnEditPage.sendKeys(contentValueWhileCreation);

        startingPage.driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.saveButton));
        act.doubleClick(templatesPage.saveButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.editTemplateButton));

        //WHEN
        //edit content field
        startingPage.driver.switchTo().frame("textArea_ifr");
        String contentValueWhileEditing = "2";
        templatesPage.contentFieldOnEditPage.sendKeys(contentValueWhileEditing);
        templatesPage.contentFieldOnEditPage.click();
        startingPage.driver.switchTo().defaultContent();
        //technical step to make Save button active
        Thread.sleep(300L);
        startingPage.driver.findElement(By.xpath("(//label[@class='ant-form-item-no-colon'])[1]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.saveButtonOnEditPage));
        act.doubleClick(templatesPage.saveButtonOnEditPage).perform();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.createTemplateButton));

        //go to Edit page again and check the text in Content field
        templatesPage.editIconForEditedTemplate.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.cancelButton));

        //switch to Content Frame
        startingPage.driver.switchTo().frame("textArea_ifr");
        String actualTemplateContent = templatesPage.contentFieldOnEditPage.getText();
        startingPage.driver.switchTo().defaultContent();
        System.out.println(actualTemplateContent);
        String expectedTemplateContent = contentValueWhileCreation+contentValueWhileEditing;
        Assertions.assertEquals(expectedTemplateContent, actualTemplateContent);

        //delete created template
        //go to list of Templates
        homePage.templatesTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.createTemplateButton));

        templatesPage.deleteIconForTemplateForEditing.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.deleteButtonOfConfirmation));
        templatesPage.deleteButtonOfConfirmation.click();
    }

    @Test
    public void deleteTemplate() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //Create new template
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.createTemplateButton));
        templatesPage.createTemplateButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.cancelButton));

        templatesPage.nameField.sendKeys("AutomationTest_For_Deletion");
        templatesPage.templateTypeField.click();
        templatesPage.exportFromDropdown.click();

        templatesPage.formatField.click();
        templatesPage.microsoftExcelFromDropdown.click();
        templatesPage.formatField.submit();
        wait.until(ExpectedConditions.visibilityOf(templatesPage.editTemplateButton));

        //go to list of Templates
        templatesPage.templatesSection.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.createTemplateButton));
        Thread.sleep(1000L);
        //THEN
        //delete template
        templatesPage.deleteIconForAutomationTest_For_DeletionTemplate.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", templatesPage.deleteButtonOfConfirmation);

        List <WebElement> deletedTemplate = startingPage.driver.findElements(By.xpath("//td[text()='AutomationTest_For_Deletion']"));
        Integer sizeOfDeletedTemplateList = deletedTemplate.size();
        System.out.println(sizeOfDeletedTemplateList);
        Assertions.assertTrue(sizeOfDeletedTemplateList==1);
    }


    @Test
    public void searchForTemplates() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        String expectedNameOfSearchedElement = "payment-export";

        //WHEN
        templatesPage.searchField.sendKeys(expectedNameOfSearchedElement);
        templatesPage.searchIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(templatesPage.searchIcon));
        Thread.sleep(500L);
        String actualNameOfSearchedElement = templatesPage.searchedElement.getText();
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