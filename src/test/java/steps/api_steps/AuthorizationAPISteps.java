package steps.api_steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.AuthResponseModel;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class AuthorizationAPISteps {
    private final String BASE_URL = "https://hackatonteam7.herokuapp.com";
    private final String BASE_PATH = "/auth";
    private final String ADMIN_LOGIN = "admin";
    private final String ADMIN_PASSWORD = "admin_password";
    private Response logInResponse;

    @Given("^I send request for authorization as (.*) with login = (.*) and password = (.*)$")
    public void iLogIn(String role, String login, String password) {
        if(role.equals("admin")) {
            logInResponse = given()
                    .baseUri(BASE_URL)
                    .basePath(BASE_PATH)
                    .contentType(ContentType.JSON)
                    .body("{ \"login\" : \"" + ADMIN_LOGIN + "\", \"password\" : \"" + ADMIN_PASSWORD + "\" }")
                    .when()
                    .post();
        } else if(role.equals("user")) {
            logInResponse = given()
                    .baseUri(BASE_URL)
                    .basePath(BASE_PATH)
                    .contentType(ContentType.JSON)
                    .body("{ \"login\" : \"" + login + "\", \"password\" : \"" + password + "\" }")
                    .when()
                    .post();
        } else {
            logInResponse = given()
                    .baseUri(BASE_URL)
                    .contentType(ContentType.JSON)
                    .when()
                    .get("")
                    .thenReturn();
        }
    }

//    @Then("^I check my role is (.*)$")
//    public void iCheckMyRoleAndStatusCode(String role) {
//        AuthResponseModel authResponseModel = logInResponse.as(AuthResponseModel.class);
//        Assert.assertEquals("Wrong role", role, authResponseModel.getRole());
//    }

    @Then("^I check authorization status code = (.*)")
    public void iCheckStatusCode(String statusCode) {
        Assert.assertEquals("Wrong status code", Integer.parseInt(statusCode), logInResponse.getStatusCode());
    }

    @When("^I send empty request$")
    public void iSendEmptyRequest() {
        logInResponse = given()
                .baseUri(BASE_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .body("")
                .when()
                .post();
    }
}
