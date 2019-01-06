package stepImplementations;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AbstractPageStepDefinition {

    protected static WebDriver driver;

    private Properties prop = new Properties();
    private InputStream input = null;
    private static final String FILENAME_PROP = "pom.properties";

    private static String password;
    private static String email;
    protected static String EMAIL;

    private void init() {
        try {
            input = getClass().getClassLoader().getResourceAsStream(FILENAME_PROP);
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally{
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected WebDriver getDriver() {
        init();

        driver = DriverFactory.open("chrome");
        driver.get(prop.getProperty("site.url"));
        driver.manage().window().maximize();

        return driver;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        AbstractPageStepDefinition.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        AbstractPageStepDefinition.email = email;
    }
}
