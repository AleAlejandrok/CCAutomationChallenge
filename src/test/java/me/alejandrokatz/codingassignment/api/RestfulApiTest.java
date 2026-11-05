package me.alejandrokatz.codingassignment.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ExpectedToFail;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RestfulApiTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://api.restful-api.dev/";
    }

    @Test
    @DisplayName("Validates that id values are unique in the list of returned objects")
    public void idValuesAreUnique() {
        List<String> ids = given().accept(ContentType.JSON)
                .when().get("/objects")
                .then().statusCode(200)
                .extract().jsonPath().getList("id");

        assertFalse(ids.isEmpty(), "Returned ID list should not be empty");
        Set<String> uniqueIds = new HashSet<>(ids);
        assertEquals(ids.size(), uniqueIds.size(), "All returned IDs should be unique");
    }

    @Test
    @DisplayName("Validates that each name value contains Apple")
    @ExpectedToFail("This endpoint gives non-apple products")
    public void allProductNamesContainApple() {
        List<String> names = given().accept(ContentType.JSON)
                .when().get("/objects")
                .then().statusCode(200)
                .extract().jsonPath().getList("name");

        assertThat("Names missing 'Apple'", names, everyItem(containsString("Apple")));
    }
}
