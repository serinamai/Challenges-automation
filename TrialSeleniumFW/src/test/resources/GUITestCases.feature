Feature: Test the search function in OpenWeather page

  @UI
  Scenario: TC1_Search weather with invalid cities
    Given I navigate to Open Weather home page
    Then no data returns in the result page when I search with invalid city
      | apucnrt |
      | 254315  |
      | #@*#%^^ |
      | test123 |


  @UI
  Scenario: TC2_Search weather with valid city
    Given I navigate to Open Weather home page
    When I search weather by valid city beijing name
    Then it brought to the result page
    And search data returns contains text "beijing"