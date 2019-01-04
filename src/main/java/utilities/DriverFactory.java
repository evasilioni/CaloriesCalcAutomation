package utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.NoSuchObjectException;
import java.util.Properties;

public class DriverFactory {
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";

    public static WebDriver open(String browserType){
        Properties properties = new Properties();
        try(InputStream inputStream = DriverFactory.class.getClassLoader().getResourceAsStream("config.properties")){
            properties.load(inputStream);
            // 1 . Create WebDriver
            if (browserType.equalsIgnoreCase(FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", properties.getProperty(FIREFOX));
                return new FirefoxDriver();
            } else if(browserType.equalsIgnoreCase(IE)){
                System.setProperty("webdriver.ie.driver", properties.getProperty(IE));
                return new InternetExplorerDriver();
            } else {
                System.setProperty("webdriver.chrome.driver", properties.getProperty(CHROME));
                return new ChromeDriver();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
