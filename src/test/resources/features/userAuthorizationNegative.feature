Feature: User authorization with invalid data

  @authorizationTests
  Scenario Outline: User authorization with invalid data
    Given I open authorization page
    When I enter login "<login>" and password "<password>"
    Then I was not authorized with login <login>

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