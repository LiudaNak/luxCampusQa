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

public class SearchFunctionality {
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    IdentitiesPage identitiesPage;
    AccountsPage accountsPage;
    SearchFunctionalityPage searchFunctionalityPage;

    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        accountsPage = new AccountsPage();
        searchFunctionalityPage = new SearchFunctionalityPage();
        startingPage.login();
        StartingPage.navigateToScope();
    }

    @Test
    public void searchForIdFieldInIdentities() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(8000L));
        //GIVEN
        String expectedSearchValue = "94";
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForFirstColumn);

        startingPage.driver.switchTo().activeElement();
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchButtonForSearchingInColumns);
        Thread.sleep(200L);
        //wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfFirstColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfFirstColumn.getText();
        System.out.println(actualSearchValue);
        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForTypeFieldInIdentities() {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //GIVEN
        String expectedSearchValue = "member";
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForSecondColumn);

        startingPage.driver.switchTo().activeElement();

        searchFunctionalityPage.membersRadioButton.click();
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchButtonForSearchingInColumns);
        String actualSearchValue = searchFunctionalityPage.searchedElementTypeField.getText();
        System.out.println(actualSearchValue);
        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForFirstNameFieldInIdentities() {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //GIVEN
        String expectedSearchValue = "test";
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForThirdColumn);

        //WHEN
        startingPage.driver.switchTo().activeElement();
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchButtonForSearchingInColumns);
        wait.until(ExpectedConditions.invisibilityOf(searchFunctionalityPage.searchButtonForSearchingInColumns));
        String actualSearchValue = searchFunctionalityPage.searchedElementFirstName.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForLastNameFieldInIdentities() {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //GIVEN
        String expectedSearchValue = "test";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForForthColumn);

        startingPage.driver.switchTo().activeElement();
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchField));

        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchButtonForSearchingInColumns);
        wait.until(ExpectedConditions.invisibilityOf(searchFunctionalityPage.searchButtonForSearchingInColumns));
        String actualSearchValue = searchFunctionalityPage.searchedElementLastName.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForEmailFieldInIdentities() {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //GIVEN
        String expectedSearchValue = "test";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForFifthColumn);
        //WHEN
        startingPage.driver.switchTo().activeElement();
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchField));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchButtonForSearchingInColumns);
        wait.until(ExpectedConditions.invisibilityOf(searchFunctionalityPage.searchButtonForSearchingInColumns));
        String actualSearchValue = searchFunctionalityPage.searchedElementEmailField.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchGroupFieldInIdentities() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //GIVEN
        String expectedSearchValue = "Test";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForSixthColumn);
        //go to opened window and choose element from prod-down
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        act.click(searchFunctionalityPage.dropDownArrowOnSearchField).perform();
        Thread.sleep(200L);
        WebElement elementFromDropDown = startingPage.driver.findElement(By.xpath("//div[@title='" + expectedSearchValue + "']"));
        act.click(elementFromDropDown).perform();
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@class='ant-table-tbody']/tr[2]/td[6]/div")));
        String actualSearchValue = searchFunctionalityPage.searchedElementGroupField.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchCountryFieldInIdentities() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //GIVEN
        String expectedSearchValue = "Albania";

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForSeventhColumn);
        //go to opened window and choose element from prod-down
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        act.click(searchFunctionalityPage.fieldForDropdownOpening).perform();
        Thread.sleep(200L);
        startingPage.driver.findElement(By.xpath("//*[text()='"+expectedSearchValue+"']")).click();
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='country'])[1]")));
        String actualSearchValue = searchFunctionalityPage.searchedElementCountryField.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchGroupFieldForGroupsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));

        //navigate to Groups
        wait.until(ExpectedConditions.elementToBeClickable(homePage.groupsTab));
        homePage.groupsTab.click();
        wait.until(ExpectedConditions.urlContains("groups"));
        //GIVEN
        String expectedSearchValue = "test";

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchField);

        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        act.click(searchFunctionalityPage.searchButton).perform();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementGroupFieldForGroupsSection));
        String actualSearchValue = searchFunctionalityPage.searchedElementGroupFieldForGroupsSection.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }
    @Test
    public void searchWorkingGroupFieldForWorkingGroupsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //navigate to Working Groups
        wait.until(ExpectedConditions.elementToBeClickable(homePage.workingGroupsTab));
        homePage.workingGroupsTab.click();
        wait.until(ExpectedConditions.urlContains("working-groups"));

        //GIVEN
        String expectedSearchValue = "pathology";

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForFirstColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        act.click(searchFunctionalityPage.searchButton).perform();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementForWorkingGroupsSection));
        String actualSearchValue = searchFunctionalityPage.searchedElementForWorkingGroupsSection.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchTotalPriceFieldOfOrdersSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //go to Orders section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.ordersTab));
        homePage.ordersTab.click();
        wait.until(ExpectedConditions.urlContains("orders"));

        //GIVEN
        String expectedSearchValue = "50";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForFirstColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchFieldForTotalPriceInOrders.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfSecondColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfSecondColumn.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }
    
   @Test
    public void searchPaymentAmountFieldOfOrdersSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //go to Orders section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.ordersTab));
        homePage.ordersTab.click();
        wait.until(ExpectedConditions.urlContains("orders"));

        //GIVEN
        String expectedSearchValue = "50";

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForSecondColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchFieldForPaymentAmountOnOrders.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody[@class='ant-table-tbody']/tr[2]/td[3]/span")));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfThirdColumn.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchOutstandingAmountFieldOfOrdersSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));

        //go to Orders section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.ordersTab));
        homePage.ordersTab.click();
        wait.until(ExpectedConditions.urlContains("orders"));

        //GIVEN
        String expectedSearchValue = "50";

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForThirdColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchFieldForOutstandingAmountFieldOnOrders.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementForOutstandingAmountFieldOnOrders));
        String actualSearchValue = searchFunctionalityPage.searchedElementForOutstandingAmountFieldOnOrders.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchCustomerFieldOfPaymentsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));
        //go to Payments section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.paymentsTab));
        homePage.paymentsTab.click();
        wait.until(ExpectedConditions.urlContains("payments"));

        //GIVEN
        String expectedSearchValue = "1000";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForFirstColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementCustomerOnPayments));
        String actualSearchValue = searchFunctionalityPage.searchedElementCustomerOnPayments.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForAmountFieldForPaymentsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //go to Payments section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.paymentsTab));
        homePage.paymentsTab.click();
        wait.until(ExpectedConditions.urlContains("payments"));

        //GIVEN
        String expectedSearchValue = "10";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForSecondColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfSecondColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfSecondColumn.getText();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForPaymentMethodFieldForPaymentsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        //go to Payments section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.paymentsTab));
        homePage.paymentsTab.click();
        wait.until(ExpectedConditions.urlContains("payments"));

        //GIVEN
        String expectedSearchValue = "Credit card";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForThirdColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.fieldForDropdownOpening.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='"+expectedSearchValue+"']")));
        startingPage.driver.findElement(By.xpath("//div[text()='"+expectedSearchValue+"']")).click();
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();
        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfThirdColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfThirdColumn.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue.toLowerCase()));
    }

    @Test
    public void searchReferenceFieldOfPaymentsSection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        Actions act = new Actions(startingPage.driver);
        //go to Orders section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.paymentsTab));
        homePage.paymentsTab.click();
        wait.until(ExpectedConditions.urlContains("payments"));

        //GIVEN
        String expectedSearchValue = "mconline";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchForForthColumn);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchFieldForReferenceOfPayments.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButtonForSearchingInColumns));
        act.click(searchFunctionalityPage.searchButtonForSearchingInColumns).perform();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementForReferenceOfPayments));
        String actualSearchValue = searchFunctionalityPage.searchedElementForReferenceOfPayments.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }


    @Test
    public void searchForExportsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));
        //go to Exports section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.exportsTab));
        homePage.exportsTab.click();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchField));
        //GIVEN
        String expectedSearchValue = "journal";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchField);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        searchFunctionalityPage.searchButton.click();
        Thread.sleep(200L);

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfFirstColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfFirstColumn.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

    @Test
    public void searchForProductsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));
        //go to Products section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.productsTab));
        homePage.productsTab.click();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchField));
        //GIVEN
        String expectedSearchValue = "journal";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchField);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        searchFunctionalityPage.searchButton.click();
        Thread.sleep(200L);

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfFirstColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfFirstColumn.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));

    }

    @Test
    public void searchForMailingsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));
        //go to Mailings section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.mailingsTab));
        homePage.mailingsTab.click();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchField));
        //GIVEN
        String expectedSearchValue = "account";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchField);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        searchFunctionalityPage.searchButton.click();
        Thread.sleep(200L);

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfFirstColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfFirstColumn.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));

    }

    @Test
    public void searchForTemplatesSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(7000L));
        //go to Templates section
        homePage.templatesTab.click();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchField));
        //GIVEN
        String expectedSearchValue = "person";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchField);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        searchFunctionalityPage.searchButton.click();
        Thread.sleep(200L);

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfFirstColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfFirstColumn.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));

    }

    @Test
    public void searchForAccountsSection() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(10000L));
        //go to Accounts section
        wait.until(ExpectedConditions.elementToBeClickable(homePage.accountsTab));
        homePage.accountsTab.click();

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchField));
        //GIVEN
        String expectedSearchValue = "test";
        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", searchFunctionalityPage.searchField);
        //go to opened window and input group value
        startingPage.driver.switchTo().activeElement();
        Thread.sleep(500L);
        searchFunctionalityPage.searchField.sendKeys(expectedSearchValue);
        //click on Search button
        wait.until(ExpectedConditions.elementToBeClickable(searchFunctionalityPage.searchButton));
        searchFunctionalityPage.searchButton.click();
        Thread.sleep(200L);

        wait.until(ExpectedConditions.visibilityOf(searchFunctionalityPage.searchedElementValueOfFirstColumn));
        String actualSearchValue = searchFunctionalityPage.searchedElementValueOfFirstColumn.getText().toLowerCase();
        System.out.println(actualSearchValue);

        //THEN
        Assertions.assertTrue(actualSearchValue.contains(expectedSearchValue));
    }

   //@AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }
}
