package com.evereats.fooder.integration.kitchen;

import com.evereats.fooder.domain.model.Kitchen;
import com.evereats.fooder.domain.repository.KitchenRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import com.evereats.fooder.util.DatabaseCleaner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class KitchenControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private KitchenRepository kitchenRepository;

    @BeforeEach
    public void beforeEach() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";
        databaseCleaner.clearTables();
        prepareDatabase();
    }

    private void prepareDatabase() {
        var chinese = new Kitchen();
        chinese.setName("Turkish");
        kitchenRepository.save(chinese);
        var turkish = new Kitchen();
        turkish.setName("Chinese");
        kitchenRepository.save(turkish);
    }

    @Test
    @DisplayName("Should return status 200")
    public void get_shouldReturnStatus200_whenSuccessful() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should create a Kitchen and return status 201")
    public void post_shouldCreateKitchenAndReturnStatus201_whenSuccessful() {
        RestAssured.given()
                .body("{ \"name\": \"Turkish\" }").contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when().post()
                .then().statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should return 02 Kitchens and status 200")
    public void get_shouldReturnTwoKitchenAndStatus200_whenSuccessful() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when().get()
                .then().body("", Matchers.hasSize(2));
    }
}
