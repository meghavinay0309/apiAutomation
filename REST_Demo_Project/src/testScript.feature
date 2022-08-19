Feature: Testing basic API testing scenario

Scenario Outline: Positive test case
Given I am authorized user
When I read input from "<dataObject>" 
And I submit POST request
Then I should see response status code "200"
Examples:
|dataObject|
|book.txt|


