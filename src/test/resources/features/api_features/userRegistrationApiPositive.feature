Feature: User registration with valid data (backend)

  @registrationTests
  Scenario Outline: User registration with valid data
    When I send request for registration with login = <login>, email = <email> and password = <password>
    Then I check registration status code = 200
    And I was registered with login = <login>

    Examples:
      | login              | password           | email                |
      | 123                | 123456             | test@test.com        |
      | USER               | QWERTY             | x@z.com              |
      | user               | qwerty             | 1@2.ua               |
      | LoginWith18symbols | PasswordWith20Symb | test123@gmail.com.ua |
      | user5              | qwerty5            | Test@email.xx        |
      | USER5              | QWERTY5            | TEST123@mail.com     |