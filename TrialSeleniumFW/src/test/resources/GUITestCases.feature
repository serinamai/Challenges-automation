Feature: Test the search function in OpenWeather page

  @UI
  Scenario: TC001_Search invalid city
    Given I navigate to Open Weather home page
    When I search weather by invalid city vn name
    Then it brought to the result page
    And no data returns

  @UI
  Scenario: TC002_Demo valid city
    Given I navigate to Open Weather home page
    When I search weather by valid city rio name
    Then it brought to the result page
    And search data returns contains text "rio"