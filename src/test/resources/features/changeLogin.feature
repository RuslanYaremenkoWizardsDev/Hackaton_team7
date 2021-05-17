Feature: Change login

  @adminAbility @userAbility
  Scenario: Change login
    Given I log in as admin
    When I go to settings modal window
    And I change my login on "new_login"
    Then I check that my login was changed