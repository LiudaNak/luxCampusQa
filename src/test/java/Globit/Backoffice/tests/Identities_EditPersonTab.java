package Globit.Backoffice.tests;

import Globit.Backoffice.pages.*;
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

public class Identities_EditPersonTab {
    private static WebDriver driver;

    //Create objects of pages
    LoginPage loginPage;
    HomePage homePage;
    StartingPage startingPage;
    Identities_PersonPage identities_PersonPage;
    Identities_PersonPage_PersonTab_Page identities_personPage_personTab_page;


    @BeforeEach
    public void openUrlAndLogin() throws InterruptedException {
        StartingPage.driverInitialization();
        StartingPage.driver.navigate().to(StartingPage.linkToDevEnvironment);
        loginPage = new LoginPage();
        homePage = new HomePage();
        startingPage = new StartingPage();
        identities_PersonPage = new Identities_PersonPage();
        identities_personPage_personTab_page = new Identities_PersonPage_PersonTab_Page();


        StartingPage.login();
        StartingPage.navigateToScope();
        IdentitiesPage.navigateToUser();
    }

    @Test
    public void editNamesToPerson1(){
        System.out.println("test");
        identities_PersonPage.groupTab.click();
    }

    @Test
    public void editNamesToPerson() throws InterruptedException {
        Actions act = new Actions(StartingPage.driver);
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(4000L));
        Thread.sleep(1000L);

        //GIVEN
        String testingValue = " AutomationTest";
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);
        String titleFieldValue = identities_personPage_personTab_page.titleField.getAttribute("value").toString();
        String firstNameFieldValue = identities_personPage_personTab_page.firstNameField.getAttribute("value").toString();
        String lastNameFieldValue = identities_personPage_personTab_page.lastNameField.getAttribute("value").toString();

        //WHEN
        //edit fields
        identities_personPage_personTab_page.titleField.sendKeys(testingValue);
       identities_personPage_personTab_page.firstNameField.sendKeys(testingValue);
       identities_personPage_personTab_page.lastNameField.sendKeys(testingValue);

        //click on the Save button
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(5000L);

        //get value of edited fields
        String editedTitleField = identities_personPage_personTab_page.titleField.getAttribute("value");
        String editedFirstNameField = identities_personPage_personTab_page.firstNameField.getAttribute("value");
        String editedLastNameField = identities_personPage_personTab_page.lastNameField.getAttribute("value");

        //print fields to check
        System.out.println(editedTitleField + " 1");
        System.out.println(editedFirstNameField + " 2");
        System.out.println(editedLastNameField + " 3");

        //THEN
        Assertions.assertTrue(editedTitleField.endsWith(testingValue));
        Assertions.assertTrue(editedFirstNameField.endsWith(testingValue));
        Assertions.assertTrue(editedLastNameField.endsWith(testingValue));

        //After-Test  - return the value to the fields
        identities_personPage_personTab_page.titleField.clear();
        identities_personPage_personTab_page.titleField.sendKeys(titleFieldValue);
        Thread.sleep(500L);
        identities_personPage_personTab_page.firstNameField.clear();
        identities_personPage_personTab_page.firstNameField.sendKeys(firstNameFieldValue);
        identities_personPage_personTab_page.lastNameField.clear();
        Thread.sleep(500L);
        identities_personPage_personTab_page.lastNameField.sendKeys(lastNameFieldValue);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
    }


    @Test
    public void editGenderToPerson() throws InterruptedException {
        Actions act = new Actions(StartingPage.driver);
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(8000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        //GIVEN
        String currentGender = identities_personPage_personTab_page.genderField.getAttribute("title");
        //WHEN
        identities_personPage_personTab_page.genderField.click();
        Thread.sleep(500L);
        if (currentGender == "Male")
        {
            wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.femaleFromGenderDropdown));
            ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.femaleFromGenderDropdown);
        }
        else {
            wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.maleFromGenderDropdown));
            ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.maleFromGenderDropdown);
        }
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);

        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.genderField));
        Thread.sleep(3000L);

        String editedGender = identities_personPage_personTab_page.genderField.getAttribute("title");
        System.out.println(currentGender + " - current");
        System.out.println(editedGender + " - edited");
        //THEN
        if (currentGender == "Male") {
            Assertions.assertTrue(editedGender == "Female");
        } else if (currentGender == "Female"){
            Assertions.assertTrue(editedGender == "Male");
        }
    }

    @Test
    public void changeEmailToPerson() throws InterruptedException {
        Actions act = new Actions(StartingPage.driver);
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(8000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        //get current user's email

        String initialUserEmail = identities_personPage_personTab_page.emailAddressField.getAttribute("value");
        String editedEmail = "test@test.com";

        identities_personPage_personTab_page.emailAddressField.clear();
        identities_personPage_personTab_page.emailAddressField.sendKeys(editedEmail);
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);

        //THEN
        String currentEmail = identities_personPage_personTab_page.emailAddressField.getAttribute("value");
        Assertions.assertEquals(editedEmail, currentEmail);

        //after test
        //return the initial email
        identities_personPage_personTab_page.emailAddressField.clear();
        identities_personPage_personTab_page.emailAddressField.sendKeys(initialUserEmail);
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(500L);
    }

    @Test
    public void addAdditionalEmailToUser() throws InterruptedException {
        Actions act = new Actions(StartingPage.driver);
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        Thread.sleep(1000L);
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        //GIVEN
        String expectedAdditionalEmail = "testAdditional@test.com";

        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.addEmailButtonForAdditionalEmail);

        identities_personPage_personTab_page.additionalEmailField.clear();
        identities_personPage_personTab_page.additionalEmailField.sendKeys(expectedAdditionalEmail);

        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);

        //THEN
        String currentAdditionalFieldValue = identities_personPage_personTab_page.additionalEmailField.getAttribute("value");
        Assertions.assertEquals(expectedAdditionalEmail, currentAdditionalFieldValue);

        //after test
        //delete added additional email
        identities_personPage_personTab_page.additionalEmailField.clear();
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(2000L);
    }


    @Test
    public void addProfileImageToPerson() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        String expectedProfileFieldValue = "Galileo"; //the picture file title
        identities_personPage_personTab_page.profileImageField.sendKeys("C:\\Users\\admin\\Desktop\\Globit photo\\Galileo.jpg");

        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        identities_personPage_personTab_page.saveButton.click();
        Thread.sleep(3000L);

        String actualProfileFieldValue = identities_personPage_personTab_page.profileField.getAttribute("src").toString();
        System.out.println(actualProfileFieldValue);

        Assertions.assertTrue(actualProfileFieldValue.contains(expectedProfileFieldValue));

        identities_personPage_personTab_page.closeButtonForImage.click();

        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        identities_personPage_personTab_page.saveButton.click();
        Thread.sleep(3000L);
    }

    @Test
    public void addTagToPerson() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);
        //GIVEN
        String expectedTagValue = "test";

        identities_personPage_personTab_page.addTagButton.click();
        identities_personPage_personTab_page.tagField.sendKeys(expectedTagValue);
        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);

        String actualTagValue = identities_personPage_personTab_page.tagField.getAttribute("value");
        System.out.println(actualTagValue + " - actual value");
        //THEN
        Assertions.assertEquals(expectedTagValue, actualTagValue);
        //After test
        identities_personPage_personTab_page.closeIconForTagField.click();
        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);
    }

    @Test
    public void addNotesToPerson() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        String expectedNotesValue = "test value";

        identities_personPage_personTab_page.notesField.clear();
        identities_personPage_personTab_page.notesField.sendKeys(expectedNotesValue);

        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(5000L);

        String actualNotesField = identities_personPage_personTab_page.notesField.getText();
        //THEN
        Assertions.assertEquals(expectedNotesValue, actualNotesField);

        //after test
        //clear the notes field
        identities_personPage_personTab_page.notesField.clear();
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);
    }

    @Test
    public void addSpecifyFieldsToPerson() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        String expectedWorkplaceCategoryFieldValue = "Private Lab";
        String expectedSpecialityFieldValue = "Molecular Biology";
        String expectedDegreeFieldValue = "Clinician (MD, MD/PhD)";
        String expectedYearOfDegreeFieldValue = "1924";
        String expectedSpecialisationFieldValue = "Training completed";
        String expectedPathologistDeclarationCheckbox = "true";


        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.workplaceCategoryField);
        identities_personPage_personTab_page.workplaceCategoryField.click();
        WebElement elementFromWorkplaceCategoryDropdown = StartingPage.driver.findElement(By.xpath("//div[text()='"+expectedWorkplaceCategoryFieldValue+"']"));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", elementFromWorkplaceCategoryDropdown);

        identities_personPage_personTab_page.specialityField.click();
        WebElement elementFromSpecialityDropdown = StartingPage.driver.findElement(By.xpath("//div[text()='"+expectedSpecialityFieldValue+"']"));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", elementFromSpecialityDropdown);

        identities_personPage_personTab_page.degreeField.click();
        WebElement elementFromDegreeDropdown = StartingPage.driver.findElement(By.xpath("//div[text()='"+expectedDegreeFieldValue+"']"));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", elementFromDegreeDropdown);

        identities_personPage_personTab_page.yearOfDegreeField.click();
        WebElement elementFromYearOfDegreeDropdown = StartingPage.driver.findElement(By.xpath("//div[text()='"+expectedYearOfDegreeFieldValue+"']"));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", elementFromYearOfDegreeDropdown);

        identities_personPage_personTab_page.specialisationField.click();
        WebElement elementFromSpecialisationDropdown = StartingPage.driver.findElement(By.xpath("//div[text()='"+expectedSpecialisationFieldValue+"']"));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", elementFromSpecialisationDropdown);

        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.pathologistDeclarationCheckbox);

        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);

        String currentWorkplaceCategoryFieldValue = identities_personPage_personTab_page.workplaceCategoryField.getAttribute("title");
        String currentSpecialityFieldValue = identities_personPage_personTab_page.specialityField.getAttribute("title");
        String currentDegreeFieldValue = identities_personPage_personTab_page.degreeField.getAttribute("title");
        String currentYearOfDegreeFieldValue = identities_personPage_personTab_page.yearOfDegreeField.getAttribute("title");
        String currentSpecialisationFieldValue = identities_personPage_personTab_page.specialisationField.getAttribute("title");
        String currentPathologistDeclarationCheckbox = identities_personPage_personTab_page.pathologistDeclarationCheckbox.getAttribute("value");

        //THEN
        Assertions.assertEquals(expectedWorkplaceCategoryFieldValue, currentWorkplaceCategoryFieldValue);
        Assertions.assertEquals(expectedSpecialityFieldValue, currentSpecialityFieldValue);
        Assertions.assertEquals(expectedDegreeFieldValue, currentDegreeFieldValue);
        Assertions.assertEquals(expectedYearOfDegreeFieldValue, currentYearOfDegreeFieldValue);
        Assertions.assertEquals(expectedSpecialisationFieldValue, currentSpecialisationFieldValue);
        Assertions.assertEquals(expectedPathologistDeclarationCheckbox, currentPathologistDeclarationCheckbox);

        //after test
        //change or clear values of fields
        identities_personPage_personTab_page.workplaceCategoryField.click();
        Thread.sleep(300L);
        StartingPage.driver.findElement(By.xpath("//div[text()='Other']")).click();

        identities_personPage_personTab_page.specialityField.click();
        Thread.sleep(300L);
        StartingPage.driver.findElement(By.xpath("//div[text()='Pharmacology']")).click();

        identities_personPage_personTab_page.degreeField.click();
        Thread.sleep(300L);
        StartingPage.driver.findElement(By.xpath("//div[text()='None']")).click();

        identities_personPage_personTab_page.yearOfDegreeField.click();
        Thread.sleep(300L);
        StartingPage.driver.findElement(By.xpath("//div[text()='none']")).click();

        identities_personPage_personTab_page.specialisationField.click();
        Thread.sleep(300L);
        StartingPage.driver.findElement(By.xpath("//div[text()='None']")).click();

        identities_personPage_personTab_page.pathologistDeclarationCheckbox.click();
        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(3000L);
    }

    @Test
    public void editAddressToPerson() throws InterruptedException {
        Actions act = new Actions(StartingPage.driver);
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        //navigate to Address tab

        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.addContact);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click(true)", identities_personPage_personTab_page.addContact);
        act.moveToElement(identities_personPage_personTab_page.addContact).perform();
        act.click(identities_personPage_personTab_page.addContact).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Address']")));
        act.moveToElement(identities_personPage_personTab_page.addressButton).perform();
        act.click(identities_personPage_personTab_page.addressButton).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.name("collectedContacts.contacts[0].label")));

        String textForLabelField = "Address";
        String textForOrganizationField = "organization test";
        String textForDepartmentField = "department test";
        String textForAddressField = "address test";
        String textForPostalCodeField = "123456";
        String textForCityField = "City test";
        String countryValue = "Albania";
        //WHEN
        identities_personPage_personTab_page.organizationField.sendKeys(textForOrganizationField);
        identities_personPage_personTab_page.departmentField.sendKeys(textForDepartmentField);
        identities_personPage_personTab_page.addressField.sendKeys(textForAddressField);
        identities_personPage_personTab_page.postalCodeField.sendKeys(textForPostalCodeField);
        identities_personPage_personTab_page.cityField.sendKeys(textForCityField);
        identities_personPage_personTab_page.countryField.click();
        WebElement countryFromDropdown = StartingPage.driver.findElement(By.xpath("//div[text()='" +countryValue+"']"));
        countryFromDropdown.click();
        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);

        Thread.sleep(2000L);
        String currentLabelField = identities_personPage_personTab_page.labelField.getAttribute("value");
        String currentOrganizationField = identities_personPage_personTab_page.organizationField.getAttribute("value").toString();
        String currentDepartmentField = identities_personPage_personTab_page.departmentField.getAttribute("value");
        String currentAddressField = identities_personPage_personTab_page.addressField.getAttribute("value");
        String currentPostalCodeField = identities_personPage_personTab_page.postalCodeField.getAttribute("value");
        String currentCityField = identities_personPage_personTab_page.cityField.getAttribute("value");
        String currentCountryValue = identities_personPage_personTab_page.countryField.getAttribute("title");

        //THEN
        //check that fields are filled correctly
        Assertions.assertEquals(textForLabelField,currentLabelField);
        Assertions.assertEquals(textForOrganizationField, currentOrganizationField);
        Assertions.assertEquals(textForDepartmentField, currentDepartmentField);
        Assertions.assertEquals(textForAddressField, currentAddressField);
        Assertions.assertEquals(textForPostalCodeField, currentPostalCodeField);
        Assertions.assertEquals(textForCityField, currentCityField);
        Assertions.assertEquals(countryValue, currentCountryValue);

        //after test
        //close the Address button
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.closeIconForAddressTab));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.closeIconForAddressTab);
        Thread.sleep(500L);
        act.moveToElement(identities_personPage_personTab_page.closeIconForAddressTab).perform();
        act.doubleClick(identities_personPage_personTab_page.closeIconForAddressTab).perform();
        Thread.sleep(2000L);
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(1000L);
    }


    @Test
    public void editPhoneToPerson() throws InterruptedException {
        Actions act = new Actions(StartingPage.driver);
        WebDriverWait wait = new WebDriverWait(StartingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.addContact);
        Thread.sleep(500);
        act.moveToElement(identities_personPage_personTab_page.addContact).perform();
        act.click(identities_personPage_personTab_page.addContact).perform();
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.phoneButton));
        act.moveToElement(identities_personPage_personTab_page.phoneButton).perform();
        act.click(identities_personPage_personTab_page.phoneButton).perform();

        String textForLabelField = "Phone";
        String textForPhoneField = "+1234567890";
        String valueOfMainPhoneCheckbox = "true";

        //WHEN

        act.moveToElement(identities_personPage_personTab_page.phoneField).perform();
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.phoneField));
        identities_personPage_personTab_page.phoneField.sendKeys(textForPhoneField);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click(true)", identities_personPage_personTab_page.mainPhoneCheckbox);
        Thread.sleep(500L);
        ((JavascriptExecutor) StartingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);


        Thread.sleep(2000L);
        String currentLabelField = identities_personPage_personTab_page.labelField.getAttribute("value");
        String currentPhoneField = identities_personPage_personTab_page.phoneField.getAttribute("value").toString();
        String currentMainPhoneCheckbox = identities_personPage_personTab_page.mainPhoneCheckbox.getAttribute("value");

        //THEN
        //check that fields are filled correctly
        Assertions.assertEquals(textForLabelField,currentLabelField);
        Assertions.assertEquals(textForPhoneField, currentPhoneField);
        Assertions.assertEquals(valueOfMainPhoneCheckbox, currentMainPhoneCheckbox);

        //after test
        //close the Phone button
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.closeIconForPhoneTab));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.closeIconForPhoneTab);
        Thread.sleep(500L);
        act.moveToElement(identities_personPage_personTab_page.closeIconForPhoneTab).perform();
        act.doubleClick(identities_personPage_personTab_page.closeIconForPhoneTab).perform();
        Thread.sleep(2000L);
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(500L);
    }

    @Test
    public void editFaxToPerson() throws InterruptedException {
        Actions act = new Actions(startingPage.driver);
        WebDriverWait wait = new WebDriverWait(startingPage.driver, Duration.ofMillis(5000L));
        // navigate to the Persons tab
        wait.until(ExpectedConditions.elementToBeClickable(identities_PersonPage.personTab));
        identities_PersonPage.personTab.click();
        Thread.sleep(1000L);

        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.addContact);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click(true)", identities_personPage_personTab_page.addContact);
        act.moveToElement(identities_personPage_personTab_page.addContact).perform();
        act.click(identities_personPage_personTab_page.addContact).perform();
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.faxButton));
        act.moveToElement(identities_personPage_personTab_page.faxButton).perform();
        act.click(identities_personPage_personTab_page.faxButton).perform();

        String textForLabelField = "Fax";
        String textForFaxField = "+1234567890";
        String valueOfMainPhoneCheckbox = "true";

        //WHEN
        ((JavascriptExecutor) startingPage.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(500L);
        //wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.faxField));
        identities_personPage_personTab_page.faxField.sendKeys(textForFaxField);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click(true)", identities_personPage_personTab_page.mainFaxCheckbox);
        Thread.sleep(500L);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);


        Thread.sleep(2000L);
        String currentLabelField = identities_personPage_personTab_page.labelField.getAttribute("value");
        String currentFaxField = identities_personPage_personTab_page.faxField.getAttribute("value").toString();
        String currentMainFaxCheckbox = identities_personPage_personTab_page.mainFaxCheckbox.getAttribute("value");

        //THEN
        //check that fields are filled correctly
        Assertions.assertEquals(textForLabelField,currentLabelField);
        Assertions.assertEquals(textForFaxField, currentFaxField);
        Assertions.assertEquals(valueOfMainPhoneCheckbox, currentMainFaxCheckbox);

        //after test
        //close the Fax button
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.closeIconForFaxTab));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].scrollIntoView(true);", identities_personPage_personTab_page.closeIconForFaxTab);
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click(true)", identities_personPage_personTab_page.closeIconForFaxTab);
        Thread.sleep(1000L);
        wait.until(ExpectedConditions.elementToBeClickable(identities_personPage_personTab_page.saveButton));
        ((JavascriptExecutor) startingPage.driver).executeScript("arguments[0].click()", identities_personPage_personTab_page.saveButton);
        Thread.sleep(500L);
    }
    @AfterEach
    public void cleanUp() {
        startingPage.driver.close();
        startingPage.driver.quit();
    }

}
