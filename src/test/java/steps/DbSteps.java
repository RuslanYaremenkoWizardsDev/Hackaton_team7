package steps;

import io.cucumber.java.en.And;
import org.junit.Assert;
import utils.database.CRUD;

public class DbSteps {
    private CRUD crud = new CRUD();

    @And("^I (.*) registered with login = (.*)$")
    public void iCheckRegistrationSuccess(String success, String login) {
        if(success.equals("was")) {
            Assert.assertTrue("User was not registered",
                    crud.deleteUser(login));
        } else if(success.equals("was not")) {
            Assert.assertFalse("User was registered",
                    crud.deleteUser(login));
        }
    }
}
