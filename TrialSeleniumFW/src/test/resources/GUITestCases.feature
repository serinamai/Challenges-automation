Feature: Test the search function in OpenWeather page

  @UI
  Scenario: TC001_Search invalid city
    Given I navigate to Open Weather home page
    When I search weather by invalid city name "vn"
    Then it brought to the result page and no data returns

#  @UI
#  Scenario: TC002_Demo GUI search function
#    Given I navigate to default home page
#    When I type a text "abc" in search box
#    Then I should able to search