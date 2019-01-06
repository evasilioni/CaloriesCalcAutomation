package tests;

import org.openqa.selenium.WebDriver;
import pages.CommonPageFactory;
import pages.HomePageFactory;
import pages.LoginPageFactory;
import stepImplementations.AbstractPageStepDefinition;
import utilities.RandomEmailGenerator;
import utilities.UserCreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase extends AbstractPageStepDefinition {

    WebDriver driver;
    LoginPageFactory loginPage;
    CommonPageFactory commons;
    HomePageFactory homePage;

    public void init() {
        driver = getDriver();
        loginPage = new LoginPageFactory(driver);
        commons = new CommonPageFactory(driver);
        homePage = new HomePageFactory(driver);

        String email = RandomEmailGenerator.getEmail();
        UserCreator.createUserIfNotExists("Silioni Evi", email, "Winter2019!");
        AbstractPageStepDefinition.setEmail(email);
        AbstractPageStepDefinition.setPassword("Winter2019!");

        loginPage.navigateToLogin();
        commons.setEmailInputField(AbstractPageStepDefinition.getEmail());
        commons.setPasswordInputField(AbstractPageStepDefinition.getPassword());
        loginPage.clickLogin();

        assertThat(homePage.isNavigatedToHomePage(), equalTo(true));
    }

}
