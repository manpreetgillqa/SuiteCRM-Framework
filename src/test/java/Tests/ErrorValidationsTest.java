package Tests;

import PageObjects.AccountsPage;
import PageObjects.HomePage;
import TestComponents.BaseTest;
import TestComponents.Retry;
import data.DataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(retryAnalyzer = Retry.class)
    public void checkLogo(){
        Assert.assertTrue(loginPage.logoDisplayed());
    }

    @Test(dataProvider = "getInvalidData",dependsOnMethods = "checkLogo")

    public void checkLoginFunctionalityWithInvalidCredentials(HashMap<String,String> input) throws InterruptedException {
        HomePage homePage = loginPage.loginApplication(input.get("username"),input.get("password"));
        Assert.assertTrue(loginPage.alertMessage().contains("try again"));
    }

    @Test(dataProvider = "getValidData",dependsOnMethods = "checkLogo",retryAnalyzer = Retry.class)
    public void checkLoginFunctionalityWithValidCredentials(HashMap<String,String> input) throws InterruptedException {
        HomePage homePage = loginPage.loginApplication(input.get("username"),input.get("password"));
        Assert.assertTrue(loginPage.getPageTitle().contains("SuiteCRM"));
    }

    @Test(dataProvider = "getValidData",groups = {"smoke"},retryAnalyzer = Retry.class)
    public void verifyAccountCreation(HashMap<String,String> input) throws InterruptedException {
        HomePage homePage = loginPage.loginApplication(input.get("username"),input.get("password"));
        AccountsPage accountsPage = homePage.selectAccounts();
        accountsPage.enterName(input.get("accName"));
        accountsPage.saveAccount();
        Assert.assertEquals(accountsPage.getAccountName(),input.get("accName"));
    }

    @Test(dataProvider = "getValidData",retryAnalyzer = Retry.class)
    public void verifySuccessfulLogout(HashMap<String,String> input) throws InterruptedException {
        HomePage homePage = loginPage.loginApplication(input.get("username"),input.get("password"));
        Thread.sleep(7000);
        loginPage.logoutApplication();
        Assert.assertTrue(loginPage.alertMessage().contains("Success"));

    }

    @DataProvider

    public Object[][] getInvalidData() throws IOException {
        DataReader dr = new DataReader();
        List<HashMap<String,String>> data = dr.getJsonDataToMap((System.getProperty("user.dir")+"//src//test//java//data//InvalidLoginData.json"));
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

    @DataProvider

    public Object[][] getValidData() throws IOException {
        DataReader dr = new DataReader();
        List<HashMap<String,String>> data = dr.getJsonDataToMap((System.getProperty("user.dir")+"//src//test//java//data//loginData.json"));
        return new Object[][]{{data.get(0)},{data.get(1)}};
    }

}
