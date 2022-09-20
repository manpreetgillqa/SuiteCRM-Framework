package PageObjects;

import AbstractComponents.AbstractComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsPage extends AbstractComp {
    WebDriver driver;
    public AccountsPage(WebDriver driver) {
        super(driver);
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//*[@type='text'])[2]")
    WebElement nameField;

    @FindBy(xpath = "(//*[@type='button'])[2]")
    WebElement saveButton;

    @FindBy(xpath = "//scrm-dynamic-label[@class='ng-star-inserted']")
    WebElement accountName;

    public void enterName(String accountName){
    nameField.sendKeys(accountName);
    }

    public void saveAccount() throws InterruptedException {
        saveButton.click();
        Thread.sleep(2000);
    }

    public String getAccountName(){
        String name = accountName.getText();
        return name;
    }
}
