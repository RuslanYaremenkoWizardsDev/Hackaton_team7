Feature: Create tournament

  @adminAbility
  Scenario: Create tournament
    Given I log in as admin
    When I go to "create tournament" page
    And I create tournament with name "tournament_name"
    Then I check that tournament with name "tournament_name" was created
