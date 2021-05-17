Feature: Save tournament results

  Background:
    Given I log in as admin

  @adminAbility
  Scenario: Save tournament result
    When I open tournaments page
    And I save results of tournament "tournament_name"
    Then I check than results was saved