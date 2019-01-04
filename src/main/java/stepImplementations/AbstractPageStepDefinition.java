package stepImplementations;

import org.openqa.selenium.WebDriver;
import utilities.DriverFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractPageStepDefinition {

    protected static WebDriver driver;

    private Properties prop = new Properties();
    private InputStream input = null;
    private static final String FILENAME_PROP = "pom.properties";


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
}
