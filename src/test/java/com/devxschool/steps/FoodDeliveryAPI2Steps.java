package com.devxschool.steps;


import com.devxschool.beans.Food;
import com.devxschool.pojo.FoodRequest;
import com.devxschool.pojo.FoodResponse;
import com.devxschool.utils.db.DataBaseUtils;
import com.devxschool.utils.restapi.RestAPIUtils;
import com.google.gson.Gson;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.List;


public class FoodDeliveryAPI2Steps {

    private Gson gson = new Gson();
    private Response response;
    List<FoodRequest> expectedFoods;
    private String QUERY;
    int numberOfFoodInCache ;
    @Given("^reset cached food in Food Delivery API$")
    public void reset_cached_food_in_Food_Delivery_API() throws Throwable {
        response = RestAPIUtils.requestSpecification()
                .post("food/resetcache");
    }
    @Given("^add new food to FoodDelivery with the following fields$")
    public void add_new_food_to_FoodDelivery_with_the_following_fields(List<FoodRequest> foodRequests) throws Throwable {
        String foodRequestJson = gson.toJson(foodRequests.get(0));
        response = RestAPIUtils.requestSpecification()
                .body(foodRequestJson)
                .post("/food/cache/add");
        System.out.println(foodRequestJson);
        expectedFoods = foodRequests;
    }
    @Then("^verify that food has been successfully added$")
    public void verify_that_food_has_been_successfully_added(List<FoodRequest> expectedFood) throws Throwable {
        FoodResponse actualFood = gson.fromJson(response.body().asString(), FoodResponse.class);
        System.out.println("actual " + actualFood.getFoodCached().get(0).getDescription());
        System.out.println("expected " + expectedFood.get(0).getDescription());
        Assert.assertEquals(expectedFood.get(0).getDescription(), actualFood.getFoodCached().get(0).getDescription());
        numberOfFoodInCache = expectedFood.size();
    }
    @Then("^verify that status code is (\\d+)\\.$")
    public void verify_that_status_code_is(int statusCode) throws Throwable {
        response.then().assertThat().statusCode(statusCode);
    }
    @Then("^verify response error message \"([^\"]*)\"$")
    public void verify_response_error_message(String expectedResponseMSG) throws Throwable {
        if (response.body().jsonPath().equals("errorMessage")) {
            Assert.assertEquals(expectedResponseMSG, response.body().jsonPath().getString("errorMessage"));
        }
        if (response.body().jsonPath().equals("error")) {
            Assert.assertEquals(expectedResponseMSG, response.body().jsonPath().getString("error"));
        }
    }
    @Given("^user updates \"([^\"]*)\" for food item with description \"([^\"]*)\"$")
    public void user_updates_for_food_item_with_description(String field, String itemDescription, List<FoodRequest> expectedFoodRequest) throws Throwable {
        String foodRequestJson = gson.toJson(expectedFoodRequest.get(0));
        response = RestAPIUtils
                .requestSpecification()
                .queryParam("name", itemDescription)
                .queryParam("field", field)
                .body(foodRequestJson)
                .when()
                .request("PUT", "/food/cache/update");
        System.out.println(foodRequestJson);
    }
    //
    @When("^user updates \"([^\"]*)\" for food with the following fields$")
    public void user_updates_for_food_with_the_following_fields(String field, List<FoodRequest> foodsRequests) throws Throwable {
        String foodRequestJson = gson.toJson(foodsRequests.get(0));
        response = RestAPIUtils
                .requestSpecification()
                .queryParam("name", foodsRequests.get(0).getName())
                .queryParam("field", field)
                .body(foodRequestJson)
                .when()
                .request("PUT", "/food/cache/update");
        System.out.println(foodRequestJson);
    }
    @Then("^verify that \"([^\"]*)\" have been updated$")
    public void verify_that_have_been_updated(String field, List<FoodRequest> expectedFoods) throws Throwable {
        FoodResponse actualFood = gson.fromJson(response.body().asString(), FoodResponse.class);
        System.out.println("actual " + actualFood.getFoodCached().get(0).getPrice());
        Assert.assertEquals(expectedFoods.get(0).getPrice(), actualFood.getFoodCached().get(0).getPrice());
    }
    @Given("^user list all food in cache$")
    public void user_list_all_food_in_cache() throws Throwable {
        response = RestAPIUtils.requestSpecification()
                .and()
                .get("food/cache/list");
    }
    @Then("^verify that response contains all cached foods$")
    public void verify_that_response_contains_all_cached_foods() throws Throwable {
        List<FoodRequest> cachedFoods = gson.fromJson(response.asString(), FoodResponse.class).getFoodCached();
        System.out.println(cachedFoods);
        response.then().assertThat().body("numberOfFoodsInCache", Matchers.equalTo(cachedFoods.size()));
//numberOfFoodInCache = cachedFoods.size();
        System.out.println(numberOfFoodInCache);
    }
    @When("^user saves all cached food$")
    public void user_saves_all_cached_food() throws Throwable {
        response = RestAPIUtils.requestSpecification()
                .and()
                .post("food/commit");
    }
    @Then("^verify number of saved food$")
    public void verify_number_of_saved_food() throws Throwable {
        response.then().assertThat().body("numberOfFoodsSaved" , Matchers.equalTo(numberOfFoodInCache));
    }
    @Then("^verify that all food information is saved in DB$")
    public void verify_that_all_food_information_is_saved_in_DB() throws Throwable {
        QUERY ="SELECT * FROM food ORDER BY id DESC limit 1;";
        List<Food> dbResult = DataBaseUtils.executeQueryToBean(Food.class,QUERY);
        System.out.println("a " + dbResult.toString());
        for(Food dbFood : dbResult){
            System.out.println("e " + expectedFoods.toString());
            System.out.println("a " + dbResult.toString());
            Assert.assertTrue(dbResult.get(0).toString().contains(expectedFoods.get(0).getDescription()));
        }
    }
}


