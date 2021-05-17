Feature: User registration with valid data

  @registrationTests
  Scenario Outline: User registration with valid data
    Given I open registration page
    When I enter login <login>, password <password> and confirm password
    Then I redirected on the authorization page
    And I was registered with login <login>

    Examples:
      | login              | password           |
      | 123                | 123456             |
      | USER               | QWERTY             |
      | user               | qwerty             |
      | LoginWith18symbols | PasswordWith20Symb |
      | user5              | qwerty5            |
      | USER5              | QWERTY5            |