Feature: Test the search function in OpenWeather page

  @API
  Scenario: TC1_Send request to search weather by city name
    Given I send request to search weather by city name is "London"
    Then Verify the status code is 200
    And Verify the response body is correct as expected
      | name        | London   |
      | id          | 2643743  |
      | sys.country | GB       |
      | weather     | not null |


  @API
  Scenario: TC2_Send request to search weather by zip code
    Given I send request to search weather by zip code is "10700,th"
    Then Verify the status code is 200
    And Verify the response body is correct as expected
      | name        | Bangkok Noi |
      | sys.country | TH          |
      | weather     | not null    |
