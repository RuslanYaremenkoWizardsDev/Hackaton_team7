package steps.ui_steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import utils.DriverSingleton;

public class MainPageSteps {
    private final String MAIN_PAGE_URL = "http://localhost:5000/mainPage.html";

    @Then("^I redirected on the main page$")
    public void iRedirectedOnTheMainPage() {
        Assert.assertEquals("I was not redirected after registration",
                MAIN_PAGE_URL,
                DriverSingleton.getDriver().getCurrentUrl());
        DriverSingleton.getDriver().quit();
    }
}
