package steps.ui_steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AuthPage;
import pages.RegPage;
import utils.DriverSingleton;

public class RegPageSteps {
    private final RegPage regPage = new RegPage();
    private final String REG_PAGE_URL = "http://localhost:5000/registrPage.html";

    @Given("^I open registration page$")
    public void iOpenRegistrationPage() {
        DriverSingleton.getDriver().get(REG_PAGE_URL);
    }

    @When("^I enter login (.*), password (.*) and confirm password$")
    public void iEnterLoginPasswordAndConfirmPassword(String login, String password) {
        regPage.getLoginInput().sendKeys(login);
        regPage.getPasswordInput().sendKeys(password);
        regPage.getConfirmPasswordInput().sendKeys(password);
        regPage.getSubmit().click();
    }

    @Then("^I got error message$")
    public void iGotErrorMessage() {
        Assert.assertTrue("Error message is not displayed", regPage.getInvalidInput().isDisplayed());
    }
}
