package AutomationTests.tests;

import AutomationTests.pages.LoginPage;
import AutomationTests.testClients.Client;
import AutomationTests.testClients.ClientLombok;
import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import static org.openqa.selenium.support.PageFactory.*;

@Feature("Login functionality")
public class LoginTest {

    private static LoginPage loginPage;

    @BeforeAll
    @Step("Set up driver before tests")
    static void init() {
        BaseSetUp baseSetUp = new BaseSetUp();
        loginPage = initElements(BaseSetUp.driver, LoginPage.class);
    }

    @RegisterExtension
    ScreenshotOnFailure watcher = new ScreenshotOnFailure(BaseSetUp.driver, "target/surefire-reports");

    @Test
    @Story("Login successful")
    @Issue("issue-12345")
    @Description("Check login is successful after entering valid credentials")
    public void testLogin() {
        //GIVEN
        //example client with manual builder
        Client client = new Client.ClientBuilder(123L)
                .withName("test")
                .withLastName("testLastName")
                .build();

        //example of client with lombok builder
        ClientLombok clientLombok = ClientLombok.builder()
                .id(123L)
                .name("name")
                .build();

        String existingUserEmail = "test@test.com";
        String existingUserPassword = "test";
        //WHEN
        loginPage.openLoginPage();

        loginPage.setEmail(existingUserEmail);
        loginPage.setPassword(existingUserPassword);

        loginPage.submit();
        //THEN
        checkUserIsRedirectedToProducts();
    }

    @Step("Check user is redirected to /products")
    private void checkUserIsRedirectedToProducts() {
        String currentUrl = BaseSetUp.driver.getCurrentUrl();
        Assertions.assertEquals("http://online-sh.herokuapp.com/products", currentUrl);
    }

    @AfterAll
    @Step("Quit browser")
    static void tearDown(){
        BaseSetUp.driver.quit();
    }
}