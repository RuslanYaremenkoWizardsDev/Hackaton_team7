Feature: User authorization with valid data (backend)

  @authorizationTests
  Scenario Outline: User authorization with valid data
    When I send request for authorization as role with login = <login> and password = <password>
#    Then I check my role is <role>
    Then I check authorization status code = 200

    Examples:
      | login              | password           | role  |
      | admin              | admin              | admin |
      | 123                | 123456             | user  |
      | USER               | QWERTY             | user  |
      | user               | qwerty             | user  |
      | LoginWith18symbols | PasswordWith20Symb | user  |
      | user5              | qwerty5            | user  |
      | USER5              | QWERTY5            | user  |