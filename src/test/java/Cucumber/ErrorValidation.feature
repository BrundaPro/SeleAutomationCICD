@tag
Feature: Error validation

@ErrorValidation
Scenario Outline:to check error validation for wrong username or password
Given I Landed on Ecommerce Page
When Logged in with username <userName> and password <passWord>
Then "Incorrect email or password." message is displayed
 
Examples:
|userName|passWord|
|bhamchandu@gmail.com|Udemy@123|