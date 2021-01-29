@regression
Feature: Orders Table tests

  Scenario: Add new data to Orders table

    Given  remove all records from "orders" table

    When add new data into the orders table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |

    Then verify that new data has been inserted into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |


  Scenario: Update order status in orders table based on id
    Given  remove all records from "orders" table

    When add new data into the orders table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |

    Then verify that new data has been inserted into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |



    Then update order status in orders table based on id
      | id | order_status |
      | 1  | 1            |
      | 2  | 2            |
      | 3  | 2            |
      | 4  | 1            |
      | 5  | 1            |

    Then verify that data has been updated into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 1            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 2            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 2            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 1            | 2020-01-14 00:00:00 | 101            |



  Scenario: Update one order status in orders table based on id
    Given  remove all records from "orders" table

    When add new data into the orders table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |

    Then verify that new data has been inserted into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |



    Then update order status to "1" in orders table based on id "1"


    Then verify that data has been updated into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 1            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |





  @order
  Scenario: Remove orders record in orders table that was placed after “2020-01-12

    Given  remove all records from "orders" table

    When add new data into the orders table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |

    Then verify that new data has been inserted into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |



    And remove all orders records placed after "2020-01-12"


    Then verify that data has been removed into order table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-10 00:00:00 | 0            | 2020-01-10 00:00:00 | 101            |
      | 2  | 2020-01-11 00:00:00 | 1            | 2020-01-11 00:00:00 | 102            |
      | 3  | 2020-01-12 00:00:00 | 1            | 2020-01-12 00:00:00 | 101            |
      | 4  | 2020-01-13 00:00:00 | 1            | 2020-01-13 00:00:00 | 102            |
      | 5  | 2020-01-14 00:00:00 | 0            | 2020-01-14 00:00:00 | 101            |



