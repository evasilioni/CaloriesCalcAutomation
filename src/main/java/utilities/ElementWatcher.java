package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementWatcher {
    public static boolean isElementPresent(By locatorKey, WebDriver driver) {
        try {
            driver.findElement(locatorKey);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static boolean isElementVisible(String cssLocator, WebDriver driver) {
        return driver.findElement(By.cssSelector(cssLocator)).isDisplayed();
    }
}
