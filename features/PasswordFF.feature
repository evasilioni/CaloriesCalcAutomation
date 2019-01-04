#Each feature file contains one feature
#  Feature files use Gherkin language - business language
Feature: Password should be at least 8 characters

  Background: Common login steps
    Given User need to be on Calories-Calc login page

  Scenario: Create an account giving an 8 character password
    When User clicks on register
    Then Navigate to Register Page
    And User enters username
    And User enters email
    And user enters password
    And User enters confirm password
    And User clicks Join
    Then Navigate to Home Page

  Scenario: Create an account giving less than 8 character password
    When User clicks on register
    Then Navigate to Register Page
    And User enters username
    And User enters email
    And user enters as password "123456"
    And User enters confirm password
    And User clicks Join
    Then Registration should fail

  Scenario: Create an account giving greater than 8 character password
    When User clicks on register
    Then Navigate to Register Page
    And User enters username
    And User enters email
    And user enters as password "1qaz@WSX3edc$RFV"
    And User enters confirm password
    And User clicks Join
    Then Navigate to Home Page