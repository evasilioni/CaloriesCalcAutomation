package stepImplementations;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePageFactory;


public class CommonPageStepDefinition extends AbstractPageStepDefinition {
    
    WebDriver driver = null;
    HomePageFactory homePage;


    @Before
    public void setUp() {
        driver = getDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Given("^User need to be on Calories-Calc login page$")
    public void userNeedToBeOnOBIEELoginPage() {
        System.out.println("User is on Calories-Calc login page");
    }

    @Then("^Navigate to Home Page$")
    public void navigateToHomePage() {
        homePage = new HomePageFactory(driver);

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement header = d.findElement(By.cssSelector("header[data-ui=\"greeting\"]"));
                return header.getText().contains("Hi");
            }
        });
    }

    @And("^User clicks Logout$")
    public void userClicksLogout(){
        homePage.logout();
    }

}
