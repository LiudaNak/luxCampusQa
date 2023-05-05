package Globit.pages;

import Globit.tests.StartingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StatisticsPage {
    public StatisticsPage() {PageFactory.initElements(StartingPage.driver, this);}

    @FindBy (xpath = "//*[text()='Virchows Archive']")
    public WebElement virchowsArchiveTitle;

    @FindBy (xpath = "//*[text()='Total members in good standing']")
    public WebElement totalMemberInGoodStandingElement;

    @FindBy (xpath = "//*[text()='Standing Statistics']")
    public WebElement standingStatisticsElement;

    @FindBy (xpath = "//*[text()='Members In Good Standing']")
    public WebElement memberInGoodStandingElement;

    @FindBy (xpath = "//*[text()='Count of members by countries']")
    public WebElement countOfMembersByCountriesElement;

    @FindBy (xpath = "//*[@id='world-map']")
    public WebElement worldMapElement;

    @FindBy  (css = "#world-map > g > path:nth-child(50)")
    public WebElement FinlandOnMapElement;

    @FindBy (xpath = "//p[@class='hover-info']")
    public WebElement hoverInfo;

    @FindBy (xpath = "//span[text()='OK']")
    public WebElement okButtonOnConfirmation;

}


