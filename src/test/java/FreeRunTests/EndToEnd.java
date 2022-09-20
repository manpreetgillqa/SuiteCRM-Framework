package FreeRunTests;

import PageObjects.AccountsPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import TestComponents.BaseTest;
import TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;

public class EndToEnd extends BaseTest {


        @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
        public void endToEnd(HashMap<String,String> input) throws InterruptedException {

        //first check if the logo is present
        Assert.assertTrue(loginPage.logoDisplayed());

        //Check if the valid users can login

        HomePage homePage = loginPage.loginApplication(input.get("username"),input.get("password"));


        // verify account creation
        AccountsPage accountsPage = homePage.selectAccounts();
        accountsPage.enterName(input.get("aName"));
        accountsPage.saveAccount();
        Assert.assertEquals(accountsPage.getAccountName(),input.get("aName"));

        //verify Logout Success
        accountsPage.logoutApplication();
        Assert.assertTrue(loginPage.alertMessage().contains("Success"));

    }

    @DataProvider
    public Object[][] getData(){
        HashMap <String,String> map = new HashMap <String,String> ();
         map.put("username","jim");
         map.put("password","123456");
         map.put("aName","mitch");

        HashMap  <String,String> map1 = new HashMap <String,String> ();
        map1.put("username","manpreet-gill");
        map1.put("password","123456");
        map1.put("aName","auston");

        return new Object[][] {{map},{map1}};

     //       return new Object [][] {{"jim","123456","mitch"},{"manpreet-gill","123456","auston"}};
    }

}
