package stepImplementations;

import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import pages.HomePageFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class HomePageStepDefinition extends AbstractPageStepDefinition{
    HomePageFactory homePage;

    @Then("^Navigate to Home Page$")
    public void navigateToHomePage() {
        homePage = new HomePageFactory(driver);

        assertThat(homePage.isNavigatedToHomePage(), equalTo(true));
    }

    @Then("^User should be able to Logout$")
    public void userShouldNotBeAbleToLogout() {
        homePage.logout();
        assertThat(driver.findElement(By.xpath("//h2[contains(text(),'Welcome to Calc')]")).isDisplayed(),equalTo(true));
    }



}
