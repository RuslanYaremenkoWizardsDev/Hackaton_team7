Feature: Invite user to participate in the tournament

  Background:
    Given I log in as admin
    And I create tournament with name "tournament_name"

  @adminAbility
  Scenario: Invite user to participate in the tournament
    When I go to "Tournaments" page
    And I invite user to the tournament with name "tournament_name"
    Then I check that user was invited