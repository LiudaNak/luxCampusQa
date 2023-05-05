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

public class WorkingGroups {
    private static WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    WorkingGroupsPage workingGroupsPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        workingGroupsPage = new WorkingGroupsPage();

        StartingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
        Thread.sleep(2000L);
        //navigate to Working Groups
        homePage.workingGroupsTab.click();
        }

    @Test
    public void createWorkingGroup(){
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        wait.until(ExpectedConditions.urlContains("working-groups"));
        //GIVEN
        //navigate to Create Working group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);
        //WHEN
        //fill in fields
        workingGroupsPage.nameOfTheGroupField.sendKeys("AutomationTest");
        workingGroupsPage.saveButton.click();
        //THEN
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));
        //delete the created group
        //navigate to Working Groups
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.addWorkingGroupButton));
        goToNextPageOfPagination(workingGroupsPage.addedWorkingGroupList);

        workingGroupsPage.trashIconForGroupAutomationTest.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.okButtonOnConfirmationDeletion);
    }

    //not finished
    public void goToNextPageOfPagination_1(List<WebElement> addedWorkingGroupList) {
        Actions act = new Actions(startingPage.driver);
        if (addedWorkingGroupList.size()==0) {
            while (driver.findElement(By.className("ant-pagination-next")).isEnabled()) {
                //знайдемо елементи з addedWorkingGroupList
                for (int i = 0; i < addedWorkingGroupList.size(); i++) {
                    //знайти елемент на основі елементу ліста
                    //driver.findElement(addedWorkingGroupList.get(i));
                }
                act.moveToElement(startingPage.driver.findElement(By.className("ant-pagination-next"))).click(driver.findElement(By.className("ant-pagination-next"))).perform();
            }
        }
    }
    public void goToNextPageOfPagination(List<WebElement> addedWorkingGroupList) {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        while (startingPage.driver.findElement(By.className("ant-pagination-next")).isEnabled()) {
        if(addedWorkingGroupList.size() > 0) {
            break;
        }else if (addedWorkingGroupList.size() == 0){
                 act.moveToElement(startingPage.driver.findElement(By.className("ant-pagination-next"))).click(startingPage.driver.findElement(By.className("ant-pagination-next"))).perform();
            }
        }
    }


    @Test
    public void editWorkingGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(2000L);
        //GIVEN
        String expectedWorkingGroupName = "Automation Test";
        String nameOfTheWorkingGroup = "testForEdit";
        //create new Working group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);

        workingGroupsPage.nameOfTheGroupField.sendKeys(nameOfTheWorkingGroup);
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();

        //go to group link
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.workingGroupForEdit);
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //go to Edit group page
        workingGroupsPage.editWorkingGroup.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.canselButton));

        //WHEN
        //change group name
        workingGroupsPage.nameOfTheGroupField.sendKeys(expectedWorkingGroupName);
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.saveButton));
        //click on the Save button
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Title']/../../div[2]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Edit working group']")));

        //THEN
        //check the group name
        String actualWorkingGroupName = workingGroupsPage.nameOfTheWorkingGroup.getText();
        Assertions.assertTrue(actualWorkingGroupName.endsWith(expectedWorkingGroupName));

        //delete the created group
        //navigate to Groups
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();
        WebElement trashIconForEditedGroup = startingPage.driver.findElement(By.xpath("//a[text()='"+nameOfTheWorkingGroup+expectedWorkingGroupName+"']/../../td[7]/button"));

        trashIconForEditedGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.okButtonOnConfirmationDeletion);

    }

    @Test
    public void deleteWorkingGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(3000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(2000L);

        //GIVEN
        String expectedWorkingGroupName = "Automation Test";

        //create new Working group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);

        workingGroupsPage.nameOfTheGroupField.sendKeys("testForDeleting");
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='testForDeleting']/../../td[7]/button")));

        //WHEN
        workingGroupsPage.trashIconForGroupForDeleting.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.okButtonOnConfirmationDeletion);

        //THEN
        //check
        Integer sizeOfDeletedGroup = workingGroupsPage.deletedWorkingGroup.size();
        System.out.println(sizeOfDeletedGroup);
        Assertions.assertTrue(sizeOfDeletedGroup==1);
    }

   @Test
    public void addChairForGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(2000L);
        //GIVEN
        //create new Working group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);

        workingGroupsPage.nameOfTheGroupField.sendKeys("Automation Test for adding Chair");
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));


        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();

        //go to group link
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.workingGroupForAddingChair);

        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //WHEN
        //go to Add Chair / Co-Chair button
        workingGroupsPage.addChairCoChairButton.click();

        startingPage.driver.switchTo().activeElement();
        workingGroupsPage.addChairCoChairField.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.firstElementFromDropdown));
        workingGroupsPage.firstElementFromDropdown.click();

        String valueOfFirstElement = workingGroupsPage.firstElementFromDropdown.getText();
        System.out.println(valueOfFirstElement);
        //workingGroupsPage.firstElementFromDropdown.click();

        workingGroupsPage.positionField.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Chair']")));
        workingGroupsPage.chairElementFromDropdown.click();

        workingGroupsPage.saveButton.click();
        Thread.sleep(1000L);

        String chairFirstName = workingGroupsPage.addedFirstName.getText();
        String chairLastName = workingGroupsPage.addedLastName.getText();

        String chairName = chairFirstName + " " + chairLastName;
        System.out.println(chairName);

        //THEN
        Assertions.assertEquals(chairName, valueOfFirstElement);

        //delete the created Working group
        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.deleteIconForAddingChairGroup));
        //delete group
       ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()",workingGroupsPage.deleteIconForAddingChairGroup);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ok']")));
        workingGroupsPage.okButtonOnConfirmationDeletion.click();
        Thread.sleep(500L);
    }

    @Test
    public void addMemberForGroup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(2000L);
        //GIVEN
        //create new Working group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);

        workingGroupsPage.nameOfTheGroupField.sendKeys("Automation Test for adding Member");
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();

        //go to group link
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.workingGroupForAddingMember);

        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //go to Add Member button
        workingGroupsPage.addMemberButton.click();

        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        workingGroupsPage.addMemberField.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.firstElementFromDropdown));

        String valueOfFirstElement = workingGroupsPage.firstElementFromDropdown.getText();
        System.out.println(valueOfFirstElement);
        workingGroupsPage.firstElementFromDropdown.click();

        workingGroupsPage.statusField.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Approved']")));
        workingGroupsPage.approvedElementFromDropdown.click();

        workingGroupsPage.saveButton.click();

        Thread.sleep(500L);

        String chairFirstName = workingGroupsPage.addedFirstName.getText();
        String chairLastName = workingGroupsPage.addedLastName.getText();

        String memberName = chairFirstName + " " + chairLastName;
        System.out.println(memberName);

        Assertions.assertEquals(memberName, valueOfFirstElement);

        //delete the created Working group
        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();
        //delete group
        workingGroupsPage.deleteIconForEditMemberGroup.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ok']")));
        workingGroupsPage.okButtonOnConfirmationDeletion.click();
        Thread.sleep(500L);
    }

    @Test
    public void checkDisabledButtonForAddingChairCoChair() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        Actions action = new Actions(startingPage.driver);
        Thread.sleep(2000L);
        //GIVEN
        //create new Working group
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);

        workingGroupsPage.nameOfTheGroupField.sendKeys("Automation Test for checking disabled button");
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();

        //go to group link
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.workingGroupForDisabledButton);
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));

        //add Chair to the group
        //go to Add Chair / Co-Chair button
        workingGroupsPage.addChairCoChairButton.click();
        startingPage.driver.switchTo().activeElement();
        workingGroupsPage.addChairCoChairField.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.firstElementFromDropdown));
        String valueOfFirstElement = workingGroupsPage.firstElementFromDropdown.getText();
        System.out.println(valueOfFirstElement);
        workingGroupsPage.firstElementFromDropdown.click();
        workingGroupsPage.positionField.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.chairElementFromDropdown));
        workingGroupsPage.chairElementFromDropdown.click();
        workingGroupsPage.saveButton.click();

        //add Co-chair to the group
        //go to Add Chair / Co-Chair button
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.addChairCoChairButton));
        workingGroupsPage.addChairCoChairButton.click();
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        action.click(workingGroupsPage.addChairCoChairField).perform();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.firstElementFromDropdown));
        String valueOfFirstElement1 = workingGroupsPage.firstElementFromDropdown.getText();
        System.out.println(valueOfFirstElement1);
        workingGroupsPage.firstElementFromDropdown.click();
        workingGroupsPage.positionField.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.coChairElementFromDropdown));
        workingGroupsPage.coChairElementFromDropdown.click();
        workingGroupsPage.saveButton.click();

        Assertions.assertTrue(workingGroupsPage.addChairCoChairField.isEnabled());

        //delete the created Working group
        //go to Working group list
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.paging10PagesElement));
        Thread.sleep(1000L);
        //open 100 elements for a page
        workingGroupsPage.paging10PagesElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.element100PagesFromDropDown));
        workingGroupsPage.element100PagesFromDropDown.click();
        //delete group
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.deleteIconForCheckingDisableButtonGroup));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.deleteIconForCheckingDisableButtonGroup);
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.okButtonOnConfirmationDeletion));
        workingGroupsPage.okButtonOnConfirmationDeletion.click();
        Thread.sleep(500L);
    }

    @Test
    public void searchForWorkingGroup() {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions act = new Actions(startingPage.driver);

        //navigate to Create Working group page
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.addWorkingGroupButton);

        //GIVEN
        String expectedNameOfSearchedElement = "Test for searching";
        //create new working group
        workingGroupsPage.nameOfTheGroupField.sendKeys(expectedNameOfSearchedElement);
        workingGroupsPage.saveButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.editWorkingGroup));
        //go to the Group section
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(workingGroupsPage.addWorkingGroupButton));

        //WHEN
        workingGroupsPage.searchField.sendKeys(expectedNameOfSearchedElement);
        workingGroupsPage.searchIcon.click();
        String actualNameOfSearchedElement = workingGroupsPage.searchedElement.getText();
        System.out.println(actualNameOfSearchedElement);
        //THEN
        Assertions.assertEquals(expectedNameOfSearchedElement, actualNameOfSearchedElement);

        //after test
        //delete created group
        WebElement trashIconForGroup = startingPage.driver.findElement(By.xpath("//a[text()='"+expectedNameOfSearchedElement+"']/../../td[7]/button"));
        trashIconForGroup.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", workingGroupsPage.okButtonOnConfirmationDeletion);
    }
    
        @AfterEach
        public void cleanUp () {
            startingPage.driver.close();
            startingPage.driver.quit();
        }
    }
