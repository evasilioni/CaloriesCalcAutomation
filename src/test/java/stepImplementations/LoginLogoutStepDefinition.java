package stepImplementations;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import pages.LoginPageFactory;
import utilities.ElementWatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginLogoutStepDefinition extends AbstractPageStepDefinition {

    LoginPageFactory loginPage;

    public void init() {
        loginPage =  new LoginPageFactory(driver);
        AbstractPageStepDefinition.setPassword("Winter2019!");
    }

    @When("^User clicks on login and navigated on login page$")
    public void userClicksOnLogin(){
        init();
        loginPage.navigateToLogin();
    }


    @And("^User clicks login$")
    public void userClicksLogin() {
        loginPage.clickLogin();
    }

    @Then("^User should not be able to Login$")
    public void userShouldNotBeAbleToLogin() {
        assertThat(ElementWatcher.isElementPresent(By.cssSelector("header[data-ui=\"greeting\"]"), driver), equalTo(false));
    }
}
