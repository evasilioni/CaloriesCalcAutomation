package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePageFactory {

    //Define web elements at class level
    WebDriver driver;

    //Definition of web elements at class level
    @FindBy(xpath="//span[contains(text(),'Logout')]")
    WebElement logoutButton;


    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        //  Wait 20 Second To Find Element If Element Is Not Present
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    //Steps
    public void logout() {
        logoutButton.click();
    }

}
