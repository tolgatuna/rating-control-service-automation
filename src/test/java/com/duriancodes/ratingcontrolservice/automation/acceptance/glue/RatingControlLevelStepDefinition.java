package com.duriancodes.ratingcontrolservice.automation.acceptance.glue;

import com.duriancodes.ratingcontrolservice.automation.RatingControlServiceAutomationApplication;
import com.duriancodes.ratingcontrolservice.automation.config.RatingControlServiceConfig;
import io.cucumber.java8.En;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest(classes = RatingControlServiceAutomationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RatingControlLevelStepDefinition implements En {

    private String customerSetControlLevel;
    private Response response;

    @Autowired
    private RatingControlServiceConfig serviceConfig;

    public RatingControlLevelStepDefinition() {
        Given("I am customer who have set rating control level (.*)$", (String customerSetControlLevel) -> {
            this.customerSetControlLevel = customerSetControlLevel;
        });

        When("I request to read book (.*) which is (.*)$", (String bookId, String ignored) -> {
            response = given()
                    .headers(generateHeader())
                    .when()
                    .get(serviceConfig.getRatingServiceEndPoint() + customerSetControlLevel + "/" + bookId);
        });

        Then("I get decision to read the book$", () -> {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .body(is(Boolean.TRUE.toString()));
        });

        Then("^I get decision not to read the book$", () -> {
            response.then()
                    .assertThat()
                    .statusCode(200)
                    .body(is(Boolean.FALSE.toString()));
        });
    }

    private Headers generateHeader() {
        List<Header> headers = new ArrayList<>();
        headers.add(new Header("Accept", "application/json"));
        return new Headers(headers);
    }
}
