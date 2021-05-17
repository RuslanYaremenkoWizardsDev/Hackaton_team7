Feature: User authorization with invalid data (backend)

  @authorizationTests
  Scenario Outline: User authorization with invalid data
    When I send request for authorization as role with login = <login> and password = <password>
    Then I check authorization status code = 405
    When I send empty request
    Then I check authorization status code = 415

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
    