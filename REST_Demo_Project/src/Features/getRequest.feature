Feature: Testing basic API testing scenario for GET request

Scenario: Positive test case for GET
Given I am authorized user to execute GET request
When I submit  GET request 
Then I should see response status code "200" for GET request
And I should retrieve json response in csv file
