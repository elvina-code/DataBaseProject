Feature: User registration
  # As a User I want to be able to register to the app, so I can use the food delivery service
  @sc
  Scenario: User should submit the following fields in order to successfully register to FoodDelivery app

    Given user registers to food delivery app with the following fields:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | John2689 | Test2w3  | John Doy | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 200
    Then verify that response message is "User registration successful" in "status"
    Then verify that user information successfully saved in DB

  Scenario: Registration with exiting username

    Given user registers to food delivery app with an existing username:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | John2689 | Test2w3  | John Doy | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then verify that response message is "Username unavailable. Please choose another one" in "errorMessage"
    Then verify that user information successfully saved in DB
    Then remove record with username
      | John2689   |



  Scenario: Registration with empty username
    Given user registers to food delivery app with empty username:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      |          | Test2w3  | John Doy | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then verify that response message is "Username cannot be null or empty" in "errorMessage"
    Then verify that user information is not saved in DB



  Scenario: Registration with null username
    Given user registers to food delivery app with null username:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | null     | Test2w3  | John Doy | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then verify that response message is "Username cannot be null or empty" in "errorMessage"
    Then verify that user information is not saved in DB




  Scenario:Registration with empty fullname
    Given user registers to food delivery app with empty fullname:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | John2689 | Test2w3  |          | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then verify that response message is "Fullname cannot be null or empty" in "errorMessage"
    Then verify that user information is not saved in DB

  Scenario: Registration with null fullname
    Given user registers to food delivery app with null fullname:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | John2689 | Test2w3  |   null   | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then verify that response message is "Fullname cannot be null or empty" in "errorMessage"
    Then verify that user information is not saved in DB

  @bug
  Scenario: Registration without password
    Given user registers to food delivery app without password:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | John2689 |          | John Doy | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 400
    Then verify that response message is "Password cannot be null or empty" in "message"
    Then verify that user information is not saved in DB



  Scenario:Registration with null password
    Given user registers to food delivery app with null password:
      | username | password | fullName | address     | city    | state | zip   | phone     |
      | John2689 | null     | John Doy | 123 main st | Chicago | IL    | 60625 | 112131321 |
    Then verify that status code is 500
    Then verify that response message is "Password cannot be null or empty" in "message"
    Then verify that user information is not saved in DB



