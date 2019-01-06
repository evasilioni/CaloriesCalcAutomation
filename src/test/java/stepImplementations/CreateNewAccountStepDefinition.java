package stepImplementations;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPageFactory;
import utilities.ElementWatcher;
import utilities.RandomEmailGenerator;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class CreateNewAccountStepDefinition extends AbstractPageStepDefinition {

    RegisterPageFactory registerPage;

    @When("^User clicks on register$")
    public void userClicksOnRegister(){
        AbstractPageStepDefinition.setEmail(RandomEmailGenerator.getEmail());
        AbstractPageStepDefinition.setPassword("P@ssW0rd");

        registerPage =  new RegisterPageFactory(driver);
        registerPage.register();
    }

    @Then("^Navigate to Register Page$")
    public void navigateToRegisterPage() {
        assertThat(registerPage.getHeaderRegistration(), containsString("Create account"));
    }

    @And("^User enters username$")
    public void userEntersUsername() {
        registerPage.setUserNameInputField("John Doe");
    }


    @And("^User enters confirm password$")
    public void userEntersConfirmPassword() {
        registerPage.setPasswordConfirmationInputField(AbstractPageStepDefinition.getPassword());
    }

    @And("^User enters different confirm password$")
    public void userEntersDifferentConfirmPassword(){
        registerPage.setPasswordConfirmationInputField(AbstractPageStepDefinition.getPassword() + "123");
    }

    @And("^User clicks Join$")
    public void userClicksJoin() {
        registerPage.join();
    }

    @Then("^User should not be able to Join$")
    public void userCannotJoin() {
        assertThat(ElementWatcher.isElementPresent(By.cssSelector("header[data-ui=\"greeting\"]"), driver), equalTo(false));
//        assertThat(driver.switchTo().alert().getText(), equalTo("Please fill out this field"));
    }

    @Then("^Registration should fail$")
    public void registrationShouldFail(){
        String expectedMessage = "Registration failed. Please try again";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flash')]"))).getText();
        assertThat(message, equalToIgnoringCase(expectedMessage));
    }

    @And("^User enters as username \"([^\"]*)\"$")
    public void userEntersAsUsername(String username) {
        registerPage.setUserNameInputField(username);
    }

}
