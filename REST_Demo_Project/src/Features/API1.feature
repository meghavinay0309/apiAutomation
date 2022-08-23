Feature: First test case of API automation

  Scenario: Testing adding, updating API request
    Given I am authorized user
    When I submit a request with header and required body in json format
    Then I should see valid response code

