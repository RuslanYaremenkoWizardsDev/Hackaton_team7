Feature: User registration with invalid data

  @registrationTests
  Scenario Outline: User registration with invalid data
    Given I open registration page
    When I enter login "<login>", password "<password>" and confirm password
    Then I got error message
    And I was not registered with login <login>

    Examples:
      | login                | password              |
      | 12                   | qwerty                |
      | LoginWithManySymbols | qwerty                |
      | " "                  | qwerty                |
      | User&5               | qwerty                |
      | user 5               | qwerty                |
      | USER_5               | qwerty                |
      | user                 | " "                   |
      | user                 | qwer ty               |
      | user                 | 12                    |
      | user                 | PasswordWith21Symbols |
      | user                 | QWER%TY5              |