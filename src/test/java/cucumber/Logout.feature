@tag
Feature: Logout Functionality

  Background:
    Given I landed on SuiteCRM page

@Logout
  Scenario Outline: Check logout functionality with Valid Users
    Given Logged in with "<username>" and "<password>"
    When I wait for page to load
    Then I go to my Profile and click logout
    Then Verify "<Message>" is displayed on homepage upon successful logout
    Then close the browser


    Examples:
      |username       | password   | Message    |
      |jim            | 123456     |  Success   |
      |manpreet-gill  | 123456     |  Success   |