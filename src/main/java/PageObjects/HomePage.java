package PageObjects;

import AbstractComponents.AbstractComp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractComp {
    WebDriver driver;
    public HomePage(WebDriver driver){
        super(driver);
        this.driver= driver;

        PageFactory.initElements(driver,this);
    }

    public By newDropDown = By.cssSelector(".quickcreate-button");

    By dropdown = By.xpath("(//ul[@class='dropdown-menu dropdown-menu-right'])[1]");

    @FindBy(css = ".quickcreate-button")
    WebElement newDropdown;

    @FindBy(xpath = "(//li[@class='new-list-item ng-star-inserted'])[1]")
    WebElement accounts;

    public AccountsPage selectAccounts (){
        waitForElementToAppearBy(newDropDown);
        Actions a = new Actions(driver);
        a.moveToElement(newDropdown).build().perform();
        waitForElementToAppearBy(dropdown);
        accounts.click();
        return new AccountsPage(driver);
    }
}
