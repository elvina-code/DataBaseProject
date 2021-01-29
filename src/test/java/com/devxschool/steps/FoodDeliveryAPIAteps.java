package com.devxschool.steps;

import com.devxschool.beans.Food_Delivery;
import com.devxschool.utils.db.DataBaseUtils;
import com.devxschool.utils.restapi.RestAPIUtils;
import com.google.gson.Gson;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;


import javax.management.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FoodDeliveryAPIAteps {
    private Gson gson = new Gson();
    private String jsonBody; // json format use post (body)
    private Response response;
    private List<Food_Delivery> expectedFoodDelivery;
    private String QUERY;
    private List <Map<String, Object>> databaseResult;


    // (?: space field, nospace field for dynamic)
    @Given("^user registers to food delivery app with(?: the following fields| empty username| an existing username| null username| empty fullname| null fullname|out password| null password):$")

    public void user_registers_to_food_delivery_app_with_the_following_fields(List<Food_Delivery> foods) throws Throwable {


        // to make dynamic we use loop
        for (Food_Delivery food : foods) {
            if(food.getUsername().equals("null")){
                food.setUsername(null);
            }else if (food.getFullName().equals("null")){
                food.setFullName(null);
            }else if(food.getPassword().equals("null")){
                food.setPassword(null);
            }

            jsonBody = gson.toJson(food); // serilization
            response = RestAPIUtils.requestSpecification()
                    .body(jsonBody)
                    .post("/user/registration");
//                    .then()
//                    .extract()
//                    .response()
//                    .prettyPrint();
        }
        expectedFoodDelivery = foods;

    }

    @Then("^verify that status code is (\\d+)$")
    public void verify_that_status_code_is(int statusCode) throws Throwable {
        response.then().assertThat().statusCode(statusCode);

    }

    //    @Then("^verify that response message is \"([^\"]*)\"$")
//    public void verify_that_response_message_is(String message) throws Throwable {
//        response.then().assertThat().body("status", Matchers.equalTo(message));
//        //response.then().assertThat().extract().response().path("find(it.status='%s'", message); //groovy
//
//    }
    @Then("^verify that response message is \"([^\"]*)\" in \"([^\"]*)\"$")
    public void verify_that_response_message_is_in(String message, String field) throws Throwable {
        response.then().assertThat().body(field, Matchers.equalTo(message));
    }

    @Then("^verify that user information successfully saved in DB$")
    public void verify_that_user_information_successfully_saved_in_DB() throws Throwable {
        for (int i = 0; i < expectedFoodDelivery.size(); i++) {

            QUERY = "select b.username, b.password, a.full_name as fullName, a.address, a.city, a.state, a.zip, a.phone from food_delivery_db.user_profile a join food_delivery_db.custom_user b\n" +
                    "on b.user_profile_id = a.id where b.username = '" + expectedFoodDelivery.get(i).getUsername() + "';";

            databaseResult = DataBaseUtils.executeQuery(QUERY);
            List<Food_Delivery> actual = DataBaseUtils.executeQueryToBean(Food_Delivery.class, QUERY);

            //expectedFoodDelivery.get(i).setPassword(response.then().extract().path("userInfo.password"));
            actual.get(i).setPassword(expectedFoodDelivery.get(i).getPassword());

            assertEquals("Failed: Mismatch in lists size", expectedFoodDelivery.size(), actual.size());
            assertEquals("Failed: Mismatch in data", expectedFoodDelivery.get(i), actual.get(i));
        }

    }
    @Then("^remove record with username$")
    public void remove_record_with_username(List<String> username) throws Throwable {
        for (int i = 0; i < username.size(); i++) {
            QUERY = "DELETE FROM food_delivery_db.custom_user WHERE username = '" + username.get(i) + "';";

            DataBaseUtils.executeQuery(QUERY);
            QUERY = "select b.username, b.password, a.full_name as fullName, a.address, a.city, a.state, a.zip, a.phone from food_delivery_db.user_profile a join food_delivery_db.custom_user b on b.user_profile_id = a.id where b.username = '" + username.get(i) + "';";

            databaseResult = DataBaseUtils.executeQuery(QUERY);

            List<Food_Delivery> actual = DataBaseUtils.executeQueryToBean(Food_Delivery.class, QUERY);

            Assert.assertTrue(actual.isEmpty());
        }
    }


    @Then("^verify that user information is not saved in DB$")
    public void verifyThatUserInformationIsNotSavedInDB() throws SQLException {
        for (int i = 0; i < expectedFoodDelivery.size(); i++) {
            if (null == expectedFoodDelivery.get(i).getUsername()) {
                QUERY = "select b.username, b.password, a.full_name as fullName, a.address, a.city, a.state, a.zip, a.phone from " +
                        "food_delivery_db.user_profile a join food_delivery_db.custom_user b on b.user_profile_id = " +
                        "a.id where a.full_name = '" + expectedFoodDelivery.get(i).getFullName() + "' and " +
                        "a.address = '" + expectedFoodDelivery.get(i).getAddress() + "' and " +
                        "a.phone = '" + expectedFoodDelivery.get(i).getPhone() + "';";
            } else {
                QUERY = "select b.username, b.password, a.full_name as fullName, a.address, a.city, a.state, a.zip, a.phone from " +
                        "food_delivery_db.user_profile a join food_delivery_db.custom_user b on b.user_profile_id = " +
                        "a.id where b.username = '" + expectedFoodDelivery.get(i).getUsername() + "';";
            }
            databaseResult = DataBaseUtils.executeQuery(QUERY);
            Assert.assertTrue(databaseResult.isEmpty());
        }
    }

}
