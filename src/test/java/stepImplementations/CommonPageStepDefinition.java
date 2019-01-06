package stepImplementations;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.CommonPageFactory;
import utilities.ElementWatcher;
import utilities.RandomEmailGenerator;
import utilities.UserCreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class CommonPageStepDefinition extends AbstractPageStepDefinition{
    
    WebDriver driver = null;
    CommonPageFactory commonPage;

    @Before
    public void setUp() {
        driver = getDriver();
        commonPage = new CommonPageFactory(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^User need to be on Calories-Calc login page$")
    public void userNeedToBeOnCaloriesCalcLoginPage() {
        assertThat(driver.findElement(By.xpath("//h2[contains(text(),'Welcome to Calc')]")).isDisplayed(),equalTo(true));
        System.out.println("User is able to access Calories-Calc page");
    }

    @And("^User enters password$")
    public void  userEntersPassword(){
        commonPage.setPasswordInputField(AbstractPageStepDefinition.getPassword());
    }

    @And("^user enters as password \"([^\"]*)\"$")
    public void userEntersAsPassword(String passWord){
        AbstractPageStepDefinition.setPassword(passWord);
        commonPage.setPasswordInputField(AbstractPageStepDefinition.getPassword());
    }

    @And("^User enters email$")
    public void userEntersEmail() {
        commonPage.setEmailInputField(AbstractPageStepDefinition.getEmail());
        EMAIL = AbstractPageStepDefinition.getEmail();
    }


    @And("^User enters an existing email$")
    public void userEntersAnExistingEmail(){
        commonPage.setEmailInputField(EMAIL);
    }

    @And("^User enters wrong email format by giving \"([^\"]*)\"$")
    public void userEntersWrongEmailFormatByGiving(String email) {
        AbstractPageStepDefinition.setEmail(email);
        commonPage.setEmailInputField(email);
//        Actions ToolTip1 = new Actions(driver);
//        WebElement googleLogo = driver.findElement(By.("//div[@id='hplogo']"));
//
//        String expectedMessage = "Please include an '@' in the email address.";
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        String message = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flash')]"))).getText();
//        assertThat(message, equalToIgnoringCase(expectedMessage));
//
        assertThat(ElementWatcher.isElementPresent(By.cssSelector("header[data-ui=\"greeting\"]"), driver), equalTo(false));
    }

    @Then("^User enters an email \"([^\"]*)\"$")
    public void userEntersAnEmail(String email){
        AbstractPageStepDefinition.setEmail(email);
        commonPage.setEmailInputField(AbstractPageStepDefinition.getEmail());
    }

    @And("^Create a user if not  exists$")
    public void createAUserIfNotExists() {
        String email = RandomEmailGenerator.getEmail();
        UserCreator.createUserIfNotExists("Silioni Evi",email,"Winter2019!");
        AbstractPageStepDefinition.setEmail(email);
    }

}
