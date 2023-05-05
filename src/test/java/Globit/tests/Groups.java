package Globit.tests;

import Globit.pages.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Groups {
    LoginPage loginPage;
    static HomePage homePage;
    static StartingPage startingPage;
    static GroupsPage groupsPage;
    MailCatcherPage mailCatcherPage;
    Identities_PersonPage identities_personPage;
    IdentitiesPage identitiesPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        groupsPage = new GroupsPage();
        mailCatcherPage = new MailCatcherPage();
        identities_personPage = new Identities_PersonPage();
        identitiesPage = new IdentitiesPage();

        StartingPage.login();
        StartingPage.navigateToScope();
    }

    @Test
    public void createNewGroup(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //navigate to Groups
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));
        //GIVEN
        //navigate to Create group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.addGroupButton);

        //WHEN
        //fill in fields
       groupsPage.nameOfTheGroupField.sendKeys("AutomationTestForCreatingNewGroup");
       groupsPage.descriptionField.sendKeys("Automation test - description");
       groupsPage.collectiveMemberCheckBox.click();
       groupsPage.saveButton.click();

       //THEN
       wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));

       //delete the created group
        //navigate to Groups
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.addGroupButton));
        groupsPage.trashIconForAutomationTestForCreatingNewGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);
    }

    @Test
    public void deleteGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(6000L));
        //navigate to Groups
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));

        //GIVEN
        //create a new group
        //navigate to Create group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.addGroupButton);

        //fill in fields
        groupsPage.nameOfTheGroupField.sendKeys("AutomationTestForDeletingGroup");
        groupsPage.descriptionField.sendKeys("Automation test for deletion - description");
        groupsPage.collectiveMemberCheckBox.click();
        groupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));
        //navigate to Groups
        homePage.groupsTab.click();

        //WHEN
        //click on delete button
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.trashIconForAutomationTestForDeletingGroup));
        groupsPage.trashIconForAutomationTestForDeletingGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);
        Thread.sleep(2000);

        //check that there is no deleted group in the group list. Array is used for checking that it's size is 0
        Integer arraySize = groupsPage.arrayOfDeletedGroups.size();
        System.out.println(arraySize);
        System.out.println(groupsPage.arrayOfDeletedGroups);
        Assertions.assertTrue(arraySize==0);
    }

    @Test
    public void editGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(6000L));
        String expectedGroutTitle = "Automation Test - Edited title";
        String expectedGroupDescription = "Edited Description";
        String expectedClassNameOfCollectiveMembership = "bi bi-x-circle";
        //navigate to Groups
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));

        //create a new group
        //navigate to Create group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.addGroupButton);
        //fill in fields
        groupsPage.nameOfTheGroupField.sendKeys("AutomationTestForEditGroup");
        groupsPage.descriptionField.sendKeys("Automation test - description");
        groupsPage.collectiveMemberCheckBox.click();
        groupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));

        //navigate to Groups
        //homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editButton));
        //go to edit page
        groupsPage.editButton.click();
        Thread.sleep(300L);
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.canselButton));

        //WHEN
        groupsPage.nameOfTheGroupFieldOfEditPage.clear();
        groupsPage.nameOfTheGroupFieldOfEditPage.sendKeys(expectedGroutTitle);
        groupsPage.descriptionFieldOfEditPage.clear();
        groupsPage.descriptionFieldOfEditPage.sendKeys(expectedGroupDescription);
        groupsPage.collectiveMemberCheckBoxOfEditPage.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.saveButtonOfEditPage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Title']/..//following-sibling::div")));

        //THEN
        String actualEditedTitle = groupsPage.editedTitle.getText().toString();
        String actualEditedDescription = groupsPage.editedDescription.getText().toString();
        String actualClassNameOfCollectiveMembership = groupsPage.collectiveMembershipStatus.getAttribute("class").toString();

        Assertions.assertEquals(expectedGroutTitle, actualEditedTitle);
        Assertions.assertEquals(expectedGroupDescription, actualEditedDescription);
        Assertions.assertEquals(expectedClassNameOfCollectiveMembership, actualClassNameOfCollectiveMembership);

        //delete the edited group
        //navigate to Groups
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.trashIconForEditedGroup));
        groupsPage.trashIconForEditedGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);

    }

    @Test
    public void addPersonToNonCollectivePersonGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions act = new Actions(startingPage.driver);

        //take data from the first user on the list
        String idOfFirstUser = identitiesPage.idOfFirstUser.getText();
        String firstNameOfFirstUser = identitiesPage.firstNameOfFirstUser.getText().toString();
        String lastNameOfFirstUser = identitiesPage.lastNameOfFirstUser.getText().toString();
        String emailOfFirstUser = identitiesPage.emailOfFirstUser.getText().toString();
        
        //navigate to Groups
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));
        //GIVEN
        //create a new group
        //navigate to Create group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.addGroupButton);
        //fill in fields
        groupsPage.nameOfTheGroupField.sendKeys("AutomationTestForNonCollective");
        groupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));
        //go to Edit Page
        groupsPage.editGroupButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));

        //go to Persons tab
        groupsPage.personsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.addNewPersonButton));

        groupsPage.addNewPersonButton.click();

        Thread.sleep(1000L);
        act.doubleClick(groupsPage.idField).sendKeys(groupsPage.idField, idOfFirstUser).perform();
        act.doubleClick(groupsPage.firstNameField).sendKeys(groupsPage.firstNameField, firstNameOfFirstUser).perform();
        act.doubleClick(groupsPage.lastNameField).sendKeys(groupsPage.lastNameField, lastNameOfFirstUser).perform();
        act.doubleClick(groupsPage.emailField).sendKeys(groupsPage.emailField,emailOfFirstUser).perform();
        act.click(groupsPage.sendButton).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='bi bi-check-circle']")));

        //delete the group
        //navigate to Groups
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.trashIconForAutomationTestForNonCollectiveGroup));
        groupsPage.trashIconForAutomationTestForNonCollectiveGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);
    }


    @Test
    public void addPersonToCollectivePersonGroup() {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        Actions act = new Actions(startingPage.driver);

        //take data from the first user on the list
        String idOfFirstUser = identitiesPage.idOfFirstUser.getText();
        String firstNameOfFirstUser = identitiesPage.firstNameOfFirstUser.getText().toString();
        String lastNameOfFirstUser = identitiesPage.lastNameOfFirstUser.getText().toString();
        String emailOfFirstUser = identitiesPage.emailOfFirstUser.getText().toString();

        //navigate to Groups
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));
        //GIVEN
        //create a new group
        //navigate to Create group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.addGroupButton);
        //fill in fields
        groupsPage.nameOfTheGroupField.sendKeys("AutomationTestForCollectiveGroup");
        groupsPage.descriptionField.sendKeys("Automation test - description");
        groupsPage.collectiveMemberCheckBox.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.saveButton);
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));
        //go to Edit Page
        groupsPage.editGroupButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.personsTab));

        //go to Persons tab
        groupsPage.personsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.addNewPersonButton));
        groupsPage.addNewPersonButton.click();

        act.doubleClick(groupsPage.idField).sendKeys(groupsPage.idField, idOfFirstUser).perform();
        act.doubleClick(groupsPage.firstNameField).sendKeys(groupsPage.firstNameField, firstNameOfFirstUser).perform();
        act.doubleClick(groupsPage.lastNameField).sendKeys(groupsPage.lastNameField, lastNameOfFirstUser).perform();
        act.doubleClick(groupsPage.emailField).sendKeys(groupsPage.emailField,emailOfFirstUser).perform();

        //membership field
        act.doubleClick(groupsPage.membershipField).perform();
        groupsPage.activeField.click();
        groupsPage.oneYearMembershipPoint.click();

        //virchow field
        act.doubleClick(groupsPage.virchowsField).perform();
        groupsPage.activeField.click();
        groupsPage.virchowsArchivPoint.click();

        //hematopathology field
        act.doubleClick(groupsPage.hematopathologyField).perform();
        groupsPage.activeField.click();

        //start day field
        act.doubleClick(groupsPage.startDateField).click(groupsPage.startDateField).sendKeys(groupsPage.startDateField, "10.10.2022").perform();
        groupsPage.pointInCalendar.click();

        act.doubleClick(groupsPage.idField).perform();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.sendButton));
        act.click(groupsPage.sendButton).perform();

        //THEN
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='bi bi-check-circle']")));

        //delete the group
        //navigate to Groups
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.trashIconForAddPersonToCollectivePersonGroup));
        groupsPage.trashIconForAddPersonToCollectivePersonGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);
    }

    @Test
    public void searchForGroup(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions act = new Actions(startingPage.driver);
        //navigate to Groups section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));

        //GIVEN
        String expectedNameOfSearchedElement = "test123";
        //create new group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.addGroupButton);
        groupsPage.nameOfTheGroupField.sendKeys(expectedNameOfSearchedElement);
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.saveButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.saveButton);
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));
        //go to the Group section
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.addGroupButton));

        //WHEN
        groupsPage.searchField.sendKeys(expectedNameOfSearchedElement);
        groupsPage.searchIcon.click();
        String actualNameOfSearchedElement = groupsPage.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);
        //THEN
        Assertions.assertEquals(expectedNameOfSearchedElement, actualNameOfSearchedElement);

        //after test
        //delete created group
        WebElement trashIconForGroup = startingPage.driver.findElement(By.xpath("//a[text()='"+expectedNameOfSearchedElement+"']/../../td[7]/button"));
        trashIconForGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);
    }

    @Test
    public void sendMailForGroupMembers() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions act = new Actions(startingPage.driver);
        //navigate to Groups section
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.nameOfTheFirstGroup));

        //GIVEN
        Thread.sleep(500L);
        act.click(groupsPage.nameOfTheFirstGroup).perform();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));
        String membersEmailText = groupsPage.emailOfTheLastUser.getText();
        System.out.println(membersEmailText + " - emailOfTheFirstUser");

        //go to Group section again
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.sendingMailButton));
        groupsPage.sendingMailButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));

        groupsPage.sendingMailButtonInsideGroup.click();

        startingPage.driver.navigate().to(StartingPage.linkToMailCatcher);
        Thread.sleep(5000L);
        //find user's email
        String usersEmailText = mailCatcherPage.emailOfLastSentItem.getText();
        System.out.println(usersEmailText + " - emailOfLastSentItem");

        Assertions.assertTrue(usersEmailText.contains(membersEmailText));
    }

    @Test
    public void checkPreviewForGroupMail() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions act = new Actions(startingPage.driver);
        //navigate to Groups section
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.sendingMailButton));
        //GIVEN
        groupsPage.sendingMailButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.editGroupButton));
        groupsPage.previewButton.click();
        startingPage.driver.switchTo().activeElement();
        //THEN
        wait.until(ExpectedConditions.visibilityOf(groupsPage.previewLetterForNewMemberTitle));
    }

    //@AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }


    @AfterAll
    //if any test was failed, created group needs to be deleted
    public static void deleteNotDeletedGroups() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //navigate to Groups
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupsPage.addGroupButton));

        while (startingPage.driver.findElements(By.xpath("//a[contains(text(),'AutomationTest')]/../../td[7]/button")).size()!=0)
        {groupsPage.notDeletedGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", groupsPage.okButtonOnConfirmationDeletion);
        Thread.sleep(1000L);}

    }
}
