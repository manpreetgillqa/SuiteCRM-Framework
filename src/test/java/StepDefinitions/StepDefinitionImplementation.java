package StepDefinitions;

import PageObjects.AccountsPage;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class StepDefinitionImplementation extends BaseTest {

    public LoginPage loginPage;
    public  HomePage homePage;
    public  AccountsPage accountsPage;
    @Given("I landed on SuiteCRM page")
    public void i_landed_on_suite_crm_page() throws IOException {
        loginPage = launchApplication();
    }
    @Given("Logged in with {string} and {string}")
    public void logged_in_with_and(String string, String string2) {
         homePage = loginPage.loginApplication(string,string2);
    }
    @When("I create Account with {string}")
    public void i_create_account_with(String string) throws InterruptedException {
        accountsPage = homePage.selectAccounts();
        accountsPage.enterName(string);
        accountsPage.saveAccount();
    }
    @Then("Verify the correct {string} is displayed")
    public void verify_the_correct_is_displayed(String string) {
        Assert.assertEquals(accountsPage.getAccountName(),string);
    }
    @Then("close the browser")
    public void close_the_browser(){
        driver.quit();
    }

    @When("I wait for page to load")
    public void I_wait_for_page_to_load() throws InterruptedException {
        Thread.sleep(7000);
    }

    @Then("I go to my Profile and click logout")
    public void I_go_to_my_profile_and_click_logout(){
        loginPage.logoutApplication();
    }

    @Then("Verify {string} is displayed on homepage upon successful logout")
    public void verify_is_displayed_on_homepage_upon_successful_logout(String string){
        Assert.assertTrue(loginPage.alertMessage().contains(string));
    }
}
