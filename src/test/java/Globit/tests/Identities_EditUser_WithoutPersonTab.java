package Globit.tests;

import Globit.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Identities_EditUser_WithoutPersonTab {
    private static WebDriver driver;

    //Create objects of pages
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    Identities_PersonPage identities_PersonPage;
    Identities_PersonPage_PersonTab_Page i_p_personTab_page;
    Identities_PersonPage_PersonTab_Group i_p_p_groupTab_page;
    Identities_PersonPage_PersonTab_WorkingGroups i_p_p_workingGroupsTab_page;
    Identities_PersonPage_PersonTab_Membership i_p_p_membership_page;
    Identities_PersonPage_PersonTab_Payments i_p_p_payments_page;
    Identities_PersonPage_PersonTab_Mails i_p_p_mails_page;
    Identities_PersonPage_PersonTab_Accounts i_p_p_accounts_page;
    Identities_PersonPage_PersonTab_Documents i_p_p_documents_page;
    Identities_PersonPage_PersonTab_System i_p_p_system_page;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        identities_PersonPage = new Identities_PersonPage();
        i_p_personTab_page = new Identities_PersonPage_PersonTab_Page();
        i_p_p_groupTab_page = new Identities_PersonPage_PersonTab_Group();
        i_p_p_workingGroupsTab_page = new Identities_PersonPage_PersonTab_WorkingGroups();
        i_p_p_membership_page = new Identities_PersonPage_PersonTab_Membership();
        i_p_p_payments_page = new Identities_PersonPage_PersonTab_Payments();
        i_p_p_mails_page = new Identities_PersonPage_PersonTab_Mails();
        i_p_p_accounts_page = new Identities_PersonPage_PersonTab_Accounts();
        i_p_p_documents_page = new Identities_PersonPage_PersonTab_Documents();
        i_p_p_system_page = new Identities_PersonPage_PersonTab_System();

        StartingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
    }

   @Test
    public void addGroupToPerson() throws InterruptedException {
        //GIVEN
        Actions action = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));
        Thread.sleep(1000L);
        //navigate to Groups tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.groupTab));
        i_p_personTab_page.groupTab.click();
        wait.until(ExpectedConditions.urlContains("group"));

        //WHEN
        i_p_p_groupTab_page.groupField.click();
        //find the first group in the list
        String expectedGroupName = i_p_p_groupTab_page.firstGroupInTheList.getAttribute("title").toString();
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", i_p_p_groupTab_page.firstGroupInTheList);

        //click save button
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_groupTab_page.saveButton);
        Thread.sleep(3000L);
        //find added group in the Group field
        //THEN
        String addedGroupName = i_p_p_groupTab_page.addedGroup.getText().toString();
        //check that write group is saved (by searching group name)
        System.out.println("Expected is " + expectedGroupName);
        System.out.println("Actual is " + addedGroupName);
        Assertions.assertEquals(expectedGroupName, addedGroupName);

        //delete the group
        //click on the close icon for a group
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_groupTab_page.closeIconForGroup));
        i_p_p_groupTab_page.closeIconForGroup.click();
        //click on the Save button
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_groupTab_page.saveButton);
    }

    @Test
    public void deleteGroupFromPerson() throws InterruptedException {
        //GIVEN
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);

        //navigate to Groups tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.groupTab));
        i_p_personTab_page.groupTab.click();
        wait.until(ExpectedConditions.urlContains("group"));
        startingPage.driver.navigate().refresh();

        //GIVEN
        //check that two classes are present in the Group field. If one group is added, there were two such classes
       System.out.println(i_p_p_groupTab_page.classesOfAddedGroups.size() + " - list size");
       if (i_p_p_groupTab_page.classesOfAddedGroups.size()==1) {
           wait.until(ExpectedConditions.elementToBeClickable(i_p_p_groupTab_page.groupField));
           action.moveToElement(i_p_p_groupTab_page.groupField).click(i_p_p_groupTab_page.groupField).perform();
           //((JavascriptExecutor) driver).executeScript("arguments[0].click()", groupField);
           i_p_p_groupTab_page.firstGroupInTheList.click();
           //click save button
           wait.until(ExpectedConditions.elementToBeClickable(i_p_p_groupTab_page.saveButton));
           ((JavascriptExecutor) driver).executeScript("arguments[0].click()", i_p_p_groupTab_page.saveButton);
           wait.until(ExpectedConditions.elementToBeClickable(i_p_p_groupTab_page.groupField));
       }

        //WHEN
        //delete the group
        //click on the close icon for a group
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_groupTab_page.closeIconForGroup));
        Thread.sleep(3000L);
        i_p_p_groupTab_page.closeIconForGroup.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_groupTab_page.saveButton));
        //click on the Save button
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_groupTab_page.saveButton);
    //THEN
        wait.until(ExpectedConditions.invisibilityOf(i_p_p_groupTab_page.saveButton));
    }

    @Test
    public void addWorkingGroupToPerson() throws InterruptedException {
        Actions actions = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Thread.sleep(1000L);
        String expectedSelectedValue = "true";

        //navigate to the Working Groups tab
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Working Groups']")));
        i_p_p_workingGroupsTab_page.workingGroupTab.click();
        wait.until(ExpectedConditions.urlContains("workingGroups"));
        startingPage.driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_workingGroupsTab_page.addWorkingGroupButton));
        //GIVEN
        //click on the Add button
        actions.moveToElement(i_p_p_workingGroupsTab_page.addWorkingGroupButton).click(i_p_p_workingGroupsTab_page.addWorkingGroupButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_workingGroupsTab_page.workingGroupField));
        //WHEN
        //fill in fields
        i_p_p_workingGroupsTab_page.workingGroupField.click();
        i_p_p_workingGroupsTab_page.firstElementFromWorkingGroupDropdown.click();
        i_p_p_workingGroupsTab_page.positionField.click();
        i_p_p_workingGroupsTab_page.memberItemFromPositionDropdown.click();
        i_p_p_workingGroupsTab_page.statusField.click();
        i_p_p_workingGroupsTab_page.approvedItemFromStatusDropdown.click();
        //click save button
        i_p_p_workingGroupsTab_page.saveButton.click();
        Thread.sleep(3000L);
        //THEN
        //check chat "disabled attribute is present in the saveButton element"
        String actualSelectedValue = i_p_p_workingGroupsTab_page.firstElementFromWorkingGroupDropdown.getAttribute("aria-selected").toString();
        System.out.println(actualSelectedValue);
        Assertions.assertEquals(expectedSelectedValue, actualSelectedValue);

        //After Test - deleting the added working group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_workingGroupsTab_page.deleteIcon);
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_workingGroupsTab_page.confirmDeleting));
        actions.click(i_p_p_workingGroupsTab_page.confirmDeleting).perform();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_workingGroupsTab_page.saveButton));
        actions.click(i_p_p_workingGroupsTab_page.saveButton).perform();
    }

    @Test
    public void changeMembership() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);

        //navigate to Membership tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.membershipTab));
        i_p_personTab_page.membershipTab.click();
        wait.until(ExpectedConditions.urlContains("membership"));

        Thread.sleep(500L);
        //GIVEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_membership_page.changeMembershipButton);
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);

        //select item from the Membership dropdown
        i_p_p_membership_page.membershipField.click();
        i_p_p_membership_page.firstElementFromTheDropdown.click();
        i_p_p_membership_page.okButton.click();
    }

    @Test
    public void membershipChangeApplicationDay() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(9000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);

        //navigate to Membership tab
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Membership']/..")));
        i_p_personTab_page.membershipTab.click();
        wait.until(ExpectedConditions.urlContains("membership"));

        //GIVEN
        //change application day for today
        i_p_p_membership_page.applicationDateField.click();
        startingPage.driver.switchTo().activeElement();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_membership_page.todayButton));
        i_p_p_membership_page.todayButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_membership_page.saveButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_membership_page.saveButton);

        String actualApplicationDate = i_p_p_membership_page.applicationDateField.getAttribute("title").toString();
        System.out.println("Actual date is " + actualApplicationDate);

        //check current date
        DateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        String currentDate = dateformat.format(date);
        System.out.println("Current date is " + currentDate);
        //THEN
        Assertions.assertEquals(currentDate, actualApplicationDate);

        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_membership_page.applicationDateField));
        Thread.sleep(2000L);
        //change date to other one in order to be able to pass the test at this day once again
        action.moveToElement(i_p_p_membership_page.closeIcon).perform();
        i_p_p_membership_page.applicationDateField.sendKeys(Keys.CONTROL + "a");
        i_p_p_membership_page.applicationDateField.sendKeys(Keys.DELETE);
        i_p_p_membership_page.applicationDateField.sendKeys("2022-11-01");
        startingPage.driver.switchTo().activeElement();
        i_p_p_membership_page.date2022_11_01.click();
        i_p_p_membership_page.saveButton.click();
    }


    @Test
    //not finished
    public void checkOrders() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);

        //navigate to Orders tab
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Orders'])[2]")));
        i_p_personTab_page.ordersTab.click();
        wait.until(ExpectedConditions.urlContains("orders"));
    }

    @Test
    public void addNewPayment() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);
        //navigate to Payments tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.paymentsTab));
        i_p_personTab_page.paymentsTab.click();
        wait.until(ExpectedConditions.urlContains("payments"));

        //open the Add payment window
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_payments_page.addPaymentButton);
        Thread.sleep(1000L);
        startingPage.driver.switchTo().activeElement();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_payments_page.saveButton));

        //GIVEN
        //fill in fields
        i_p_p_payments_page.dateField.click();
        i_p_p_payments_page.nowLink.click();
        i_p_p_payments_page.amountField.click();
        i_p_p_payments_page.amountField.sendKeys("123");
        i_p_p_payments_page.paymentMethodField.click();
        i_p_p_payments_page.otherPaymentItemFromDropdown.click();
        i_p_p_payments_page.saveButton.click();
        //After test - delete the created payment
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_payments_page.trashIconForThePayment));
        i_p_p_payments_page.trashIconForThePayment.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_payments_page.deleteButtonOnConfirmation));
        i_p_p_payments_page.deleteButtonOnConfirmation.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_payments_page.addPaymentButton));
    }

    @Test
    //is not finished because of bug on the dev
    public void sendEmailForPerson() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);
        //navigate to the MAils tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_mails_page.previewButtonForFirstEmail));
        i_p_personTab_page.mailsTab.click();
        wait.until(ExpectedConditions.urlContains("mails"));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_mails_page.sendButtonForFirstEmail);
    }

    @Test
    public void sendEmailFromPreviewForPerson() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);
        //navigate to the MAils tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.mailsTab));
        i_p_personTab_page.mailsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_mails_page.previewButtonForFirstEmail));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_mails_page.previewButtonForFirstEmail);
        startingPage.driver.switchTo().activeElement();
        wait.until(ExpectedConditions.visibilityOf(i_p_p_mails_page.previewColumn));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_mails_page.sendMailButton);
    }

    @Test
    //is not finished because of bug on the dev
    public void checkPreviewOfMailing() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);
        //navigate to the MAils tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.mailsTab));
        i_p_personTab_page.mailsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_mails_page.previewButtonForFirstEmail));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_mails_page.previewButtonForFirstEmail);
        startingPage.driver.switchTo().activeElement();
        wait.until(ExpectedConditions.visibilityOf(i_p_p_mails_page.previewColumn));
    }


    @Test
    public void addNewAccount() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(1000L);

        //GIVEN
        //navigate to the Accounts tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.accountsTab));
        i_p_personTab_page.accountsTab.click();
        wait.until(ExpectedConditions.urlContains("accounts"));

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_accounts_page.addAccountButton);
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_accounts_page.accountsListButton));
        //Find the first account from the list
        String firstAccountEmail = i_p_p_accounts_page.firstAccountEmailElement.getText().toString();
        System.out.println(firstAccountEmail);

        //click on the plus icon
        i_p_p_accounts_page.plusIconForTheFirstAccount.click();

        //confirm
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_accounts_page.addButton);

        //THEN
        //check that account is added
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='"+firstAccountEmail+"']")));

        //delete account
        WebElement trashIconForAddedAccount = startingPage.driver.findElement(By.xpath("//a[text()='"+firstAccountEmail+"']/../../*[4]/*"));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", trashIconForAddedAccount);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_accounts_page.deleteButtonForConfirming);

        }

        @Test
        public void deleteAccount() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
            Actions action = new Actions(startingPage.driver);

            //GIVEN
            //navigate to the Accounts tab
            wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.accountsTab));
            i_p_personTab_page.accountsTab.click();
            wait.until(ExpectedConditions.urlContains("accounts"));

            //WHEN
            //add new account
            ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_accounts_page.addAccountButton);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[@class='bi bi-plus'])[1]")));
            //Find the first account from the list
            String firstAccountEmail = i_p_p_accounts_page.firstAccountEmailElement.getText().toString();
            System.out.println(firstAccountEmail);
            //click on the plus icon
            i_p_p_accounts_page.plusIconForTheFirstAccount.click();
            //confirm
            ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_accounts_page.addButton);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='"+firstAccountEmail+"']")));

            //delete added account
            WebElement addedAccount = driver.findElement(By.xpath("//a[text()='"+firstAccountEmail+"']"));
            WebElement trashIconForAddedAccount = driver.findElement(By.xpath("//a[text()='"+firstAccountEmail+"']/../../*[4]/*"));
            ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", trashIconForAddedAccount);
            ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_accounts_page.deleteButtonForConfirming);
            wait.until(ExpectedConditions.elementToBeClickable(i_p_p_accounts_page.addAccountButton));
            Thread.sleep(2000L);

            //THEN
            //check that there is no added account on the page. Array is used for checking that it's size is 0
            List arrayOfAddedAccounts = driver.findElements(By.xpath("//a[text()='"+firstAccountEmail+"']"));
            Integer sizeOfArray = arrayOfAddedAccounts.size();
            //check that deleted account is not present on the page
            Assertions.assertTrue(sizeOfArray==0);
        }


    @Test
    public void uploadFileToDocumentsTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);

        //GIVEN
        //navigate to the Documents tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.documentsTab));
        i_p_personTab_page.documentsTab.click();
        wait.until(ExpectedConditions.urlContains("documents"));

        //WHEN
        //upload file
        i_p_p_documents_page.inputForFileUploading.sendKeys(i_p_p_documents_page.pathToTheFile);
        //click on the Save button
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_documents_page.saveButton);
        Thread.sleep(2000L);

        //THEN
        String actualFileName = i_p_p_documents_page.addedFile.getText();
        System.out.println(actualFileName);

        Assertions.assertEquals(i_p_p_documents_page.expectedFileName, actualFileName);

        //after test
        //delete the uploaded file
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody[@class='ant-table-tbody']/tr[last()]/td[last()]/button")));
        i_p_p_documents_page.deletedIconForTheFile.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Are you sure you want to delete this document?']")));
        i_p_p_documents_page.deleteButtonForConfirmation.click();
        //click on the Save button
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_documents_page.saveButton);
        Thread.sleep(2000L);
    }

    @Test
    public void addTagForFileToDocumentsTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions action = new Actions(startingPage.driver);

        //GIVEN
        //navigate to the Documents tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.documentsTab));
        i_p_personTab_page.documentsTab.click();
        wait.until(ExpectedConditions.urlContains("documents"));

        //WHEN
        //upload file
        i_p_p_documents_page.inputForFileUploading.sendKeys(i_p_p_documents_page.pathToTheFile);
        //click on the Save button
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_documents_page.saveButton);
        Thread.sleep(2000L);

        WebElement newTagButton = startingPage.driver.findElement(By.xpath("//span[text()='+ New Tag']"));
        newTagButton.click();
        i_p_p_documents_page.inputForNewTagButton.sendKeys(i_p_p_documents_page.expectedTagName);
        i_p_p_documents_page.inputForNewTagButton.sendKeys(Keys.ENTER);

        //THEN
        String actualTagName = i_p_p_documents_page.addedTag.getText();
        Assertions.assertEquals(i_p_p_documents_page.expectedTagName, actualTagName);

        //after test
        //delete the uploaded file
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_documents_page.deletedIconForTheFile));
        i_p_p_documents_page.deletedIconForTheFile.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_documents_page.deleteButtonForConfirmation));
        i_p_p_documents_page.deleteButtonForConfirmation.click();
        //click on the Save button
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", i_p_p_documents_page.saveButton);
        Thread.sleep(2000L);
    }

        @Test
    //not finished
    public void checkSystemTab() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        //GIVEN
        //navigate to the System tab
        wait.until(ExpectedConditions.elementToBeClickable(i_p_personTab_page.systemTab));
        i_p_personTab_page.systemTab.click();
        wait.until(ExpectedConditions.urlContains("system"));

        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_system_page.exportPersonButton));
        i_p_p_system_page.exportPersonButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(i_p_p_system_page.exportVCard));
        i_p_p_system_page.exportVCard.click();
    }

    //@AfterEach
    public void cleanUp() {
        driver.close();
        driver.quit();
    }
}
