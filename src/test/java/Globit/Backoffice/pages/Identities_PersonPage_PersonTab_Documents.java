package Globit.Backoffice.pages;

import Globit.Backoffice.tests.StartingPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Identities_PersonPage_PersonTab_Documents {
    public Identities_PersonPage_PersonTab_Documents() {
        PageFactory.initElements(StartingPage.driver, this);
    }

    public String pathToTheFile = "C:\\Users\\admin\\Desktop\\Globit photo\\Galileo.jpg";
    public String expectedFileName = "Galileo.jpg";
    public String expectedTagName = "Test";

    @FindBy (xpath = "//input[@type='file']")
    public WebElement inputForFileUploading;

    @FindBy (xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[last()]/td[1]/span")
    public WebElement addedFile;

    @FindBy (xpath = "//tbody[@class='ant-table-tbody']/tr[last()]/td[last()]/button")
    public WebElement deletedIconForTheFile;

    @FindBy (xpath = "//button/span[text()='Delete']")
    public WebElement deleteButtonForConfirmation;

    @FindBy (xpath = "//input[@class='ant-input ant-input-sm tags__input']")
    public WebElement inputForNewTagButton;

    @FindBy (xpath = "//span[@class='ant-tag tags__edit']")
    public WebElement addedTag;

}
