package com.devxschool.utils.restapi;

import com.devxschool.utils.config.Config;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;

public class RestAPIUtils {
    // request specification
    //   1st - way
    //   private static RequestSpecification requestSpecification = new RequestSpecBuilder()
//            .setBaseUri(TrelloEndPoints.URL)
//            .setContentType(ContentType.JSON)
//            .build();
//
//    public static void main(String[] args) {
//        given().spec(requestSpecification);
//    }

    /**
     * TODO:
     * Create a method that returns RequestSpecification
     * with the following headers: AcceptType,ContentType
     *
     * @return RequestSpecification
     */
    public static RequestSpecification requestSpecification() {
        // if not static RestAssured.given() to avoidrepeatation
        return given()
                .baseUri(Config.getPropertiesValue("food_delivery_main_url"))
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON);
    }
}
