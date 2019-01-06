package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.EdiblesPageFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class AddRecordsTest extends TestBase {

    EdiblesPageFactory ediblesPage;
    WebDriverWait wait;

    @BeforeClass
    public void setPreconditions(){
        init();
        homePage.selectEdibles();

        ediblesPage = new EdiblesPageFactory(driver);
        wait  = new WebDriverWait(driver, 10);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void add_records_by_foodtype_calories_date_test(){
        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Pasta and Salad");
        ediblesPage.setCalories(535);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Sushi");
        ediblesPage.setCalories(285);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("CheeseBurger");
        ediblesPage.setCalories(447);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();

        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement foodtype = d.findElement(By.xpath("//td[contains(text(),'Pasta and Salad')]"));
                WebElement calories = d.findElement(By.xpath("//td[contains(text(),'535')]"));
                return foodtype.isDisplayed() && calories.isDisplayed();
            }
        });
    }

    @Ignore
    @Test
    public void add_records_by_calories_and_date_test() {
        ediblesPage.addBtnClick();

        ediblesPage.setFoodType("");
        ediblesPage.setCalories(1347);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();

        String expectedMessage = "Unprocessable entity";
        String message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flash')]"))).getText();
        assertThat(message, equalToIgnoringCase(expectedMessage));
    }

    @Test
    public void add_records_by_foodtype_and_date_test() {
        ediblesPage.addBtnClick();

        ediblesPage.setFoodType("Whatever food type");
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();

        String expectedMessage = "Unprocessable entity";
        String message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'flash')]"))).getText();
        assertThat(message, equalToIgnoringCase(expectedMessage));
    }
}
