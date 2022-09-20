package PageObjects;

import AbstractComponents.AbstractComp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class LoginPage extends AbstractComp {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "(//*[@class='ng-star-inserted'])[2]")
    WebElement logo;

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = ".alert")
    WebElement alert;

    public HomePage loginApplication(String username, String password){
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new HomePage(driver);
    }

    public Boolean logoDisplayed(){
        Boolean match = logo.isDisplayed();
        return match;
    }

    public String alertMessage(){
        String alertMessage = alert.getText();
        return alertMessage;
    }

    public void goTo(){
        driver.get("https://suitecrm.theautomationtechies.com/public/index.php#/Login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getPageTitle(){
      String title =  driver.getTitle();
      return title;
    }

}
