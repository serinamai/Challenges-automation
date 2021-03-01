Feature: Test the search function in OpenWeather page

  @API
  Scenario: TC001_Demo API search function
    Given I send request to search weather of "London"
    Then Verify the status code is 200
    And Verify the response body is correct as expected
      | name        | London  |
      | id          | 2643743 |
      | sys.country | GB      |
