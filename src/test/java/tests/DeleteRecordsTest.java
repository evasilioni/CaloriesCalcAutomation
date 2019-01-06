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
import tests.TestBase;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteRecordsTest extends TestBase {
    EdiblesPageFactory ediblesPage;
    WebDriverWait wait;

    @BeforeClass
    public void setPreconditions(){
        init();
        homePage.selectEdibles();

        ediblesPage = new EdiblesPageFactory(driver);
        wait  = new WebDriverWait(driver, 10);

        ediblesPage.addBtnClick();
        ediblesPage.setFoodType("Not deleted yet: Shoes is not food type");
        ediblesPage.setCalories(535);
        ediblesPage.setDateInputField();
        ediblesPage.saveEdible();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void delete_records_test(){
        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                WebElement foodtype = d.findElement(By.xpath("//td[contains(text(),'Not deleted yet: Shoes is not food type')]"));
                WebElement calories = d.findElement(By.xpath("//td[contains(text(),'535')]"));
                return foodtype.isDisplayed() && calories.isDisplayed();
            }
        });


        List<WebElement> rowElements = driver.findElements(By.xpath("//td[contains(text(),'Not deleted yet: Shoes is not food type')]//..//button[@data-ui='delete-btn']"));
        int size = rowElements.size();
        rowElements.get(0).click();

        (wait).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                List<WebElement> elements = d.findElements(By.xpath("//td[contains(text(),'Not deleted yet: Shoes is not food type')]"));
                return elements.size() == (size - 1);
            }
        });
    }
}
