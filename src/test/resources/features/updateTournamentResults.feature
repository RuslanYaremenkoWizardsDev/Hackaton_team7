Feature: Update tournament results

  Background:
    Given I log in as admin

  @adminAbility
  Scenario: Update tournament results
    When I open tournaments page
    And I change results of tournament "tournament_name"
    Then I check that results was changed