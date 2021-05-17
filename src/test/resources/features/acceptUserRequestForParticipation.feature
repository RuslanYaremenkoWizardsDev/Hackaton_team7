Feature: Accept user request for participation

  Background:
    Given I log in as admin
    And I see user's request for participation on the tournament page

    @adminAbility
  Scenario: Accept user request for participation
    When I accept request for participation
    Then I check that user was added