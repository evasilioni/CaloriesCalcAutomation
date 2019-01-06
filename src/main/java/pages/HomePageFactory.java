package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageFactory {

    //Define web elements at class level
    WebDriver driver;

    @FindBy(xpath = "//a[contains(text(),'Edibles')]")
    WebElement edibles;

    @FindBy(xpath = "//a[contains(text(),'Profile')]")
    WebElement profile;

    @FindBy(id="max-daily-calories")
    WebElement maxDailyCaloriesInputField;

    //Definition of web elements at class level
    @FindBy(xpath="//span[contains(text(),'Logout')]")
    WebElement logoutButton;


    public HomePageFactory(WebDriver driver) {
        this.driver = driver;
        //  Wait 20 Second To Find Element If Element Is Not Present
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    //Steps
    public void selectEdibles(){
        edibles.click();
    }

    public void selectProfile(){
        profile.click();
    }

    public void setMaxDailyCaloriesInputField(int calories){
        maxDailyCaloriesInputField.clear();
        maxDailyCaloriesInputField.sendKeys(String.valueOf(calories));
    }

    public void logout() {
        logoutButton.click();
    }

    public Boolean isNavigatedToHomePage(){
        return (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement header = d.findElement(By.cssSelector("header[data-ui=\"greeting\"]"));
                return header.getText().contains("Hi");
            }
        });
    }

}
