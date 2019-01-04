#Each feature file contains one feature
#  Feature files use Gherkin language - business language
Feature: User should be able to add records that indicate the food type, tha calories and the date

  Background: Common login steps
    Given User need to be on Calories-Calc login page
    And User should be already registered
    And User should be able to login
#
#  Scenario: Create an account giving an 8 character password
#    When User clicks on register
#    Then Navigate to Register Page
#    And User enters username
#    And User enters email
#    And user enters password
#    And User enters confirm password
#    And User clicks Join
#    Then Navigate to Home Page