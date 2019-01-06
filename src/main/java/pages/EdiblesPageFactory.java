package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EdiblesPageFactory {

    //Define web elements at class level
    WebDriver driver;

    @FindBy(xpath = "//button[@data-ui='add-btn']")
    WebElement addBtn;

    @FindBy(xpath = "//button[@data-ui='edit-btn']")
    WebElement editBtn;

    @FindBy(xpath = "//button[@data-ui='delete-btn']")
    WebElement deleteBtn;

    @FindBy(xpath = "//button[@data-ui='save-btn']")
    WebElement saveButton;

    @FindBy(xpath = "//input[@name='name']")
    WebElement foodTypeInputField;

    @FindBy(xpath = "//input[@name='calories']")
    WebElement caloriesInputField;

    @FindBy(xpath = "//input[@name='created_at']")
    WebElement dateInputField;

    public EdiblesPageFactory(WebDriver driver) {
        this.driver = driver;
        //  Wait 20 Second To Find Element If Element Is Not Present
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);
    }

    //Steps
   public void addBtnClick(){
        addBtn.click();
   }

   public void setFoodType(String foodType){
       foodTypeInputField.clear();
       foodTypeInputField.sendKeys(foodType);
   }

    public void setCalories(int calories) {
        caloriesInputField.clear();
        caloriesInputField.sendKeys(String.valueOf(calories));
    }

    public void setDateInputField() {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        dateInputField.sendKeys(date);
    }

    public void saveEdible(){
        saveButton.click();
    }

    public void editEdible(){
        editBtn.click();
    }

    public void deleteEdible(){
        deleteBtn.click();
    }

}
