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

public class EditRecordsTest extends TestBase {

    EdiblesPageFactory ediblesPage;
    WebDriverWait wait;

    @BeforeClass
    public void setPreconditions(){
        init();
        homePage.selectEdibles();

        ediblesPage = new EdiblesPageFactory(driver);
        wait  = new WebDriverWait(driver, 10);

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Not edited yet: Pasta and Salad");
        ediblesPage.setCalories(535);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void edit_records_by_foodtype_test(){
        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement foodtype = d.findElement(By.xpath("//td[contains(text(),'Not edited yet: Pasta and Salad')]"));
                return foodtype.isDisplayed();
            }
        });

        WebElement rowElement = driver.findElement(By.xpath("//td[contains(text(),'Not edited yet: Pasta and Salad')]//..//button[@data-ui='edit-btn']"));
        rowElement.click();
        ediblesPage.setFoodType("Edit: Pasta and Salad Food Type");
        ediblesPage.saveEdible();

        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement foodtype = d.findElement(By.xpath("//td[contains(text(),'Edit: Pasta and Salad Food Type')]"));
                return foodtype.isDisplayed();
            }
        });
    }

    @Test
    public void edit_records_by_calories_test() {
        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement calories = d.findElement(By.xpath("//td[contains(text(),'535')]"));
                return calories.isDisplayed();
            }
        });

        WebElement rowElement = driver.findElements(By.xpath("//td[contains(text(),'535')]//..//button[@data-ui='edit-btn']")).get(0);
        rowElement.click();
        ediblesPage.setCalories(1347);
        ediblesPage.saveEdible();

        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement calories = d.findElement(By.xpath("//td[contains(text(),'1347')]"));
                return calories.isDisplayed();
            }
        });
    }

}
