package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePageFactory;
import pages.RegisterPageFactory;
import utilities.DriverFactory;

import java.rmi.NoSuchObjectException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateAccountTest {

    WebDriver driver;

    RegisterPageFactory registerPage;
    HomePageFactory homePage;

    @BeforeMethod
    public void setUp(){
        String webUrl = "http://calories-calc.herokuapp.com/";

        driver = DriverFactory.open("chrome");
        driver.get(webUrl);

        registerPage = new RegisterPageFactory(driver);
        homePage = new HomePageFactory(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void create_account_giving_name_email_psw(){
        // Generate a random email
        final String randomEmail = randomEmail();

        registerPage.register();
        assertThat(registerPage.getHeaderRegistration(), containsString("Create account"));

        registerPage.setUserNameInputField("John UnitTest Doe");
        registerPage.setEmailInputField(randomEmail);
        registerPage.setPasswordInputField("P@ssw0rd");
        registerPage.setPasswordConfirmationInputField("P@ssw0rd");

        registerPage.join();

        // Check the sign up succeeded by checking that the user name
        // appears in the website's header bar.
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement header = d.findElement(By.cssSelector("header[data-ui=\"greeting\"]"));
                return header.getText().contains("Hi John");
            }
        });
    }

    @Test
    public void create_account_giving_only_email_psw(){
        // Generate a random email
        final String randomEmail = randomEmail();

        registerPage.register();
        assertThat(registerPage.getHeaderRegistration(), containsString("Create account"));

        registerPage.setEmailInputField(randomEmail);
        registerPage.setPasswordInputField("P@ssw0rd");
        registerPage.setPasswordConfirmationInputField("P@ssw0rd");

        registerPage.join();

        assertThat(isElementPresent(By.cssSelector("header[data-ui=\"greeting\"]")), equalTo(false));
    }

    private static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }

    public boolean isElementPresent(By locatorKey) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(String cssLocator){
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }
}
