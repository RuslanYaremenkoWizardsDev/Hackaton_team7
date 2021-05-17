Feature: User authorization with valid data

  @authorizationTests
  Scenario Outline: User authorization with valid data
    Given I open authorization page
    When I enter login <login> and password <password>
    Then I redirected on the main page

    Examples:
      | login | password |
      | admin | admin    |