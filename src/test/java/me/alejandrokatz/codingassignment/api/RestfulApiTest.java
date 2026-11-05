package me.alejandrokatz.codingassignment.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ExpectedToFail;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

public class RestfulApiTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev/";
    }

    @Test
    @ExpectedToFail("This endpoint gives non-apple products")
    public void allProductNamesContainApple() {
        List<String> names = given().accept(ContentType.JSON)
                .when().get("/objects")
                .then().statusCode(200)
                .extract().jsonPath().getList("name");

        assertThat("Names missing 'Apple'", names, everyItem(containsString("Apple")));
    }
}
