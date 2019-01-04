package stepImplementations;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPageFactory;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public class CreateNewAccountStepDefinition extends AbstractPageStepDefinition {

    RegisterPageFactory registerPage;

    private static String PASSWORD ="P@ssw0rd";
    private static String email;

    @When("^User clicks on register$")
    public void userClicksOnRegister(){
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

    @And("^User enters email$")
    public void userEntersEmail() {
        email = randomEmail();
        registerPage.setEmailInputField(email);
    }

    @And("^User enters an existing email$")
    public void userEntersAnExistingEmail(){
        registerPage.setEmailInputField(email);
    }

    @And("^user enters password$")
    public void userEntersPassword(){
        registerPage.setPasswordInputField(PASSWORD);
    }

    @And("^User enters confirm password$")
    public void userEntersConfirmPassword() {
        registerPage.setPasswordConfirmationInputField(PASSWORD);
    }

    @And("^User enters different confirm password$")
    public void userEntersDifferentConfirmPassword(){
        registerPage.setPasswordConfirmationInputField(PASSWORD + "123");
    }

    @And("^user enters as password \"([^\"]*)\"$")
    public void userEntersAsPassword(String passWord){
        PASSWORD = passWord;
        registerPage.setPasswordInputField(passWord);
    }

    @And("^User clicks Join$")
    public void userClicksJoin() {
        registerPage.join();
    }

    @Then("^User should not be able to Join$")
    public void userCannotJoin() {
        assertThat(isElementPresent(By.cssSelector("header[data-ui=\"greeting\"]")), equalTo(false));
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
    public void userEntersAsUsername(String username) throws Throwable {
        registerPage.setUserNameInputField(username);
    }

    private static String randomEmail() {
        return "John-" + UUID.randomUUID().toString() + "@example.com";
    }

    private boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}
