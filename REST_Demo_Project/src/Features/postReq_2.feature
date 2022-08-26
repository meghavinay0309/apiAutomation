Feature: POST request by reading csv file and generating POJO class object

Scenario Outline: Test to read csv file and convert to pojo class object
Given I am authorized user for the "<API URL>"
When I read data from "<dataObject>" file
And I have generated pojo class object
And I submit POST request using pojo class
Then I should see Unique ID 
Examples:
|dataObject        |API URL                             |
|csvDataForPojo.txt|https://jsonplaceholder.typicode.com|
