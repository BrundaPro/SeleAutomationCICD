@tag
Feature: Purchase the order from Ecommerce Webpage

Background:
Given I Landed on Ecommerce Page

@Regression
Scenario Outline: Positive Test of Submitting the Order
Given Logged in with username <userName> and password <passWord>
When I add product <productName> to cart
And Checkout <productName> and sumbit the order
Then "THANK YOU FOR ORDER." message is displayed on ConfirmationPage

Examples:
|  userName            |passWord    | productName    |
|bhamchandu@gmail.com |Udemy@123	|ADIDAS ORIGINAL|