Feature: Test the search function in OpenWeather page

  @UI
  Scenario: TC001_Demo GUI search function
    Given I navigate to default home page
    When I type a text "abc" in search box
    Then I should able to search

  @UI
  Scenario: TC002_Demo GUI search function
    Given I navigate to default home page
    When I type a text "abc" in search box
    Then I should able to search