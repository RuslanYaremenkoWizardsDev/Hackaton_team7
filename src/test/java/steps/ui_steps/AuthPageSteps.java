package steps.ui_steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AuthPage;
import utils.DriverSingleton;

public class AuthPageSteps {
    private final AuthPage authPage = new AuthPage();
    private final String AUTH_PAGE_URL = "http://localhost:5000/index.html";
    private final String ADMIN_LOGIN = "admin";
    private final String ADMIN_PASSWORD = "admin_password";

    @Given("^I open authorization page$")
    public void iOpenAuthorizationPage() {
        DriverSingleton.getDriver().get(AUTH_PAGE_URL);
    }

    @Given("^I log in as admin$")
    public void iLogInAsAdmin(String login, String password) {
        authPage.getLoginInput().sendKeys(ADMIN_LOGIN);
        authPage.getPasswordInput().sendKeys(ADMIN_PASSWORD);
        authPage.getSubmit().click();
    }

    @When("^I enter login (.*) and password (.*)$")
    public void iEnterLoginAndPassword(String login, String password) {
        WebDriverWait wait = new WebDriverWait(DriverSingleton.getDriver(), 20);
        wait.until(ExpectedConditions.visibilityOf(authPage.getLoginInput())).sendKeys(login);
        authPage.getPasswordInput().sendKeys(password);
        authPage.getSubmit().click();
    }


    @Then("^I redirected on the authorization page$")
    public void iRedirectedOnTheAuthorizationPage() {
        Assert.assertEquals("I was not redirected after registration",
                AUTH_PAGE_URL,
                DriverSingleton.getDriver().getCurrentUrl());
    }

    @Then("^I was not authorized with login (.*)$")
    public void iWasNotAuthorizedWithLogin(String login) {
        Assert.assertTrue("Error message is not displayed", authPage.getInvalidInput().isDisplayed());
    }
}
