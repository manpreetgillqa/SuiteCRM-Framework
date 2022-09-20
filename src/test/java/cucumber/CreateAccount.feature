@tag
Feature: Account creation with valid users

  Background:
  Given I landed on SuiteCRM page

    @CreateAccount
  Scenario Outline: Positive test of Account creation
    Given Logged in with "<username>" and "<password>"
    When I create Account with "<AccountName>"
    Then Verify the correct "<AccountName>" is displayed
    Then close the browser

    Examples:
    |username       | password   | AccountName    |
    |jim            | 123456     |  Mitch         |
    |manpreet-gill  | 123456     |  Auston        |