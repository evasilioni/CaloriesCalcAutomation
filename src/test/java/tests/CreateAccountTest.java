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
import utilities.DriverFactory;

import java.rmi.NoSuchObjectException;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateAccountTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() throws NoSuchObjectException {
        String webUrl = "http://calories-calc.herokuapp.com/";

        driver = DriverFactory.open("chrome");
        driver.get(webUrl);
    }

    @AfterMethod
    public void tearDown(){
//        driver.quit();
    }

    @Test
    public void create_account_giving_name_email_psw(){
        // Generate a random email
        final String randomEmail = randomEmail();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Register')]")));
        registerBtn.click();

        String headerRegistration = driver.findElement(By.tagName("h3")).getText();
        assertThat(headerRegistration, containsString("Create account"));

        WebElement userName = driver.findElement(By.id("name-input"));
        WebElement userEmail = driver.findElement(By.id("email-input"));
        WebElement password = driver.findElement(By.id("password-input"));
        WebElement passwordConfirmation = driver.findElement(By.id("password-confirmation-input"));

        userName.clear();
        userName.sendKeys("John Doe");
        userEmail.sendKeys(randomEmail);
        password.sendKeys("P@ssw0rd");
        passwordConfirmation.sendKeys("P@ssw0rd");

        driver.findElement(By.xpath("//input[@value='Join']")).click();

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

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Register')]")));
        registerBtn.click();

        String headerRegistration = driver.findElement(By.tagName("h3")).getText();
        assertThat(headerRegistration, containsString("Create account"));

        WebElement userEmail = driver.findElement(By.id("email-input"));
        WebElement password = driver.findElement(By.id("password-input"));
        WebElement passwordConfirmation = driver.findElement(By.id("password-confirmation-input"));

        userEmail.sendKeys(randomEmail);
        password.sendKeys("P@ssw0rd");
        passwordConfirmation.sendKeys("P@ssw0rd");

        driver.findElement(By.xpath("//input[@value='Join']")).click();

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
