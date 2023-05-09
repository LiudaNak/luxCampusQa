package Globit.Backoffice.tests;

import Globit.Backoffice.pages.HomePage;
import Globit.Backoffice.pages.IdentitiesPage;
import Globit.Backoffice.pages.LoginPage;
import Globit.Backoffice.pages.MailingsPage;
import Globit.pages.*;
import lombok.SneakyThrows;
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

public class Mailings {
    private static WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    MailingsPage mailingsPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        mailingsPage = new MailingsPage();

        startingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);

        //go to Mailings section
        homePage.mailingsTab.click();
    }

    @Test
    public void createPersonMailing() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("mails"));
        //GIVEN
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

        //WHEN
        mailingsPage.mailingNameField.sendKeys("AutomationTestPerson");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        mailingsPage.personItemFromDropdown.click();
        mailingsPage.saveButton.click();
        Thread.sleep(2000L);
        mailingsPage.mailingsSection.click();
        //THEN
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTestPerson']")));
        //delete created email
        mailingsPage.deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.deleteButtonForConfirmation));
        mailingsPage.confirmationDeletionButton.click();
    }

    @Test
    public void createGroupMailing() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

        //WHEN
        mailingsPage.mailingNameField.sendKeys("AutomationTestGroup");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        mailingsPage.groupItemFromDropdown.click();
        mailingsPage.contentField.click();
        mailingsPage.newItemFromContentDropdown.click();
        mailingsPage.saveButton.click();
        Thread.sleep(2000L);

        homePage.mailingsTab.click();
        //THEN
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTestGroup']")));

        //delete created email
        mailingsPage.deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.deleteButtonForConfirmation));
        mailingsPage.confirmationDeletionButton.click();
    }

    @Test
    public void createBulkMailing() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cancel']")));
        //WHEN
        mailingsPage.mailingNameField.sendKeys("AutomationTestBulk");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        mailingsPage.groupItemFromDropdown.click();
        mailingsPage.contentField.click();
        mailingsPage.newItemFromContentDropdown.click();
        mailingsPage.saveButton.click();
        Thread.sleep(2000L);
        homePage.mailingsTab.click();
        //THEN
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTestBulk']")));

        //delete created email
        mailingsPage.deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.deleteButtonForConfirmation));
        mailingsPage.confirmationDeletionButton.click();
    }


    @Test
    public void createWorkingGroupMailing() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));
        //WHEN
        mailingsPage.mailingNameField.sendKeys("AutomationTestWorkingGroup");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        mailingsPage.groupItemFromDropdown.click();
        mailingsPage.contentField.click();
        mailingsPage.newItemFromContentDropdown.click();
        mailingsPage.saveButton.click();
        Thread.sleep(2000L);
        homePage.mailingsTab.click();
        //THEN
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTestWorkingGroup']")));
        //delete created email
        mailingsPage.deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.deleteButtonForConfirmation));
        mailingsPage.confirmationDeletionButton.click();
    }


    @SneakyThrows
    @Test
    public void createMailingWithAttachedFile(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

        mailingsPage.mailingNameField.sendKeys("AutomationTest_with_attached_files");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        mailingsPage.personItemFromDropdown.click();
        mailingsPage.saveButton.click();

        Thread.sleep(500L);
        //go to the third tab
        mailingsPage.attachmentsTab.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Upload']")));

        //uploadButton.click();
        mailingsPage.uploadButton.sendKeys("C:\\Users\\admin\\Desktop\\Globit photo\\Galileo.jpg");
        Thread.sleep(3000);
        //click on the Save button
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@type='submit']")));
        Thread.sleep(2000L);

        //THEN
        Assertions.assertTrue(driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr[2]")).size()==1);

        //delete created mailing
        //go to Mailing section
        WebElement mailingSection = driver.findElement(By.xpath("//a[text()='Mailings']"));
        mailingSection.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTest_with_attached_files']")));

        //deleting of mailing
        mailingsPage.deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.deleteButtonForConfirmation));
        mailingsPage.confirmationDeletionButton.click();
    }

    @Test
    public void editMailing() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        String expectedTextInPreviewWindow = "test";
        //create a new mailing
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

        //WHEN
        mailingsPage.mailingNameField.sendKeys("AutomationTestForEditing");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        mailingsPage.personItemFromDropdown.click();
        mailingsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.contentAndPreviewTab));
        Thread.sleep(3000L);

        //go to Mailing list
        homePage.mailingsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.addMailingButton));

        //go to created mailing for editing
        WebElement createdMailing = startingPage.driver.findElement(By.xpath("//a[text()='AutomationTestForEditing']"));
        createdMailing.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.contentAndPreviewTab));

        //go to the second tab
        mailingsPage.contentAndPreviewTab.click();
        wait.until(ExpectedConditions.visibilityOf(mailingsPage.refreshButton));

        //switch to Content Frame
       startingPage.driver.switchTo().frame("textArea_ifr");
        mailingsPage.editField.clear();
        mailingsPage.editField.sendKeys(expectedTextInPreviewWindow);

        startingPage.driver.switchTo().defaultContent();
        mailingsPage.refreshButton.click();
        Thread.sleep(2000L);

        //click on the save button
        act.moveToElement(mailingsPage.saveButton).perform();
        act.click(mailingsPage.saveButton).perform();

        //Check edited test
        startingPage.driver.switchTo().frame(startingPage.driver.findElement(By.className("mail-view__html-preview")));
        String actualTextInPreviewWindow = mailingsPage.previewWindow.getText();
        System.out.println(expectedTextInPreviewWindow);
        System.out.println(actualTextInPreviewWindow);
        Assertions.assertEquals(expectedTextInPreviewWindow, actualTextInPreviewWindow);

        startingPage.driver.switchTo().defaultContent();

        //delete created mailing
        //go to Mailing list one more time
        homePage.mailingsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.addMailingButton));
        //click on delete button of a mailing
        WebElement deleteButton = startingPage.driver.findElement(By.xpath("//a[text()='AutomationTestForEditing']/../../td[4]/button"));
        deleteButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.confirmationDeletionButton));
        mailingsPage.confirmationDeletionButton.click();

    }

    @Test
    public void editMailingAttachFile() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        //create a new mailing
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

        //WHEN
        mailingsPage.mailingNameField.sendKeys("AutomationTestForAttachingFile");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.personItemFromDropdown));
        mailingsPage.personItemFromDropdown.click();
        mailingsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Content and preview']")));
        Thread.sleep(3000L);

        //go to Mailing list
        homePage.mailingsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Add mailing']")));

        //go to created mailing for editing
        WebElement createdMailing = startingPage.driver.findElement(By.xpath("//a[text()='AutomationTestForAttachingFile']"));
        createdMailing.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.contentAndPreviewTab));

        //go to the third tab
        mailingsPage.attachmentsTab.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Upload']")));

        //upload file
        mailingsPage.inputForFileUploading.sendKeys("C:\\Users\\admin\\Desktop\\Globit photo\\Galileo.jpg");
        //click on the Save button
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", mailingsPage.saveButton);
        Thread.sleep(2000L);

        //THEN
        Assertions.assertTrue(startingPage.driver.findElements(By.xpath("//tbody[@class='ant-table-tbody']/tr[2]")).size()==1);

        //delete created mailing
        //go to Mailing section
        homePage.mailingsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTestForAttachingFile']")));

        //deleting of mailing
        WebElement deleteIconForMail = startingPage.driver.findElement(By.xpath("//a[text()='AutomationTestForAttachingFile']/../../td[4]/button"));
        deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span='Delete']")));
        mailingsPage.confirmationDeletionButton.click();

    }

    @Test
    public void deleteMailing() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        //GIVEN
        //create a new mailing
        mailingsPage.addMailingButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

        mailingsPage.mailingNameField.sendKeys("AutomationTestForDeletion");
        mailingsPage.senderEmailField.sendKeys("test@test.com");
        mailingsPage.subjectField.sendKeys("AT test");
        mailingsPage.mailingTypeField.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.personItemFromDropdown));
        mailingsPage.personItemFromDropdown.click();
        mailingsPage.saveButton.click();
        Thread.sleep(2000L);

        homePage.mailingsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AutomationTestForDeletion']")));

        //WHEN
        //delete created email
        WebElement deleteIconForMail = startingPage.driver.findElement(By.xpath("//a[text()='AutomationTestForDeletion']/../../td[4]/button"));
        deleteIconForMail.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[span='Delete']")));
        mailingsPage.confirmationDeletionButton.click();

        //THEN
        List <WebElement> deletedMailing = startingPage.driver.findElements(By.xpath("//a[text()='AutomationTestForDeletion']"));
        Integer sizeOfDeletedMailing = deletedMailing.size();
        System.out.println(sizeOfDeletedMailing);
        Assertions.assertTrue(sizeOfDeletedMailing==1);

    }


    @Test
    public void searchForMailings() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        //GIVEN
        String expectedNameOfSearchedElement = "add-new-member";

        //WHEN
        mailingsPage.searchField.sendKeys(expectedNameOfSearchedElement);
        mailingsPage.searchIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.searchIcon));
        Thread.sleep(500L);
        String actualNameOfSearchedElement = mailingsPage.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);

        //THEN
        Assertions.assertTrue(actualNameOfSearchedElement.contains(expectedNameOfSearchedElement));
    }

    @Test
    public void checkPersonalEmailSending() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        //check if personal mailing exists
        //if no, create it
        if (startingPage.driver.findElements(By.xpath("//tr/td[2]/span[text()='Person']")).size()==0) {
            mailingsPage.addMailingButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

            mailingsPage.mailingNameField.sendKeys("AT_Person_For_Sending");
            mailingsPage.senderEmailField.sendKeys("test@test.com");
            mailingsPage.subjectField.sendKeys("AT test");
            mailingsPage.mailingTypeField.click();
            mailingsPage.personItemFromDropdown.click();
            mailingsPage.saveButton.click();
            Thread.sleep(2000L);

            //navigate to Mailings section again
            homePage.mailingsTab.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='AT_Person_For_Sending']")));
        }

        //GIVEN
        String email = "PersonalTest@test.com";
            mailingsPage.sendButtonForPersonMailing.click();

            Thread.sleep(1000L);
            startingPage.driver.switchTo().activeElement();
            mailingsPage.addMailButton.click();
            mailingsPage.mailField.sendKeys(email);
            mailingsPage.sendMailButton.click();

            startingPage.driver.navigate().to(StartingPage.linkToMailCatcher);
            Thread.sleep(3000L);
            //find sent email
            String usersEmailText = mailingsPage.usersEmail.getText();
            System.out.println(usersEmailText);
            //THEN
            Assertions.assertTrue(usersEmailText.contains(email));
    }

    @Test
    public void checkGroupEmailSending() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        //check if Group mailing exists
        //if no, create it
        if (startingPage.driver.findElements(By.xpath("//tr/td[2]/span[text()='Group']")).size()==0){
            mailingsPage.addMailingButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

            mailingsPage.mailingNameField.sendKeys("ATGroup_For_Sending");
            mailingsPage.senderEmailField.sendKeys("test@test.com");
            mailingsPage.subjectField.sendKeys("AT test");
            mailingsPage.mailingTypeField.click();
            wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.groupItemFromDropdown));
            mailingsPage.groupItemFromDropdown.click();
            mailingsPage.contentField.click();
            mailingsPage.newItemFromContentDropdown.click();
            Thread.sleep(1000L);
            act.moveToElement(mailingsPage.saveButton).click(mailingsPage.saveButton).perform();
            Thread.sleep(2000L);
            //navigate to Mailings section again
            homePage.mailingsTab.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='ATGroup_For_Sending']")));
        }

        //GIVEN
        String email = "GroupTest@test.com";
        mailingsPage.sendButtonForGroupMailing.click();
        Thread.sleep(1000L);
        startingPage.driver.switchTo().activeElement();
        mailingsPage.addMailButton.click();
        mailingsPage.mailField.sendKeys(email);
        mailingsPage.sendMailButton.click();

        startingPage.driver.navigate().to(StartingPage.linkToMailCatcher);
        Thread.sleep(3000L);
        //find sent email
        String usersEmailText = mailingsPage.usersEmail.getText();
        System.out.println(usersEmailText);
        //THEN
        Assertions.assertTrue(usersEmailText.contains(email));
    }

    @Test
    public void checkBulkMailSending() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        //check if Bulk mailing exists
        //if no, create it
        if (startingPage.driver.findElements(By.xpath("//tr/td[2]/span[text()='Bulk Mail']")).size()==0){
            mailingsPage.addMailingButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Cancel']")));

            mailingsPage.mailingNameField.sendKeys("ATBulkMailing_ForSending");
            mailingsPage.senderEmailField.sendKeys("test@test.com");
            mailingsPage.subjectField.sendKeys("AT test");
            mailingsPage.mailingTypeField.click();
            mailingsPage.groupItemFromDropdown.click();
            mailingsPage.contentField.click();
            WebElement newItemFromContentDropdown = driver.findElement(By.xpath("//span[text()='New']"));
            newItemFromContentDropdown.click();
            mailingsPage.saveButton.click();
            Thread.sleep(2000L);

            homePage.mailingsTab.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='ATBulkMailing_ForSending']")));
        }
        //GIVEN
        String email = "BulkMailTest@test.com";
        mailingsPage.sendButtonForBulkMailing.click();

        Thread.sleep(1000L);
        startingPage.driver.switchTo().activeElement();
        mailingsPage.addMailButton.click();
        mailingsPage.mailField.sendKeys(email);
        mailingsPage.sendMailButton.click();

        startingPage.driver.navigate().to(StartingPage.linkToMailCatcher);
        Thread.sleep(3000L);
        //find sent email
        String usersEmailText = mailingsPage.usersEmail.getText();
        System.out.println(usersEmailText);
        //THEN
        Assertions.assertTrue(usersEmailText.contains(email));
    }

    @Test
    public void checkWorkingGroupMailSending() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));

        //check if Working Group mailing exists
        //if no, create it
        if (startingPage.driver.findElements(By.xpath("//tr/td[2]/span[text()='Working Group']")).size()==0){
            mailingsPage.addMailingButton.click();
            wait.until(ExpectedConditions.elementToBeClickable(mailingsPage.cancelButton));

            mailingsPage.mailingNameField.sendKeys("ATWorkingGroup_ForSending");
            mailingsPage.senderEmailField.sendKeys("test@test.com");
            mailingsPage.subjectField.sendKeys("AT test");
            mailingsPage.mailingTypeField.click();
            mailingsPage.groupItemFromDropdown.click();
            mailingsPage.contentField.click();
            mailingsPage.newItemFromContentDropdown.click();
            mailingsPage.saveButton.click();
            Thread.sleep(2000L);

            homePage.mailingsTab.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='ATWorkingGroup_ForSending']")));
        }
        //GIVEN
        String email = "WorkingGroupTest@test.com";
        mailingsPage.sendButtonForWorkingGroupMailing.click();

        Thread.sleep(1000L);
        startingPage.driver.switchTo().activeElement();
        mailingsPage.addMailButton.click();
        mailingsPage.mailField.sendKeys(email);
        mailingsPage.sendMailButton.click();

        startingPage.driver.navigate().to(StartingPage.linkToMailCatcher);
        Thread.sleep(3000L);
        //find sent email
        String usersEmailText = mailingsPage.usersEmail.getText();
        System.out.println(usersEmailText);
        //THEN
        Assertions.assertTrue(usersEmailText.contains(email));
    }

    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
