Feature: Check tournament statistics

  Scenario Outline: Check tournament statistics
    Given I open authorization page
    When I enter login "<login>" and password "<password>"
    Then I go to tournament statistics page

    Examples:
      | login | password  |
      | user  | qwerty123 |