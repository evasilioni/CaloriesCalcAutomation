package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.EdiblesPageFactory;

import static org.testng.Assert.fail;

public class MaxDailyCaloriesPerProfileTest extends TestBase{

    EdiblesPageFactory ediblesPage;
    WebDriverWait wait;

    @BeforeClass
    public void setPreconditions(){
        init();

        homePage.selectProfile();
        homePage.setMaxDailyCaloriesInputField(1870);

        homePage.selectEdibles();
        ediblesPage = new EdiblesPageFactory(driver);
        wait  = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test(priority = 1)
    public void maxDailyCaloriesExceededTest(){
        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Pasta and Salad");
        ediblesPage.setCalories(530);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();

        isElementDisplayed(By.xpath("//td[contains(text(),'Pasta and Salad')]"));

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Bread and Breakfast");
        ediblesPage.setCalories(450);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();
        isElementDisplayed(By.xpath("//td[contains(text(),'Bread and Breakfast')]"));

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Fruits and Vegetables");
        ediblesPage.setCalories(400);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();
        isElementDisplayed(By.xpath("//td[contains(text(),'Fruits and Vegetables')]"));

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Cake");
        ediblesPage.setCalories(600);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();
        isElementDisplayed(By.xpath("//td[contains(text(),'Bread and Breakfast')]"));

        try{
            fail("VALIDATION MESSAGE NEVER DISPLAYED");
        }catch(Exception e){
            isElementDisplayed(By.xpath("//div[@class='calorybox u-full-width red']"));
        }
    }

    @Test(priority = 2)
    public void maxDailyCaloriesNotExceededTest(){
        WebElement deleteCake = driver.findElement(By.xpath("//td[contains(text(),'Cake')]//..//button[@data-ui='delete-btn']"));

        deleteCake.click();

        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement validatorMsg = d.findElement(By.xpath("//div[@class='calorybox u-full-width green']"));
                return validatorMsg.isDisplayed();
            }
        });
    }

    private Boolean isElementDisplayed(By var1){
        return (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement element = d.findElement(var1);
                return element.isDisplayed();
            }
        });
    }
}
