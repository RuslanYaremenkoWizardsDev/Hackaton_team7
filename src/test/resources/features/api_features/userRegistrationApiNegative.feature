Feature: User registration with invalid data (backend)

  @registrationTests
  Scenario Outline: User registration with invalid data
    When I send request for registration with login = <login>, email = <email> and password = <password>
    Then I check registration status code = 415

    Examples:
      | login                | password              | email                |
      | 12                   | qwerty                | test@test.com        |
      | LoginWithManySymbols | qwerty                | x@z.com              |
      |                      | qwerty                | 1@2.ua               |
      | User&5               | qwerty                | test123@gmail.com.ua |
      | user 5               | qwerty                | Test@email.xx        |
      | USER_5               | qwerty                | TEST123@mail.com     |
      | user                 |                       | 1test@test.com       |
      | user                 | qwer ty               | test@test1.com       |
      | user                 | 12                    | test@1test.com       |
      | user                 | PasswordWith21Symbols | test.test@test.com   |
      | user                 | QWER%TY5              | test1.test@test.com  |
      | 123                  | 123456                | @test.com            |
      | 123                  | 123456                | testtest.com         |
      | 123                  | 123456                | test@test            |
      | 123                  | 123456                | test@test..com       |
      | 123                  | 123456                | test.@.test.com      |
