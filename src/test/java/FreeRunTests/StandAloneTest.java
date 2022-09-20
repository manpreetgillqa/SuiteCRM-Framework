package FreeRunTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://suitecrm.theautomationtechies.com/public/index.php#/Login");

        //first check if the logo is present
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement logo = driver.findElement(By.xpath("(//*[@class='ng-star-inserted'])[2]"));
        Assert.assertTrue(logo.isDisplayed());

        //Check if the valid users can login
        driver.findElement(By.name("username")).sendKeys("manpreet_gill");
        driver.findElement(By.name("password")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click();

      //  WebElement alert = driver.findElement(By.cssSelector(".alert"));

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".quickcreate-button")));

        WebElement newDropdown = driver.findElement(By.cssSelector(".quickcreate-button"));
        Actions a = new Actions(driver);
        a.moveToElement(newDropdown).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//ul[@class='dropdown-menu dropdown-menu-right'])[1]")));

        WebElement accounts = newDropdown.findElement(By.xpath("(//li[@class='new-list-item ng-star-inserted'])[1]"));
        accounts.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//*[@type='text'])[2]"))));
        WebElement nameField = driver.findElement(By.xpath("(//*[@type='text'])[2]"));
        nameField.sendKeys("abc");
        WebElement saveButton = driver.findElement(By.xpath("(//*[@type='button'])[2]"));
        saveButton.click();
//        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//scrm-dynamic-label[@class='ng-star-inserted']"))));
        Thread.sleep(2000);
        WebElement accountName = driver.findElement(By.xpath("//scrm-dynamic-label[@class='ng-star-inserted']"));
        String accName = accountName.getText();
        Assert.assertEquals(accName,"abc");
       WebElement profileName = driver.findElement(By.cssSelector(".primary-global-link"));
        profileName.click();
        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();

          WebElement alert = driver.findElement(By.cssSelector(".alert"));
         String alertMessage =  alert.getText();
        Assert.assertTrue(alertMessage.contains("Success"));
        driver.quit();
    }
}
