package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class CommonPageFactory {

    //Define web elements at class level
    WebDriver driver;

    //Definition of web elements at class level
    @FindBy(id ="email-input")
    WebElement emailInputField;

    @FindBy(id="password-input")
    WebElement passwordInputField;


    public CommonPageFactory(WebDriver driver) {
        this.driver = driver;
        //  Wait 20 Second To Find Element If Element Is Not Present
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    //Steps
    public void setEmailInputField(String email) {
        emailInputField.sendKeys(email);
    }

    public void setPasswordInputField(String password) {
        passwordInputField.sendKeys(password);
    }

}
