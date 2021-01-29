@regression
Feature: Food Table tests

  Scenario: Add new data to Food table

    Given  remove all records from "food" table

    When add new data into the food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |

    Then verify that new data has been inserted into food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |



  Scenario: Update price in food table based on id

    Given remove all records from "food" table

    When add new data into the food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |

    Then verify that new data has been inserted into food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |

    Then update price to "4.99" into the food table id "1"

    And verify that data has been updated into food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 4.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |


  @food
  Scenario: Remove food record in food table based on id
    Given remove all records from "food" table

    When add new data into the food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |

    Then verify that new data has been inserted into food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |
      | 2  | vegetable   | 24        | www.steamless.com | potato | 2.99  |
      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |

    Then remove record with id "2"

    And verify that data has been removed into food table
      | id | description | food_type | image_url         | name   | price |
      | 1  | fruit       | 23        | www.steamless.com | banana | 3.99  |

      | 3  | grain       | 25        | www.steamless.com | bread  | 1.99  |
      | 4  | nut         | 26        | www.steamless.com | almond | 4.99  |

