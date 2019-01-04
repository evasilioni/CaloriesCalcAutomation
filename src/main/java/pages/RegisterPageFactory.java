package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPageFactory{

    //Define web elements at class level
    WebDriver driver;

    //Definition of web elements at class level
    @FindBy(id ="name-input")
    WebElement userNameInputField;

    @FindBy(id ="email-input")
    WebElement emailInputField;

    @FindBy(id="password-input")
    WebElement passwordInputField;

    @FindBy(id="password-confirmation-input")
    WebElement passwordConfirmationInputField;

    @FindBy(tagName="h3")
    WebElement headerRegistration;

    @FindBy(xpath="//button[contains(text(),'Register')]")
    WebElement registerButton;

    @FindBy(xpath="//input[@value='Join']")
    WebElement joinButton;

    //Steps
    public String getHeaderRegistration() {
        return headerRegistration.getText();
    }

    public void setUserNameInputField(String userName) {
        userNameInputField.sendKeys(userName);
    }

    public void setEmailInputField(String email) {
        emailInputField.sendKeys(email);
    }

    public void setPasswordInputField(String password) {
        passwordInputField.sendKeys(password);
    }

    public void setPasswordConfirmationInputField(String passwordConfirmation) {
        passwordConfirmationInputField.sendKeys(passwordConfirmation);
    }

    public void join() {
        joinButton.click();
    }

    public void register(){
        registerButton.click();
    }

    //Contructor initiallizes the state of the driver
    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        //  Wait 20 Second To Find Element If Element Is Not Present
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }
}
