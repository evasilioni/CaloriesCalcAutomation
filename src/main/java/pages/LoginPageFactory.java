package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPageFactory{

    //Define web elements at class level
    WebDriver driver;

    @FindBy(xpath="//a[@href='/login']")
    WebElement login;

    @FindBy(xpath="//input[@value='Login']")
    WebElement loginButton;


    public LoginPageFactory(WebDriver driver) {
        this.driver = driver;
        //  Wait 20 Second To Find Element If Element Is Not Present
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    //Steps
    public void clickLogin() {
        loginButton.click();
    }
    public void navigateToLogin() {
        login.click();
    }

}
