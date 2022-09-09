Feature: Validate the GET request for sample API, Football-data.org

  @getRequest
  Scenario Outline: Validate the status code returned for the GET request on the given end point
    Given We set up test environment for "positive" test for "<API>"
    And We have header data as "<key>" and "<value>"
    When We make Get request to the API at endpoint "<EndPoint Resource>"
    Then We should see that the response should contain "<name>"
    And The response status code code must be "200"

    Examples: 
      | API                          | EndPoint Resource | name                 |key         |value|
      | http://api.football-data.org | /v3/teams/66      | Manchester United FC |X-Auth-Token|0ae16a7fab1043c78806fcca25c434fb|
