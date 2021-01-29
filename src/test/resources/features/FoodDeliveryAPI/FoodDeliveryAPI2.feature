@food
Feature: Food functionalities
  Background:
#  Given reset cached food in Food Delivery API

  Scenario: Adding new food - happy path
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 7.00  | Green Tea | Beverages |
    Then verify that status code is 200.
    Then verify that food has been successfully added
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 7.00  | Green Tea | Beverages |
  Scenario: Adding new food without image url - negative
    Given add new food to FoodDelivery with the following fields
      | description | price | name   | foodType  |
      | Wine        | 20.00 | Merlot | Beverages |
    Then verify that status code is 403.
    Then verify response error message "Invalid request - Food image url cannot be null or empty."
#expected code 403 ; actual 500
  @bug
  Scenario: adding new food without price
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | name   | foodType  |
      | Wine        | https:foods.com | Merlot | Beverages |
    Then verify that status code is 403.
    Then verify response error message "Invalid request - Food price cannot be null or empty."
  Scenario: adding new food without food name
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | foodType  |
      | Wine        | https:foods.com | 20.00 | Beverages |
    Then verify that status code is 403.
    Then verify response error message "Invalid request - Food name cannot be null or empty."
  Scenario: adding new food with null food type
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | name   | price |
      | Wine        | https:foods.com | Merlot | 20.00 |
    Then verify that status code is 403.
    Then verify response error message "Invalid request - Food type cannot be null or empty."
  Scenario: adding new food with invalid food type
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | name   | price | foodType |
      | Wine        | https:foods.com | Merlot | 20.00 | Soups    |
    Then verify that status code is 400.
    Then verify response error message "Bad Request"
  Scenario: list all newly created foods
    Given user list all food in cache
    Then verify that status code is 200.
    Then verify that response contains all cached foods
  @update
  Scenario: update price on food item
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 7.00  | Green Tea | Beverages |
    Then verify that status code is 200.
    Then verify that food has been successfully added
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 7.00  | Green Tea | Beverages |
    When user updates "price" for food with the following fields
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 100.00  | Green Tea | Beverages |
    Then verify that status code is 200.
    Then verify that "price" have been updated
      | description | imageUrl        | price  | name      | foodType  |
      | Tea         | https:foods.com | 100.00 | Green Tea | Beverages |
  Scenario: update price over the max limit
    Given user updates "price" for food item with description "Merlot"
      | description | imageUrl        | price  | name   | foodType  |
      | Wine        | https:foods.com | 125.50 | Merlot | Beverages |
    Then verify that status code is 403.
    Then verify response error message "Invalid request - Food price should be kept less than 125"
  Scenario: save a list of food from cache in DataBase
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 7.00  | Green Tea | Beverages |
    Then verify that status code is 200.
    Then verify that food has been successfully added
      | description | imageUrl        | price | name      | foodType  |
      | Tea         | https:foods.com | 7.00  | Green Tea | Beverages |
    When user saves all cached food
    Then verify that status code is 200.
    Then verify number of saved food
    Then verify response error message "Food Cache is committed to db"
    Then verify that all food information is saved in DB
