#Each feature file contains one feature
#  Feature files use Gherkin language - business language
Feature: New users should be able to create an account giving name, email and password

  Background: Common login steps
    Given User need to be on Calories-Calc login page
    When User clicks on register
    Then Navigate to Register Page

#  Scenario: Create an account giving name, email and password
#    And User enters username
#    And User enters email
#    And User enters password
#    And User enters confirm password
#    And User clicks Join
#    Then Navigate to Home Page
#
#  Scenario: Create an account giving only email and password
#    And User enters email
#    And User enters password
#    And User enters confirm password
#    And User clicks Join
#    Then User should not be able to Join
#
#  Scenario: Create an account giving only name and password
#    And User enters username
#    And User enters password
#    And User enters confirm password
#    And User clicks Join
#    Then User should not be able to Join
#
#  Scenario: Create an account giving wrong email format
#    And User enters username
#    And User enters wrong email format by giving "joedoeexample.com"
#    And User enters password
#    And User enters confirm password
#    And User clicks Join
#    Then User should not be able to Join
#
#  Scenario: Create an account giving name, email, password and different confirm-password
#    And User enters username
#    And User enters email
#    And User enters password
#    And User enters different confirm password
#    And User clicks Join
#    Then Registration should fail

  Scenario: Create an account giving name, existing-email, password and different confirm-password
    And User enters username
    And User enters email
    And User enters password
    And User enters confirm password
    And User clicks Join
    Then Navigate to Home Page
    And User should be able to Logout
    When User clicks on register
    Then Navigate to Register Page
    And User enters as username "Test User Num2"
    And User enters an existing email
    And user enters as password "1qaz@WSX"
    And User enters confirm password
    And User clicks Join
    Then Registration should fail