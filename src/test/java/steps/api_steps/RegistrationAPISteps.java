package steps.api_steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class RegistrationAPISteps {
    private Response registrationResponse;
    private final String BASE_URL = "https://hackatonteam7.herokuapp.com";
    private final String BASE_PATH = "/reg";

    @When("^I send request for registration with login = (.*), email = (.*) and password = (.*)$")
    public void iSendRequestForRegistration(String login, String email, String password) {
        registrationResponse = given()
                .baseUri(BASE_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .body("{\"login\": \""+ login +"\",\"email\": \""+ email +"\",\"email\": \""+ password +"\"}")
                .when()
                .post();
    }

    @Then("^I check registration status code = (.*)")
    public void iCheckStatusCode(String statusCode) {
        Assert.assertEquals("Wrong status code", Integer.parseInt(statusCode), registrationResponse.getStatusCode());
    }

    @When("^I send empty request for registration$")
    public void iSendEmptyRequestForRegistration() {
        registrationResponse = given()
                .baseUri(BASE_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post();
    }
}
