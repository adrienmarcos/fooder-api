package com.evereats.fooder.integration.kitchen;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KitchenControllerIT {

    @LocalServerPort
    private int port;

    @Test
    @DisplayName("Should test the Api at url /kitchens using GET method")
    public void get_shouldReturnStatus200_whenSuccessful() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.given().basePath("/kitchens").port(port).accept(ContentType.JSON)
                .when().get()
                .then().statusCode(HttpStatus.OK.value());
    }
}
