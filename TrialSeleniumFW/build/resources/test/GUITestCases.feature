Feature: Test the search function in OpenWeather page

  Scenario: TC_Demo GUI search function
    Given I navigate to default home page
    When I type a text "abc" in search box
    Then I should able to search