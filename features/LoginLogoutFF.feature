#Each feature file contains one feature
#  Feature files use Gherkin language - business language
Feature: User should be able to logout and login again

  Background: Common steps
    Given User need to be on Calories-Calc login page
    When User clicks on login and navigated on login page

  Scenario: User login into Calories Calc home page
    And Create a user if not  exists
    Then User enters email
    And User enters password
    And User clicks login
    Then Navigate to Home Page

  Scenario: User tries to login into Calories Calc by wrong credentials
    Then User enters an email "silionie@yahoo.com"
    And User enters password
    And User clicks login
    Then User should not be able to Login

  Scenario: User tries to logout of Calories Calc
    And Create a user if not  exists
    Then User enters email
    And User enters password
    And User clicks login
    And User should not be able to Login
    And Navigate to Home Page
    Then User should be able to Logout