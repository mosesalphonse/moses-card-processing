
package com.moses.ccp.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;

/*
* <h1>Card Process App - Test</h1>
* CreditCard Test Cases for Credit Card Processing API
*
* @author  Moses |Alphonse
* @version 1.0
* @since   2022-04-18 
 */
@QuarkusTest
class CreditCardResourceTest {

    /**
     * This testcase verifies the CreditCard REST API
     * 
     */
    @Test
    public void testListAllCards() {

        given()
                .when().get("/cards")
                .then()
                .statusCode(200)
                .body(
                        containsString("Alice"),
                        containsString("Bob"));
        given()
                .when()
                .body("{\"name\": \"Moses\",\"cardNumber\": 1111222233334444,\"limit\": 1000}")
                .contentType("application/json")
                .post("/cards")
                .then()
                .statusCode(201);
        given()
                .when()
                .body("{\"name\": \"Alex\",\"cardNumber\": 111122223333467543,\"limit\": 1000}")
                .contentType("application/json")
                .post("/cards")
                .then()
                .statusCode(400);

    }

}
