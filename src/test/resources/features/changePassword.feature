Feature: Change password

  @adminAbility @userAbility
  Scenario: Change password
    Given I log in as admin
    When I go to settings modal window
    And I enter my old password
    And I change my password on "new_password"
    Then I check that my login was changed