package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DriverFactory;

import java.rmi.NoSuchObjectException;

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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Register')]")));

        registerBtn.click();
    }
}
