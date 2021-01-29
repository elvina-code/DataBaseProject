@regression
Feature: Cart item tests
  @cart_item
  Scenario: Add new data to table cart_item

    Given  remove all records from "cart_item" table

    When add new data into the cart_items table
      | id  | quantity | total_price | food_id |
      | 101 | 324      | 38.99       | 23      |
      | 102 | 543      | 49.99       | 24      |
      | 103 | 548      | 30.99       | 25      |
      | 104 | 734      | 86.99       | 26      |
      | 105 | 999      | 35.99       | 27      |

    Then verify that new data has been inserted into cart_item
      | id  | quantity | total_price | food_id |
      | 101 | 324      | 38.99       | 23      |
      | 102 | 543      | 49.99       | 24      |
      | 103 | 548      | 30.99       | 25      |
      | 104 | 734      | 86.99       | 26      |
      | 105 | 999      | 35.99       | 27      |
