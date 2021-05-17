package steps.ui_steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StatisticsPageSteps {

    @When("^I go to (.*) page$")
    public void iCheckPage(String pageName) {
       if(pageName.equals("tournament statistics")) {

       } else if(pageName.equals("tournament")) {

       } else if(pageName.equals("create tournament")) {

       }
    }
}
